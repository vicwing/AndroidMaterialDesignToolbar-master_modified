package com.tekinarslan.material.sample.common.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.apkfuns.logutils.LogUtils;
import com.tekinarslan.material.sample.common.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName: JsonObjectDefaultGetRequest
 * @Description: JsonObject Get 请求
 * @author liuyongzheng
 * @date 2014-4-9 上午8:55:50
 */
public class JsonObjectDefaultGetRequest extends Request<JSONObject> {
	private Listener<JSONObject> listener;
	private final static String DEFAULT_PARAMS_ENCODING = "UTF-8";
	private static String TAG = JsonObjectDefaultGetRequest.class.getSimpleName();
	public JsonObjectDefaultGetRequest(String url, Listener<JSONObject> reponseListener, ErrorListener errorListener) {
		super(Method.GET, url, errorListener);
		this.listener = reponseListener;
	}

	public JsonObjectDefaultGetRequest(String url, Map<String, String> params, Listener<JSONObject> reponseListener, ErrorListener errorListener) {
		super(Method.GET, getFinalUrl(url, params), errorListener);
		this.listener = reponseListener;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//			LogUtils.d(TAG, "返回的jsonString====》"+StringEscapeUtils.unescapeJava(jsonString));
			LogUtils.d("jsonString     "+jsonString);
			return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		listener.onResponse(response);
	}

	/**
	 * @Description:[获取最终的URL]
	 * @param url
	 * @param params
	 * @return
	 */
	private static String getFinalUrl(String url, Map<String, String> params) {
		StringBuilder encodedParams = new StringBuilder();
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				encodedParams.append(URLEncoder.encode(entry.getKey(), DEFAULT_PARAMS_ENCODING));
				encodedParams.append('=');
				if (!StringUtils.isEmpty(entry.getValue())) {
					encodedParams.append(URLEncoder.encode(entry.getValue(), DEFAULT_PARAMS_ENCODING));
				}
				encodedParams.append('&');
			}
			String strParams = encodedParams.toString();
			if (!StringUtils.isEmpty(strParams)) {
				strParams = strParams.substring(0, strParams.length() - 1);
				url = url + "&" + strParams;
			}
			LogUtils.d(TAG, "请求url====>"+url);
			return url;
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException("Encoding not supported: " + DEFAULT_PARAMS_ENCODING, uee);
		}
	}
}
