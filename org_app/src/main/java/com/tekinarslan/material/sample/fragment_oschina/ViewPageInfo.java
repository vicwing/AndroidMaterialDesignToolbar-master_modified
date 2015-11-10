package com.tekinarslan.material.sample.fragment_oschina;

import android.os.Bundle;

public final class ViewPageInfo {

	public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;

    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
    	title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
    }
    public ViewPageInfo(String _title, String _tag, Class<?> _class) {
        title = _title;
        tag = _tag;
        clss = _class;
        args = null;
    }
}