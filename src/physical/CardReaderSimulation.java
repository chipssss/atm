package physical;

import javax.swing.JTextField;

import domain.CardReader;
import util.Logger;


public class CardReaderSimulation extends JTextField implements CardReader{
	private static final long serialVersionUID = 1L;
	private static final String TAG = "CardReaderSimulation";

	@Override
	public String readCard() {
		String cardNumber = getText();
		Logger.log(TAG, "cardNumber: " + cardNumber);
		return cardNumber;
	}

	@Override
	public void exitCard() {
		// TODO 交给你了	
	}

	@Override
	public void keepCard() {
		// TODO 交给你了	
	}

}
