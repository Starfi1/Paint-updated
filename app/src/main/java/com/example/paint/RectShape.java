package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends AreaShape  {

    private int xEnd;
    private int yEnd;
    private int Size;
    private boolean fillOrNot;
    public static float currentSize = 0;

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
        calculateArea();

        PaintView.shapes.get(PaintView.shapes.size() - 1).sizeShape = currentSize;

    }
    public void calculateArea() {

        int width = Math.abs(Math.max(x, xEnd) - Math.min(x, xEnd));
        int height = Math.abs(Math.max(x, xEnd) - Math.min(x, xEnd));
        currentSize = width * height;

    }

}
