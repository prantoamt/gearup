package com.gearup.pranto.gearupmechanic;

/**
 * Created by pranto on 12/1/17.
 */

public class my_mechanic {

    String name, user_name, password, email, phone, address, category;

    public my_mechanic()
    {

    }

    public my_mechanic( String name, String user_name, String password,String email, String phone, String address, String category)
    {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.category = category;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
