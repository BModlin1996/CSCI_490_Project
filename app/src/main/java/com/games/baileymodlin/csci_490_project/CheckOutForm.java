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
import android.sax.StartElementListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckOutForm extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String emailAdd;
    private String billAdd;
    private String nameOnCard;
    private String cardNumber;
    private String cardSecure;
    private String expireDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);
        //Initialize form components
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize components
                EditText firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
                EditText lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
                EditText emailAddEdit = (EditText) findViewById(R.id.emailEdit);
                EditText billAddEdit = (EditText) findViewById(R.id.emailEdit);
                EditText nameOnCardEdit = (EditText) findViewById(R.id.cardNameEdit);
                EditText cardNumberEdit = (EditText) findViewById(R.id.cardNumEdit);
                EditText cardSecureEdit = (EditText) findViewById(R.id.cardSecurityCodeEdit);
                EditText expireDateEdit = (EditText) findViewById(R.id.expireDateEdit);

                CardVerify verify = new CardVerify();

                //Assign form objects to strings
                firstName = firstNameEdit.getText().toString();
                lastName = lastNameEdit.getText().toString();
                emailAdd = emailAddEdit.getText().toString();
                billAdd = billAddEdit.getText().toString();
                nameOnCard = nameOnCardEdit.getText().toString();
                cardNumber = cardNumberEdit.getText().toString();
                cardSecure = cardSecureEdit.getText().toString();
                expireDate = expireDateEdit.getText().toString();

                //Proceed to activity
                if(verify.verify(cardNumber)){
                    changeActivity(1);
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

    public void changeActivity(int id){
        switch (id){
            case 0:
                Intent prevAct = new Intent(this, LoginActivity.class);
                startActivity(prevAct);
                break;
            case 1:
                break;
        }
    }
}
