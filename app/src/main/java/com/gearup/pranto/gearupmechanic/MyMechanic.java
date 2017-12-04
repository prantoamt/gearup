package com.gearup.pranto.gearupmechanic;

import java.io.Serializable;

/**
 * Created by pranto on 12/1/17.
 */

public class MyMechanic implements Serializable{

    String name, user_name, password, email, phone, acc_type;

    public MyMechanic()
    {

    }

    public MyMechanic( String name, String user_name, String password,String email, String phone, String category)
    {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.acc_type = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String category) {
        this.acc_type = category;
    }
}
