package com.anan.anancooking.client.ws.remote;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipeInterface;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class FetchRecipeRequest extends JsonObjectRequest{


    public FetchRecipeRequest(FetchRecipeRequestCallbackInterface callback, String hostname, int port, long id){

        super(Method.GET, setURL(hostname, port, id), null, new FetchRecipeRequestResponseListener(callback), new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port, long id){
        return "http://"+hostname+":"+port +AnAnNetworkProtocols.WEB_APP_NAME+AnAnNetworkProtocols.URL_PATTERN_FETCH_RECIPE+"?id="+id;
    }

    private static class FetchRecipeRequestResponseListener implements Response.Listener<JSONObject> {
        FetchRecipeRequestCallbackInterface callback;

        public FetchRecipeRequestResponseListener(FetchRecipeRequestCallbackInterface callback){
            this.callback = callback;
        }
        @Override
        public void onResponse(JSONObject response) {
            //callback.setText(response.toString());
            try {

                //this.callback.setIngredientsText(response.getString("ingredients"));
                Gson gson = new Gson();
                RecipeImplementation recipe = gson.fromJson(response.toString(), RecipeImplementation.class);
                if(recipe.getDescription().split("19920829kuoxin").length == 2) {
                    String tmp_ingredients = recipe.getDescription().split("19920829kuoxin")[1];
                    if(tmp_ingredients.length()>2)
                        recipe.setIngredients(tmp_ingredients.substring(1,tmp_ingredients.length()-1));
                    recipe.setDescription(recipe.getDescription().split("19920829kuoxin")[0]);
                }
                this.callback.setIngredientsText(recipe.getIngredients());
                //this.callback.setTimeText(response.getString("time"));
                this.callback.setTimeText(""+recipe.getTime());

                System.out.println("recipe name is "+ recipe.getName());
                //this.callback.setDescriptionText(response.getString("description"));
                this.callback.setDescriptionText(recipe.getDescription());

                this.callback.setPreviewImage(BitmapFactory.decodeByteArray(recipe.getPreviewByteCode(),0,recipe.getPreviewByteCode().length));

                this.callback.setRecipe(recipe);

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
