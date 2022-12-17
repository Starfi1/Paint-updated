package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private ImageView colorPic;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        colorPic=findViewById(R.id.pallete);
        colorPic.setDrawingCacheEnabled(true);
        colorPic.buildDrawingCache(true);
        colorPic.setOnTouchListener(new View.OnTouchListener() {
            String color = "";

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap = colorPic.getDrawingCache();
                    int pixels = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixels);
                    int g = Color.green(pixels);
                    int b = Color.blue(pixels);
                    color = "#" + Integer.toHexString(pixels);
                }
                paintView.setColor(color);

                return true;
            }


        });

        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        frame.addView(paintView);
    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }
    public void addWidth(View view){paintView.addWidth();}
    public void decWidth(View view){paintView.decWidth();}
    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    public void changeFill(View view){paintView.changeFill();}

    public void clear(View view) {
        paintView.undo();
    }
}
