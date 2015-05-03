package com.anan.anancooking.client.ws.remote;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by zihsiangsyu on 5/2/15.
 */
public class GsonRequest<T> extends Request<T> {
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private Map<String, String> postParams;
    private String postString = null;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url
     *            URL of the request to make
     * @param clazz
     *            Relevant class object, for Gson's reflection
     * @param headers
     *            Map of request headers
     */
    public GsonRequest(int method, String url, Class<T> clazz,
                       Map<String, String> headers, Map<String, Object> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

        if (method == Request.Method.POST && params != null && params.size() > 0) {
            setRetryPolicy(new DefaultRetryPolicy(12000, 0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            postString = new GsonBuilder().create().toJson(params);
        }

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();

    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        return postString != null ? postString.getBytes(Charset
                .forName("UTF-8")) : super.getBody();
    }

    @Override
    public String getBodyContentType() {
        return postString !=null?"application/json; charset=utf-8":super.getBodyContentType();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.i("response", json);

            T responses = new GsonBuilder().create().fromJson(json, clazz);
            return Response.success(responses,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));

        }
    }
}
