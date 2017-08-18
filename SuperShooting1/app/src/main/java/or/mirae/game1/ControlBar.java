package or.mirae.game1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ControlBar {
	Bitmap left[]=new Bitmap[2];
	Bitmap center[]=new Bitmap[2];
	Bitmap right[]=new Bitmap[2];
	
	boolean isPressLeft;
	boolean isPressCenter;
	boolean isPressRight;
	
	public ControlBar(){
		left[0]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.left);
		left[0]=Bitmap.createScaledBitmap(left[0], MyGameView.width/3-10, MyGameView.height*100/1000, true);
		
		left[1]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.left_press);
		left[1]=Bitmap.createScaledBitmap(left[1], MyGameView.width/3-10, MyGameView.height*100/1000, true);
		
		center[0]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.center);
		center[0]=Bitmap.createScaledBitmap(center[0], MyGameView.width/3-20, MyGameView.height*100/1000, true);
		
		center[1]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.center_press);
		center[1]=Bitmap.createScaledBitmap(center[1], MyGameView.width/3-20, MyGameView.height*100/1000, true);
		
		
		right[0]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.right);
		right[0]=Bitmap.createScaledBitmap(right[0], MyGameView.width/3-10, MyGameView.height*100/1000, true);
		
		right[1]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.right_press);
		right[1]=Bitmap.createScaledBitmap(right[1], MyGameView.width/3-10, MyGameView.height*100/1000, true);
				
	}
	
	public void draw(Canvas canvas){
		if(!isPressLeft){
			canvas.drawBitmap(left[0],0,MyGameView.height*900/1000, null);
		}else{
			canvas.drawBitmap(left[1],0,MyGameView.height*900/1000, null);
		}
		
		if(!isPressCenter){
			canvas.drawBitmap(center[0],MyGameView.width/3+10,MyGameView.height*900/1000, null);
		}else{
			canvas.drawBitmap(center[1],MyGameView.width/3+10,MyGameView.height*900/1000, null);
		}
		
		if(!isPressRight){
			canvas.drawBitmap(right[0],MyGameView.width*2/3+10,MyGameView.height*900/1000, null);
		}else{
			canvas.drawBitmap(right[1],MyGameView.width*2/3+10,MyGameView.height*900/1000, null);
		}
		
	}
	
	

}
