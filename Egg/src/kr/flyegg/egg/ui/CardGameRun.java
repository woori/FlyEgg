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
			drawTable(2);
		} else if (level == 2) {
			drawTable(3);
		} else if (level == 3) {
			drawTable(4);
		}
		
		Log.d(TAG, "OnCreate Done");
	}

	
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

    /**
     * 카드패 그리기
     * @param pair
     */
    public void drawTable(int pair) {
    	Log.d(TAG, "DrawTable start");
        int row = 2;
        int col = 0;
        
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
        
        // card list
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

        Log.d(TAG, "Clear Table View");
        // clear table view
		tableLayout.removeAllViews();

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
				gameCard.setSide(GameCard.SIDE_BACK);
				
				gameCard.setCardNo(cardsList.get(0));
				cardsList.remove(0);
				
				// 카드 정보를 태그에 기록
				btn.setTag(gameCard);
				
				// TODO: 이미지 보여주는 방식으로 변경
//				btn.setText("Card");
				btn.setText("no:" + gameCard.getCardNo());
				//btn.setBackgroundResource(resid)
				
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
			GameCard gameCard = (GameCard) v.getTag();
			
			// 카드이미지를 띄우도록 한다.
			/*
			if (v.getTag(TAG_SOLVED) != null) {
				return;
			}
			*/
			Log.d(TAG, "Card Toast");
			Toast.makeText(getApplicationContext(), "haha:" + gameCard.getCardNo(), Toast.LENGTH_SHORT).show();
//			v.setBackgroundResource(R.drawable.ic_launcher);
//			v.setBackgroundResource(0);
		}
		
	};
	
}
