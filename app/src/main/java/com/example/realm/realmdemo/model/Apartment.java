package com.example.realm.realmdemo.model;

import io.realm.RealmObject;

public class Apartment extends RealmObject {
  private Location location;
  private int rentValue;

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
