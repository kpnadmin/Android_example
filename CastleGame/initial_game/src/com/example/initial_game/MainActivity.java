package com.example.initial_game;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity{

	private Handler frame = new Handler();
	private static final int FRAME_RATE = 20;
	
	//private Point dot_velocity;
	private int dot_maxX;
	private int dot_maxY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Handler h = new Handler();
		

		h.postDelayed(new Runnable() {
            @Override
            public void run() {
                    initGfx();
            }
		}, 1000);
		
		initialGameView drawView = (initialGameView) findViewById(R.id.the_canvas);
        MyOnTouchListener listener = new MyOnTouchListener();
        drawView.setOnTouchListener(listener);
        drawView.requestFocus();
	}
	
	class MyOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
            case (MotionEvent.ACTION_DOWN):
            initialGameView drawView = (initialGameView) findViewById(R.id.the_canvas);
        	drawView.onTouch(v, event);
              break;
            }
			return false;
		}
		
	}
	
	synchronized public void initGfx(){
		
		//write stuff
		((initialGameView)findViewById(R.id.the_canvas)).setDot(10, 10);
//		dot_velocity = new Point(10, 15);
		((initialGameView)findViewById(R.id.the_canvas)).setDotVelocity(new Point(10, 15));
		dot_maxX = findViewById(R.id.the_canvas).getWidth() -
				((initialGameView)findViewById(R.id.the_canvas)).getDotWidth();
		dot_maxY = findViewById(R.id.the_canvas).getHeight() -
				((initialGameView)findViewById(R.id.the_canvas)).getDotHeight();
		
		frame.removeCallbacks(frameUpdate);
        frame.postDelayed(frameUpdate, FRAME_RATE);
	}

	private Runnable frameUpdate = new Runnable(){

		@Override
		public void run() {
			frame.removeCallbacks(frameUpdate);
			//write stuff
			
			initialGameView the_canvas = ((initialGameView)findViewById(R.id.the_canvas));
			Point dot_velocity = the_canvas.getDotVelocity();
			
			Point sprite1 = new Point
					(the_canvas.getDotX(),
					 the_canvas.getDotY()) ;
			
			sprite1.x = sprite1.x + dot_velocity.x;
	        if (sprite1.x > dot_maxX || sprite1.x < 5) {
	               the_canvas.setDotVelocityX(dot_velocity.x *= -1);
	        }
	        sprite1.y = sprite1.y + dot_velocity.y;
	        if (sprite1.y > dot_maxY || sprite1.y < 5) {
	        	the_canvas.setDotVelocityY(dot_velocity.y *= -1);
	        }
	          
	        ((initialGameView)findViewById(R.id.the_canvas)).setDot(sprite1.x, sprite1.y);
	        ((initialGameView)findViewById(R.id.the_canvas)).invalidate();
			frame.postDelayed(frameUpdate, FRAME_RATE);
		}
		
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
