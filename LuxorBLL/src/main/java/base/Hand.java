package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exceptions.DeckException;
import base.Card;
import pokerEnums.eHandScore;
import pokerEnums.eRank;

public class Hand implements Comparable {
	private ArrayList<Card> cardsInHand;
	private eHandScore Handscore;
	private int highHand;
	private int lowHand;
	private ArrayList<Card> kickers;

	public Hand() {
		this.cardsInHand = new ArrayList<Card>();
		this.kickers = new ArrayList<Card>();
		this.highHand = 0;
		this.lowHand = 0;
	}

	public Hand AddCard(Deck d) throws DeckException {
		cardsInHand.add(d.Draw());
		return this;
	}

	public Hand AddCard(Card c) {
		cardsInHand.add(c);
		return this;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}

	public void setCardsInHand(ArrayList<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	public eHandScore getHandscore() {
		return Handscore;
	}

	public void setHandscore(eHandScore handscore) {
		Handscore = handscore;
	}

	public int getHighHand() {
		return highHand;
	}

	public void setHighHand(int highHand) {
		this.highHand = highHand;
	}

	public int getLowHand() {
		return lowHand;
	}

	public void setLowHand(int lowHand) {
		this.lowHand = lowHand;
	}

	public ArrayList<Card> getKickers() {
		return kickers;
	}

	public void setKickers(ArrayList<Card> kickers) {
		this.kickers = kickers;
	}

	public void sortHand() {
		Collections.sort(this.cardsInHand);
	}

	public int compareTo(Object o) {
		Hand h = (Hand) o;
		return h.Handscore.compareTo(this.Handscore);
	}

	public static Hand evaluateHand(Hand h) {
		h.sortHand();

		if (Hand.isRoyalFlush(h) || Hand.isStraightFlush(h) || Hand.isFourOfAKind(h) || Hand.isFullHouse(h)
				|| Hand.isFlush(h) || Hand.isStraight(h) || Hand.isThreeOfAKind(h) || Hand.isTwoPair(h)
				|| Hand.isOnePair(h) || Hand.isHighCard(h)) {

		}

		return h;
	}

	public static boolean isRoyalFlush(Hand h) {
		int[] values = { 14, 13, 12, 11, 10 };
		for (int x = 0; x < 5; x++) {
			if (h.getCardsInHand().get(x).getCardRank().getiCardNumber() != values[x]) {
				return false;
			}
			if (h.getCardsInHand().get(x).getCardSuit().getiCardSuit() != h.getCardsInHand().get(0).getCardSuit()
					.getiCardSuit()) {
				return false;
			}
		}
		h.setHandscore(eHandScore.ROYALFLUSH);
		return true;
	}

	public static boolean isStraightFlush(Hand h) {
		int lc = h.getCardsInHand().get(4).getCardRank().getiCardNumber();
		int[] values = { lc + 4, lc + 3, lc + 2, lc + 1, lc };
		for (int x = 0; x < 5; x++) {
			if (h.getCardsInHand().get(x).getCardRank().getiCardNumber() != values[x]) {
				return false;
			}
			if (h.getCardsInHand().get(x).getCardSuit().getiCardSuit() != h.getCardsInHand().get(0).getCardSuit()
					.getiCardSuit()) {
				return false;
			}
		}
		h.setHandscore(eHandScore.STRAIGHTFLUSH);
		h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
		return true;
	}

	public static boolean isFourOfAKind(Hand h) {
		if (h.getCardsInHand().get(0).getCardRank().getiCardNumber() == h.getCardsInHand().get(3).getCardRank()
				.getiCardNumber()) {
			h.setHandscore(eHandScore.FOUROFAKIND);
			h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(4));
			h.setKickers(kickers);
			return true;

		} else if (h.getCardsInHand().get(1).getCardRank().getiCardNumber() == h.getCardsInHand().get(4).getCardRank()
				.getiCardNumber()) {
			h.setHandscore(eHandScore.FOUROFAKIND);
			h.setHighHand(h.getCardsInHand().get(1).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(0));
			h.setKickers(kickers);
			return true;
		}
		return false;
	}

	public static boolean isFullHouse(Hand h) {
		if ((h.getCardsInHand().get(0).getCardRank().getiCardNumber() == h.getCardsInHand().get(2).getCardRank()
				.getiCardNumber())
				&& (h.getCardsInHand().get(3).getCardRank().getiCardNumber() == h.getCardsInHand().get(4).getCardRank()
						.getiCardNumber())) {
			h.setHandscore(eHandScore.FULLHOUSE);
			h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
			h.setLowHand(h.getCardsInHand().get(4).getCardRank().getiCardNumber());
			return true;
		} else if ((h.getCardsInHand().get(0).getCardRank().getiCardNumber() == h.getCardsInHand().get(1).getCardRank()
				.getiCardNumber())
				&& (h.getCardsInHand().get(2).getCardRank().getiCardNumber() == h.getCardsInHand().get(4).getCardRank()
						.getiCardNumber())) {
			h.setHandscore(eHandScore.FULLHOUSE);
			h.setHighHand(h.getCardsInHand().get(4).getCardRank().getiCardNumber());
			h.setLowHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
			return true;
		} else {
			return false;

		}
	}

	public static boolean isFlush(Hand h) {
		for (int x = 0; x < 5; x++) {
			if (h.getCardsInHand().get(x).getCardSuit().getiCardSuit() != h.getCardsInHand().get(0).getCardSuit()
					.getiCardSuit()) {
				return false;
			}
		}
		h.setHandscore(eHandScore.FLUSH);
		h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
		ArrayList<Card> kickers = new ArrayList<Card>();
		for (int i = 1; i < 5; i++) {
			kickers.add(h.getCardsInHand().get(i));
		}
		h.setKickers(kickers);
		return true;
	}

