package com.games.baileymodlin.csci_490_project;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.android.volley.Response;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email= (EditText) findViewById(R.id.loginEmail);
        final EditText pass= (EditText) findViewById(R.id.loginPASS);

        final Button blogin= (Button) findViewById(R.id.blogin);
        registerLink.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaillogin = email.getText().toString();
                final String password = pass.getText().toString();
                Response.Listener<String> responseListener= new Response.Listener<String>(){


                };

                LoginRequest loginRequest = new LoginRequest(emaillogin,password,);
            }
        });{

                };

            }
        }
    }

}