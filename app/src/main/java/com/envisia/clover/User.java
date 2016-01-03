package com.envisia.clover;

/**
 * Created by JenishK on 25/12/15.
 */
public class User {
    String firstname, lastname, username, password;
    int age;

    public User(String firstname,String lastname,int age,String username,String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password){
        this.firstname = "";
        this.lastname = "";
        this.age = -1;
        this.username = username;
        this.password = password;
    }
}

