package or.mirae.game1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;




public class ResidueAlly extends ResidueEnomy{
	
	
	public ResidueAlly(){
		
	}
	
	public ResidueAlly(Ally ally){
		w=ally.w;
		h=ally.h;
		
		dataResidueEnomy=new Bitmap[10];
		dataResidueEnomy[0]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.hero);
		Matrix m=new Matrix();
		
		for(int i=0;i<dataResidueEnomy.length;i++){
			dataResidueEnomy[i]=Bitmap.createScaledBitmap(dataResidueEnomy[0], w-i*w/dataResidueEnomy.length, w-i*w/dataResidueEnomy.length, true);
			m.postRotate(i*90, ally.midX, ally.midY);
			dataResidueEnomy[i]=Bitmap.createBitmap(dataResidueEnomy[i], 0, 0, dataResidueEnomy[i].getWidth(), dataResidueEnomy[i].getHeight(), m, true);
		}
		showResidueEnomy=dataResidueEnomy[0];
				
		midX=ally.midX;
		midY=ally.midY;
		
		drawX=midX-showResidueEnomy.getWidth()/2;
		drawY=midY-showResidueEnomy.getHeight()/2;
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


		if(imgNum>=dataResidueEnomy.length-1){		
			lifeCount=false;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent=new Intent(MyGameView.mContext, StartActivity.class);
			MyGameView.mContext.startActivity(intent);
			MyGameView.mContext.setTheme(R.anim.fade_in);
		}
		
		imgNum=(loop/3)%dataResidueEnomy.length;

		showResidueEnomy=dataResidueEnomy[imgNum];
		drawX=midX-showResidueEnomy.getWidth()/2;
		drawY=midY-showResidueEnomy.getHeight()/2;
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}
	
	
	
}
