/**
 *
 * @author Bailey Modlin
 * @version 1.0
 * @since 9/11/2018
 */
package com.games.baileymodlin.csci_490_project;

public class Student {

    private String fName;
    private String lName;
    private String studId;
    private String emailAdd;

    public Student(){

    }

    public  Student(String fName, String lName, String studId, String emailAdd){
        this.fName = fName;
        this.lName = lName;
        this.studId = studId;
        this.emailAdd = emailAdd;
    }

    public void setfName(String fName){
        this.fName = fName;
    }

    public void setlName(String lName){
        this.lName = lName;
    }

    public void setStudId(String studId){
        this.studId = studId;
    }

    public void setEmailAdd(String emailAdd) {
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
