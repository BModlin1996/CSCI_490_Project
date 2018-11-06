/**
 *
 * @author Bailey Modlin
 * @version 1.0
 * @since 9/11/2018
 */
package com.games.baileymodlin.csci_490_project;

public class Student {

    private static final String TAG = Student.class.getSimpleName();
    private static Student student = new Student();
    private String fName;
    private String lName;
    private String studId;
    private String emailAdd;
    private String mailAdd;
    private String city;
    private String state;
    private String zip;
    private String password;
    private String phone;



    private Student(){


    }



    public static Student getInstance(){
        return student;
    }

    public void logIn() {


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

    public void setMailAdd(String mailAdd){
        this.mailAdd = mailAdd;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setZip(String zip){
        this.zip = zip;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPhone(String phone){
        this.phone = phone;
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

    public String getMailAdd(){
        return mailAdd + " " + city + ", " + state + " " + zip;
    }

    public String getPassword(){
        return password;
    }
}