	public static boolean isStraight(Hand h) {
		int lc = h.getCardsInHand().get(4).getCardRank().getiCardNumber();
		int[] values = { lc + 4, lc + 3, lc + 2, lc + 1, lc };
		if (h.getCardsInHand().get(0).getCardRank() == eRank.ACE) {
			for (int x = 1; x < 5; x++) {
				if (h.getCardsInHand().get(x).getCardRank().getiCardNumber() != values[x]) {
					return false;
				}
			}
		} else {
			for (int x = 0; x < 5; x++) {
				if (h.getCardsInHand().get(x).getCardRank().getiCardNumber() != values[x]) {
					return false;
				}
			}
		}

		h.setHandscore(eHandScore.STRAIGHT);
		h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
		return true;
	}

	public static boolean isThreeOfAKind(Hand h) {
		int card1 = h.getCardsInHand().get(0).getCardRank().getiCardNumber();
		int card2 = h.getCardsInHand().get(1).getCardRank().getiCardNumber();
		int card3 = h.getCardsInHand().get(2).getCardRank().getiCardNumber();
		int card4 = h.getCardsInHand().get(3).getCardRank().getiCardNumber();
		int card5 = h.getCardsInHand().get(4).getCardRank().getiCardNumber();

		if ((card1 == card3) && (card4 != card5)) {
			h.setHandscore(eHandScore.THREEOFAKIND);
			h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(3));
			kickers.add(h.getCardsInHand().get(4));
			h.setKickers(kickers);
			return true;
		} else if ((card2 == card4)) {
			h.setHandscore(eHandScore.THREEOFAKIND);
			h.setHighHand(h.getCardsInHand().get(2).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(0));
			kickers.add(h.getCardsInHand().get(4));
			h.setKickers(kickers);
			return true;
		} else if ((card3 == card5) && (card1 != card2)) {
			h.setHandscore(eHandScore.THREEOFAKIND);
			h.setHighHand(h.getCardsInHand().get(4).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(0));
			kickers.add(h.getCardsInHand().get(1));
			h.setKickers(kickers);
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTwoPair(Hand h) {

		int card1 = h.getCardsInHand().get(0).getCardRank().getiCardNumber();
		int card2 = h.getCardsInHand().get(1).getCardRank().getiCardNumber();
		int card3 = h.getCardsInHand().get(2).getCardRank().getiCardNumber();
		int card4 = h.getCardsInHand().get(3).getCardRank().getiCardNumber();
		int card5 = h.getCardsInHand().get(4).getCardRank().getiCardNumber();
		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((card1 == card2) && (card3 == card4) && (card3 != card5)) {
			h.setHandscore(eHandScore.TWOPAIR);
			h.setHighHand(card1);
			h.setLowHand(card3);
			kickers.add(h.getCardsInHand().get(4));
			h.setKickers(kickers);
			return true;
		} else if ((card1 == card2) && (card4 == card5) && (card3 != card4)) {
			h.setHandscore(eHandScore.TWOPAIR);
			h.setHighHand(card1);
			h.setLowHand(card4);
			kickers.add(h.getCardsInHand().get(2));
			h.setKickers(kickers);
			return true;
		} else if ((card1 != card2) && (card2 == card3) && (card4 == card5)) {
			h.setHandscore(eHandScore.TWOPAIR);
			h.setHighHand(card1);
			h.setLowHand(card3);
			kickers.add(h.getCardsInHand().get(4));
			h.setKickers(kickers);
			return true;
		} else {
			return false;
		}

	}

	public static boolean isOnePair(Hand h) {
		int numPairs = 0;
		int posOfPair = 0;
		for (int i = 0; i < 4; i++) {
			if (h.getCardsInHand().get(i).getCardRank().getiCardNumber() == h.getCardsInHand().get(i + 1).getCardRank()
					.getiCardNumber()) {
				numPairs += 1;
				posOfPair = i;
			}
		}
		if (numPairs == 1) {
			h.setHandscore(eHandScore.ONEPAIR);
			h.setHighHand(h.getCardsInHand().get(posOfPair).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get((posOfPair + 2) % 5));
			kickers.add(h.getCardsInHand().get((posOfPair + 3) % 5));
			kickers.add(h.getCardsInHand().get((posOfPair + 4) % 5));
			Collections.sort(kickers);
			h.setKickers(kickers);
			return true;
		} else {
			return false;
		}
	}

	public static boolean isHighCard(Hand h) {
		int numPairs = 0;
		for (int i = 0; i < 4; i++) {
			if (h.getCardsInHand().get(i).getCardRank().getiCardNumber() == h.getCardsInHand().get(i + 1).getCardRank()
					.getiCardNumber()) {
				numPairs += 1;
			}
		}
		if (numPairs == 0) {
			int numOfSameSuit = 0;
			for (int x = 0; x < 5; x++) {
				if (h.getCardsInHand().get(x).getCardSuit().getiCardSuit() == h.getCardsInHand().get(0).getCardSuit()
						.getiCardSuit()) {
					numOfSameSuit += 1;
				}
				if (numOfSameSuit == 5) {
					return false;
				}
			}
			h.setHandscore(eHandScore.HIGHCARD);
			h.setHighHand(h.getCardsInHand().get(0).getCardRank().getiCardNumber());
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int i = 1; i < 5; i++) {
				kickers.add(h.getCardsInHand().get(i));
			}
			h.setKickers(kickers);
			return true;
		} else {
			return false;
		}
	}

}