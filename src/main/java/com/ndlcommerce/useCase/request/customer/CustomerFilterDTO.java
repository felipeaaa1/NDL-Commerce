package com.ndlcommerce.useCase.request.customer;

public class CustomerFilterDTO {
  String name;
  String userLogin;
  String address;
  String contact;
  Boolean active;

  public CustomerFilterDTO() {}

  public CustomerFilterDTO(
      String name, String userLogin, String address, String contact, Boolean active) {
    this.name = name;
    this.userLogin = userLogin;
    this.address = address;
    this.contact = contact;
    this.active = active;
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

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
