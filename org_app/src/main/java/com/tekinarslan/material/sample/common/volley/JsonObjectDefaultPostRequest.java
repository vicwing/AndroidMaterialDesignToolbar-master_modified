	package com.tekinarslan.material.sample.common.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @ClassName: JsonObjectDefaultPostRequest
 * @Description: JsonObject Post 请求
 * @author liuyongzheng
 * @date 2014-4-9 上午8:54:34
 */
public class JsonObjectDefaultPostRequest extends Request<JSONObject> {
	private Listener<JSONObject> listener;
	private Map<String, String> params;

	public JsonObjectDefaultPostRequest(String url, Map params, Listener<JSONObject> reponseListener, ErrorListener errorListener) {
		super(Request.Method.POST, url, errorListener);
		this.params = params;
		this.listener = reponseListener;

	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
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

}