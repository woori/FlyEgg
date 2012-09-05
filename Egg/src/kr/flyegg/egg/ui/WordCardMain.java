package kr.flyegg.egg.ui;

import kr.flyegg.egg.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

public class WordCardMain extends Activity {

	//버튼은 분류의 개수에 따라 동적으로 만들어 져야한다.
	//분류를 db에 저장해야한다.
	private Button frui_vegi = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_card);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		final LinearLayout l = (LinearLayout) findViewById(R.layout.word_card);
		
		frui_vegi = (Button) findViewById(R.id.fruits_vegitable);
		frui_vegi.setLongClickable(true);
		
		frui_vegi.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				callCardBoard();
			}
		});
		
		frui_vegi.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				showImage(l);
				return true;
			}
		});
		
	}

	AlertDialog alert = null;
	private void deleteDialog() {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
			builder.setTitle("삭제");
			builder.setMessage(getString(R.string.question))
			        .setCancelable(false)
			        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   dialog.cancel();
			           }
			       })
			       .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			                frui_vegi.setCompoundDrawables(null, null, null, null);
			           }
			       });
			
			alert = builder.create();
			alert.show();
		} catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
    private void showImage(LinearLayout l) {
    	Drawable close = getResources().getDrawable(R.drawable.close);
    	close.setBounds(0, 0, close.getIntrinsicWidth(), close.getIntrinsicHeight());
    	frui_vegi.setCompoundDrawables(close, null, null, null);
    	deleteDialog();
    }
	
	private void callCardBoard() {
		Intent i = new Intent(getApplicationContext(), CardBoard.class);
		startActivity(i);
	}
	
	
	@Override
	protected void onDestroy() {
		if(alert != null)
			alert.dismiss();
		
		super.onDestroy();
	}

	
}
