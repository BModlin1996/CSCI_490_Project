/**
 * CheckOutForm - This class collects the data used to make payment on the ticket
 * @author Bailey Modlin
 * @version 1.0
 * @since  8/28/2018
 * Last updated - 10/01/2018
 */
package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CheckOutForm extends AppCompatActivity {

    private String TAG = CheckOutForm.class.getSimpleName();
    private String nameOnCard, cardNumber, cardSecure, expireDate;
    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, emailAddEdit, billAddEdit, nameOnCardEdit, cardNumberEdit, cardSecureEdit, expireDateEdit;
    private Button submitButton;
    private boolean valid = true;
    private Student student;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);

        student = Student.getInstance();

        //Initialize form components
        submitButton = findViewById(R.id.submitButton);
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        emailAddEdit = findViewById(R.id.emailEdit);
        billAddEdit = findViewById(R.id.billAddressEdit);
        nameOnCardEdit = findViewById(R.id.cardNameEdit);
        cardNumberEdit = findViewById(R.id.cardNumEdit);
        cardSecureEdit = findViewById(R.id.cardSecurityCodeEdit);
        expireDateEdit = findViewById(R.id.expireDateEdit);


        firstNameEdit.setText(student.getfName());
        lastNameEdit.setText(student.getlName());
        emailAddEdit.setText(student.getEmailAdd());
        billAddEdit.setText(student.getMailAdd());


        nameOnCardEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CardVerify verify = CardVerify.getInstance();
                verify.verifyName(nameOnCard.toString());
            }
        });

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
                if (!verify.verifyName(nameOnCard)) {
                    valid = false;
                    nameOnCardEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information name invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    nameOnCardEdit.setTextColor(Color.WHITE);
                }

                //Verify card number is valid
                if (verify.verifyCardNum(cardNumber)) {
                    valid = false;
                    cardNumberEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information card number invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    cardNumberEdit.setTextColor(Color.WHITE);
                }

                //Verify expiration date is valid
                if (verify.verifyDate(expireDate)) {
                    valid = false;
                    expireDateEdit.setTextColor(Color.RED);
                    Toast.makeText(CheckOutForm.this, "Incorrect information expiration date invalid", Toast.LENGTH_LONG);
                } else {
                    valid = true;
                    expireDateEdit.setTextColor(Color.WHITE);
                }

                if (valid) {
                    intent = new Intent(CheckOutForm.this, UserActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class updateTicketStatus extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            WebServiceConnect webConn = WebServiceConnect.getInstance();
            webConn.getData("https://ccuresearch.coastal.edu/bcmodlin/CSCI_490/index.php?run=updateTicketStatus&param=");
            return null;
        }
    }
}
