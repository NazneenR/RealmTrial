package com.example.realm.realmdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.realm.realmdemo.R;
import com.example.realm.realmdemo.model.Apartment;
import com.example.realm.realmdemo.repository.Repository;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = MainActivity.class.getName();
  private LinearLayout rootLayout = null;

  private Repository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_realm_basic_example);
    rootLayout = ((LinearLayout) findViewById(R.id.container));
    rootLayout.removeAllViews();

    repository = new Repository();

    basicCRUD();
    basicQuery();
    basicLinkQuery();
    complexReadWrite();
    complexQuery();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    repository.closeDB();
  }

  private void showStatus(String txt) {
    Log.i(TAG, txt);
    TextView tv = new TextView(this);
    tv.setText(txt);
    rootLayout.addView(tv);
  }

  private void basicCRUD() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("Perform basic create operations...");
    repository.addApartmentWithRent(7500);
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("Basic create operations :- " + diff + " milliseconds");

    long startTimeForUpdate = SystemClock.currentThreadTimeMillis();
    showStatus("Perform update...");
    repository.updateFirstApartmentInTheList();
    long endTimeForUpdate = SystemClock.currentThreadTimeMillis();
    long diffForUpdate = endTimeForUpdate - startTimeForUpdate;
    showStatus("Basic Update operations :- " + diffForUpdate + " milliseconds");
  }

  private void basicQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming basic Query operation...");
    RealmResults<Apartment> results = repository.fetchApartmentsBasedOnRentValue(8000);

    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;

    showStatus("Size of result set: " + results.size());
    showStatus("Basic query :- " + diff + " milliseconds");
  }

  private void basicLinkQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming basic Link Query operation...");

    RealmResults<Apartment> results = repository.fetchApartmentsBasedOnName("Hermes Heritage");
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;

    showStatus("Size of result set: " + results.size());
    showStatus("Basic link query :- " + diff + " milliseconds");
  }

  private void complexReadWrite() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\nPerforming complex Read/Write operation...");

    for (int i = 7500; i < 10000; i++) {
      repository.addApartmentWithRent(i);
    }
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("Complex read and write :- " + diff + " milliseconds");
  }

  private void complexQuery() {
    long startTime = SystemClock.currentThreadTimeMillis();
    showStatus("\n\nPerforming complex Query operation...");

    RealmResults<Apartment> results = repository.executeComplexQuery();
    long endTime = SystemClock.currentThreadTimeMillis();
    long diff = endTime - startTime;
    showStatus("\nSize of result set: " + results.size());
    showStatus("Complex link :- " + diff + " milliseconds");
  }

  public void clickedAddProperty(View view) {
    Intent intent = new Intent(this, RentPropertyActivity.class);
    startActivity(intent);
  }
}