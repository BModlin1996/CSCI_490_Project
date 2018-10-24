package com.games.baileymodlin.csci_490_project;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    private String TAG = CheckOutForm.class.getSimpleName();
    private String billAdd, nameOnCard, cardNumber, cardSecure, expireDate;
    private Intent intent;
    private EditText emailEdit, passwordEdit;
    private Button loginButton, registerButton;
    private Student student;
    private Bundle bundle;
    ArrayList<HashMap<String, String>> studList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize form components
        student = Student.getInstance();
        loginButton = findViewById(R.id.blogin);
        registerButton = findViewById(R.id.bsignup);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.lastNameEdit);
        bundle = savedInstanceState;


        /**
         * submitButton.setOnClickListener() - The onClick listener for the submit button.
         * Creates View.OnClickListener(){}
         */
        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * When pressed the onClick listener will get and verify the credit card information provided by the user.
             * If the provided information is correct the payment process will begin followed by an activity change.
             * Otherwise the user will be prompted that they have entered incorrect information along with instructions to fix it.
             * @param v - This activities View
             */
            @Override
            public void onClick(View v) {
                final String email = emailEdit.getText().toString();
                final String password = passwordEdit.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                int ccuID=jsonResponse.getInt("ccuId");
                                String name=jsonResponse.getString("fname");

                                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                intent.putExtra("ccuID", ccuID);
                                intent.putExtra("fname", name);

                            }else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Fail")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);

            }
        });

        /**
         * cancelButton.setOnClickListener - The onClick listener for the cancel button.
         * Creates View.OnClickListener(){}
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * When pressed the user will be prompted that they are about to cancel the payment process.
             * If the user agrees to cancel they will be redirected to the previous activity.
             * Otherwise they will be returned to the CheckOutActivity.
             * @param v
             */
            @Override
            public void onClick(View v) {

                // Dialog dialogBox = new DialogBox().onCreateDialog(bundle);
                // dialogBox.show();
                //Return to previous activity
                changeActivity(0);
            }
        });

    }

    /**
     * changeActivity(int) - Selects which activity to change to based off of the provided integer
     *
     * @param id - The integer provided to select the next activity
     */
    private void changeActivity(int id) {
        switch (id) {
            case 0:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }
    }
}