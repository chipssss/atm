package domain;

/**
 * 凭证打印机接口
 *
 */
public interface ReceiptPrinter {
	/**
	 * 打印凭证
	 * @param receiptLog
	 */
	public void printReceipt(String receiptLog); //返回值及参数各组自行设计
}
