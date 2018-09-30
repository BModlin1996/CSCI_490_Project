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
    private static Student student = new Student();

    private Student (){

    }

    public static Student getInstance() {
        return student;
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
