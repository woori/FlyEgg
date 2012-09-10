package kr.flyegg.egg.cardgame;

import android.view.View;

public class GameCard {

	public static final String SIDE_FRONT = "front";
	public static final String SIDE_BACK = "back";

	private boolean solved;		// 해결여부
	private boolean checked;	// 선택여부
	private String side;		// 앞/뒤
	private int cardNo;			// 카드번호
	
	private View view;	// 연결된 View Object
	
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
