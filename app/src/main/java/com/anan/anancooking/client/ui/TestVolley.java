package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DownloadManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.FetchRecipeRequest;
import com.anan.anancooking.client.ws.remote.TestRequest;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.client.ws.remote.ResponseErrorListener;
import com.anan.anancooking.client.ws.remote.TestVolleyCallbackInterface;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

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
        url = "http://kuoxindeMBP.wv.cc.cmu.edu:1234";
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
        Map<String, Object> postParam= new HashMap<String, Object>();
        postParam.put("un", "xyz@gmail.com");
        postParam.put("p", "somepasswordhere");
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
        //queue.add(jsObjRequest);
        queue.add(new TestRequest(AnAnNetworkProtocols.HOST_NAME,AnAnNetworkProtocols.PORT_NUM,11122,this));
    }

    @Override
    public void setText(String str) {
        TextView mTextView = (TextView) findViewById(R.id.hellovolley);
        mTextView.setText(str);
    }
}
