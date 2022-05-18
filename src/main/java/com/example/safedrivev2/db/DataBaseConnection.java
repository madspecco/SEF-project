package com.example.safedrivev2.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {

    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "safedriveuserdatabase";
        //String databaseName = "Local instance MySQL80";
        String databaseUser = "root";
        String databasePassword = "1234";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try{

           Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;

    }

}
