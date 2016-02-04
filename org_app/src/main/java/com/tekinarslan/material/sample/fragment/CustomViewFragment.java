package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.border.BorderConfiguration;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.google.gson.Gson;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.fragment.BaseFragment;
import com.tekinarslan.material.sample.bean.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CustomViewFragment extends BaseFragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static CustomViewFragment newInstance(int position) {
        CustomViewFragment f = new CustomViewFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
//private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        LogUtils.d("CustomViewFragment....................");
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_customui, container, false);
//       context =  getActivity();
        View customUi = rootView.findViewById(R.id.custom_ui);

        File externalCacheDir = context.getExternalCacheDir();
        LogUtils.d( "externalCacheDir==" + externalCacheDir);

        File fileDir = context.getFilesDir();
        LogUtils.d( "filesDir==" + fileDir);

        File ExternalFiles = context.getExternalFilesDir("123");
//        File[] files = context.getExternalFilesDirs("vvvv");
//        File[]  files = context.getExternalFilesDirs("vvvv");
//        for (int i = 0; i < files.length; i++) {
//            LogUtils.d( "files==" + files[i].toString());
//        }
            LogUtils.d("externalCacheDir==" + ExternalFiles);
//        new String();

        getPath2();
        List<String> extSDCardPath = getExtSDCardPath();
        for (int i = 0; i < extSDCardPath.size(); i++) {
            LogUtils.d("sd    " +extSDCardPath.get(i).toString());
        }


        Logger logger = XLog.printers(
                new AndroidPrinter(
                        new BorderConfiguration
                                .Builder()
                                .enable(true)
                                .build()))
                .build();
        logger.d("The message");
        logger.d("The message with argument: age=%s", 18);
        String name ="cdj";
        String age ="18";
        Person person = new Person();
        person.setAge(18);
        person.setName("123");
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);
        LogUtils.d(jsonString);
        logger.json(jsonString);
//        logger.xml(xmlString);
//        logger.method(arg1, arg2, arg3);
//        logger.stack("Here's the call stack");

        init(rootView);


        ImageView glide_iv = (ImageView) rootView.findViewById(R.id.glide_iv);
        Glide.with(this).load("http://www.faxingw.cn/userimg/201208/meinv1.jpg").into(glide_iv);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    LogUtils.d("okhttp~~~~~~~~~~~~~~~~~~~~~~请求");
//                    String response = OkHttpUtils.run("http://www.faxingw.cn/userimg/201208/meinv1.jpg");
//                    LogUtils.d("response   " + response);
//            }
//        }).start();
        return rootView;
    }
    SeekBar myseekbar;
    private void init(View rootView) {
        myseekbar = (SeekBar)rootView.findViewById(R.id.myseekbar);
        myseekbar.setMax(7000);
        myseekbar.setProgress(50);
        myseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                com.apkfuns.logutils.LogUtils.d("progress="+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        Log.d(TAG, "init() called with: " + "rootView = [" + rootView + "]");
        Log.d(TAG, "init() returned: " +getResources().getString(R.string.load) );
    }
    /**
     * 获取外置SD卡路径
     * @return  应该就一条记录或空
     */
    public List<String> getExtSDCardPath()
    {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("sdcard"))
                {
                    String [] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory())
                    {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }
    /**
     * 获取外置存储器的
     * @return
     */
    //获取外置存储卡的根路径，如果没有外置存储卡，则返回null
    public String getPath2() {
        String sdcard_path = null;
        String sd_default = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        if (sd_default.endsWith("/")) {
            sd_default = sd_default.substring(0, sd_default.length() - 1);
        }
        // 得到路径
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure"))
                    continue;
                if (line.contains("asec"))
                    continue;
                if (line.contains("fat") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }
                        sdcard_path = columns[1];
                    }
                } else if (line.contains("fuse") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }
                        sdcard_path = columns[1];
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    LogUtils.d(" 外置  "+sdcard_path);
        return sdcard_path;
    }
//
//   static class OkHttpUtils {
//
//      static OkHttpClient client = new OkHttpClient();
//
//      public static    String run(String url){
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//          Response response = null;
//          try {
//              response = client.newCall(request).execute();
//              return response.body().string();
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//         return  null;
//        }
//    }
//    private void okhttp(){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//    }
}