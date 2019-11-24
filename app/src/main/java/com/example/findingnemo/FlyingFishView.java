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

    private int yellowX, yellowY, yellowSpeed = 8;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 13;
    private Paint greentPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();


    private int score;

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greentPaint.setColor(Color.GREEN);
        greentPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(60);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishy = 550;
        score = 0;
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
        yellowX = yellowX - yellowSpeed;

        if(hitBallChecker(yellowX,yellowY)){
            score = score + 10;
            yellowX = -100;
        }
        if(yellowX < 0){
            yellowX = canvasWidth + 21;
            yellowY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX,yellowY, 20, yellowPaint);


        greenX = greenX - greenSpeed;

        if(hitBallChecker(greenX,greenY)){
            score = score + 20;
            greenX = -100;
        }
        if(greenX < 0){
            greenX = canvasWidth + 21;
            greenY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX,greenY, 15, greentPaint);

        redX = redX - redSpeed;

        if(hitBallChecker(redX,redY)){
            redX = -100;
        }
        if(redX < 0){
            redX = canvasWidth + 21;
            redY = (int)Math.floor(Math.random() * (maxFishY-minFishY)) + minFishY;
        }
        canvas.drawCircle(redX,redY, 25, redPaint);


        canvas.drawText("Score : " + score, 20, 60, scorePaint);

        canvas.drawBitmap(life[0],380,10,null);
        canvas.drawBitmap(life[0],480,10,null);
        canvas.drawBitmap(life[0],580,10,null);
    }

    public boolean hitBallChecker(int x, int y){
        if(fishx < x && x < (fishx+fish[0].getWidth()) && fishy < y && y < (fishy+fish[0].getHeight())){
            return true;
        }
        return false;
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
