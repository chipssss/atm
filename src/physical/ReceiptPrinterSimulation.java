package physical;

import javax.swing.JTextArea;

import domain.ReceiptPrinter;

public class ReceiptPrinterSimulation extends JTextArea implements ReceiptPrinter {
	private static final long serialVersionUID = 1L;

	@Override
	public void printReceipt(String receiptLog) {
		setText(receiptLog);
	}
}
