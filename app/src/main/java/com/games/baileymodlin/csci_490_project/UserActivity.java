package com.games.baileymodlin.csci_490_project;

import android.os.Bundle;
import android.widget.EditText;

public class UserActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText fname = (EditText) findViewById(R.id.Fname);
        final EditText lname = (EditText) findViewById(R.id.Lname);
        final EditText ccuid = (EditText) findViewById(R.id.CCUID);
        final EditText pass = (EditText) findViewById(R.id.loginPASS);
    }

}
