package kr.flyegg.egg;

import kr.flyegg.egg.ui.CardGameMain;
import kr.flyegg.egg.ui.WordCardMain;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button wordCard= null;
	private Button gameCard= null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //화면 안꺼지게..
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        wordCard = (Button) findViewById(R.id.workd_card);
        wordCard.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				callWordCardActivity();
			}
		});
        
        gameCard = (Button) findViewById(R.id.game_card);
        gameCard.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				callGameCardActivity();
			}
		});
    }
    
    
    private void callWordCardActivity() {
    	Intent i = new Intent(getApplicationContext(), WordCardMain.class);
    	startActivity(i);
    }
    
    /**
     * Card Matching Game
     */
    private void callGameCardActivity() {
    	Intent i = new Intent(getApplicationContext(), CardGameMain.class);
    	startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
    
}
