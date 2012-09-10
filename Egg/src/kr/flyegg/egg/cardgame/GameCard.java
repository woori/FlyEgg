package kr.flyegg.egg.cardgame;

public class GameCard {
	private static final int SOLVED = 1;	// 이미 풀렸는 카드여부
	private static final int SIDE = 2;	// 카드면 (앞/뒤)
	private static final int CARD_NO = 3;	// 할당된 카드번호

	public static final String SIDE_FRONT = "front";
	public static final String SIDE_BACK = "back";

	private boolean solved;
	private String side;	//
	private int cardNo;	// 카드번호
	
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean isSolved) {
		this.solved = isSolved;
	}
	
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	
	
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	
	

	
}
