package com.example.realm.realmdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Owner extends RealmObject {
  @PrimaryKey
  public String name;
}
