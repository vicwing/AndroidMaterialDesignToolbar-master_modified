package com.tekinarslan.material.sample.fragment;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tekinarslan.material.sample.FloatingActionButton;
import com.tekinarslan.material.sample.ProgressBarCircular;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.http.URLImageGetter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    public static final String TAG = SampleFragment.class.getSimpleName();
    private int position;

    public static SampleFragment newInstance(int position) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.page, container, false);

        ProgressBarCircular progressBarCircular = (ProgressBarCircular) rootView.findViewById(R.id.progress);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabButton);
        fab.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        switch (position) {
            case 0:
                fab.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                break;
            case 1:
                fab.setBackgroundColor(getResources().getColor(R.color.red));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.red));

                break;
            case 2:
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.blue));
                fab.setBackgroundColor(getResources().getColor(R.color.blue));

                break;
            case 3:
                fab.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));

                break;
        }


        textHtmlDemo(rootView);

        return rootView;
    }

    private void textHtmlDemo(View rootView) {
        final TextView content = (TextView) rootView.findViewById(R.id.demo);

        final String html = "<h2>html测试</h2><p font='red'>这是测试内容</p><p><img src='http://www.baidu.com/img/baidu_sylogo1.gif'></p>";
//        tv.post(new Runnable() {
//            @Override
//            public void run() {
//                tv.setText(Html.fromHtml(html, new Html.ImageGetter() {
//                    @Override
//                    public Drawable getDrawable(String source) {
//                        Drawable drawable = httpGetDrawable(source);
//                        return drawable;
//                    }
//                }, null));
//            }
//        });



        content.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效
        URLImageGetter ReviewImgGetter = new URLImageGetter(getActivity(), content);//实例化URLImageGetter类
        content.setText(Html.fromHtml(html, ReviewImgGetter, null));

    }

    public class ProgressBarAsyncTask extends AsyncTask<String, Integer,Drawable> {

        private TextView textView;
        private ProgressBar progressBar;
        public ProgressBarAsyncTask() {
            super();
        }
        public ProgressBarAsyncTask(TextView textView) {
            super();
            this.textView = textView;
        }
        public ProgressBarAsyncTask(TextView textView, ProgressBar progressBar) {
            super();
            this.textView = textView;
            this.progressBar = progressBar;
        }


        /**
         * 这里的Integer参数对应AsyncTask中的第一个参数
         * 这里的String返回值对应AsyncTask的第三个参数
         * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
         * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
         */
        @Override
        protected Drawable doInBackground(String... params) {
//                NetOperator netOperator = new NetOperator();
//                int i = 0;
//                for (i = 10; i <= 100; i+=10) {
//                    netOperator.operator();
//                    publishProgress(i);
//                }
//                return i + params[0].intValue() + "";
            return null;
        }

//
//        /**
//         * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
//         * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
//         */
//        @Override
//        protected void onPostExecute(String result) {
//            textView.setText("异步操作执行结束" + result);
//        }



        //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
        @Override
        protected void onPreExecute() {
//            textView.setText("开始执行异步线程");
        }


        /**
         * 这里的Intege参数对应AsyncTask中的第二个参数
         * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
         * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
//            int vlaue = values[0];
//            progressBar.setProgress(vlaue);
        }
    }

    private Drawable httpGetDrawable(String params) {
        Drawable myDrawable = null;
        try {
            myDrawable = Drawable.createFromStream(new URL(params).openStream(), "baidu_sylogo1.gif");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        return myDrawable;
    }


    class MyRunnable implements Runnable {
        private String source;

        Drawable myDrawable = null;

        MyRunnable(String source) {
            this.source = source;
        }

        @Override
        public void run() {
            try {
                myDrawable = Drawable.createFromStream(new URL(source).openStream(), "baidu_sylogo1.gif");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        }

    }
}