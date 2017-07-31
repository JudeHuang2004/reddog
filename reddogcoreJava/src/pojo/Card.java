package pojo;

public class Card implements Comparable<Card> {
	
	private Suit suit;
	
	private CardNum cardNum;
	
	public Card (Suit suit, CardNum cardNum) {
		this.suit = suit;
		this.cardNum = cardNum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card other) {
		int result = -1;
		if(this.suit.compareTo(other.suit) != 0 ) {
			result = -1;
		}else {
			result = this.cardNum.getValue() - other.cardNum.getValue();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNum == null) ? 0 : cardNum.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		if (cardNum != other.cardNum) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		return true;
	}

	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	public CardNum getCardNum() {
		return cardNum;
	}
	public void setCardNum(CardNum cardNum) {
		this.cardNum = cardNum;
	}

}
