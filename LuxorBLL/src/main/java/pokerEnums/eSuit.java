package pokerEnums;

public enum eSuit {
	
	SPADES(1), CLUBS(2), DIAMONDS(3), HEARTS(4);
	
	private int iCardSuit;

	private eSuit(int iCardSuit) {
		this.iCardSuit = iCardSuit;
	}

	public int getiCardSuit() {
		return iCardSuit;
	}
}