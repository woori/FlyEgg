package kr.flyegg.egg.ui;

import java.util.ArrayList;

import kr.flyegg.egg.R;
import kr.flyegg.egg.cardgame.GameCard;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

public class CardGameRun extends Activity {
	
	private static final String TAG = "CardGameRun";

	private int pairs = 0;
	
	private ArrayList<GameCard> gameCards = new ArrayList<GameCard>();
	/*
	private static final int TAG_WRONG = 2;
	private static final int TAG_CHECKED = 3;
	*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardgame_run);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		Intent intent = getIntent();
		int level = intent.getIntExtra("LEVEL", 0);
		
		Log.d(TAG, "Level:" + level);
//		Toast.makeText(getApplicationContext(), "Level:" + level, Toast.LENGTH_SHORT).show();
		
		if (level == 1) {
			pairs = 2;
		} else if (level == 2) {
			pairs = 3;
		} else if (level == 3) {
			pairs = 4;
		}

		// 레벨에 맞는 테이블 그리기
		drawGameTable(pairs);
		
		Log.d(TAG, "OnCreate Done");
	}

	
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	/**
	 * 선택된 카드 갯수
	 * @return count
	 */
	private int getCheckedCardNum() {
		int count = 0;
		for (int i=0; i<gameCards.size(); i++) {
			if (gameCards.get(i).isChecked()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 완성 여부 확인
	 * @return
	 */
	private boolean isAllSolved() {
		for (int i=0; i<gameCards.size(); i++) {
			if (gameCards.get(i).isSolved() == false) {
				return false;
			}
		}
		
		return true;
	}
	
    /**
     * 카드패 그리기
     * @param pair
     */
    public void drawGameTable(int pair) {
    	Log.d(TAG, "DrawTable start");
    	
        int row = 2;
        int col = 0;
        
        // 짝 카드 수에 따른 row, col 정하기
        if (pair == 2) {
        	row = 2;
        	col = 2;
        } else if (pair == 3) {
        	row = 2;
        	col = 3;
        } else if (pair == 4) {
        	row = 2;
        	col = 4;
        }
        
        Log.d(TAG, "Make Card");
        
        // card list 생성
        // TODO: 카드 이미지의 아이디? 파일명?
    	ArrayList<Integer> cardsList = new ArrayList<Integer>();
    	for (int i=0; i<pair; i++) {
    		// 같은 카드를 두장 씩
    		cardsList.add(i);
    		cardsList.add(i);
    	}
        
		Log.d(TAG, "Card Shuffle");
		// random shuffle
    	int length = cardsList.size();
    	for (int i=0; i<length*100; i++) {
    		int first = (int) Math.floor(Math.random()*length);
    		int second = (int) Math.floor(Math.random()*length);
    		int temp = cardsList.get(first);
    		
    		cardsList.set(first, cardsList.get(second));
    		cardsList.set(second, temp);
    	}
    	
    	////////////////////////////////////////////
    	// 카드 그리기 (버튼)
        
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tblGame);

        Log.d(TAG, "Clear Table View and GameCards");
        
        // clear table view and cards
		tableLayout.removeAllViews();
		gameCards.clear();

		Integer cardNo = new Integer(0);
		Log.d(TAG, "cardNo:" + cardNo);
		
        for (int i=0; i<row; i++) { // row loop
		
        	Log.d(TAG, "Create a row");
			// create a row
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			for (int j=0; j<col; j++) {	// column loop
				
				// Create a Button to be the row-content
				Log.d(TAG, "Create a button");
				Button btn = new Button(this);
				
				// 카드 정보
				GameCard gameCard = new GameCard();
				gameCard.setSide(GameCard.SIDE_BACK);	// 카드는 기본적으로 뒷면을 향함
				btn.setBackgroundResource(R.drawable.ic_launcher);	// 뒷면 이미지 // TODO: 뒷면 이미지 리소스로 변경 
				
				// 카드 번호 지정
				gameCard.setCardNo(cardsList.get(0));
				cardsList.remove(0);
				
				// 카드 정보를 태그에 기록
//				btn.setTag(gameCard);
				btn.setTag(cardNo++);
				
				gameCard.setView(btn);
				gameCards.add(gameCard);
				
				// 사이즈
				Log.d(TAG, "Set Card Size");
				btn.setWidth(100);
				btn.setHeight(100);
				
				btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				btn.setOnClickListener(cardClickListener);
				
				Log.d(TAG, "Add Card to row");
				// Add Card Button to row
				tableRow.addView(btn);
			}
			
			// Add row to TableLayout
			Log.d(TAG, "Add row to TableLayout");
			tableLayout.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }
    
    // 카드 클릭 처리
	private OnClickListener cardClickListener = new OnClickListener() {

		public void onClick(View v) {
			Log.d(TAG, "Get Card Tag");
//			GameCard gameCard = (GameCard) v.getTag();
			Integer cardNo = (Integer) v.getTag();
			
			Log.d(TAG, "CardNo:" + cardNo);
//			Toast.makeText(getApplicationContext(), "CardNo:" + cardNo, Toast.LENGTH_SHORT).show();
			GameCard gameCard = gameCards.get(cardNo);
			
			
			// 풀었는 카드 선택시 아무것도 하지 않음
			if (gameCard.isSolved()) {
				return;
			}

			
			// 이미 선택된 카드가 2개 있는 경우 선택된 카드들은 다시 뒤집음
			if (getCheckedCardNum() == 2) {
				
				for (int i=0; i<gameCards.size(); i++) {
					if (gameCards.get(i).isChecked()) {
						// 선택된 카드 다시 뒤집기
						gameCards.get(i).setSide(GameCard.SIDE_BACK);
						gameCards.get(i).getView().setBackgroundResource(R.drawable.ic_launcher);
						((Button) gameCards.get(i).getView()).setText("");
						
						gameCards.get(i).setChecked(false);
					}
				}
			}
			
			// 선택되지 않은 카드 선택시 카드 뒤집음
			if (gameCard.isChecked() == false) {
				gameCard.setSide(GameCard.SIDE_FRONT);
				gameCard.setChecked(true);	// 선택처리
				
				// TODO: 해당 카드 이미지가 나타나도록
				((Button) v).setText("no:" + gameCard.getCardNo());
				v.setBackgroundResource(0);
			} else {
				// 이미 선택된 카드 클릭시 아무것도 하지 않음
				return;
			}
			
			// 두개 선택되어 있는 경우 맞는지 틀린지 검사
			if (getCheckedCardNum() == 2) {
				int preCheckedCardIndex = -1;	// 선택된 카드 index
				
				for (int i=0; i<gameCards.size(); i++) {
					if (gameCards.get(i).isChecked()) {
						if (preCheckedCardIndex == -1) {
							preCheckedCardIndex = i;
							continue;
						}
						
						// 이전 선택 카드 번호와 지금 선택 카드 번호가 같으면 맞음
						if (gameCards.get(preCheckedCardIndex).getCardNo() == gameCards.get(i).getCardNo()) {
							gameCards.get(preCheckedCardIndex).setSolved(true);
							gameCards.get(i).setSolved(true);
							
							// 선택 해제
							gameCards.get(preCheckedCardIndex).setChecked(false);
							gameCards.get(i).setChecked(false);
							
//							Toast.makeText(getApplicationContext(), "짝짝짝", Toast.LENGTH_SHORT).show();
							break;
						}
					}
				}
			} // end if 두개 선택시 맞는지 틀린지 검사
			
			// 전부 해결
			if (isAllSolved()) {
				Toast.makeText(getApplicationContext(), "우왕 굳! ㅋ Clear!", Toast.LENGTH_SHORT).show();
			}
	
		}
		
	};
	
}
