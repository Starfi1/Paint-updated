package com.example.paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends Shape {

    private float xEnd;
    private float yEnd;
    private boolean fillOrNot;

    private int Size;
    public CircleShape(int x, int y, String color, int size,boolean fill) {
        super(x, y, color);
        xEnd =(float) x;
        yEnd = (float)y;
        Size=size;
        fillOrNot=fill;

    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = (float)xe;
        yEnd = (float)ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if(!fillOrNot)paint.setStyle(Paint.Style.STROKE);
        else paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(Size);
        super.draw(canvas,paint);
        float radius= (float) Math.sqrt(Math.pow((xEnd-x),2)+Math.pow((yEnd-y),2));
        canvas.drawCircle(x,y,radius,paint);
    }
}
