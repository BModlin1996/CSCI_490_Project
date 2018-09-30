package com.games.baileymodlin.csci_490_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PaymentConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        TextView confirmText = (TextView) findViewById(R.id.billConfirmView);
        String name = "";
        String email = "";

        confirmText.setText("Name: " + name + "\n" + "Email: " + email + "\n");


    }

}
