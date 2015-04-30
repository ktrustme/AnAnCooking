package com.anan.anancooking.client.ws.remote;

import com.anan.anancooking.client.ui.TestVolley;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class TestRequest extends JsonObjectRequest {

    public TestRequest(String hostname, int port, long id, TestVolleyCallbackInterface callback) {
        super(Method.GET, setURL(hostname, port, id), null,
                new TestRequestResponseListener(callback), new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port, long id) {
        return "http://" + hostname + ":" + port + AnAnNetworkProtocols.WEB_APP_NAME + AnAnNetworkProtocols.URL_PATTERN_TEST + "?id=" + id;
    }

    private static class TestRequestResponseListener implements Response.Listener<JSONObject> {
        TestVolleyCallbackInterface callback;

        public TestRequestResponseListener(TestVolleyCallbackInterface callback){
            this.callback = callback;
        }
        @Override
        public void onResponse(JSONObject response) {
            callback.setText(response.toString());
            callback.setText("OK we are good");
        }
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<String, String>();
    }
}
