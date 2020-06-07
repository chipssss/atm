package physical;


import javax.swing.*;

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
	 * @param transactionMoney
	 */
	@Override
	public void dispenseCash(double transactionMoney) {
		setEnabled(true);
		cashBalance -= transactionMoney;
		String output = String.format("%.2f RMB", transactionMoney);
		setText(output);
	}

	@Override
	public void close() {
		setEnabled(false);
		String output = String.format("%.2f RMB", cashBalance);
		setText("钞箱余额" + output);
	}

	@Override
	public boolean checkEnough(double money) {
		return money <= cashBalance;
	}

}
