package com.anan.anancooking.client.ws.remote;

import android.graphics.BitmapFactory;

import com.anan.anancooking.model.RecipeImplementation;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class FetchRecommendastionPreviewsRequest extends JsonObjectRequest{


    public FetchRecommendastionPreviewsRequest(FetchRecommendationPreviewListCallbackInterface callback, String hostname, int port, long id){

        super(Method.GET, setURL(hostname, port, id), null, new FetchRecipeRequestResponseListener(callback), new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port, long id){
        //return "http://"+hostname+":"+port +AnAnNetworkProtocols.WEB_APP_NAME+AnAnNetworkProtocols.URL_PATTERN_FETCH_RECIPE+"?id="+id;
        return "http://"+hostname+":"+port +AnAnNetworkProtocols.WEB_APP_NAME+AnAnNetworkProtocols.URL_PATTERN_FETCH_RECOMMENDATION_LIST;
    }

    private static class FetchRecipeRequestResponseListener implements Response.Listener<JSONObject> {
        FetchRecommendationPreviewListCallbackInterface callback;

        public FetchRecipeRequestResponseListener(FetchRecommendationPreviewListCallbackInterface callback){
            this.callback = callback;
        }
        @Override
        public void onResponse(JSONObject response) {
            //callback.setText(response.toString());
            try {

                //this.callback.setIngredientsText(response.getString("ingredients"));
                Gson gson = new Gson();
                RecipeImplementation recipe = gson.fromJson(response.toString(), RecipeImplementation.class);

                /*
                this.callback.setIngredientsText("Ingredients?");
                //this.callback.setTimeText(response.getString("time"));
                this.callback.setTimeText(""+recipe.getTime());
                //this.callback.setDescriptionText(response.getString("description"));
                this.callback.setDescriptionText(recipe.getDescription());

                this.callback.setPreviewImage(BitmapFactory.decodeByteArray(recipe.getPreviewByteCode(),0,recipe.getPreviewByteCode().length));
                this.callback.setRecipe(recipe);
                */

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, String> getParams(){
        return new HashMap<String, String>();
    }
}
