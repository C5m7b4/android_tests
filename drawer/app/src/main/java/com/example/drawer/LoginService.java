package com.example.drawer;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginService {
    Context context;
    public static final String BASE_URL = "http://10.0.0.23:5000/api/login";

    public LoginService(Context context)
    {
        this.context = context;
    }

    public interface LoginResponseListener
    {
        void onError(String message);
        void onResponse(BasicResponse response);
    }

    public void Login(String username, String email, LoginResponseListener loginResponseListener)
    {
        String url = BASE_URL + "?username=" + username + "&email=" + email;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BasicResponse rsp = new BasicResponse();
                try {
                    rsp.setError(Integer.valueOf(response.getString("error")));
                    rsp.setSuccess(response.getBoolean("success"));
                    loginResponseListener.onResponse(rsp);
                } catch (JSONException e) {
                    loginResponseListener.onError(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginResponseListener.onError(error.toString());
            }
        });

        SingleInstance.getInstance(context).addToRequestQueue(request);
    }
}
