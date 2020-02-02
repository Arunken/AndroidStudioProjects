package com.example.silverdoe.a31_graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnchange = (Button) findViewById(R.id.button);
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout llay = (LinearLayout) findViewById(R.id.rect);

                Bitmap bm = Bitmap.createBitmap(400,800,Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bm);
                Paint paint  = new Paint();
                paint.setColor(Color.parseColor("#da4432"));

                //canvas.drawRect(50,50,200,200,paint);
                canvas.drawCircle(100,100,50,paint);
                llay.setBackground(new BitmapDrawable(bm));
            }
        });

    }
}
