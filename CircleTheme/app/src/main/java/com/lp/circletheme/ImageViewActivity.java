package com.lp.circletheme;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;



public class ImageViewActivity extends Activity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        image = (android.widget.ImageView) findViewById(R.id.imageView6);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_vew, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {
            setImage(1);

        }
        else if(id == R.id.item2){
            setImage(2);

        }
        else{
            setImage(3);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setImage (int imageNum){
          if(imageNum == 1) {
              image.setImageResource(R.drawable.imag1);
          }
          else if(imageNum == 2) {
              image.setImageResource(R.drawable.image2);
          }
          else {
              image.setImageResource(R.drawable.image3);
          }
    }
}
