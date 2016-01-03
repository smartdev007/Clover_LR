package com.envisia.clover;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JenishK on 25/12/15.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context c) {
        userLocalDatabase = c.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User u) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("firstname", u.firstname);
        spEditor.putString("lastname", u.lastname);
        spEditor.putInt("age", u.age);
        spEditor.putString("username", u.username);
        spEditor.putString("password", u.password);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String firstname = userLocalDatabase.getString("firstname", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        int age = userLocalDatabase.getInt("age", -1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(firstname, lastname, age, username, password);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn",false)==true) {
            return true;
        }
        else
            return false;
    }


    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}

