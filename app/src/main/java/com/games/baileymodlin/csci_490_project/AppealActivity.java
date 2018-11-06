package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AppealActivity extends AppCompatActivity {

    private String TAG = AppealActivity.class.getSimpleName();
    private Intent intent;
    private Spinner reasonAppeal;
    private EditText fNameEdit, lNameEdit, addressEdit, cityEdit, stateEdit, zipCodeEdit, emailEdit, phoneEdit;
    private Button submitButton;
    private Bundle bundle;
    private Student student;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);

        student = Student.getInstance();

        fNameEdit = findViewById(R.id.firstNameEdit);
        lNameEdit = findViewById(R.id.lastNameEdit);
        addressEdit = findViewById(R.id.addressEditText);
        cityEdit = findViewById(R.id.cityEditText);
        stateEdit = findViewById(R.id.stateEditText);
        zipCodeEdit = findViewById(R.id.zipCodeEdit);
        emailEdit = findViewById(R.id.emailEdit);
        phoneEdit = findViewById(R.id.phoneEdit);

        reasonAppeal = findViewById(R.id.reasonSpinner);
        submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(0);
            }
        });
        new GetAppealInfo().execute();
    }



    private void changeActivity(int id){
        switch (id){
            case 0:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class GetAppealInfo extends AsyncTask<Void, Void, Void> {

        private String fName;
        private String lName;
        private String address;
        private String city;
        private String state;
        private String zip;
        private String email;
        private String phone;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(AppealActivity.this, "Data is downloading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/kjyoungr/csci225e1fa17/getAppealInfo.php");
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONArray studArray = new JSONArray(jsonStr);
                    JSONObject stud = studArray.getJSONObject(0);
                    fName = stud.getString("Fname");
                    lName = stud.getString("Lname");
                    address = stud.getString("Address");
                    city = stud.getString("City");
                    state = stud.getString("State");
                    zip = stud.getString("Zip");
                    email = stud.getString("Email");
                    phone = stud.getString("Phone");


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
            fNameEdit.setText(fName);
            lNameEdit.setText(lName);
            addressEdit.setText(address);
            cityEdit.setText(city);
            stateEdit.setText(state);
            zipCodeEdit.setText(zip);
            emailEdit.setText(email);
            phoneEdit.setText(phone);
        }
    }




}