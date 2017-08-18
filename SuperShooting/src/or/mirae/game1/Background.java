package or.mirae.game1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
	Bitmap back;

	int srcH1, srcH2;
	int w, h;
	int moveLength;
	Rect src, dst;
	
	public Background(){
		w=MyGameView.width*1100/1000;
		h=MyGameView.height*2;
		int a=new Random(System.currentTimeMillis()).nextInt(6);
		
		back=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.back0+a);
		back=Bitmap.createScaledBitmap(back, w, h, true);
		
		if(MyGameView.height<500){
			moveLength=MyGameView.height*3/1000;
		}else if(MyGameView.height>=500&&MyGameView.height<1000){
			moveLength=MyGameView.height*2/1000;
		}else if(MyGameView.height>=1000){
			moveLength=MyGameView.height*1/1000;
		}
		srcH1=h/2;
		srcH2=h;
		
		src=new Rect(0,srcH1,w,srcH2);
		dst=new Rect(0,0,MyGameView.width*1100/1000,MyGameView.height*1100/1000);
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(back, src, dst, null);
		move();
	}
	
	public void move(){
		srcH1-=moveLength;
		srcH2-=moveLength;
		
		if(srcH1<=0){
			srcH1=h/2;
		}
		if(srcH2<=h/2){
			srcH2=h;
		}
		
		src.set(0, srcH1, w, srcH2);
	}

}
