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
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CheckOutForm extends AppCompatActivity{

    private String billAdd, nameOnCard, cardNumber, cardSecure, expireDate;
    private Bill bill;
    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, emailAddEdit, billAddEdit, nameOnCardEdit, cardNumberEdit, cardSecureEdit, expireDateEdit;
    private Button submitButton, cancelButton;
    private Student student;
    private Bundle bundle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);
        //Initialize form components
        student = Student.getInstance();
        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.submitButton);
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        emailAddEdit = findViewById(R.id.emailEdit);
        billAddEdit = findViewById(R.id.emailEdit);
        nameOnCardEdit = findViewById(R.id.cardNameEdit);
        cardNumberEdit = findViewById(R.id.cardNumEdit);
        cardSecureEdit = findViewById(R.id.cardSecurityCodeEdit);
        expireDateEdit = findViewById(R.id.expireDateEdit);
        bundle = savedInstanceState;

        firstNameEdit.setText(student.getfName());
        lastNameEdit.setText(student.getlName());
        emailAddEdit.setText(student.getEmailAdd());

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

                bill = new Bill();

                //Assign form objects to string
                billAdd = billAddEdit.getText().toString();
                nameOnCard = nameOnCardEdit.getText().toString();
                cardNumber = cardNumberEdit.getText().toString();
                cardSecure = cardSecureEdit.getText().toString();
                expireDate = expireDateEdit.getText().toString();

                //Validate CardNumber
                CardVerify verify = CardVerify.getInstance();

                //Proceed to activity
                if(verify.verify(cardNumber)){
                    bill.payBill(5);

                } else {
                    cardNumberEdit.setTextColor(Color.RED);
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
