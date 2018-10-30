package com.games.baileymodlin.csci_490_project;

import android.os.Bundle;
import android.widget.EditText;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;


public class UserActivity {
    private Bundle bundle;

    protected void onCreate(final Bundle savedInstanceBundle){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText fname = (EditText) findViewById(R.id.Fname);
        final EditText lname = (EditText) findViewById(R.id.Lname);
        final EditText ccuid = (EditText) findViewById(R.id.CCUID);
        final EditText pass = (EditText) findViewById(R.id.loginPASS);
        bundle = savedInstanceState;
    }

}
