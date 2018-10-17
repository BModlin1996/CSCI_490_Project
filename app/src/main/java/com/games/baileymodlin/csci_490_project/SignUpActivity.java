package com.games.baileymodlin.csci_490_project;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity{
    protected void onCreate( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText email= (EditText) findViewById(R.id.email);
        final EditText fname= (EditText) findViewById(R.id.Fname);
        final EditText lname= (EditText) findViewById(R.id.Lname);
        final EditText ccuid= (EditText) findViewById(R.id.CCUID);
        final EditText pass= (EditText) findViewById(R.id.loginPASS);

        final Button bsignup= (Button) findViewById(R.id.bsignup);
        final Button bcancel= (Button) findViewById(R.id.bcancel);


        bsignup.setOnClickListener(new View.OnClickListener()){
            @Override
                    public void onClick(View v){
                final String fname = Fname.getText().toString();
                final String lname = Lname.getText().toString();
                final String email = email.getText().toString();
                final String ccuid = CCUID.getText().toString();
                final String pass = loginPASS.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(SignUpActivityActivity.this, LoginActivity.class);
                                SignUpActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(SignupActivity.this);
                                builder.setMessage("Register Fail")
                                        .setNegativeButton("Retry", null);
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(fname,lname,ccuid,email,pass);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(registerRequest);

                RegisterRequest registerRequest = new RegisterRequest(fname,lname,email,ccuid,pass, )
            }
        }
    }

}
