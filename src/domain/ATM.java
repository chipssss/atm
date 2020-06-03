package domain;

import physical.CardReaderSimulation;
import physical.CashDispenserSimulation;
import physical.ReceiptPrinterSimulation;
import remote.AccountTransactionService;

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
	private String currentAccount;

	/**
	 * XXXXXX
	 * 系统顺序图中的第一个事件。
	 */
	public boolean readCard(){  //返回类型、方法名、参数各组自行设计
		String account = cardReader.readCard();
		boolean success = accountTransactionService.validateAccount(account);
		if (success) {
			// 读卡成功时，ATM记录当前账号
			currentAccount = account;
		}
		return success;
	}
	/**
	 * XXXXXX
	 * 系统顺序图中的第二个事件。
	 * @param password
	 */
	public boolean validatePassword(String password){ //返回类型、方法名、参数各组自行设计
		return accountTransactionService.validatePassword(currentAccount, password);
	}
	
	/**
	 * XXXXXX
	 * 系统顺序图中的第三个事件。
	 */
  	public void operation3(){ //返回类型、方法名、参数各组自行设计
  		
	}
	
	
	/**
	 * XXXXXX
	 * 系统顺序图中的第N个事件。
	 */
	public void operationN(){  //返回类型、方法名、参数各组自行设计
	
	}
	
	/**
	 * ATM开机
	 */
	public void turnOn(){
		cardReader = new CardReaderSimulation();
		cashDispenser = new CashDispenserSimulation(10000.00);  //模拟钞箱中放入10000元
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
