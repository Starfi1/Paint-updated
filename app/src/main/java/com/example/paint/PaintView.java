package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.hardware.GeomagneticField;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {
    private Context context1;
    public int size;
    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";
    int brushSize=10;
    public static int largestIndex = 0;
    public static float largestSize = 0;
    boolean fill=false;
    public static Stack<AreaShape> shapes;
    public PaintView(Context context) {
        super(context);
        this.context1 = context;
        size = 12;
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        bgPaint.setColor(Color.rgb(255,255,255));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }


    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor,brushSize,fill);
            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor,brushSize,fill);
            }
            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor,brushSize,fill);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor,brushSize,fill);

            }

            shapes.push((AreaShape) shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {

            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }

        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }
    public void addWidth() {
    if(brushSize<50) {
        brushSize += 5;
    }
    }
    public void decWidth() {
        if(brushSize>5) {
            brushSize-= 5;
        }
    }
    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }
    public void changeFill()
    {
        if(fill==false) fill=true;
        else fill=false;
    }

    public void showBiggest() {
//
        if (shapes.size() > 0) {
            for (int i = 0; i < shapes.size(); i++) {
                if (shapes.get(i).sizeShape> largestSize) {
                    largestSize = shapes.get(i).sizeShape;
                    largestIndex = i;
                }

            }
            if (largestSize != 0) {


                Shape newShape = shapes.get(largestIndex);
                shapes = new Stack<>();
                shapes.push((AreaShape) newShape);
                largestIndex = 0; // may delete
                largestSize = ((AreaShape) newShape).sizeShape;
                invalidate();
            } else {
                Toast toast = Toast.makeText(context1, "No area shapes", Toast.LENGTH_SHORT);
                toast.show();
                shapes = new Stack<>();
                largestIndex = 0; // may delete
                largestSize = 0;
                invalidate();
            }
        } else {
            Toast toast = Toast.makeText(context1, "There are no shapes", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    public void undo()
    {
        shapes.pop();
        invalidate();
    }
}
