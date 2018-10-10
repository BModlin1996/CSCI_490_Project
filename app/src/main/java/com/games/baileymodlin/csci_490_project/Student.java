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

public class Student {


    private static Student student = new Student();
    private String fName;
    private String lName;
    private String studId;
    private String emailAdd;
    private String password;

    private Student(){


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
