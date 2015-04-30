package com.anan.anancooking.client.ws.remote;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by kuoxin on 4/29/15.
 */
public class ResponseErrorListener implements Response.ErrorListener {
    private VolleyError error;
    @Override
    public void onErrorResponse(VolleyError error) {
        this.error = error;
    }

    public String getErrorMessage(){
        return error.toString();
    }
}
