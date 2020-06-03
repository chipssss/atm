package physical;


import javax.swing.JButton;

import domain.CashDispenser;

public class CashDispenserSimulation extends JButton implements CashDispenser{
	private static final long serialVersionUID = 1L;
	private double cashBalance; // 钞箱现金余额
	public CashDispenserSimulation(double cashBalance) {
		super();
		this.cashBalance = cashBalance;
		this.setEnabled(false);  //初始按钮不可用
		this.setText("钞箱余额"+cashBalance);
	}
	
	/**
	 * 吐钞
	 * 
	 */
	@Override
	public void dispenseCash() {
		//TODO 交给你了...
	}

	@Override
	public void close() {	
		//TODO 交给你了...
	}

}
