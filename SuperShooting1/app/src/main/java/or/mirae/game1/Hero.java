package or.mirae.game1;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Hero extends Ally {

	public Hero() {

	}

	public Hero(int img, int dataSize) {
		w = MyGameView.width * 200 / 1000;
		h = MyGameView.width * 200 / 1000;

		dataAlly = new Bitmap[dataSize];

		for (int i = 0; i < dataAlly.length; i++) {
			dataAlly[i] = BitmapFactory.decodeResource(
					MyGameView.mContext.getResources(), img+i);
			dataAlly[i] = Bitmap.createScaledBitmap(dataAlly[i], w, h, true);
		}

		showAlly = dataAlly[0];

		midX = MyGameView.width / 2;
		midY = MyGameView.height*900/1000 - h * 2 / 2;

		drawX = midX - w / 2;
		drawY = midY - h / 2;

		moveLength = 0;
		loopCount = 4;
		lifeCount = 1;
		
		bombCount=1;
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.drawBitmap(showAlly, drawX, drawY, null);
	}

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		loop++;
		loop=loop>=10000?0:loop;
		
		imgNum=(loop/5)%dataAlly.length;
		showAlly=dataAlly[imgNum];
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(loop%3==0){
			midX-=moveLength;
			
			midX=midX<=w/2?w/2:midX;
			moveLength=midX<=w/2?0:moveLength;
							
			midX=midX>=MyGameView.width-w/2?MyGameView.width-w/2:midX;
			moveLength=midX>=MyGameView.width-w/2?0:moveLength;
			
			drawX=midX-w/2;	
		}
		
		
	}
}
