package com.example.restapi3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_getCityId, btn_getWeacherByCityId, btn_getWeatherByCityName;
    EditText ed_datainput;
    ListView lv_weatherReports;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getCityId = findViewById(R.id.btn_getCityId);
        btn_getWeacherByCityId = findViewById(R.id.btn_getWeacherByCityId);
        btn_getWeatherByCityName = findViewById(R.id.btn_getWeatherByCityName);
        textView = findViewById(R.id.textView);

        ed_datainput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

        final UsersService usersService = new UsersService(MainActivity.this);

        btn_getCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( TextUtils.isEmpty(ed_datainput.getText()))
                {
                    Toast.makeText(MainActivity.this, "Please enter a userid", Toast.LENGTH_LONG).show();
                    return;
                }
                int userid = Integer.parseInt(ed_datainput.getText().toString());
                usersService.getFirstName(userid, new UsersService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        textView.setText("error: " + message);
                    }

                    @Override
                    public void onResponse(String userFirstname) {
                        textView.setText("firstname: " + userFirstname);
                    }
                });
            }
        });

        // click listeners
        btn_getWeacherByCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( TextUtils.isEmpty(ed_datainput.getText()))
                {
                    Toast.makeText(MainActivity.this, "Please enter a userid", Toast.LENGTH_LONG).show();
                    return;
                }
                int userid = Integer.parseInt(ed_datainput.getText().toString());
                usersService.getUserHours(userid, new UsersService.HourResponse() {
                    @Override
                    public void onError(String message) {
                        textView.setText(message.toString());
                    }

                    @Override
                    public void onResponse(List<Hour> hours) {
                       // make a list view
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, hours);
                        lv_weatherReports.setAdapter(arrayAdapter);
                    }
                });
            }
        });



        btn_getWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You typed " + ed_datainput.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}