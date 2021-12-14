package com.example.bikestore;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.bikestore.db.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)//USER TABLE IN APPDATABASE
public class User {

    @PrimaryKey(autoGenerate = true)//DB index number
    private int mUsernameID;


    private String mUsername;
    private String mPassword;

    private Boolean isAdmin = false;//always false admin rights unless admin

    public User(String username, String password) {//constructor
        this.mUsername = username;
        this.mPassword = password;
    }

    //setters and getters
    public int getUsernameID() {
        return mUsernameID;
    }

    public void setUsernameID(int mUsernameID) {
        this.mUsernameID = mUsernameID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }//not used yet , using when registering

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }//not used yet, using when registering

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}
