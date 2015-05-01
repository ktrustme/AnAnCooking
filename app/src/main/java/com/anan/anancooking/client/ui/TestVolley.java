package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.client.ws.remote.TestVolleyCallbackInterface;
import com.anan.anancooking.client.ws.remote.UploadRecipeRequest;
import com.anan.anancooking.model.Step;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestVolley extends Activity implements TestVolleyCallbackInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_volley);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_volley, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendRequest(View view) {
        final TextView mTextView = (TextView) findViewById(R.id.hellovolley);
        //RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queue = MySingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();
        String url = "http://www.google.com";
        //url = "http://kuoxindeMBP.wv.cc.cmu.edu:1234";

        //url="http:zihs-mbp.wv.cc.cmu.edu:8080";
        /*
        url = "Zihs-MacBook-Pro.local:8080";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        */
        Step temp = new Step();
        temp.setDescription("kuokuo");
        temp.setRecipeID("ssss");
        temp.setStepID(1001);

        Step temp2 = new Step();
        temp2.setDescription("aaa");
        temp2.setRecipeID("ksksks");
        temp2.setStepID(1002);

        Map<String, String> postParam= new HashMap<String, String>();
        ArrayList<Step> a = new ArrayList<Step>();
        a.add(temp);
        a.add(temp2);

        ArrayList<String> toServer = convertList(a);

        JSONArray jsArray = new JSONArray(toServer);

        JSONObject object = null;






        postParam.put("un", temp.toString());
        postParam.put("p", "somepasswordhere");
        //System.out.println(temp.toString());




        object = new JSONObject(postParam);

        /*
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(postParam), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView.setText("Response: " + response.toString());
                    }
                }, new ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mTextView.setText(error.toString());
                    }
                });
        // Add the request to the RequestQueue.
        //queue.add(jsObjRequest);*/
        //queue.stop();
        //queue.add(new UploadRecipeRequest(AnAnNetworkProtocols.HOST_NAME,AnAnNetworkProtocols.PORT_NUM, jsArray.toString(),this));

    }

    @Override
    public void setText(String str) {
        TextView mTextView = (TextView) findViewById(R.id.hellovolley);
        mTextView.setText(str);
        //mTextView.setText("Why??!?!");
    }

    public ArrayList<String> convertList(ArrayList<Step> steps){
        int n = steps.size();
        ArrayList<String> ret = new ArrayList<String>();
        for(int i=0;i<n;i++){
            ret.add(i,steps.get(i).toString());
        }
        return ret;
    }
}
