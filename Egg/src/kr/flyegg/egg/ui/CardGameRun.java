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

		// ������ �´� ���̺� �׸���
		drawGameTable(pairs);
		
		Log.d(TAG, "OnCreate Done");
	}

	
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	/**
	 * ���õ� ī�� ����
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
	 * �ϼ� ���� Ȯ��
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
     * ī���� �׸���
     * @param pair
     */
    public void drawGameTable(int pair) {
    	Log.d(TAG, "DrawTable start");
    	
        int row = 2;
        int col = 0;
        
        // ¦ ī�� ���� ���� row, col ���ϱ�
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
        
        // card list ����
        // TODO: ī�� �̹����� ���̵�? ���ϸ�?
    	ArrayList<Integer> cardsList = new ArrayList<Integer>();
    	for (int i=0; i<pair; i++) {
    		// ���� ī�带 ���� ��
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
    	// ī�� �׸��� (��ư)
        
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
				
				// ī�� ����
				GameCard gameCard = new GameCard();
				gameCard.setSide(GameCard.SIDE_BACK);	// ī��� �⺻������ �޸��� ����
				btn.setBackgroundResource(R.drawable.ic_launcher);	// �޸� �̹��� // TODO: �޸� �̹��� ���ҽ��� ���� 
				
				// ī�� ��ȣ ����
				gameCard.setCardNo(cardsList.get(0));
				cardsList.remove(0);
				
				// ī�� ������ �±׿� ���
//				btn.setTag(gameCard);
				btn.setTag(cardNo++);
				
				gameCard.setView(btn);
				gameCards.add(gameCard);
				
				// ������
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
    
    // ī�� Ŭ�� ó��
	private OnClickListener cardClickListener = new OnClickListener() {

		public void onClick(View v) {
			Log.d(TAG, "Get Card Tag");
//			GameCard gameCard = (GameCard) v.getTag();
			Integer cardNo = (Integer) v.getTag();
			
			Log.d(TAG, "CardNo:" + cardNo);
//			Toast.makeText(getApplicationContext(), "CardNo:" + cardNo, Toast.LENGTH_SHORT).show();
			GameCard gameCard = gameCards.get(cardNo);
			
			
			// Ǯ���� ī�� ���ý� �ƹ��͵� ���� ����
			if (gameCard.isSolved()) {
				return;
			}

			
			// �̹� ���õ� ī�尡 2�� �ִ� ��� ���õ� ī����� �ٽ� ������
			if (getCheckedCardNum() == 2) {
				
				for (int i=0; i<gameCards.size(); i++) {
					if (gameCards.get(i).isChecked()) {
						// ���õ� ī�� �ٽ� ������
						gameCards.get(i).setSide(GameCard.SIDE_BACK);
						gameCards.get(i).getView().setBackgroundResource(R.drawable.ic_launcher);
						((Button) gameCards.get(i).getView()).setText("");
						
						gameCards.get(i).setChecked(false);
					}
				}
			}
			
			// ���õ��� ���� ī�� ���ý� ī�� ������
			if (gameCard.isChecked() == false) {
				gameCard.setSide(GameCard.SIDE_FRONT);
				gameCard.setChecked(true);	// ����ó��
				
				// TODO: �ش� ī�� �̹����� ��Ÿ������
				((Button) v).setText("no:" + gameCard.getCardNo());
				v.setBackgroundResource(0);
			} else {
				// �̹� ���õ� ī�� Ŭ���� �ƹ��͵� ���� ����
				return;
			}
			
			// �ΰ� ���õǾ� �ִ� ��� �´��� Ʋ���� �˻�
			if (getCheckedCardNum() == 2) {
				int preCheckedCardIndex = -1;	// ���õ� ī�� index
				
				for (int i=0; i<gameCards.size(); i++) {
					if (gameCards.get(i).isChecked()) {
						if (preCheckedCardIndex == -1) {
							preCheckedCardIndex = i;
							continue;
						}
						
						// ���� ���� ī�� ��ȣ�� ���� ���� ī�� ��ȣ�� ������ ����
						if (gameCards.get(preCheckedCardIndex).getCardNo() == gameCards.get(i).getCardNo()) {
							gameCards.get(preCheckedCardIndex).setSolved(true);
							gameCards.get(i).setSolved(true);
							
							// ���� ����
							gameCards.get(preCheckedCardIndex).setChecked(false);
							gameCards.get(i).setChecked(false);
							
//							Toast.makeText(getApplicationContext(), "¦¦¦", Toast.LENGTH_SHORT).show();
							break;
						}
					}
				}
			} // end if �ΰ� ���ý� �´��� Ʋ���� �˻�
			
			// ���� �ذ�
			if (isAllSolved()) {
				Toast.makeText(getApplicationContext(), "��� ��! �� Clear!", Toast.LENGTH_SHORT).show();
			}
	
		}
		
	};
	
}
