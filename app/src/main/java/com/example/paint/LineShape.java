package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
public class LineShape extends Shape {

    private int xEnd;
    private int yEnd;
    private boolean fillOrNot;

    private int size;
    public LineShape(int x, int y, String color,int Size,boolean fill) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        size=Size;
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
        paint.setStrokeWidth(size);
        super.draw(canvas,paint);
        canvas.drawLine(x,y,xEnd,yEnd,paint);
    }
}
