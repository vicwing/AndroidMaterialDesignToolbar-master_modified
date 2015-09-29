
package com.tekinarslan.material.sample.customui.XferModeDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

public class DynamicWaveView extends View {

    // ������ɫ
    private static final int WAVE_PAINT_COLOR = 0x880000aa;
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 20;
    private static final int OFFSET_Y = 0;
    // ��һ��ˮ���ƶ��ٶ�
    private static final int TRANSLATE_X_SPEED_ONE = 7;
    // �ڶ���ˮ���ƶ��ٶ�
    private static final int TRANSLATE_X_SPEED_TWO = 5;
    private float mCycleFactorW;

    private int mTotalWidth, mTotalHeight;
    private float[] mYPositions;
    private float[] mResetOneYPositions;
    private float[] mResetTwoYPositions;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOneOffset;
    private int mXTwoOffset;

    private Paint mWavePaint;
    private DrawFilter mDrawFilter;

    public DynamicWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // ��dpת��Ϊpx�����ڿ��Ʋ�ͬ�ֱ������ƶ��ٶȻ���һ��
        mXOffsetSpeedOne = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_ONE);
        mXOffsetSpeedTwo = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_TWO);

        // ��ʼ���Ʋ��ƵĻ���
        mWavePaint = new Paint();
        // ȥ�����ʾ��
        mWavePaint.setAntiAlias(true);
        // ���÷��Ϊʵ��
        mWavePaint.setStyle(Style.FILL);
        // ���û�����ɫ
        mWavePaint.setColor(WAVE_PAINT_COLOR);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // ��canvas����ȥ������ʱ���
        canvas.setDrawFilter(mDrawFilter);
        resetPositonY();
        for (int i = 0; i < mTotalWidth; i++) {

            // ��400ֻ��Ϊ�˿��Ʋ��ƻ��Ƶ�y������Ļ��λ�ã���ҿ��Ըĳ�һ��������Ȼ��̬�ı�����������Ӷ��γɲ��������½�Ч��
            // ���Ƶ�һ��ˮ����
            canvas.drawLine(i, mTotalHeight - mResetOneYPositions[i] - 400, i,
                    mTotalHeight,
                    mWavePaint);

            // ���Ƶڶ���ˮ����
            canvas.drawLine(i, mTotalHeight - mResetTwoYPositions[i] - 400, i,
                    mTotalHeight,
                    mWavePaint);
        }

        // �ı��������Ƶ��ƶ���
        mXOneOffset += mXOffsetSpeedOne;
        mXTwoOffset += mXOffsetSpeedTwo;

        // ����Ѿ��ƶ�����β��������ͷ��¼
        if (mXOneOffset >= mTotalWidth) {
            mXOneOffset = 0;
        }
        if (mXTwoOffset > mTotalWidth) {
            mXTwoOffset = 0;
        }

        // ����view�ػ棬һ����Կ����ӳ�20-30ms�ػ棬�ճ�ʱ��Ƭ
        postInvalidate();
    }

    private void resetPositonY() {
        // mXOneOffset����ǰ��һ��ˮ����Ҫ�ƶ��ľ���
        int yOneInterval = mYPositions.length - mXOneOffset;
        // ʹ��System.arraycopy��ʽ��������һ�����Ƶ�����
        System.arraycopy(mYPositions, mXOneOffset, mResetOneYPositions, 0, yOneInterval);
        System.arraycopy(mYPositions, 0, mResetOneYPositions, yOneInterval, mXOneOffset);

        int yTwoInterval = mYPositions.length - mXTwoOffset;
        System.arraycopy(mYPositions, mXTwoOffset, mResetTwoYPositions, 0,
                yTwoInterval);
        System.arraycopy(mYPositions, 0, mResetTwoYPositions, yTwoInterval, mXTwoOffset);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // ��¼��view�Ŀ��
        mTotalWidth = w;
        mTotalHeight = h;
        // ���ڱ���ԭʼ���Ƶ�yֵ
        mYPositions = new float[mTotalWidth];
        // ���ڱ��沨��һ��yֵ
        mResetOneYPositions = new float[mTotalWidth];
        // ���ڱ��沨�ƶ���yֵ
        mResetTwoYPositions = new float[mTotalWidth];

        // �����ڶ�Ϊview�ܿ��
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // ����view�ܿ�ȵó����ж�Ӧ��yֵ
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }
    }

}
