package com.example.findingnemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class FlyingFishView extends View {

    private Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private int fishy;
    private int fishSpeed;
    private int canvasWidth, canvasHeight;
    private boolean touch = false;

    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(60);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishy = 550;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage,0,0,null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishy = fishy + fishSpeed;
        if(fishy < minFishY){
            fishy = minFishY;
        }
        if(fishy > maxFishY){
            fishy = maxFishY;
        }
        fishSpeed = fishSpeed+2;

        if(touch){
            canvas.drawBitmap(fish[1],fishx,fishy,null);
            touch = false;
        }
        else{
            canvas.drawBitmap(fish[0],fishx, fishy,null);
        }
        canvas.drawText("Score : ", 20, 60, scorePaint);

        canvas.drawBitmap(life[0],380,10,null);
        canvas.drawBitmap(life[0],480,10,null);
        canvas.drawBitmap(life[0],580,10,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;
            fishSpeed = -22;
        }
        return true;
    }
}
