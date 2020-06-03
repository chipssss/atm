package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import physical.CardReaderSimulation;
import physical.CashDispenserSimulation;
import physical.ReceiptPrinterSimulation;
import domain.ATM;
import domain.CardReader;
import domain.CashDispenser;
import domain.ReceiptPrinter;

/**
 * 视图主窗口
 * 
 */
public class AtmFrame extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private CardReaderSimulation cardReaderSimulation;  //读卡器
	private CashDispenserSimulation cashDispenserSimulation;  //钞箱
	private ReceiptPrinterSimulation receiptPrinterSimulation; //凭证打印机
	private JButton turnOnButton, printRecBtn; //开机按钮，打印按钮
	private ServicePanel servicePanel; //服务面板
	private JPanel displayPanel, mainPanel,physicalPanel; //屏幕面板，主面板，硬件面板
	private ATM atm  = new ATM(); //控制器类。设计要求：界面类不允许调用其他领域类
	public AtmFrame(){
		this.setTitle("UML课程实验示例：ATM模拟系统");
		this.setContentPane(getMainPanel());  //设置主容器
        this.setSize(700, 600); 
        this.setLocation(450,100); 
        this.setDefaultCloseOperation(3); 
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);   //显示窗口，默认隐藏
        this.validate();   
	}

	/**
	 * 主界面
	 * @return
	 */
	public JPanel getMainPanel() {
		mainPanel = new JPanel();
		mainPanel.add(getDisplayerPanel(),BorderLayout.NORTH);
		mainPanel.add(getPhysicalPanel(),BorderLayout.SOUTH); 
		mainPanel.setBackground(Color.ORANGE);
		return mainPanel;
	}
	
	/**
	 * 硬件模拟面板
	 * @return
	 */
	public JPanel getPhysicalPanel(){
		physicalPanel = new JPanel();
		physicalPanel.setPreferredSize(new Dimension(690,300));
		physicalPanel.setBackground(Color.LIGHT_GRAY);
		physicalPanel.setBounds(0, 400, 690, 300);
		return physicalPanel;
	}
	
	/**
	 * 启动按钮
	 * @return
	 */
	public JButton getTurnOnButton(){
		turnOnButton = new JButton("启动ATM");
		turnOnButton.setFont(new Font("微软雅黑",Font.BOLD,14));
		turnOnButton.setBounds(250, 200, 200, 40);
		turnOnButton.addActionListener(new ActionListener() {    //为按钮加上事件监听器
            public void actionPerformed(ActionEvent arg0) {      //对点击按钮事件做出响应
            	atm.turnOn();  //启动ATM用例
    			turnOnButton.setVisible(false);
    			physicalPanel.add((JTextField)getCardReader());
    			physicalPanel.add((JButton)getCashDispenser());
    			physicalPanel.add(new JScrollPane((JTextArea)getReceiptPrinter()));
    			validate();
            }
        });
		return turnOnButton;

	}
	
	/**
	 * 得到显示屏幕实例
	 * @return
	 */
	public JPanel getDisplayerPanel(){
		displayPanel = new JPanel();
		JPanel welcomePanel = new WelcomePanel();
		welcomePanel.add(getTurnOnButton());
		displayPanel.add(welcomePanel);
		return displayPanel; 
	}
	

	/**
	 * 得到打印机实例
	 * @return
	 */
	public ReceiptPrinter getReceiptPrinter(){
		receiptPrinterSimulation = (ReceiptPrinterSimulation) atm.getReceiptPrinter();
		receiptPrinterSimulation.setText("此处打印交易凭证");
		receiptPrinterSimulation.setFont(new Font("微软雅黑",Font.BOLD,15));
		receiptPrinterSimulation.setColumns(20);
		receiptPrinterSimulation.setRows(7);
		receiptPrinterSimulation.setLineWrap(true);
		receiptPrinterSimulation.setEnabled(false);
		return receiptPrinterSimulation;
	}
	
	/**
	 * 得到钞箱实例
	 * @return
	 */
	public CashDispenser getCashDispenser() { 
		cashDispenserSimulation = (CashDispenserSimulation) atm.getCashDispenser();
		cashDispenserSimulation.setPreferredSize(new Dimension(200,50));
		cashDispenserSimulation.setBackground(Color.GRAY);
		cashDispenserSimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	cashDispenserSimulation.close();
    			refreshDisplayPanel(getPrintPanel());
    			validate();
            }
        });
		cashDispenserSimulation.setFont(new Font("微软雅黑",Font.BOLD,15));
		return cashDispenserSimulation;
	}
	/**
	 * 得到读卡器实例
	 * @return
	 */
	public CardReader getCardReader(){
		cardReaderSimulation = (CardReaderSimulation) atm.getCardReader();
		cardReaderSimulation.setColumns(10);
		cardReaderSimulation.setFont(new Font("微软雅黑",Font.BOLD,15));
		cardReaderSimulation.setText("");
		cardReaderSimulation.setToolTipText("插入银行卡后回车读卡");
		cardReaderSimulation.addKeyListener(this);
		return cardReaderSimulation;
	}

	/**
	 *  输入密码面板
	 */
	
	public JPanel getLoginPanel(){
		LoginPanel loginPanel = new LoginPanel();
		loginPanel.getRightBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了......
				String password = String.valueOf(loginPanel.getPasswordField().getPassword());
				boolean success = atm.validatePassword(password);
				if (success) {
					JOptionPane.showMessageDialog(null, "验证成功");
					refreshDisplayPanel(getServicePanel()); //验证密码成功，显示交易菜单面板
				} else {
					JOptionPane.showMessageDialog(null, "验证失败，请重新输入");
					refreshDisplayPanel(new WelcomePanel()); //验证密码失败，返回欢迎面板
				}
            }
        });
		loginPanel.getLeftBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了
            	refreshDisplayPanel(new WelcomePanel()); //验证密码失败，返回欢迎面板
            }
        });
		
		return loginPanel;
	}
	
	/**
	 *  交易服务选择菜单面板
	 */
	public JPanel getServicePanel() {
		if(servicePanel ==null){
			servicePanel = new ServicePanel();
			servicePanel.getLeftBtn1().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	//TODO 交给你了....
	            	refreshDisplayPanel(getWithdrawPanel()); //选择取款交易，显示取款面板
	            }
	        });
			servicePanel.getLeftBtn4().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	//TODO 交给你了......
	            	refreshDisplayPanel(new WelcomePanel()); //选择退出，返回欢迎面板
	            }
	        });
		}
		return servicePanel;
	}
	
	/**
	 *	输入取款金额面板
	 */
	public JPanel getWithdrawPanel() {
		WithdrawPanel withdrawlPanel = new WithdrawPanel();
		withdrawlPanel.getLeftBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了....
            	refreshDisplayPanel(getServicePanel()); //放弃取款，返回交易服务菜单面板
            }
        });
		
		withdrawlPanel.getRightBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了....  //取款,如果取款成功，ATM控制钞箱打开吐超口吐钞(激活button显示吐钞金额)
            	                    //后续逻辑：用户取走现金后(按下button)，显示打印凭证面板。
            	
            	//以下两行代码仅为方便可以通过界面完整演示操作逻辑。
            	//实际代码非如此，逻辑代码也不一定在此处，需删除。
            	((AbstractButton) getCashDispenser()).setText("200RMB/仅测试"); 
            	((AbstractButton) getCashDispenser()).setEnabled(true); 
            	
	        }
        });
		return withdrawlPanel;
	}
	
	/**
	 *  打印凭证询问面板
	 */
	public JPanel getPrintPanel() {
		PrintPanel printPanel = new PrintPanel();
		printPanel.getLeftBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了 ......
            	
            	refreshDisplayPanel(getServicePanel()); //放弃打印凭证，返回服务菜单面板
            	
            	
            }
        });
		printPanel.getRightBtn4().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	//TODO 交给你了 ......
            	
            	refreshDisplayPanel(getServicePanel());//打印凭证，返回服务菜单面板
            	
            }
        });
		return printPanel;
	}
	
	
	/**
	 * 刷新显示面板
	 * 实现界面跳转，简化代码
	 */
	public void refreshDisplayPanel(JPanel panel){
		displayPanel.removeAll();
		displayPanel.add(panel);
		repaint();
	}
	
	//输入卡号后回车，模拟插卡效果，调用读卡器读卡
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			// 读卡验证
			boolean flag = atm.readCard();
			if(flag) {     //银行卡读取成功且有效，显示输入密码面板
				displayPanel.removeAll();
				displayPanel.add(getLoginPanel());
				this.repaint();
			}else {               // 银行卡无效，提示卡号无效
				JOptionPane.showMessageDialog(null, "卡号无效，请重新尝试");
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
