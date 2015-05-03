

/**
 * Created by kuoxin on 4/13/15.
 */
package com.anan.anancooking.client.ws.remote;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by zihsiangsyu on 4/30/15.
 */
public class UploadRecipeRequest extends JsonObjectRequest {

    public UploadRecipeRequest(String hostname, int port, TestVolleyCallbackInterface callback, JSONObject object) {
        super(Method.POST, setURL(hostname, port), object,
                new TestRequestResponseListener(callback)
                , new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port) {
        //System.out.println("http://" + hostname + ":" + port + AnAnNetworkProtocols.WEB_APP_NAME + AnAnNetworkProtocols.URL_PATTERN_TEST + "?id=3");
        return "http://" + hostname + ":" + port + AnAnNetworkProtocols.WEB_APP_NAME + AnAnNetworkProtocols.URL_PATTERN_TEST;

    }

    private static class TestRequestResponseListener implements Response.Listener<JSONObject> {
        TestVolleyCallbackInterface callback;

        public TestRequestResponseListener(TestVolleyCallbackInterface callback){
            this.callback = callback;
        }
        @Override
        public void onResponse(JSONObject response) {
            callback.setText(response.toString());
            //callback.setText("OK we are good");
        }
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<String, String>();
    }

    /*
    @Override
    public String getBodyContentType() {
        return postString !=null?"application/json; charset=utf-8":super.getBodyContentType();
    }
    */
}
