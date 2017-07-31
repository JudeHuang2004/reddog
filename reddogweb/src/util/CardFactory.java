package util;

import pojo.Card;
import pojo.CardNum;
import pojo.Suit;

public class CardFactory {
	public static Card getCard(Suit suit, CardNum num) {
		return new Card(suit, num);
	}
}
