package or.mirae.game1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ResidueEnomy implements MethodInter{
	Bitmap showResidueEnomy;
	Bitmap dataResidueEnomy[];
	int imgNum;
	
	int loop;
	boolean lifeCount=true;
	int midX, midY;
	int drawX, drawY;
	
	int w, h;
	
	public ResidueEnomy(){
		
	}
	
	public ResidueEnomy(Enomy enomy) {
		w = enomy.w;
		h = enomy.h;

		dataResidueEnomy = new Bitmap[5];
		dataResidueEnomy[0] = BitmapFactory.decodeResource(
				MyGameView.mContext.getResources(), R.drawable.explosion);
		for (int i = dataResidueEnomy.length - 1; i >= 0; i--) {
			dataResidueEnomy[i] = Bitmap.createScaledBitmap(
					dataResidueEnomy[0], w - i * w / dataResidueEnomy.length, w
							- i * w / dataResidueEnomy.length, true);
		}
		imgNum = dataResidueEnomy.length - 1;
		showResidueEnomy = dataResidueEnomy[imgNum];

		midX = enomy.midX;
		midY = enomy.midY;

		drawX = midX - showResidueEnomy.getWidth() / 2;
		drawY = midY - showResidueEnomy.getHeight() / 2;
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(showResidueEnomy, drawX, drawY, null);
		trans();
	}

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		loop++;
				
		if(loop%3==0){
			imgNum--;
			if(imgNum<0){
				imgNum=0;
				lifeCount=false;
			}
			
			showResidueEnomy=dataResidueEnomy[imgNum];
			drawX=midX-showResidueEnomy.getWidth()/2;
			drawY=midY-showResidueEnomy.getHeight()/2;
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
