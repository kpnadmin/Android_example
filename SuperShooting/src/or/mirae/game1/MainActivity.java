package or.mirae.game1;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public static Handler mHan=new Handler();
	public static LinearLayout lay;
	
	MyGameView mGameView;
	Button btn1, btn2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.game_main);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				
		lay=(LinearLayout) findViewById(R.id.lay);
		
		mGameView = (MyGameView) findViewById(R.id.mGameView);
		
		
	}
	
	public void haha(View v){
		if(v.getId()==R.id.btn1){
			mGameView.mThread.mAlly.add(new Hero(R.drawable.banggucha0, 1));
		}else if(v.getId()==R.id.btn2){
			mGameView.StopGame();

			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.add(0, 4, 0, "Pause Game");
		menu.add(0, 5, 0, "Continue Game");
		menu.add(0, 6, 0, "Quit Game");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {

		case 4:
			mGameView.PauseGame();
			break;
		case 5:
			mGameView.ResumeGame();
			break;
		case 6:
			mGameView.StopGame();

			finish();
			break;
		}
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mGameView.PauseGame();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mGameView.ResumeGame();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		finish();
		
		
	}
	
		
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		
		
//		mGameView.mThread.PauseNResume(true);
//		
//		synchronized (this) {
//			this.notify();
//		}
//		
//		new AlertDialog.Builder(MainActivity.this)
//		.setMessage("Do you want to Exit?")
//		.setCancelable(false)
//		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//			public void onClick(DialogInterface arg0, int arg1) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//
//		})
//		.setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//			public void onClick(DialogInterface arg0, int arg1) {
//				// TODO Auto-generated method stub
//				mGameView.mThread.PauseNResume(false);
//				
//				synchronized (this) {
//					this.notify();
//				}	
//			}
//
//		}).create().show();
//		
//	
	}



}
