/**
 *
 * @author Bailey Modlin
 * @version 1.0
 * @since 9/11/2018
 */
package com.games.baileymodlin.csci_490_project;
import java.util.Date;

public class Bill {

    private float amount;
    private String datePaid;
    private boolean paid;
    private Date date;

    public Bill(){
        paid = false;
        datePaid = "Null";
    }

    public void payBill(float amount){

        this.amount -= amount;
        date = new Date();

        if(amount == 0) {
            datePaid = date.toString();
            paid = true;
        } else {
            paid = false;
        }

    }

    public String printAmountDue(){
        return "Amount due: " + amount;
    }

    public String printAmountPaid(){
        return "";
    }

    public String getDatePaid(){
        return datePaid;
    }

    public boolean getPaid(){
        return paid;
    }
}
