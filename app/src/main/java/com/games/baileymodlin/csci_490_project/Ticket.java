package com.games.baileymodlin.csci_490_project;

public class Ticket {

    private String ticketID, officerFName, officerLName, officerId, date, comment, price;

    public Ticket(String ticketID, String officerFName, String officerLName, String officerId, String date, String comment, String price){
            this.ticketID = ticketID;
            this.officerFName = officerFName;
            this.officerLName = officerLName;
            this.officerId = officerId;
            this.date = date;
            this.comment = comment;
            this.price = price;
    }


    public String getOfficerName(){
        return officerFName + " " + officerLName;
    }

    public String getDate(){
        return date;
    }

    public String toString(){
        return  "Ticket id: " + ticketID + "\nOfficer: " + getOfficerName() + "\nOfficer ID: " + officerId + "\nDate: " + date + "\nPrice: $" + price + "\nComment: " + comment;

    }
}
