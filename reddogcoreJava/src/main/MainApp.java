package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import pojo.CardNum;
import pojo.Suit;
import util.CardFactory;
import pojo.Card;

public class MainApp {

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
				break;
			}
		}
		if (winCards.isEmpty()) {
			System.out.println("You lose!");
		} else {
			System.out.println("You WIN!");
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Card> serverPack = null;
		List<Card> userCards = null;
		List<Card> winCards = null;
		System.out.println(":Enter 's' to start the game: ");
		while (sc.hasNextLine()) {
			String userCommd = sc.nextLine();
			if ("s".equals(userCommd)) {
				System.out.println("-------------------Game Start--------------------");
				if(winCards==null || winCards.isEmpty()) {
					System.out.println("Fresh Start!");
					serverPack = getNewPackCards();
					shuffle(serverPack);
					userCards = distributeCards(serverPack);
					printUserCards(userCards);
				}else if(winCards.size()==1) {
					System.out.println("One card win!");
					winCards.forEach(c -> System.out.println(c.getSuit()+":"+c.getCardNum()));
					userCards.remove(winCards.get(0));
					printUserCards(userCards);
				}else if(winCards.size()>1) {
					System.out.println("More than one card wins!");
					printUserCards(userCards);
				}				
				System.out.println(":Enter 'b' to bet: ");
			} else if ("b".equals(userCommd)) {
				System.out.println("-------------------Bet---------------------------");
				winCards = bet(userCards, serverPack);
				showServerCard(serverPack);
				System.out.println(":Enter 's' to continue or 'e' to quit: ");
			} else if ("e".equals(userCommd)) {
				break;
			}
		}
		System.out.println("-------------------Game Stop--------------------");
		sc.close();
	}

}
