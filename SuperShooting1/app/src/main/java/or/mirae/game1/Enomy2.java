package or.mirae.game1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class Enomy2 extends Enomy {

	public Enomy2() {

	}

	public Enomy2(int img) {
		w = MyGameView.width * 300 / 1000;
		h = MyGameView.width * 300 / 1000;

		dataEnomy = new Bitmap[2];
		
		dataEnomy[0] = BitmapFactory.decodeResource(
					MyGameView.mContext.getResources(), img);
		dataEnomy[0] = Bitmap.createScaledBitmap(dataEnomy[0], w, h, true);
		
		Matrix m=new Matrix();
		m.postScale(-1, 1);
		dataEnomy[1]=Bitmap.createBitmap(dataEnomy[0],0,0,w,h,m,true);
		

		showEnomy = dataEnomy[0];

		midX = new Random(System.currentTimeMillis())
				.nextInt(MyGameView.width + 1);
		midY = -h / 2;

		drawX = midX - w / 2;
		drawY = midY - h / 2;

		moveLength = MyGameView.width * 30 / 1000;
		lifeCount = 5;
		
		lifeBar=new LifeBar(this);
		score=100;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		lifeBar.draw(canvas);
		canvas.drawBitmap(showEnomy, drawX, drawY, null);
		
	}

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		loop++;
		loop=loop>=10000?0:loop;
		if(loop%2==0){
			imgNum++;
			if(imgNum>=dataEnomy.length){
				imgNum=0;
			}
			showEnomy=dataEnomy[imgNum];
		}
		
		lifeBar.trans();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(loop%3==0){
			midY += moveLength;

			if (midY>=MyGameView.height+h/2) {
				midX=new Random(System.currentTimeMillis()).
				nextInt(MyGameView.width + 1);
			}

			if(midY>=MyGameView.height+h/2){
				midY=-h/2;
			}
			
			drawY=midY-h/2;
			drawX=midX-w/2;
			
		}
		
		lifeBar.move();
		
	}
	
	

}
