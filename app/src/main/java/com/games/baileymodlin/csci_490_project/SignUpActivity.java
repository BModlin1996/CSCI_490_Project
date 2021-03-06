<<<<<<< HEAD
package com.games.baileymodlin.csci_490_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity{

    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, ccuIdEdit, emailAddEdit, passwordEdit, checkPass;
    private Button signupButton;
    private Student student;
    private Bundle bundle;
    ArrayList<HashMap<String, String>> studList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Initialize form components
        student = Student.getInstance();
        signupButton = findViewById(R.id.bsignup);
        firstNameEdit = findViewById(R.id.Fname);
        lastNameEdit = findViewById(R.id.Lname);
        ccuIdEdit = findViewById(R.id.CCUID);
        emailAddEdit = findViewById(R.id.emailReg);
        passwordEdit = findViewById(R.id.regPASS);
        checkPass = findViewById(R.id.checkPass);
        bundle = savedInstanceState;


        /**
         * submitButton.setOnClickListener() - The onClick listener for the submit button.
         * Creates View.OnClickListener(){}
         */
        signupButton.setOnClickListener(new View.OnClickListener() {

            /**
             * When pressed the onClick listener will get and verify the credit card information provided by the user.
             * If the provided information is correct the payment process will begin followed by an activity change.
             * Otherwise the user will be prompted that they have entered incorrect information along with instructions to fix it.
             * @param v - This activities View
             */
            @Override
            public void onClick(View v) {
                String fname = firstNameEdit.getText().toString();
                String lname = lastNameEdit.getText().toString();
                String ccuid = ccuIdEdit.getText().toString();
                String email = emailAddEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String checkPassword = checkPass.getText().toString();

                if(password.equals(checkPassword)){
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if(success){
                                    int ccuID=jsonResponse.getInt("ccuId");
                                    String name=jsonResponse.getString("fname");

                                    Intent intent = new Intent(SignUpActivity.this, UserActivity.class);
                                    intent.putExtra("ccuID", ccuID);
                                    intent.putExtra("fname", name);

                                }else{
                                    AlertDialog.Builder builder= new AlertDialog.Builder(SignUpActivity.this);
                                    builder.setMessage("Sign Up Fail")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(fname,lname,ccuid,email,password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                    queue.add(registerRequest);
                    changeActivity(0);

                }else{
                    AlertDialog.Builder builder= new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("Password does not match")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }

        });
    }

    /**
     * changeActivity(int) - Selects which activity to change to based off of the provided integer
     * @param id - The integer provided to select the next activity
     */
    private void changeActivity(int id){
        switch (id) {
            case 0:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
=======
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity{

    private String TAG = CheckOutForm.class.getSimpleName();
    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, ccuidEdit, emailAddEdit, passwordEdit, checkPass;
    private Button signupButton, cancelButton;
    private Student student;
    private Bundle bundle;
    ArrayList<HashMap<String, String>> studList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Initialize form components
        student = Student.getInstance();
        signupButton = findViewById(R.id.bsignup);
        cancelButton = findViewById(R.id.bcancel);
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        ccuidEdit = findViewById(R.id.CCUID);
        emailAddEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.loginPASS);
        checkPass = findViewById(R.id.checkPass);
        bundle = savedInstanceState;


        /**
         * submitButton.setOnClickListener() - The onClick listener for the submit button.
         * Creates View.OnClickListener(){}
         */
        signupButton.setOnClickListener(new View.OnClickListener() {

            /**
             * When pressed the onClick listener will get and verify the credit card information provided by the user.
             * If the provided information is correct the payment process will begin followed by an activity change.
             * Otherwise the user will be prompted that they have entered incorrect information along with instructions to fix it.
             * @param v - This activities View
             */
            @Override
            public void onClick(View v) {
                if(passwordEdit==checkPass){
                final String fname = firstNameEdit.getText().toString();
                final String lname = lastNameEdit.getText().toString();
                final String ccuid = ccuidEdit.getText().toString();
                final String email = emailAddEdit.getText().toString();
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

                                Intent intent = new Intent(SignUpActivity.this, UserActivity.class);
                                intent.putExtra("ccuID", ccuID);
                                intent.putExtra("fname", name);

                                SignUpActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("Sign Up Fail")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(fname,lname,ccuid,email,password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                    queue.add(registerRequest);

            }else{
                    AlertDialog.Builder builder= new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("Password does not match")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }

        });

        /**
         * cancelButton.setOnClickListener - The onClick listener for the cancel button.
         * Creates View.OnClickListener(){}
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {
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
     * @param id - The integer provided to select the next activity
     */
    private void changeActivity(int id){
        switch (id){
            case 0:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }
    }


    }

>>>>>>> origin/master
