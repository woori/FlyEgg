package kr.flyegg.egg.ui;

import kr.flyegg.egg.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

public class MirrorMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mirror_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
	    // 클릭 이벤트
		/*
		OnClickListener levelClickListener = new OnClickListener() {

			public void onClick(View v) {
				
				
			}
		};
		*/
		
		// 클릭 이벤트 연결
//		findViewById(R.id.btnGameEasy).setOnClickListener(levelClickListener);
		
		
	}

	
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

}
