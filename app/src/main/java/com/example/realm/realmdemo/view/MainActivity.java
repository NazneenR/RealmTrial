package com.example.realm.realmdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    showStatus("Perform basic Create/Read/Update operations...");
    repository.addApartmentWithRent(7500);

    repository.updateFirstApartmentInTheList();
    showStatus("Rent increased");
  }

  private void basicQuery() {
    showStatus("\nPerforming basic Query operation...");
    RealmResults<Apartment> results = repository.fetchApartmentsBasedOnRentValue(8000);

    showStatus("Size of result set: " + results.size());
  }

  private void basicLinkQuery() {
    showStatus("\nPerforming basic Link Query operation...");

    RealmResults<Apartment> results = repository.fetchApartmentsBasedOnName("Hermes Heritage");

    showStatus("Size of result set: " + results.size());
  }

  private void complexReadWrite() {
    showStatus("\nPerforming complex Read/Write operation...");

    for (int i = 7500; i < 10000; i++) {
      repository.addApartmentWithRent(i);
    }
  }

  private void complexQuery() {
    showStatus("\n\nPerforming complex Query operation...");

    RealmResults<Apartment> results = repository.executeComplexQuery();
    showStatus("\nSize of result set: " + results.size());
  }
}
