/**
 * CheckOutForm - This class collects the data used to make payment on the ticket
 * @author Bailey Modlin
 * @version 1.0
 * Created - 8/28/2018
 * Last updated - 8/28/2018
 */
package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

public class CheckOutForm extends AppCompatActivity{

    private String billAdd, nameOnCard, cardNumber, cardSecure, expireDate;
    private Bill bill;
    private Intent intent;
    private EditText firstNameEdit, lastNameEdit, emailAddEdit, billAddEdit, nameOnCardEdit, cardNumberEdit, cardSecureEdit, expireDateEdit;
    private Button submitButton, cancelButton;
    private PopupWindow popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);
        //Initialize form components
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


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = Student.getInstance();
                bill = new Bill();

                //Assign form objects to strings
                firstNameEdit.setText(student.getfName());
                lastNameEdit.setText(student.getlName());
                emailAddEdit.setText(student.getEmailAdd());
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
                    showPopup();
                } else {
                    cardNumberEdit.setTextColor(Color.RED);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return to previous activity
                changeActivity(0);
            }
        });
    }

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

    private void showPopup(){


    }
}
