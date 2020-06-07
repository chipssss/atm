package physical;

import javax.swing.JTextField;

import domain.CardReader;
import util.Logger;


public class CardReaderSimulation extends JTextField implements CardReader{
	private static final long serialVersionUID = 1L;
	private static final String TAG = "CardReaderSimulation";
	private boolean enable;

	@Override
	public String readCard() {
		String cardNumber = getText();
		Logger.log(TAG, "cardNumber: " + cardNumber);
		return cardNumber;
	}

	@Override
	public void exitCard() {
		enable = false;
	}

	@Override
	public void keepCard() {
		enable = true;
	}

}
