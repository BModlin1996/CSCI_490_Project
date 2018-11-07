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
                Student stu = Student.getInstance();

                if (email.equals(stu.getEmailAdd()) && password.equals(stu.getPassword())) {
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    intent.putExtra("ccuID", stu.getStudId());
                    LoginActivity.this.startActivity(intent);
                }

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
        new GetLoginInfo().execute();

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
    private class GetLoginInfo extends AsyncTask<Void, Void, Void>{

        private String email;
        private String password;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Data is downloading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/mykistner/490project/Login.php");
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject stud = new JSONObject(jsonStr);

                    email = stud.getString("Email");
                    password = stud.getString("Password");

                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server.  Check log for errors", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server.  Check log for errors", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            Student stu = Student.getInstance();
            stu.setEmailAdd(email);
            stu.setPassword(password);



        }
    }

}
