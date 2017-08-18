package or.mirae.game1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class WeaponAlly extends Ally{

	public WeaponAlly(){
		
	}
	public WeaponAlly(int img, int dataSize,Ally ally){
		power=1;		
		w=MyGameView.width*100/1000;
		h=MyGameView.width*100/1000;
		
		dataAlly=new Bitmap[dataSize];
		for(int i=0;i<dataAlly.length;i++){
			dataAlly[i]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), img+i);
			dataAlly[i]=Bitmap.createScaledBitmap(dataAlly[i], w, h, true);
		}
		showAlly=dataAlly[0];
		
		midX=ally.midX;
		midY=ally.drawY;
		
		drawX=midX-w/2;
		drawY=midY-h/2;
		
		lifeCount=1;
		moveLength=w=MyGameView.width*50/1000;
				
	}
	

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		loop++;
		if(loop>=10000){
			loop=0;
		}
		
			
		imgNum=(loop/2)%dataAlly.length;
		
		showAlly=dataAlly[imgNum];
	}

	@Override
	public void move() {
		if(loop%3==0){
			midY-=moveLength;
			if(midY<=(h/2)){
				lifeCount=0;
			}
		}
		
		drawY=midY-h/2;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(showAlly, drawX, drawY, null);
	}
}
