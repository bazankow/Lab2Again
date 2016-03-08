package base;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.DeckException;
import pokerEnums.*;

public class Deck {
	
	private ArrayList<Card> deckOfCards = new ArrayList<Card>();
	
	public Deck(){
		int cardNumber = 1;
		for(eRank rank : eRank.values()){
			for(eSuit suit : eSuit.values()){
				deckOfCards.add(new Card(rank, suit, cardNumber++));
			}
		}
		Collections.shuffle(this.deckOfCards);
	}
	
	public void shuffle(){
		Collections.shuffle(this.deckOfCards);
	}
	
	private int GetDeckSize(){
		return deckOfCards.size();
	}
	
	public Card Draw() throws DeckException{
		if (deckOfCards.size() == 0){
			throw new DeckException(this);
		}
		return deckOfCards.remove(0);
	}
}
