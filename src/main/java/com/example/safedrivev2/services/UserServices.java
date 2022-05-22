package com.example.safedrivev2.services;




import com.example.safedrivev2.model.User;
import com.example.safedrivev2.db.DataBaseConnection;
import com.example.safedrivev2.exceptions.CredentialsException;
import com.example.safedrivev2.exceptions.InexistentUserException;
import com.example.safedrivev2.exceptions.UserPasswordInvalidException;
import com.example.safedrivev2.utilities.PassBasedEnc;
import javafx.scene.control.Button;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserServices {

/*
    public static void updateUserBalance(float newBalanceValue,int accId){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();


        String updateUserBalance = "UPDATE user_account SET balance ="+newBalanceValue+" WHERE account_id ="+accId+";";

        try{

            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(updateUserBalance);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public static void initializeUser(User currentUser,String username) {

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String retriveLastUserId = "SELECT * FROM user_account WHERE username =" + "'" + username + "';";

        try{

            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(retriveLastUserId);

            while (queryResult.next()) {

                int retrivedAcountId=queryResult.getInt("account_id");
                //int retrivedInventoryId=queryResult.getInt("inventory_id");
                //String retrivedEmail=queryResult.getString("email");
                String retrivedUsername=queryResult.getString("username");
                //float retrivedBalance=queryResult.getFloat("balance");


                currentUser.setAcountId(retrivedAcountId);
                //currentUser.setInventoryId(retrivedInventoryId);
                //currentUser.setEmail(retrivedEmail);
                currentUser.setUsername(retrivedUsername);
                //currentUser.setBalance(retrivedBalance);

            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }


    public static int retriveLastUserId(Connection connectionDB) {

        String retriveLastUserId ="SELECT MAX(account_id) FROM user_account;";
        int retriveResult=0;

        try{

            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(retriveLastUserId);

            if(queryResult.next()){
                retriveResult=queryResult.getInt(1);
            }else{
                return retriveResult;
            }
        }catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

        return retriveResult;
    }

    public static void registerUser(String firstname, String lastname, String email, String username, String saltvalue, String encryptedPass){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //int isAdmin = 0;
        int user_id=retriveLastUserId(connectDB);
        user_id++;

        String insertFields = "INSERT INTO user_account(firstname, lastname, username, password, saltvalue) VALUES ('";
        String insertValues = firstname+ "','" +lastname+ "','" +username+ "','" +encryptedPass+ "','" +saltvalue+ "')";
        String insertToRegister = insertFields + insertValues;


       /* int banned = 0;
        int adminAproved = 1;
        float balance = 1000;
        int inventory_id=retriveLastUserId(connectDB);
        inventory_id++;

        String insertFields = "INSERT INTO user_account(inventory_id,firstname,lastname,email,username,encryptedPass,salt,balance,banned,adminAproved) VALUES ('";
        String insertValues = inventory_id+"','"+firstname+"','" +lastname+"','" +email+"','" +username+"','" +encryptedPass+"','"+saltvalue+"','"+balance+"','"+banned+"','"+adminAproved+"')";
        String insertToRegister = insertFields + insertValues;*/

        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public static boolean validateLogin(String username, String password, Button loginButton)throws CredentialsException,java.sql.SQLException,java.io.IOException{
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String retriveEncryptedPassStatement ="SELECT password,saltvalue FROM user_account WHERE username = '" + username +"'";


            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(retriveEncryptedPassStatement);

            if (!queryResult.isBeforeFirst() ) {
               throw new InexistentUserException("User does not exist!");
            }else {
                while (queryResult.next()) {
                    String proviedPassword = password;
                    String retrivedEncryptedPass = queryResult.getString("password");
                    String retrivedSalt = queryResult.getString("saltvalue");
                    //int isBanned = queryResult.getInt("banned");
                    //if (isBanned == 0) {
                        if (PassBasedEnc.verifyUserPassword(proviedPassword, retrivedEncryptedPass, retrivedSalt) == true) {

                          return true;

                        } else {
                            throw new UserPasswordInvalidException("The username or password is incorrect.");
                        }
                    //}
                }

            }
        return false;
    }


    public static void registerAdmin(String firstname, String lastname, String username, String saltvalue, String encryptedPass){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        int user_id=retriveLastUserId(connectDB);
        user_id++;

        String insertFields = "INSERT INTO admin_account(firstname, lastname, username, password, saltvalue) VALUES ('";
        String insertValues = firstname+ "','" +lastname+ "','" +username+ "','" +encryptedPass+ "','" +saltvalue+ "')";
        String insertToRegister = insertFields + insertValues;


        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }




    public static boolean validateLoginAdmin(String username, String password, Button loginButton)throws CredentialsException,java.sql.SQLException,java.io.IOException{
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String retriveEncryptedPassStatement ="SELECT password,saltvalue FROM admin_account WHERE username = '" + username +"'";


        Statement statement = connectionDB.createStatement();
        ResultSet queryResult = statement.executeQuery(retriveEncryptedPassStatement);

        if (!queryResult.isBeforeFirst() ) {
            throw new InexistentUserException("Admin does not exist!");
        }else {
            while (queryResult.next()) {
                String proviedPassword = password;
                String retrivedEncryptedPass = queryResult.getString("password");
                String retrivedSalt = queryResult.getString("saltvalue");

                if (PassBasedEnc.verifyUserPassword(proviedPassword, retrivedEncryptedPass, retrivedSalt) == true) {

                    return true;

                } else {
                    throw new UserPasswordInvalidException("The username or password is incorrect.");
                }

            }

        }
        return false;
    }




    public static void AddDriver(String firstname, String lastname){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //int user_id=retriveLastUserId(connectDB);
        //user_id++;

        String insertFields = "INSERT INTO driver_db(firstname, lastname) VALUES ('";
        String insertValues = firstname+ "','" +lastname+ "')";
        String insertToRegister = insertFields + insertValues;


        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public static void DeleteDriver(String id){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //int user_id=retriveLastUserId(connectDB);
        //user_id++;
        System.out.println("THe delete id is: " + id + "\n\n");
        //String DeleteFields = "DELETE FROM driver_db WHERE ('driver_id' = '"+id+"')";
        String DeleteFields = "DELETE FROM driver_db WHERE driver_id='" +id + "';";
        //DELETE FROM `safedriveuserdatabase`.`driver_db` WHERE (`driver_id` = '4');
        //String insertValues = firstname+ "','" +lastname+ "')";
        //String insertToRegister = insertFields + insertValues;


        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(DeleteFields);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public static void AddCar(String brand, String model, String licenseNumber){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //int user_id=retriveLastUserId(connectDB);
        //user_id++;

        String insertFields = "INSERT INTO car_db(brand, model, licenseNumber) VALUES ('";
        String insertValues = brand+ "','" +model+ "','" + licenseNumber + "')";
        String insertToRegister = insertFields + insertValues;


        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public static void DeleteCar(String id){

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        System.out.println("The delete id is: " + id + "\n\n");
        String DeleteFields = "DELETE FROM car_db WHERE car_id='" +id + "';";

        try{

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(DeleteFields);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public static boolean checkIfExist(String username){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectionDB = connectNow.getConnection();

        //String retriveEncryptedPassStatement ="SELECT password,saltvalue FROM admin_account WHERE username = '" + username +"'";
        String insertFields = "SELECT username FROM request_db";


        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(insertFields);

            while (queryResult.next()) {
                String dbUsername = queryResult.getString("username");

                if (dbUsername.compareTo(username) == 0) {
                    return true;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;

    }





}
