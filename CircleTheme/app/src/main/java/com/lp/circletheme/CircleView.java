package com.lp.circletheme;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class CircleView extends ActionBarActivity {
    private ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
	private float xCurrentPos, yCurrentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);
        image4 = (ImageView) findViewById(R.id.imageView4);
        image5 = (ImageView) findViewById(R.id.imageView5);
		xCurrentPos = image1.getLeft();
        yCurrentPos = image1.getTop();
        final TranslateAnimation animation2,animation3,animation4,animation5;

        xCurrentPos = image1.getLeft();
        yCurrentPos = image1.getTop();
        animation2 = new TranslateAnimation(xCurrentPos,-50, yCurrentPos, - 50);
        animation2.setDuration(1000);
        animation2.setFillAfter( true );
        animation2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });
        image2.startAnimation(animation2);

        animation3 = new TranslateAnimation(xCurrentPos,-50, yCurrentPos,  50);
        animation3.setDuration(1000);
        animation3.setFillAfter( true );
        animation3.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });
        image3.startAnimation(animation3);

        animation4 = new TranslateAnimation(xCurrentPos,50, yCurrentPos,  -50);
        animation4.setDuration(1000);
        animation4.setFillAfter( true );
        animation4.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });
        image4.startAnimation(animation4);

        animation5 = new TranslateAnimation(xCurrentPos,50, yCurrentPos,  50);
        animation5.setDuration(1000);
        animation5.setFillAfter( true );
        animation5.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });
        image5.startAnimation(animation5);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_circle_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
