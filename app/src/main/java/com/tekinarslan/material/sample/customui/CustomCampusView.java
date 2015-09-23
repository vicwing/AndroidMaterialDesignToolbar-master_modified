package com.tekinarslan.material.sample.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by cdj on 2015/9/23.
 */
public class CustomCampusView extends View {
    private Paint paint;
    public CustomCampusView(Context context) {
        super(context);
        LogUtils.d("000000000000000");
    }

    public CustomCampusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtils.d("1111111111111111111111");
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.d("2222222222222222");
//        drawCampous(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        int dx = 250;
        int dy = 150;
        canvas.drawLine(dx, dy, dx, 0, paint);
//        canvas.save();
        for (int i =0;i<12;i++){
            canvas.rotate(i*30, dx, dy);//这个方法是将整个画布旋转，起点位置还是之前画布里面的150，
            canvas.drawLine(dx, dy, dx, 0, paint);
        }

//        canvas.rotate(30);
//        canvas.restore();
//        canvas.drawLine(250, 150, 150, 0, paint);
    }

    private void drawCampous(Canvas canvas) {
        int radius = 200;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(canvas.getWidth() / 2, canvas.getWidth()/2); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, radius, paint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate((float) -(radius*0.8), (float) -(radius*0.8));
        Path path = new Path();
        path.addArc(new RectF(0,0,150,150), -180, 180);
        Paint citePaint = new Paint(paint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
        canvas.restore();

        Paint tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);

        float  y=100;
        int count = 60; //总刻度数

        for(int i=0 ; i <count ; i++){
            if(i%5 == 0){
                canvas.drawLine(0f, y, 0, y+12f, paint);
                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

            }else{
                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
            }
            canvas.rotate(360/count,0f,0f); //旋转画纸
        }

        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, paint);
    }
}
