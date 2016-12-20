package com.example.realm.realmdemo.repository;

import com.example.realm.realmdemo.model.Apartment;
import com.example.realm.realmdemo.model.Location;
import com.example.realm.realmdemo.model.Owner;

import java.util.List;
import java.util.UUID;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class Repository {
  private final Realm realm;

  public Repository() {
    realm = Realm.getDefaultInstance();

  }

  public void addApartment(String locationName, int rentValue, String ownerName, String completeAddress) {
    realm.beginTransaction();
    Apartment apartment = new Apartment();
    apartment.setUuid(UUID.randomUUID().toString());
    Location location = new Location();
    location.name = locationName;
    apartment.setLocation(location);
    apartment.setRentValue(rentValue);
    Owner owner = new Owner();
    owner.name = ownerName;
    apartment.setOwner(owner);
    apartment.setCompleteAddress(completeAddress);

    realm.copyToRealmOrUpdate(apartment);
    realm.commitTransaction();
  }

  public void addApartmentWithRent(final int rentValue) {
    Location location = new Location();
    String locationName = "Hermes Heritage";
    location.name = locationName;
    String ownerName = "Nazneen R";
    String completeAddress = "Opp. Binaruis Building, Pune-411006 ";
    addApartment(locationName, rentValue, ownerName, completeAddress);
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

  public void addLocation(String locationName) {
    realm.beginTransaction();
    Location location = new Location();
    location.name = locationName;
    realm.copyToRealmOrUpdate(location);
    realm.commitTransaction();
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnRentValue(int rentValue) {
    return realm.where(Apartment.class).equalTo("rentValue", rentValue).findAll();
  }

  public RealmResults<Apartment> fetchApartmentsBasedOnName(String locationName) {
    return realm.where(Apartment.class).equalTo("location.name", locationName.toString()).findAll();
  }

  public List<Location> getAllLocationsStartingWith(String prefix) {
    return realm.where(Location.class)
        .beginsWith("name", prefix, Case.INSENSITIVE)
        .findAll();
  }

  public RealmResults<Location> getAllLocations() {
    return realm.where(Location.class)
        .findAll();
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