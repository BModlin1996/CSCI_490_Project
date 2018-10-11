package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class AppealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);
    }


    public void onClickSubmit(View view){

        Spinner reason = findViewById(R.id.reasonSpinner);

        String wasSelected = String.valueOf(reason.getSelectedItem());


        //This creates an intent to share between activities.
        //Intent intent = new Intent(this, displayInfo.class);
        //intent.putExtra("message", wasSelected);


        //This sends the activity to the second activity.
        //startActivity(intent);





    }



}
