package com.example.realm.realmdemo.repository;

import com.example.realm.realmdemo.model.Apartment;
import com.example.realm.realmdemo.model.Location;

import io.realm.Realm;
import io.realm.RealmResults;

public class Repository {
  private final Realm realm;

  public Repository() {
    realm = Realm.getDefaultInstance();

  }

  public void addApartmentWithRent(final int rentValue) {
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        Location location = realm.createObject(Location.class);
        location.name = "Hermes Heritage";
        Apartment apartment = realm.createObject(Apartment.class);
        apartment.setLocation(location);
        apartment.setRentValue(rentValue);
      }
    });
  }

  public void updateFirstApartmentInTheList() {
    final Apartment apartment = realm.where(Apartment.class).findFirst();

    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        Location location = realm.createObject(Location.class);
        location.name = "Hermes Heritage";
        apartment.setLocation(location);
        apartment.setRentValue(8000);
      }
    });
  }

  public void deleteApartments() {
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        realm.delete(Apartment.class);
      }
    });
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnRentValue(int rentValue) {
    return realm.where(Apartment.class).equalTo("rentValue", rentValue).findAll();
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnName(String locationName) {
    return realm.where(Apartment.class).equalTo("location.name", locationName.toString()).findAll();
  }

  public RealmResults<Apartment> executeComplexQuery() {
    return realm.where(Apartment.class)
        .between("rentValue", 7700, 9900)
        .beginsWith("location.name", "Hermes").findAll();
  }

  public void closeDB() {
    realm.close();
  }
}