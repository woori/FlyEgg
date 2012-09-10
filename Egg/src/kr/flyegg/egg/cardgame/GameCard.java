package kr.flyegg.egg.cardgame;

import android.view.View;

public class GameCard {

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
