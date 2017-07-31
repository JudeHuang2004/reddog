package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pojo.Card;
import pojo.CardNum;
import pojo.Suit;
import util.CardFactory;

public class GameService {
	// get a new pack of cards
	public static List<Card> getNewPackCards() {
		List<Card> newPack = new ArrayList<Card>(52);
		for (CardNum c : CardNum.values()) {
			newPack.add(CardFactory.getCard(Suit.Diamond, c));
			newPack.add(CardFactory.getCard(Suit.Heart, c));
			newPack.add(CardFactory.getCard(Suit.Club, c));
			newPack.add(CardFactory.getCard(Suit.Spade, c));
		}
		return newPack;
	}

	// shuffle cards
	public static void shuffle(List<Card> toShuffle) {
		Collections.shuffle(toShuffle);
	}

	// distribute the first 5 cards
	public static List<Card> distributeCards(List<Card> serverPack) {
		List<Card> subList = new ArrayList<Card>();
		subList.addAll(serverPack.subList(0, 5));
		serverPack.removeAll(subList);
		//sort userCards
		Collections.sort(subList);
//		Collections.reverse(subList);
		return subList;
	}

	// print out the 5 cards for user
	public static void printUserCards(List<Card> userCards) {
		System.out.println("----------------Your Card:-----------");
		for (Card c : userCards) {			
			System.out.println(c.getSuit() + ":" + c.getCardNum());
		}
		System.out.println("----------------Your Card-----------");
	}

	// compare userCards with the first card on sever pack, 
	// return the card list higher than server card.
	public static List<Card> bet(List<Card> userCards, List<Card> serverPack) {
		List<Card> winCards = new ArrayList<Card>();
		Card sCard = serverPack.get(0);
		for (Card ucard : userCards) {
			if (ucard.compareTo(sCard) >= 0) {
				winCards.add(ucard);	
			}
		}
		return winCards;
	}

	// show the server card and remove the card from pack
	public static Card showServerCard(List<Card> serverPack) {
		Card s1 = serverPack.get(0);
		System.out.println(s1.getSuit() + ":" + s1.getCardNum());
		serverPack.remove(s1);
		System.out.println("---------------------------------------");
		return s1;
	}
}
