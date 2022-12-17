package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class PathShape extends Shape {
    private Path path;
    private int xEnd;
    private int yEnd;
    private  int Size;
    private boolean fillOrNot;
    public PathShape(int x, int y, String color,int size, boolean fill) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        path=new Path();
        path.moveTo(xEnd,yEnd);
        Size=size;
        fillOrNot=fill;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        path.lineTo(xe,ye);
    }


    @Override
    public void draw(Canvas canvas, Paint paint) {
        if(!fillOrNot)paint.setStyle(Paint.Style.STROKE);
        else paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(Size);
        super.draw(canvas,paint);
        canvas.drawPath(path,paint);
    }
}
