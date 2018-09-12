package com.games.baileymodlin.csci_490_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public DBConnect(){
        String connectString = "";
        String userName = "";
        String password ="";
        String driver = "com.mysql.jdbc.Driver";

        try {
            Connection con = DriverManager.getConnection(connectString, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
