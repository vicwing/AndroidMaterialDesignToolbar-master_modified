package com.tekinarslan.material.sample.customui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cdj on 2015/7/21.
 */
public class SongLyric {
    // 歌名
    private String title = "";
    // 歌手名
    private String artist = "";
    // 专辑名
    private String album = "";
    // 偏移时间
    private long offset = 0;
    // 最大时间
    private long maxTime = 0;
    // 歌词内容
    private Map<Long, String> lrcs = new HashMap<Long, String>();
    // 验证是否通过
    private boolean valid = false;

    public SongLyric(String url) {
        File file = new File(url);
        if (file.exists()) {
            try {
                // 构建读取器
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "gbk"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    dealLine(line);
                }
                valid = true;
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public boolean isValid() {
        return this.valid;
    }

    public long getMaxTime() {
        return this.maxTime;
    }

    public void setMaxTime(long time) {
        this.maxTime = time;
    }

    /**
     * 获取该时间应当显示的歌词
     *
     * @param ls
     * @return
     */
    public String get(long ls) {
        long time = ls + offset;
        Long curr = -1l;
        for (Long l : lrcs.keySet()) {
            curr = l > time ? curr : l < curr ? curr : l;
        }
        return lrcs.get(curr);
    }

    /**
     * 获取该时间所要显示的歌词初始时间的索引
     *
     * @param ls
     * @return
     */
    public int getIndex(long ls) {
        Long[] ts = getAllTimes();
        for (int i = 0; i < ts.length - 1; i++) {
            if (ls + offset >= ts[i] && ls + offset < ts[i + 1]) {
                return i;
            }
        }
        return ts.length - 1;
    }

    /**
     * 获取该时间与歌词初始时间差值
     *
     * @param ls
     * @return
     */
    public int getOffset(long ls) {
        Long[] ts = getAllTimes();
        int index = getIndex(ls);
        if (index < ts.length && index >= 0) {
            return (int) (ls + offset - ts[index]);
        }
        return 0;
    }

    /**
     * 获取该时间段播放的歌词共播放时间
     *
     * @param ls
     * @return
     */
    public int getNextTime(long ls) {
        Long[] ts = getAllTimes();
        int index = getIndex(ls);
        if (index < ts.length - 1) {
            return (int) (ts[index + 1] - ts[index]);
        }
        return 0;
    }

    /**
     * 处理歌词行
     *
     * @param line
     */
    private void dealLine(String line) {
        if (line != null && !line.equals("")) {
            if (line.startsWith("[ti:")) {// 标题
                title = line.substring(4, line.length() - 1);
            } else if (line.startsWith("[ar:")) {// 歌手
                artist = line.substring(4, line.length() - 1);
            } else if (line.startsWith("[al:")) {// 专辑
                album = line.substring(4, line.length() - 1);
            } else if (line.startsWith("[offset:")) {// 专辑
                offset = Long.parseLong(line.substring(8, line.length() - 1));
            } else {
                // 该行歌词内容
                Pattern ptn = Pattern.compile("\\[(\\d{2}:\\d{2}\\.\\d{2})\\]");
                Matcher mth = ptn.matcher(line);
                while (mth.find()) {
                    // 得到时间点
                    long time = strToLong(mth.group(1));
                    // 得到时间点后的内容
                    String[] str = ptn.split(line);
                    String lrc = str.length > 0 ? str[str.length - 1] : "";
                    lrcs.put(time, lrc);
                    maxTime = maxTime > time ? maxTime : time;
                }
            }
        }
    }

    /**
     * 将00:00.00格式的歌词时间转换为long
     *
     * @param timeStr
     * @return
     */
    public static long strToLong(String timeStr) {
        String[] s = timeStr.split(":");
        int min = Integer.parseInt(s[0]);
        String[] ss = s[1].split("\\.");
        int sec = Integer.parseInt(ss[0]);
        int mill = Integer.parseInt(ss[1]);
        return min * 60 * 1000 + sec * 1000 + mill * 10;
    }

    /**
     * 处理毫秒数，以00:00的方式返回
     *
     * @param ts
     * @return
     */
    public static String longToString(long ts) {
        int time = (int) ts / 1000;
        int ms = time % 60;
        int ss = time / 60;
        ss = ss > 99 ? 99 : ss;
        StringBuffer str = new StringBuffer();
        str.append(ss < 10 ? "0" + ss + ":" : ss + ":");
        str.append(ms < 10 ? "0" + ms : ms + "");
        return str.toString();
    }

    /**
     * 获取顺序时间数组对象
     *
     * @return
     */
    public Long[] getAllTimes() {
        Long[] ts = new Long[lrcs.size()];
        int index = 0;
        for (Long l : lrcs.keySet()) {
            ts[index++] = l;
        }
        for (int i = 0; i < ts.length - 1; i++) {
            for (int j = i; j < ts.length; j++) {
                if (ts[i] > ts[j]) {
                    Long tmp = ts[i];
                    ts[i] = ts[j];
                    ts[j] = tmp;
                }
            }
        }
        return ts;
    }

}