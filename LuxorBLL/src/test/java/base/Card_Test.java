package base;

import static org.junit.Assert.*;
import pokerEnums.*;
import exceptions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Card_Test {

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
	public void test() {
		Card c = new Card(eRank.SEVEN,eSuit.SPADES,5);
		Card d = new Card(eRank.NINE,eSuit.SPADES,5);
		assertTrue(c.getiCardNumber()==5);
		assertTrue(c.getCardRank()==eRank.SEVEN);
		assertTrue(c.getCardSuit()==eSuit.SPADES);
		assertTrue(c.compareTo(d)==2);
	}

}
