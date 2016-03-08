package base;

import static org.junit.Assert.*;
import pokerEnums.*;
import exceptions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import base.*;

public class Hand_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void addCardTest()throws DeckException{
		Hand h = new Hand();
		Deck d = new Deck();
		Card cd = new Card(eRank.ACE, eSuit.CLUBS, 0);
		
		h.AddCard(d);
		assertTrue(h.getCardsInHand().size() == 1);
		
		h.AddCard(cd);
		assertTrue(h.getCardsInHand().size() == 2);
	}

	@Test
	public void handScoreTest(){
		Hand h = new Hand();
		h.setHandscore(eHandScore.THREEOFAKIND);
		
		assertTrue(h.getHandscore()==eHandScore.THREEOFAKIND);
	}
	
	@Test
	public void isRoyalFlushTest() {
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eRank.ACE, eSuit.CLUBS, 0));
		RoyalFlush.add(new Card(eRank.QUEEN, eSuit.CLUBS, 0));
		RoyalFlush.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		RoyalFlush.add(new Card(eRank.JACK, eSuit.CLUBS, 0));
		RoyalFlush.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(RoyalFlush);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isRoyalFlush(h));
		assertEquals(h.getHandscore(), eHandScore.ROYALFLUSH);
		
		ArrayList<Card> RoyalFlush2 = new ArrayList<Card>();
		RoyalFlush2.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		RoyalFlush2.add(new Card(eRank.QUEEN, eSuit.CLUBS, 0));
		RoyalFlush2.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		RoyalFlush2.add(new Card(eRank.JACK, eSuit.CLUBS, 0));
		RoyalFlush2.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(RoyalFlush2);
		h2.sortHand();
		assertFalse(Hand.isRoyalFlush(h2));
		
		ArrayList<Card> RoyalFlush3 = new ArrayList<Card>();
		RoyalFlush3.add(new Card(eRank.ACE, eSuit.CLUBS, 0));
		RoyalFlush3.add(new Card(eRank.QUEEN, eSuit.SPADES, 0));
		RoyalFlush3.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		RoyalFlush3.add(new Card(eRank.JACK, eSuit.CLUBS, 0));
		RoyalFlush3.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(RoyalFlush3);
		h3.sortHand();
		assertFalse(Hand.isRoyalFlush(h3));
	}

	@Test
	public void isStraightFlushTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.FOUR, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(testHand);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isStraightFlush(h));
		// Checking if proper high hand is being saved
		assertEquals(h.getHighHand(), eRank.SIX.getiCardNumber());
		assertEquals(h.getHandscore(), eHandScore.STRAIGHTFLUSH);
		
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(new Card(eRank.EIGHT, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.FOUR, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(testHand2);
		h2.sortHand();
		assertFalse(Hand.isStraightFlush(h2));
		
		ArrayList<Card> testHand3 = new ArrayList<Card>();
		testHand3.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.FOUR, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(testHand3);
		h3.sortHand();
		assertFalse(Hand.isStraightFlush(h3));
	}
	
	@Test
	public void isFourOfAKindTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(testHand);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isFourOfAKind(h));
		
		assertEquals(h.getHandscore(), eHandScore.FOUROFAKIND);;
		assertEquals(h.getHighHand(), eRank.SIX.getiCardNumber());
		ArrayList<Card> testKickers = new ArrayList<Card>();
		testKickers.add(h.getCardsInHand().get(4));
		assertEquals(h.getKickers(), testKickers);
		
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(testHand2);
		h2.sortHand();
		assertTrue(Hand.isFourOfAKind(h2));
		
		ArrayList<Card> testHand3 = new ArrayList<Card>();
		testHand3.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand3.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand3.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand3.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(testHand3);
		h3.sortHand();
		assertFalse(Hand.isFourOfAKind(h3)); 
	}
	
	@Test
	public void isFullHouseTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		testHand.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(testHand);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isFullHouse(h));
		
		assertEquals(h.getHandscore(), eHandScore.FULLHOUSE);
		ArrayList<Card> testKickers = new ArrayList<Card>();
		assertEquals(h.getHighHand(), eRank.SIX.getiCardNumber());
		assertEquals(h.getLowHand(), eRank.TWO.getiCardNumber());
		assertEquals(h.getKickers(), testKickers);
		
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand2.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(testHand2);
		h2.sortHand();
		assertFalse(Hand.isFullHouse(h2));
		
		ArrayList<Card> testHand3 = new ArrayList<Card>();
		testHand3.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand3.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand3.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		testHand3.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.TWO, eSuit.HEARTS, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(testHand3);
		h3.sortHand();
		assertTrue(Hand.isFullHouse(h3)); 
	}
	
	@Test
	public void isFlushTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.TEN, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.SEVEN, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.TWO, eSuit.HEARTS, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(testHand);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isFlush(h));
		assertEquals(h.getHandscore(), eHandScore.FLUSH);
		ArrayList<Card> testKickers = new ArrayList<Card>();
		for (int i = 1; i < 5; i++) {
			testKickers.add(h.getCardsInHand().get(i));
		}
		assertEquals(h.getKickers(), testKickers);
		assertEquals(h.getHighHand(), eRank.TEN.getiCardNumber());
		
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(testHand2);
		h2.sortHand();
		assertFalse(Hand.isFlush(h2));

	}
	
	@Test
	public void isStraightTest() {
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.TEN, eSuit.DIAMONDS, 0));
		testHand.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.SEVEN, eSuit.HEARTS, 0));
		testHand.add(new Card(eRank.NINE, eSuit.HEARTS, 0));
		
		Hand h = new Hand();
		h.setCardsInHand(testHand);
		h.sortHand();
		/*
		for(int x = 0; x<5; x++){
			System.out.println(h.getCardsInHand().get(x).getCardRank().getiCardNumber());
			System.out.println(h.getCardsInHand().get(x).getCardSuit().getiCardSuit());
		}*/
		
		assertTrue(Hand.isStraight(h));
		assertEquals(h.getHandscore(), eHandScore.STRAIGHT);
		assertEquals(h.getHighHand(), eRank.TEN.getiCardNumber());
		
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(new Card(eRank.EIGHT, eSuit.HEARTS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.SPADES, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		testHand2.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(testHand2);
		h2.sortHand();
		assertFalse(Hand.isStraight(h2));
		
		ArrayList<Card> testHand3 = new ArrayList<Card>();
		testHand3.add(new Card(eRank.TWO, eSuit.HEARTS, 0));
		testHand3.add(new Card(eRank.THREE, eSuit.DIAMONDS, 0));
		testHand3.add(new Card(eRank.ACE, eSuit.SPADES, 0));
		testHand3.add(new Card(eRank.FOUR, eSuit.CLUBS, 0));
		testHand3.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(testHand3);
		h3.sortHand();
		assertTrue(Hand.isStraight(h3));

		ArrayList<Card> testHand4 = new ArrayList<Card>();
		testHand4.add(new Card(eRank.TWO, eSuit.HEARTS, 0));
		testHand4.add(new Card(eRank.SEVEN, eSuit.DIAMONDS, 0));
		testHand4.add(new Card(eRank.ACE, eSuit.SPADES, 0));
		testHand4.add(new Card(eRank.FOUR, eSuit.CLUBS, 0));
		testHand4.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		
		Hand h4 = new Hand();
		h4.setCardsInHand(testHand4);
		h4.sortHand();
		assertFalse(Hand.isStraight(h4));
	}
	
	@Test
	public void isThreeOfAKindTest() {
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.TEN, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.TEN, eSuit.SPADES, 0));
		cardList1.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		ArrayList<Card> cardList2 = new ArrayList<Card>();
		cardList2.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList2.add(new Card(eRank.FIVE, eSuit.SPADES, 0));
		cardList2.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		cardList2.add(new Card(eRank.FIVE, eSuit.DIAMONDS, 0));
		cardList2.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(cardList2);
		h2.sortHand();
		
		ArrayList<Card> cardList3 = new ArrayList<Card>();
		cardList3.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.DIAMONDS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(cardList3);
		h3.sortHand();
		
		ArrayList<Card> cardList4 = new ArrayList<Card>();
		cardList4.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.ACE, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.EIGHT, eSuit.CLUBS, 0));
		
		Hand h4 = new Hand();
		h4.setCardsInHand(cardList4);
		h4.sortHand();
		
		assertTrue(Hand.isThreeOfAKind(h1));
		assertEquals(h1.getHighHand(), eRank.TEN.getiCardNumber());
		
		ArrayList<Card> testKickers = new ArrayList<Card>();
		testKickers.add(h1.getCardsInHand().get(3));
		testKickers.add(h1.getCardsInHand().get(4));
		assertEquals(h1.getKickers(), testKickers);
		
		assertTrue(Hand.isThreeOfAKind(h2));
		assertTrue(Hand.isThreeOfAKind(h3));
		assertFalse(Hand.isThreeOfAKind(h4));
	}
	
	@Test
	public void isTwoPairTest() {
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.TEN, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.NINE, eSuit.SPADES, 0));
		cardList1.add(new Card(eRank.NINE, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		ArrayList<Card> cardList2 = new ArrayList<Card>();
		cardList2.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList2.add(new Card(eRank.TEN, eSuit.SPADES, 0));
		cardList2.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		cardList2.add(new Card(eRank.FOUR, eSuit.DIAMONDS, 0));
		cardList2.add(new Card(eRank.FOUR, eSuit.SPADES, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(cardList2);
		h2.sortHand();
		
		ArrayList<Card> cardList3 = new ArrayList<Card>();
		cardList3.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.DIAMONDS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(cardList3);
		h3.sortHand();
		
		ArrayList<Card> cardList4 = new ArrayList<Card>();
		cardList4.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.ACE, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.EIGHT, eSuit.CLUBS, 0));
		
		Hand h4 = new Hand();
		h4.setCardsInHand(cardList4);
		h4.sortHand();
		
		assertTrue(Hand.isTwoPair(h1));
		
		assertEquals(h1.getHighHand(), eRank.TEN.getiCardNumber());
		assertEquals(h1.getLowHand(), eRank.NINE.getiCardNumber());
		ArrayList<Card> testKickers = new ArrayList<Card>();
		testKickers.add(h1.getCardsInHand().get(4));
		assertEquals(h1.getKickers(), testKickers);
		
		assertTrue(Hand.isTwoPair(h2));
		assertTrue(Hand.isTwoPair(h3));
		assertFalse(Hand.isTwoPair(h4));
	}
	
	@Test
	public void isOnePairTest() {
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.TEN, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.NINE, eSuit.SPADES, 0));
		cardList1.add(new Card(eRank.SIX, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.FIVE, eSuit.HEARTS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		ArrayList<Card> cardList2 = new ArrayList<Card>();
		cardList2.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList2.add(new Card(eRank.NINE, eSuit.SPADES, 0));
		cardList2.add(new Card(eRank.NINE, eSuit.HEARTS, 0));
		cardList2.add(new Card(eRank.FOUR, eSuit.DIAMONDS, 0));
		cardList2.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(cardList2);
		h2.sortHand();
		
		ArrayList<Card> cardList3 = new ArrayList<Card>();
		cardList3.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.FIVE, eSuit.DIAMONDS, 0));
		cardList3.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(cardList3);
		h3.sortHand();
		
		ArrayList<Card> cardList4 = new ArrayList<Card>();
		cardList4.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.EIGHT, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		cardList4.add(new Card(eRank.TWO, eSuit.CLUBS, 0));
		
		Hand h4 = new Hand();
		h4.setCardsInHand(cardList4);
		h4.sortHand();
		
		ArrayList<Card> cardList5 = new ArrayList<Card>();
		cardList5.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList5.add(new Card(eRank.NINE, eSuit.DIAMONDS, 0));
		cardList5.add(new Card(eRank.EIGHT, eSuit.SPADES, 0));
		cardList5.add(new Card(eRank.SEVEN, eSuit.DIAMONDS, 0));
		cardList5.add(new Card(eRank.SIX, eSuit.HEARTS, 0));
		
		Hand h5 = new Hand();
		h5.setCardsInHand(cardList5);
		h5.sortHand();
		
		assertTrue(Hand.isOnePair(h1));
		
		assertEquals(h1.getHighHand(), eRank.TEN.getiCardNumber());
		ArrayList<Card> testKickers = new ArrayList<Card>();
		testKickers.add(h1.getCardsInHand().get(2));
		testKickers.add(h1.getCardsInHand().get(3));
		testKickers.add(h1.getCardsInHand().get(4));
		assertEquals(h1.getKickers(), testKickers);
		
		assertTrue(Hand.isOnePair(h2));
		assertTrue(Hand.isOnePair(h3));
		assertTrue(Hand.isOnePair(h4));
		assertFalse(Hand.isOnePair(h5));
	}
	
	@Test
	public void isHighCardTest() {
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.EIGHT, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.SIX, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		ArrayList<Card> cardList2 = new ArrayList<Card>();
		cardList2.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList2.add(new Card(eRank.NINE, eSuit.SPADES, 0));
		cardList2.add(new Card(eRank.NINE, eSuit.HEARTS, 0));
		cardList2.add(new Card(eRank.FOUR, eSuit.DIAMONDS, 0));
		cardList2.add(new Card(eRank.THREE, eSuit.SPADES, 0));
		
		Hand h2 = new Hand();
		h2.setCardsInHand(cardList2);
		h2.sortHand();
		
		ArrayList<Card> cardList3 = new ArrayList<Card>();
		cardList3.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		cardList3.add(new Card(eRank.THREE, eSuit.DIAMONDS, 0));
		cardList3.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		
		Hand h3 = new Hand();
		h3.setCardsInHand(cardList3);
		h3.sortHand();
		
		assertFalse(Hand.isHighCard(h1));
		assertFalse(Hand.isHighCard(h2));
		assertTrue(Hand.isHighCard(h3));
		
		assertEquals(h3.getHighHand(), eRank.TEN.getiCardNumber());
		ArrayList<Card> testKickers = new ArrayList<Card>();
		testKickers.add(h3.getCardsInHand().get(1));
		testKickers.add(h3.getCardsInHand().get(2));
		testKickers.add(h3.getCardsInHand().get(3));
		testKickers.add(h3.getCardsInHand().get(4));
		assertEquals(h3.getKickers(), testKickers);
	}
	
	@Test
	public void evaluateHand1Test(){
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.NINE, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.FIVE, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.THREE, eSuit.DIAMONDS, 0));
		cardList1.add(new Card(eRank.TWO, eSuit.SPADES, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		Hand h = new Hand();
		h.setCardsInHand(h1.getCardsInHand());
		Hand.evaluateHand(h);
		
		assertEquals(h.getHandscore(), eHandScore.HIGHCARD);
	}
	
	@Test
	public void evaluateHand2Test(){
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.QUEEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.JACK, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.TEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.ACE, eSuit.CLUBS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		Hand h = new Hand();
		h.setCardsInHand(h1.getCardsInHand());
		Hand.evaluateHand(h);
		
		assertEquals(h.getHandscore(), eHandScore.ROYALFLUSH);
	}
	
	@Test
	public void evaluateHand3Test(){
		ArrayList<Card> cardList1 = new ArrayList<Card>();
		cardList1.add(new Card(eRank.KING, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.SEVEN, eSuit.CLUBS, 0));
		cardList1.add(new Card(eRank.SEVEN, eSuit.SPADES, 0));
		cardList1.add(new Card(eRank.SEVEN, eSuit.HEARTS, 0));
		cardList1.add(new Card(eRank.THREE, eSuit.CLUBS, 0));
		
		Hand h1 = new Hand();
		h1.setCardsInHand(cardList1);
		h1.sortHand();
		
		Hand h = new Hand();
		h.setCardsInHand(h1.getCardsInHand());
		Hand.evaluateHand(h);
		
		assertEquals(h.getHandscore(), eHandScore.THREEOFAKIND);
	}
}
