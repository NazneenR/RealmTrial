package com.example.realm.realmdemo.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.example.realm.realmdemo.model.Location;
import com.example.realm.realmdemo.repository.Repository;

import java.util.Collections;
import java.util.List;

import io.realm.RealmResults;

public class LocalityAdapter extends FilterableRealmBaseAdapter<Location> {

  private final Repository repository;

  public LocalityAdapter(Context context, @LayoutRes int layout, RealmResults<Location> realmObjectList, Repository repository) {
    super(context, layout, realmObjectList);
    this.repository = repository;
  }

  @Override
  protected List<Location> performRealmFiltering(@NonNull CharSequence constraint, RealmResults<Location> results) {
    if (repository != null) {
      return repository.getAllLocationsStartingWith(constraint.toString());
    }
    return Collections.EMPTY_LIST;
  }


}
