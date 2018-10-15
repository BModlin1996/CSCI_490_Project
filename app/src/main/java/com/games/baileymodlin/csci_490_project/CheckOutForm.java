/**
 * CheckOutForm - This class collects the data used to make payment on the ticket
 * @author Bailey Modlin
 * @version 1.0
 * @since  8/28/2018
 * Last updated - 10/01/2018
 */
package com.games.baileymodlin.csci_490_project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;


public class CheckOutForm extends AppCompatActivity{

    private String TAG = CheckOutForm.class.getSimpleName();
    private String  nameOnCard, cardNumber, cardSecure, expireDate;
    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, emailAddEdit, billAddEdit, nameOnCardEdit, cardNumberEdit, cardSecureEdit, expireDateEdit;
    private Button submitButton, cancelButton;
    private boolean valid = true;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);
        //Initialize form components
        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        emailAddEdit = findViewById(R.id.emailEdit);
        billAddEdit = findViewById(R.id.billAddressEdit);
        nameOnCardEdit = findViewById(R.id.cardNameEdit);
        cardNumberEdit = findViewById(R.id.cardNumEdit);
        cardSecureEdit = findViewById(R.id.cardSecurityCodeEdit);
        expireDateEdit = findViewById(R.id.expireDateEdit);

        new GetBillInfo().execute();

        /**
         * submitButton.setOnClickListener() - The onClick listener for the submit button.
         * Creates View.OnClickListener(){}
         */
        submitButton.setOnClickListener(new View.OnClickListener() {

            /**
             * When pressed the onClick listener will get and verify the credit card information provided by the user.
             * If the provided information is correct the payment process will begin followed by an activity change.
             * Otherwise the user will be prompted that they have entered incorrect information along with instructions to fix it.
             * @param v - This activities View
             */
            @Override
            public void onClick(View v) {

                //Assign form objects to string
                nameOnCard = nameOnCardEdit.getText().toString();
                cardNumber = cardNumberEdit.getText().toString();
                cardSecure = cardSecureEdit.getText().toString();
                expireDate = expireDateEdit.getText().toString();

                //Validate CardNumber
                CardVerify verify = CardVerify.getInstance();

                //Verify name on card is valid
                if(!verify.verifyName(nameOnCard)){
                    valid = false;
                    nameOnCardEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information name invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    nameOnCardEdit.setTextColor(Color.WHITE);
                }

                //Verify card number is valid
                if(verify.verifyCardNum(cardNumber)){
                    valid = false;
                    cardNumberEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information card number invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    cardNumberEdit.setTextColor(Color.WHITE);
                }

                //Verify expiration date is valid
                if(verify.verifyDate(expireDate)){
                    valid = false;
                    expireDateEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information expiration date invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    expireDateEdit.setTextColor(Color.WHITE);
                }

                if(valid){
                    changeActivity(1);
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
                intent = new Intent(this, BillInfoActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }
    }

    private class GetBillInfo extends AsyncTask<Void, Void, Void>{

        private String fName, lName, email, addr, city, state, zip;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(CheckOutForm.this, "Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/bcmodlin/CSCI490/GetBillInfo.php");
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray studArray = new JSONArray(jsonStr);
                    JSONObject stud = studArray.getJSONObject(0);
                    fName = stud.getString("Fname");
                    lName = stud.getString("Lname");
                    email = stud.getString("Email");
                    addr = stud.getString("Address");
                    city = stud.getString("City");
                    state = stud.getString("State");
                    zip = stud.getString("Zip");

                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "JSON parsing error.  Check log for errors", Toast.LENGTH_LONG).show();
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
            Student student = Student.getInstance();

            student.setfName(fName);
            student.setlName(lName);
            student.setEmailAdd(email);
            student.setMailAdd(addr);
            student.setCity(city);
            student.setState(state);
            student.setZip(zip);

            firstNameEdit.setText(student.getfName());
            lastNameEdit.setText(student.getlName());
            emailAddEdit.setText(student.getEmailAdd());
            billAddEdit.setText(student.getMailAdd());
        }
    }

}
