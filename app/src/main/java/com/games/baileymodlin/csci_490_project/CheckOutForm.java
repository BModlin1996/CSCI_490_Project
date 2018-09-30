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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckOutForm extends AppCompatActivity {

    private String billAdd;
    private String nameOnCard;
    private String cardNumber;
    private String cardSecure;
    private String expireDate;
    private Bill bill;

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

                CardVerify verify = CardVerify.getInstance();
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

                //Proceed to activity
                if(verify.verify(cardNumber)){
                    bill.payBill(5);
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
