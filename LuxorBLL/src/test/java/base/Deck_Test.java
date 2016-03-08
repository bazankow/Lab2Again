package base;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import base.*;

public class Deck_Test {

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
	public void normalDeckSizeTest() {
		int iExpectedValue = 51;
		int iActualValue;

		try {
			Class<?> c = Class.forName("base.Deck");
			Object t = c.newInstance();
			Method mDraw = c.getDeclaredMethod("Draw", null);
			Method mGetDeckSize = c.getDeclaredMethod("GetDeckSize", null);
			mGetDeckSize.setAccessible(true);
			
			Object oDraw = mDraw.invoke(t, null);
			
			Object oGetDeckSize = mGetDeckSize.invoke(t, null);

			iActualValue = ((Integer) oGetDeckSize).intValue();
			//System.out.println(iActualValue);
			assertEquals(iExpectedValue, iActualValue);
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void DeckDrawIsCard() throws DeckException {
		Deck d = new Deck();
		Object o = d.Draw();

		if (!(o instanceof Card)) {
			fail("Object drawn from deck isn't card");
		}
	}
	
	@Test(expected = DeckException.class)
	public void DeckOverDraw() throws Exception {
		Deck d = new Deck();
		Card c = null;
		for (int i = 0; i < 100; i++) {
			c = d.Draw();		
		}
	}

}
