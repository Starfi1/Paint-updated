package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends Shape {

    private int xEnd;
    private int yEnd;
    private int Size;
    private boolean fillOrNot;

    public RectShape(int x, int y, String color,int size,boolean fill) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        Size=size;
        fillOrNot=fill;

    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if(!fillOrNot)paint.setStyle(Paint.Style.STROKE);
        else paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(Size);
        super.draw(canvas,paint);
        canvas.drawRect(x,y,xEnd,yEnd,paint);
    }
}
