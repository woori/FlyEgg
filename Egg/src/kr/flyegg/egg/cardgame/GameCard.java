package kr.flyegg.egg.cardgame;

public class GameCard {
	private static final int SOLVED = 1;	// �̹� Ǯ�ȴ� ī�忩��
	private static final int SIDE = 2;	// ī��� (��/��)
	private static final int CARD_NO = 3;	// �Ҵ�� ī���ȣ

	public static final String SIDE_FRONT = "front";
	public static final String SIDE_BACK = "back";

	private boolean solved;
	private String side;	//
	private int cardNo;	// ī���ȣ
	
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
