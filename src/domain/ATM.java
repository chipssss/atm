package domain;

import entity.Operation;
import entity.PerformStatus;
import physical.CardReaderSimulation;
import physical.CashDispenserSimulation;
import physical.ReceiptPrinterSimulation;
import remote.AccountTransactionService;
import remote.TransactionStatusCode;
import util.Util;

/**
 * 控制器
 * 参考GRASP1控制器设计模式
 * ATM对象接收系统顺序图中所有事件
 */
public class ATM {
	private CardReader cardReader; //建立ATM和cardReader关联。
	private CashDispenser cashDispenser;
	private ReceiptPrinter receiptPrinter;

	private AccountTransactionService accountTransactionService = new AccountTransactionService();
	private PerformStatus performStatus = new PerformStatus();

	/**
	 * XXXXXX
	 * 系统顺序图中的第一个事件。
	 */
	public boolean readCard(){  //返回类型、方法名、参数各组自行设计
		String account = cardReader.readCard();
		boolean success = accountTransactionService.validateAccount(account);
		if (success) {
			// 读卡成功时，ATM记录当前账号
			performStatus.setAccount(account);
		}
		return success;
	}
	/**
	 * XXXXXX
	 * 系统顺序图中的第二个事件。
	 * @param password
	 */
	public boolean validatePassword(String password){ //返回类型、方法名、参数各组自行设计
		boolean success = accountTransactionService.validatePassword(performStatus.getAccount(), password);
		if (success) {
			cardReader.keepCard();
		}
		return success;
	}


	public void makeNewWithdraw() {

	}

	/**
	 * XXXXXX
	 * 系统顺序图中的第N个事件。
	 */
	public TransactionStatusCode enterAmount(double quantity){  //返回类型、方法名、参数各组自行设计
		// 检查机箱余额
		if (!cashDispenser.checkEnough(quantity)) {
			return TransactionStatusCode.ERROR_WITH_CASH_BALANCE;
		}

		TransactionStatusCode statusCode = accountTransactionService.withDraw(performStatus.getAccount(), quantity);
		if (statusCode.isSuccess()) {
			// 记录凭证
			performStatus.setTransactionMoney(quantity);
			performStatus.setReceipt(quantity, Operation.WITHDRAW);
		}
		// 成功时记录事件
		return statusCode;
	}

	public void printReceipt() {
		if (performStatus.getReceipt() != null)
			receiptPrinter.printReceipt(performStatus.getReceipt());
	}

	public void endWithdraw() {
		cashDispenser.close();
	}

	public void returnCard() {
		performStatus.reset();
		cardReader.exitCard();
	}

	public void dispenseCash() {
		// 吐钞
		cashDispenser.dispenseCash(performStatus.getTransactionMoney());
	}


	
	/**
	 * ATM开机
	 */
	public void turnOn(){
		cardReader = new CardReaderSimulation();
		cashDispenser = new CashDispenserSimulation(Config.CASE_BALACE);  //模拟钞箱中放入10000元
		receiptPrinter = new ReceiptPrinterSimulation();
	}
	//getter
	public CardReader getCardReader() {
		return cardReader;
	}
	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}
	public ReceiptPrinter getReceiptPrinter() {
		return receiptPrinter;
	}
	
	
}
