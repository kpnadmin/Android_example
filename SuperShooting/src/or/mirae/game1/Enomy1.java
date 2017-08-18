package or.mirae.game1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Enomy1 extends Enomy {

	public Enomy1() {

	}

	public Enomy1(int img, int dataSize) {
		Random rnd=new Random(System.currentTimeMillis());
		
		w = MyGameView.width * 60 / 1000;
		h = MyGameView.width * 60 / 1000;

		dataEnomy = new Bitmap[dataSize];
		for (int i = 0; i < dataEnomy.length; i++) {
			dataEnomy[i] = BitmapFactory.decodeResource(
					MyGameView.mContext.getResources(), img+i);
			dataEnomy[i] = Bitmap.createScaledBitmap(dataEnomy[i], w, h, true);
		}

		showEnomy = dataEnomy[0];

		midX = rnd.nextInt(MyGameView.width + 1);
		midY = -h / 2;

		drawX = midX - w / 2;
		drawY = midY - h / 2;
		
		moveLength = rnd.nextInt(w/2-w/4+1)+w/4;
		lifeCount = 1;
		
		score=100;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
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
		
		
		
	}
	
	

}
