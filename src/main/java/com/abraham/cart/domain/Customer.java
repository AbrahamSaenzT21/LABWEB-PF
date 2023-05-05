package com.abraham.cart.domain;

public record Customer (Integer customerId, String firstName, String lastName, String email, String phone, String password) {

    public Integer getCustomerId(){
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
};

