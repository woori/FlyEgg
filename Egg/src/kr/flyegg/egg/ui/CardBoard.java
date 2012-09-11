package kr.flyegg.egg.ui;

import kr.flyegg.egg.R;
import android.app.Activity;
import android.app.TabActivity;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost;

public class CardBoard extends TabActivity implements TabHost.TabContentFactory {

	TabHost tabHost = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_board);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		tabHost = getTabHost();
		TabHost.TabSpec spec = null;
	
		spec = tabHost.newTabSpec("전체");
		tabHost.addTab( spec );
		
		spec = tabHost.newTabSpec("과일");
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("탈것");
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab( 0 );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public View createTabContent(String tag) {
		return null;
	}

	
}
