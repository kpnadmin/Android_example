package or.mirae.game1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class BombEnomy extends Enomy {
	
	public BombEnomy(){
		
	}
	
	public BombEnomy(int img, int dataSize){
		w=MyGameView.width*300/1000;
		h=MyGameView.width*300/1000;
		
		dataEnomy=new Bitmap[dataSize];
		for(int i=0;i<dataEnomy.length;i++){
			dataEnomy[i]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), img+i);
			dataEnomy[i]=Bitmap.createScaledBitmap(dataEnomy[i], w, h, true);
		}
		
		showEnomy=dataEnomy[0];
		
		Random rnd=new Random(System.currentTimeMillis());
		
		midX=rnd.nextInt(MyGameView.width+1);
		midY=-h/2;
		
		drawX=midX-w/2;
		drawY=midY-h/2;
		
		lifeCount=5;
		moveLength=MyGameView.width*150/1000;
		
		score=300;
	}

	

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		loop++;
		if(loop>=10000){
			loop=0;
		}
		
		if(loop%2==0){
			imgNum++;
			if(imgNum>=dataEnomy.length){
				imgNum=0;
			}
			
		}
		
		//imgNum=(loop/2)%dataEnomy.length;
		
		showEnomy=dataEnomy[imgNum];
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(loop%3==0){
			midY+=moveLength;
			if(midY>=MyGameView.height+h/2){
				
				midX=new Random(System.currentTimeMillis()).nextInt(MyGameView.width+1);
				drawX=midX-w/2;
				midY=-h/2;
			}
			drawY=midY-h/2;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(showEnomy, drawX, drawY, null);
	}
	
	
	

}
