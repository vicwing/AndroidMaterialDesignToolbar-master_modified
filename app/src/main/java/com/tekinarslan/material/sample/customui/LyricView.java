package com.tekinarslan.material.sample.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by cdj on 2015/7/21.
 */

public final class LyricView extends View {
    // 歌词对象
    private SongLyric lrc = null;
    // 当前播放时间
    private long time = 0l;
    // 字体画笔
    private Paint fontPaint = null;
    // 当前歌词字体画笔
    private Paint lrcPaint = null;
    // 字体颜色
    private int fontColor = Color.WHITE;
    // 当前歌词字体颜色
    private int lrcColor = Color.RED;
    // 字体大小
    private int fontSize = 14;

    public LyricView(Context context) {
        super(context);
    }

    /**
     * 设置歌词对象
     *
     * @param lrc
     */
    public void setLyric(SongLyric lrc) {
        this.lrc = lrc;
    }

    /**
     * 设置当前时间
     *
     * @param ms
     */
    public void setTime(long ms) {
        this.time = ms;
    }

    /**
     * 设置歌词字体颜色
     *
     * @param color
     */
    public void setFontColor(int color) {
        this.fontColor = color;
    }

    /**
     * 设置当前歌词字体颜色
     *
     * @param color
     */
    public void setLyricColor(int color) {
        this.lrcColor = color;
    }

    /**
     * 设置字体大小
     *
     * @param size
     */
    public void setFontSize(int size) {
        this.fontSize = size;
    }

    /**
     * 重绘视图
     */
    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        if (lrc != null) {
            try {
                if (fontPaint == null) {
                    fontPaint = new Paint();
                }
                if (lrcPaint == null) {
                    lrcPaint = new Paint();
                }
                fontPaint.setColor(fontColor);
                fontPaint.setTextSize(fontSize);
                lrcPaint.setColor(lrcColor);
                lrcPaint.setTextSize(fontSize);
                // 获取当前要播放歌词的索引
                int cIndex = lrc.getIndex(time);
                // 计算绘制歌词y坐标
                int h = getHeight()
                        / 2
                        - cIndex
                        * fontSize
                        * 3
                        / 2
                        - (int) ((fontSize * 3 / 2) * (lrc.getOffset(time) / (float) lrc
                        .getNextTime(time)));
                Long[] ts = lrc.getAllTimes();
                // 循环绘制每一行歌词，当前播放歌词特殊绘制
                for (Long l : ts) {
                    c.drawText(lrc.get(l), 0, h,
                            lrc.getIndex(l) == cIndex ? lrcPaint : fontPaint);
                    h += fontSize * 3 / 2;
                }
            } catch (Exception e) {
            }
        }
        invalidate();
    }

}