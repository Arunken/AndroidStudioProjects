package com.example.silverdoe.a31_graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyGraphics extends View{

    Paint paint;
    public MyGraphics(Context context)
    {
        super(context);
        init(null);
    }

    public void init(AttributeSet attributeSet)
    {
        paint = new Paint();
    }

    public void changeColor()
    {
        if(paint.getColor()==Color.GREEN)
        {
            paint.setColor(Color.BLACK);
        }
        if(paint.getColor()==Color.BLUE)
        {
            paint.setColor(Color.GRAY);
        }
        if(paint.getColor()==Color.RED)
        {
            paint.setColor(Color.MAGENTA);
        }
        //invalidate();
        postInvalidate();

    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        paint.setAntiAlias(false);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(20, 20, 15, paint);

        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(60, 20, 15, paint);

        paint.setAntiAlias(false);
        paint.setColor(Color.RED);
        canvas.drawRect(100, 5, 200, 30, paint);

        canvas.rotate(-45);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("My Graphics", 40, 180, paint);

        canvas.restore();
    }

}
