package com.anan.anancooking.client.ws.remote;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class FetchRecommendastionPreviewsRequest extends JsonArrayRequest {


    public FetchRecommendastionPreviewsRequest(FetchRecommendationPreviewListCallbackInterface callback, String hostname, int port) {

        super(Method.GET, setURL(hostname, port), null, new FetchRecommendationPreviewListRequestResponseListener(callback), new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port) {

        return "http://" + hostname + ":" + port + AnAnNetworkProtocols.WEB_APP_NAME + AnAnNetworkProtocols.URL_PATTERN_FETCH_RECOMMENDATION_LIST;
    }

    private static class FetchRecommendationPreviewListRequestResponseListener implements Response.Listener<JSONArray> {

        FetchRecommendationPreviewListCallbackInterface callback;

        public FetchRecommendationPreviewListRequestResponseListener(FetchRecommendationPreviewListCallbackInterface callback) {
            this.callback = callback;

        }

        @Override
        public void onResponse(JSONArray response) {
            //callback.setText(response.toString());

            try {
                //this.callback.setIngredientsText(response.getString("ingredients"));
                Gson gson = new Gson();
                //RecipeImplementation recipe = gson.fromJson(response.toString(), RecipeImplementation.class);

                this.callback.setRecommendationPreviewList(
                        (List<RecipePreviewImplementation>) gson.fromJson(response.toString(),
                                new TypeToken<List<RecipePreviewImplementation>>() {
                                }.getType()));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<String, String>();
    }


}
