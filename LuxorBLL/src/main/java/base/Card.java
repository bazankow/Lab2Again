package base;

import pokerEnums.*;

public class Card implements Comparable {
	private eRank cardRank;
	private eSuit cardSuit;
	private int iCardNumber;
	
	public Card(eRank cardRank, eSuit cardSuit, int iCardNumber) {
		super();
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
		this.iCardNumber = iCardNumber;
	}

	public eRank getCardRank() {
		return cardRank;
	}

	public eSuit getCardSuit() {
		return cardSuit;
	}

	public int getiCardNumber() {
		return iCardNumber;
	}

	public int compareTo(Object o){
		Card c = (Card) o;
		return c.cardRank.compareTo(this.cardRank);
	}


}
