package com.example.realm.realmdemo.repository;

import com.example.realm.realmdemo.model.Apartment;
import com.example.realm.realmdemo.model.Location;
import com.example.realm.realmdemo.model.Owner;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class Repository {
  private final Realm realm;

  public Repository() {
    realm = Realm.getDefaultInstance();

  }

  public void addApartmentWithRent(final int rentValue) {
    realm.beginTransaction();
    Location location = new Location();
    location.name = "Hermes Heritage";
    Owner owner = new Owner();
    owner.name = "Nazneen R";

    Apartment apartment = new Apartment();
    apartment.setUuid(UUID.randomUUID().toString());
    apartment.setLocation(location);
    apartment.setRentValue(rentValue);
    apartment.setOwner(owner);
    apartment.setCompleteAddress("Opp. Binaruis Building, Pune-411006 ");

    realm.copyToRealmOrUpdate(apartment);
    realm.commitTransaction();
  }

  public void updateFirstApartmentInTheList() {
    final Apartment apartment = realm.where(Apartment.class).findFirst();
    realm.beginTransaction();
    apartment.setRentValue(8000);
    realm.copyToRealmOrUpdate(apartment);
    realm.commitTransaction();
  }

  public void deleteApartments() {
    realm.beginTransaction();
    realm.delete(Apartment.class);
    realm.commitTransaction();
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnRentValue(int rentValue) {
    return realm.where(Apartment.class).equalTo("rentValue", rentValue).findAll();
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnName(String locationName) {
    return realm.where(Apartment.class).equalTo("location.name", locationName.toString()).findAll();
  }

  public RealmResults<Apartment> executeComplexQuery() {
    return realm.where(Apartment.class)
        .between("rentValue", 7700, 9900).findAll();
  }

  public void closeDB() {
    realm.close();
  }

}