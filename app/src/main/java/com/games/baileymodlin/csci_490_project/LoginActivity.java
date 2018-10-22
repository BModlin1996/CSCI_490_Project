package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button nav = findViewById(R.id.appealNave);




        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(0);
            }
        });



    }

    private void changeActivity(int id){
        switch (id){
            case 0:
                intent = new Intent(this, AppealActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }
    }





}
