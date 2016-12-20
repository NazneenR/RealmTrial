package com.example.realm.realmdemo.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Apartment extends RealmObject {

  @PrimaryKey
  private String uuid;
  private String completeAddress;
  private Owner owner;
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

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  public String getUuid() {
    return uuid;
  }
}
