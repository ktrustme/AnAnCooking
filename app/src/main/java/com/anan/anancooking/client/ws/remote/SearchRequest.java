package com.anan.anancooking.client.ws.remote;

import com.anan.anancooking.model.RecipePreviewImplementation;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kuoxin on 4/13/15.
 */
public class SearchRequest extends JsonArrayRequest{


    public SearchRequest(SearchCallbackInterface callback, String hostname, int port, String ingredients){

        super(Method.GET, setURL(hostname, port, ingredients), null, new SearchRequestResponseListener(callback), new ResponseErrorListener());
    }

    private static String setURL(String hostname, int port, String ingredients){
        //return "http://"+hostname+":"+port +AnAnNetworkProtocols.WEB_APP_NAME+AnAnNetworkProtocols.URL_PATTERN_FETCH_RECIPE+"?id="+id;
        return "http://"+hostname+":"+port +AnAnNetworkProtocols.WEB_APP_NAME+AnAnNetworkProtocols.URL_PATTERN_SEARCH+"?ingredients="+ingredients;
    }

    private static class SearchRequestResponseListener implements Response.Listener<JSONArray> {

        SearchCallbackInterface callback;

        public SearchRequestResponseListener(SearchCallbackInterface callback){
            this.callback = callback;

        }
        @Override
        public void onResponse(JSONArray response) {
            //callback.setText(response.toString());

            try {
                //this.callback.setIngredientsText(response.getString("ingredients"));
                Gson gson = new Gson();
                //RecipeImplementation recipe = gson.fromJson(response.toString(), RecipeImplementation.class);
                this.callback.displayDebug();
                this.callback.setSearchResultList(
                        (List<RecipePreviewImplementation>) gson.fromJson(response.toString(),
                                new TypeToken<List<RecipePreviewImplementation>>() {
                                }.getType() ));
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
