/**
 *
 * @author Bailey Modlin
 * @version 1.0
 * @since 9/11/2018
 */
package com.games.baileymodlin.csci_490_project;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Student extends AsyncTask<Void, Void, Void> {

    private static final String TAG = Student.class.getSimpleName();
    private static Student student = new Student();
    private WebServiceConnect webCon;
    private String fName;
    private String lName;
    private String studId;
    private String emailAdd;
    private String password;
    private String jsonStr;



    private Student(){


    }

    @Override
    protected Void doInBackground(Void... voids) {

        webCon = new WebServiceConnect();
        jsonStr = webCon.getData("/student/1269838");
        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject stud = new JSONObject(jsonStr);

                fName = stud.getString("Fname");
                lName = stud.getString("Lname");
                emailAdd = stud.getString("Email");
                password= stud.getString("Password");


            } catch (final JSONException e) {
                Log.e(TAG, "JSON parsing error: " + e.getMessage());
            }
        }

        return null;
    }

    public static Student getInstance(){
        return student;
    }

    public void logIn() {


    }



    private void setfName(String fName){
        this.fName = fName;
    }

    private void setlName(String lName){
        this.lName = lName;
    }

    private void setStudId(String studId){
        this.studId = studId;
    }

    private void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public String getStudId(){
        return studId;
    }

    public String getEmailAdd(){
        return emailAdd;
    }
}
