package com;

/**
 * Created by bilal on 10/15/2016.
 */

import java.net.URL;
import java.util.List;


/**
 * A user registered in the system
 * At the moment, I'm thinking mostly about "edit profile" functionality here... this class is something to return when
 * we try to fetch a user from the service. It is very likely to change as we start using it.
 */
public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String token;
    private String email;
    private String tel;
    private String address;
    private boolean issuperuser=false;

    public User(String username,String firstname,String lastname,String email,String tel, String address,String token,boolean issuperuser) {
        this.username=username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tel=tel;
        this.address = address;
        this.token = token;
        this.issuperuser = issuperuser;
    }


    public String getUsername() {
        return username;
    }
    public boolean getIssuperuser() {
        return issuperuser;
    }
    public String getToken() {
        return token;
    }


    public String getTel(){return tel;}

    public String getFirstname() { return firstname; };
    public String getLastname() { return lastname; };

    public String getAddress() { return address; }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){return id;}

}
