package com.example.amr.wearit.Model;

/**
 * Created by amr on 12/25/17.
 */

public class User {

    private String Name;
    private String Password;
    private String Uname;

    public User(){

    }


    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }
}
