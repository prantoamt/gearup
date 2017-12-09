package com.gearup.pranto.gearupmechanic;

import java.io.Serializable;

/**
 * Created by pranto on 12/1/17.
 */

public class MyMechanic implements Serializable{

    String name;
    String password;
    String email;
    String phone;
    String service;
    String rating;

    public MyMechanic()
    {

    }

    public MyMechanic( String name, String password,String email, String phone, String category)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.service = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getService() {
        return service;
    }

    public void setService(String category) {
        this.service = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
