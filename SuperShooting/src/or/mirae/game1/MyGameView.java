package or.mirae.game1;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MyGameView extends SurfaceView implements Callback {
	int xDown, yDown;
	int xUp, yUp;
	
	Rect toLeft;
	Rect superWeapon;
	Rect toRight;
	
	Rect heroArea;
	
	Typeface mfont;
	GameThread mThread;
	SurfaceHolder mHolder;
	public static Context mContext;
	
	public static int width, height;
	
	Paint paint;
	
	Vibrator jindong;
	MediaPlayer player;
	SoundPool soundpool;
	int sound;

	public MyGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		mHolder = holder;
		mContext = context;
		paint=new Paint();
		
		mThread = new GameThread(holder, context);
		mfont=Typeface.createFromAsset(mContext.getAssets(), "font/BKANT.TTF");
		
		soundpool=new SoundPool(9, AudioManager.STREAM_MUSIC, 0);
		sound=soundpool.load(mContext, R.raw.bubblepang, 1);
		
		
		jindong=(Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
		setFocusable(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {

			mThread.start();
		} catch (Exception e) {
			mThread = new GameThread(mHolder, mContext);
			mThread.start();
			
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		 StopGame();
		
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	
	public void StopGame() {
		mThread.StopThread();
		player.stop();
		player.release();
		soundpool.release();
		
	}

	public void PauseGame() {
		mThread.PauseNResume(true);
		
	}

	public void ResumeGame() {
		mThread.PauseNResume(false);
		
	}

	public void RestartGame() {
				
		mThread.StopThread();

		mThread = null;
		mThread = new GameThread(mHolder, mContext);
		mThread.start();
	}

	public void SaveGame() {
		
	}

	public void LoadGame() {
	}
	
	
	

	class GameThread extends Thread {
		int loop=-200;
		
		SurfaceHolder mHolder;
		Context mContext;

		boolean canRun = true;
		public boolean isWait = false;
		ArrayList<Ally> mAlly;	
		Background back;
		ArrayList<Enomy> mEnomy;
		ArrayList<WeaponAlly> mWeaponAlly;
		Random rnd;
		ControlBar controlBar;

		int moveLength;
		int srcH1, srcH2;

		long score;
		
		ArrayList<ResidueEnomy> mResidueEnomy;

		
		public GameThread(SurfaceHolder holder, Context context) {
			// TODO Auto-generated constructor stub
			mHolder = holder;
			mContext = context;
		
			Display display = ((WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE))
					.getDefaultDisplay();
			
			
			width=display.getWidth();
			height=display.getHeight();
			
			mAlly=new ArrayList<Ally>();
			mAlly.add(new Hero(R.drawable.hero, 1));
			
			mWeaponAlly=new ArrayList<WeaponAlly>();
			mWeaponAlly.add(new WeaponAlly(R.drawable.weapon0, 3, mAlly.get(0)));
			
			back=new Background();
			
			
			mEnomy=new ArrayList<Enomy>();
			mEnomy.add(new Enomy1(R.drawable.enomy0, 3));
			mEnomy.add(new BombEnomy(R.drawable.bomb0, 3));
			
			mEnomy.add(new Enomy2(R.drawable.enemy2));
			
			rnd=new Random(System.currentTimeMillis());
			
			toLeft=new Rect(0,height*850/1000,width/3-10,height);
			superWeapon=new Rect(width/3+10,height*850/1000,width*2/3-10, height);
			toRight=new Rect(width*2/3+10,height*850/1000,width, height);
			
			 controlBar=new ControlBar();
			 
			paint.setColor(Color.YELLOW);
			paint.setTextSize(MyGameView.width * 100 / 1000);
			paint.setTypeface(mfont);
			
			mResidueEnomy=new ArrayList<ResidueEnomy>();
			
			player=MediaPlayer.create(mContext, R.raw.bcmusic);
			player.setVolume(0.2f, 0.2f);
			player.setLooping(true);
			player.start();
		}
		
		
		public void StopThread() {	
			canRun = false;
			synchronized (this) {
				this.notify();
			}
		}
		
		public void PauseNResume(boolean wait) {
			isWait = wait;
			synchronized (this) {
				this.notify();
			}
		}
		
		
		public void makeWeaponAlly(){
			if (loop % 5 == 0&&mAlly.size()>0) {
				if (mWeaponAlly.size() <= 100) {
					mWeaponAlly.add(new WeaponAlly(R.drawable.weapon0, 3, mAlly
							.get(0)));
				}
			}
		}
		
		
		
		public void coll(){
			for(Enomy e:mEnomy){
				for(WeaponAlly w:mWeaponAlly){
					int x1=e.midX;
					int x2=w.midX;
					int x1x2Dis=Math.abs(x1-x2);
					
					int y1=e.midY;
					int y2=w.midY;
					int y1y2Dis=Math.abs(y1-y2);
					
					int xRad=(e.w/2+w.w/2);
					int yRad=(e.h/2+w.h/2);
					
					if(e.lifeCount>0&&w.lifeCount>0&&x1x2Dis<=xRad&&y1y2Dis<=yRad){
						
						e.lifeCount-=w.power;
						w.lifeCount--;
						soundpool.play(sound, 0.3f, 0.3f, 1, 0, 1.0f);
						
						break;
						
					}
				}
				
				for(Ally h:mAlly){

					int x1=e.midX;
					int x2=h.midX;
					int x1x2Dis=Math.abs(x1-x2);
					
					int y1=e.midY;
					int y2=h.midY;
					int y1y2Dis=Math.abs(y1-y2);
					
					int xRad=(e.w/2+h.w/2)/2;
					int yRad=(e.h/2+h.h/2)/2;
					
					if(e.lifeCount>0&&h.lifeCount>0&&x1x2Dis<=xRad&&y1y2Dis<=yRad){
						
						e.lifeCount--;
						h.lifeCount--;
						
						break;
						
					}
				
				}
			}
			
			
		
		}
	

		public void run() {

			Canvas canvas = null;
			while (canRun) {
				canvas = mHolder.lockCanvas();

				try {
					synchronized (mHolder) {
						
						back.draw(canvas);
						//canvas.drawBitmap(back, 0, 0,null);
					
						if(loop>=0){
							coll();
							
							for(int i=mEnomy.size()-1;i>=0;i--){
								if (mEnomy.get(i).lifeCount>0) {
									mEnomy.get(i).draw(canvas);
								}else{
									mResidueEnomy.add(new ResidueEnomy(mEnomy.get(i)));
									score+=mEnomy.get(i).score;
									mEnomy.remove(i);
									
								}
								
							}
							
							for(int i=mResidueEnomy.size()-1;i>=0;i--){
								if(mResidueEnomy.get(i).lifeCount){
									mResidueEnomy.get(i).draw(canvas);
								}else{
									if(mResidueEnomy.get(i).showResidueEnomy!=null){
										mResidueEnomy.get(i).showResidueEnomy.recycle();
										mResidueEnomy.get(i).showResidueEnomy=null;
									}
									
									for(int j=0;j<mResidueEnomy.get(i).dataResidueEnomy.length;j++){
										mResidueEnomy.get(i).dataResidueEnomy[j].recycle();
										mResidueEnomy.get(i).dataResidueEnomy[j]=null;
									}
																		
									mResidueEnomy.remove(i);
								}
							}
							
							
							
							for(int i=mWeaponAlly.size()-1;i>=0;i--){
								if(mWeaponAlly.get(i).lifeCount>0){
									mWeaponAlly.get(i).draw(canvas);
								}else{
									if(mWeaponAlly.get(i).showAlly!=null){
										mWeaponAlly.get(i).showAlly.recycle();
										mWeaponAlly.get(i).showAlly=null;
									}
									
									for(int j=0;j<mWeaponAlly.get(i).dataAlly.length;j++){
										mWeaponAlly.get(i).dataAlly[j].recycle();
										mWeaponAlly.get(i).dataAlly[j]=null;
									}
									
									mWeaponAlly.remove(i);
								}
									
							}
							
							for(int i=mAlly.size()-1;i>=0;i--){
								if (mAlly.get(i).lifeCount>0) {
									mAlly.get(i).draw(canvas);
								}else{
									mResidueEnomy.add(new ResidueAlly(mAlly.get(0)));
									
									if(mAlly.get(i).showAlly!=null){
										mAlly.get(i).showAlly.recycle();
										mAlly.get(i).showAlly=null;
									}
									
									for(int j=0;j<mAlly.get(i).dataAlly.length;j++){
										mAlly.get(i).dataAlly[j].recycle();
										mAlly.get(i).dataAlly[j]=null;
									}
									mAlly.remove(i);
								}
								
							}
							
							for(Ally a:mAlly){
								a.move();
							}
							
							for(Enomy e:mEnomy){
								e.move();
							}
							
							for(WeaponAlly w:mWeaponAlly){
								w.move();
							}
							
							for(Ally a:mAlly){
								a.trans();
							}
							
							for(Enomy a:mEnomy){
								a.trans();
							}
							
							for(WeaponAlly w:mWeaponAlly){
								w.trans();
							}
							
							makeWeaponAlly();
							makeEnomy();
							
							controlBar.draw(canvas);
							canvas.drawText(""+score, 0, MyGameView.width*100/1000, paint);
							if(!mAlly.isEmpty()){
								int length=(int) paint.measureText(""+mAlly.get(0).bombCount);
								canvas.drawText(""+mAlly.get(0).bombCount, width-length, MyGameView.width*100/1000, paint);
							}
							
							
						}else{
							paint.setColor(Color.YELLOW);
							paint.setTextSize(MyGameView.width*100/1000);
							paint.setTypeface(mfont);
							canvas.drawText("wait0123456789", 0, 100, paint);
							
							MainActivity.mHan.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									if(loop<=-2){
										MainActivity.lay.setVisibility(View.VISIBLE);
										
									}else{
										MainActivity.lay.setVisibility(View.GONE);
									}
								}
							});
						}
						
						
						
						loop++;
						loop=loop>=10000?0:loop;
						
					}
				} finally {
					if (canvas != null)
						mHolder.unlockCanvasAndPost(canvas);
				}

				synchronized (this) {
					if (isWait)
						try {
							wait();
						} catch (InterruptedException e) {
						}
				}

			}// while
		}// /run


		public void makeEnomy() {
			// TODO Auto-generated method stub
			if(loop%5==0&&mEnomy.size()<5){
				rnd=new Random(System.currentTimeMillis());
				int x=rnd.nextInt(100);
				
				if(x<80){
					mEnomy.add(new Enomy1(R.drawable.enomy0, 3));
				}else if(x>=80&&x<85){
					mEnomy.add(new BombEnomy(R.drawable.bomb0, 3));
				}else if(x==85){
					mEnomy.add(new Enomy2(R.drawable.enemy2));
				}
				
				
			}
			
		}

	}

	public boolean onTouchEvent(MotionEvent event) {
		if(!mThread.mAlly.isEmpty()){
			heroArea=new Rect(mThread.mAlly.get(0).drawX, mThread.mAlly.get(0).drawY, mThread.mAlly.get(0).drawX+mThread.mAlly.get(0).w,mThread.mAlly.get(0).drawY+mThread.mAlly.get(0).h);
		}
		
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			xDown=(int) event.getX();
			yDown=(int) event.getY();
			
			if(toLeft.contains(xDown, yDown)){
				mThread.controlBar.isPressLeft=true;
				jindong.vibrate(20);
			}
			if(superWeapon.contains(xDown, yDown)){
				mThread.controlBar.isPressCenter=true;
				jindong.vibrate(20);
			}
			if(toRight.contains(xDown, yDown)){
				mThread.controlBar.isPressRight=true;
				jindong.vibrate(20);
			}
			
		}
		if(event.getAction()==MotionEvent.ACTION_UP){
			xUp=(int) event.getX();
			yUp=(int) event.getY();
			
			mThread.controlBar.isPressLeft=false;
			mThread.controlBar.isPressCenter=false;
			mThread.controlBar.isPressRight=false;
			
			synchronized (mHolder) {


				if(!mThread.mAlly.isEmpty()&&toLeft.contains(xDown, yDown)&&toLeft.contains(xUp, yUp)){
					mThread.mAlly.get(0).moveLength=MyGameView.width * 50 / 1000;
					
				}
				if(!mThread.mAlly.isEmpty()&&toRight.contains(xDown, yDown)&&toRight.contains(xUp, yUp)){
					mThread.mAlly.get(0).moveLength=-MyGameView.width * 50 / 1000;
				}
				
				if(!mThread.mAlly.isEmpty()&&superWeapon.contains(xDown, yDown)&&superWeapon.contains(xUp, yUp)){
					mThread.mAlly.get(0).moveLength=0;
				}
				
				if(!mThread.mAlly.isEmpty()&&heroArea.contains(xDown, yDown)&&heroArea.contains(xUp, yUp)){
					if(mThread.mAlly.get(0).bombCount>0){
						mThread.mEnomy.clear();
						mThread.mAlly.get(0).bombCount--;
					}
				}
			}
			
		}
		
		
		postInvalidate();
		return true;
		
	}

}
