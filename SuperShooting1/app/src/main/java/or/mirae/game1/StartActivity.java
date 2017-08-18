package or.mirae.game1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class StartActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
	}
	
	public void haha(View v){
		if(v.getId()==R.id.button1){
			Intent intent=new Intent(StartActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}else if(v.getId()==R.id.button2){
			finish();
		}
	}
}
