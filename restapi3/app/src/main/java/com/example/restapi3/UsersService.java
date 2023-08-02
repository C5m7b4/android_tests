package com.example.restapi3;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersService {

    public static final String USERS_API = "http://10.0.0.23:5000/api/users/";
    Context context;
    String firstname;

    public UsersService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String userFirstname);
    }

    public void getFirstName(Integer userid, VolleyResponseListener volleyResponseListener)
    {
        String url = USERS_API + "userid?id=" + userid;
        firstname = "";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String error = response.getString("error");
                    String success = response.getString("success");
                    //JSONArray people = response.getJSONArray("users");
                    JSONObject person = response.getJSONObject("user");
                    firstname = person.getString("firstname");
                    //Toast.makeText(context, firstname, Toast.LENGTH_LONG).show();
                    volleyResponseListener.onResponse(firstname);
                } catch(JSONException e){
                    e.printStackTrace();
                    volleyResponseListener.onError("something is wrong mans");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "something went wrong", Toast.LENGTH_LONG).show();
                volleyResponseListener.onError("something went wrong mans!!!!!");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);

       // return firstname;
    }

    public interface HourResponse{
        void onError(String message);
        void onResponse(List<Hour> hours);
    }

    public void getUserHours(Integer id, HourResponse hourResponse)
    {
        String url = USERS_API + "hours?userid=" + id;
        List<Hour> usersHours = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String error = response.getString("error");
                    String success = response.getString("success");
                    JSONArray hours = response.getJSONArray("hours");


                    for(int i = 0; i < hours.length(); i++)
                    {
                        Hour hour = new Hour();
                        JSONObject one_day_hours = (JSONObject) hours.get(i);
                        hour.setId(one_day_hours.getInt("id"));
                        hour.setUserid(one_day_hours.getInt("userId"));
                        hour.setWorkDate(one_day_hours.getString("workDate"));
                        hour.setStartTime(one_day_hours.getString("startTime"));
                        hour.setEndTime(one_day_hours.getString("endTime"));
                        hour.setTotalHours((float) one_day_hours.getLong("totalHours"));

                        usersHours.add(hour);
                    }


                    hourResponse.onResponse(usersHours);
                } catch (JSONException e) {
                    hourResponse.onError(e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hourResponse.onError(error.toString());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
