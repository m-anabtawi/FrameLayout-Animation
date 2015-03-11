package com.lp.circletheme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class BallBounces extends SurfaceView implements SurfaceHolder.Callback {
   private GameThread thread;
   private int screenW;
   private int screenH;
   private int ballX;
   private int ballY;
   private int initialY ;
   private float dY;
   private int ballW;
   private int ballH;
   private int bgrW;
   private int bgrH;
   private int angle;
   private int bgrScroll;
   private int dBgrY;
   private float acc;
   private Bitmap ball, bgr, bgrReverse;
   private boolean reverseBackroundFirst;
   private boolean ballFingerMove;
   private long now;
   private int framesCount=0;
   private int framesCountAvg=0;
   private long framesTimer=0;
   private Paint fpsPaint=new Paint();
   private long timeNow;
   private long timePrev = 0;
   private long timePrevFrame = 0;
   private long timeDelta;


    public BallBounces(Context context) {
        super(context);
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.image2);
        bgr = BitmapFactory.decodeResource(getResources(),R.drawable.image_view);
        ballW = ball.getWidth();
        ballH = ball.getHeight();
        reverseBackroundFirst = false;
        acc = 0.2f;
        dY = 0;
        initialY = 100;
        angle = 0;
        bgrScroll = 0;
        dBgrY = 1;
        fpsPaint.setTextSize(30);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenW = w;
        screenH = h;

        bgr = Bitmap.createScaledBitmap(bgr, w, h, true);
        bgrW = bgr.getWidth();
        bgrH = bgr.getHeight();


        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        bgrReverse = Bitmap.createBitmap(bgr, 0, 0, bgrW, bgrH, matrix, true);

        ballX = (int) (screenW /2) - (ballW / 2) ;
        ballY = -50;
    }


    @Override
    public synchronized boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                ballX = (int) ev.getX() - ballW/2;
                ballY = (int) ev.getY() - ballH/2;

                ballFingerMove = true;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                ballX = (int) ev.getX() - ballW/2;
                ballY = (int) ev.getY() - ballH/2;

                break;
            }

            case MotionEvent.ACTION_UP:
                ballFingerMove = false;
                dY = 0;
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        Rect fromRect1 = new Rect(0, 0, bgrW-bgrScroll , bgrH);
        Rect toRect1 = new Rect(bgrScroll, 0, bgrW, bgrH);
        canvas.drawBitmap(bgr, fromRect1, toRect1, null);

        if ( (bgrScroll += dBgrY) >= bgrW) {
            bgrScroll = 0;
            reverseBackroundFirst = !reverseBackroundFirst;
        }
        canvas.save();

        canvas.drawBitmap(ball, ballX, ballY, null);
        canvas.restore();
        now=System.currentTimeMillis();

        framesCount++;
        if(now-framesTimer>1000) {
            framesTimer=now;
            framesCountAvg=framesCount;
            framesCount=0;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


    class GameThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private BallBounces gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, BallBounces gameView) {
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }

        public void setRunning(boolean run) {
            this.run = run;
        }

        public SurfaceHolder getSurfaceHolder() {
            return surfaceHolder;
        }

        @Override
        public void run() {
            Canvas c;
            while (run) {
                c = null;


                timeNow = System.currentTimeMillis();
                timeDelta = timeNow - timePrevFrame;
                if ( timeDelta < 16) {
                    try {
                        Thread.sleep(16 - timeDelta);
                    }
                    catch(InterruptedException e) {

                    }
                }
                timePrevFrame = System.currentTimeMillis();

                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {

                        gameView.draw(c);
                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}
