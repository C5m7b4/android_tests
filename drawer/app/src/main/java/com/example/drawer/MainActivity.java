package com.example.drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView et_username, et_email;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        btnLogin = findViewById(R.id.btnLogin);

        final LoginService loginService = new LoginService(MainActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( TextUtils.isEmpty(et_username.getText()))
                {
                    Toast.makeText(MainActivity.this, "Please enter a username", Toast.LENGTH_LONG).show();
                    return;
                }
                if ( TextUtils.isEmpty(et_email.getText()))
                {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                String username = et_username.getText().toString();
                String email = et_email.getText().toString();

                loginService.Login(username, email, new LoginService.LoginResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(BasicResponse response) {
                        if ( response.getError() == 0)
                        {
                            Toast.makeText(MainActivity.this, "You can log in", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);

                            intent.putExtra("name", username);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Could not log you in", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}