package com.ndlcommerce.useCase.request.customer;

public class CustomerFilterDTO {
    String name;
    String userLogin;
    String address;

    public CustomerFilterDTO() {
    }

    public CustomerFilterDTO(String name, String userLogin, String address) {
        this.name = name;
        this.userLogin = userLogin;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
