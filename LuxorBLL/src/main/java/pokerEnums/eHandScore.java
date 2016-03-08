package pokerEnums;

public enum eHandScore {
	
	ROYALFLUSH(10),
	STRAIGHTFLUSH(9),
	FOUROFAKIND(8),
	FULLHOUSE(7),
	FLUSH(6),
	STRAIGHT(5),
	THREEOFAKIND(4),
	TWOPAIR(3),
	ONEPAIR(2),
	HIGHCARD(1);
	
	private int iHandScore;

	private eHandScore(int iHandScore) {
		this.iHandScore = iHandScore;
	}

	public int getiHandScore() {
		return iHandScore;
	}
	
	
}
