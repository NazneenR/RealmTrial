package com.example.realm.realmdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Location extends RealmObject {
  @PrimaryKey
  public String name;

  @Override
  public String toString() {
    return name;
  }
}
