package com.anan.anancooking.client.ws.remote;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class FetchRecipeRequest extends JsonObjectRequest{

    public FetchRecipeRequest(String hostname, int port, long id){

        super(Method.GET, setURL(hostname, port, id), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //mTextView.setText("Response: " + response.toString());
            }
        }, new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port, long id){
        return "http://"+hostname+":"+port +"/"+AnAnNetworkProtocols.URL_PATTERN_FETCH_RECIPE_PREVIEW+"?id="+id;
    }

    @Override
    public Map<String, String> getParams(){
        return new HashMap<String, String>();
    }
}
