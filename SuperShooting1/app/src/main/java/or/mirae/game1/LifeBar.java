package or.mirae.game1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class LifeBar implements MethodInter{
	Bitmap showBack;
	Bitmap showLifeBar;
	Bitmap dataLifeBar[];
	
	int midX, midY;
	int drawX, drawY;
	
	int w, h;
	
	Enomy enomy;
	
	int imgNum;
	int originLifeCount;
	
	public LifeBar(){
		
	}
	
	public LifeBar(Enomy enomy){
		this.enomy=enomy;
		w=this.enomy.w;
		h=MyGameView.width*50/1000;
		
		midX=enomy.midX;
		midY=enomy.drawY-h/2;
		
		drawX=midX-w/2;
		drawY=midY-h/2;
		
		showBack=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.lifebarbackground);
		showBack=Bitmap.createScaledBitmap(showBack, w, h, true);
		
		dataLifeBar=new Bitmap[enomy.lifeCount];
		
		
		for(int i=0;i<dataLifeBar.length;i++){
			dataLifeBar[i]=BitmapFactory.decodeResource(MyGameView.mContext.getResources(), R.drawable.lifebar);
			dataLifeBar[i]=Bitmap.createScaledBitmap(dataLifeBar[0], w-i*w/enomy.lifeCount, h, true);
		}
		
		showLifeBar=dataLifeBar[0];
		
		originLifeCount=enomy.lifeCount;
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(showBack, drawX, drawY, null);
		canvas.drawBitmap(showLifeBar, drawX, drawY,null);
	}
	
	@Override
	public void trans() {
		// TODO Auto-generated method stub
		imgNum=originLifeCount-enomy.lifeCount;
		
		showLifeBar=dataLifeBar[imgNum];
	}

	@Override
	public void move() {
		midX=enomy.midX;
		midY=enomy.drawY-h/2;
		drawX=midX-w/2;
		drawY=midY-h/2;		
	}
	

}
