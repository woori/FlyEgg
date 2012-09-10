package kr.flyegg.egg.cardgame;

import android.view.View;

public class GameCard {
	private static final int SOLVED = 1;	// �̹� Ǯ�ȴ� ī�忩��
	private static final int SIDE = 2;	// ī��� (��/��)
	private static final int CARD_NO = 3;	// �Ҵ�� ī���ȣ

	public static final String SIDE_FRONT = "front";
	public static final String SIDE_BACK = "back";

	private boolean solved;		// �ذῩ��
	private boolean checked;	// ���ÿ���
	private String side;		// ��/��
	private int cardNo;			// ī���ȣ
	
	private View view;	// ����� View Object
	
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean isSolved) {
		this.solved = isSolved;
	}
	
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
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
	
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	
	

	
}
