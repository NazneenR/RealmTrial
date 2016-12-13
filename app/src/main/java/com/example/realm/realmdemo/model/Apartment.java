package com.example.realm.realmdemo.model;

import io.realm.RealmObject;

public class Apartment extends RealmObject {
  private Location location;
  private int rentValue;

  public String getCompleteAddress() {
    return completeAddress;
  }

  public void setCompleteAddress(String completeAddress) {
    this.completeAddress = completeAddress;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  private String completeAddress;
  private Owner owner;

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getRentValue() {
    return rentValue;
  }

  public void setRentValue(int rentValue) {
    this.rentValue = rentValue;
  }
}
