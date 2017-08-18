package or.mirae.game1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class JumpActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
	}
	
	public void ju(){
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
