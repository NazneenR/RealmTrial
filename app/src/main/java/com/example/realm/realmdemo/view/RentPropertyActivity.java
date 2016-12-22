package com.example.realm.realmdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.realm.realmdemo.R;
import com.example.realm.realmdemo.repository.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentPropertyActivity extends AppCompatActivity {

  private Repository repository;

  @BindView(R.id.rent_value_edittext)
  EditText rentValueEditText;
  @BindView(R.id.owner_name_edittext)
  EditText ownerNameEditText;
  @BindView(R.id.complete_address_edittext)
  EditText completeAddressEditText;
  @BindView(R.id.locality_auto_complete_view)
  AutoCompleteTextView localityView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rent_property);
    ButterKnife.bind(this);
    loadLocalities();
  }

  private void loadLocalities() {
    repository = new Repository();
    InputStream localityInputStream = getResources().openRawResource(R.raw.localities);
    BufferedReader localitiesFileBufferedReader = new BufferedReader(new InputStreamReader(localityInputStream));
    String localityLine;

    try {
      while ((localityLine = localitiesFileBufferedReader.readLine()) != null) {
        repository.addLocation(localityLine);
      }
    } catch (Exception ignored) {
      Log.e("Error", "error while reading localities" + ignored);
    }
  }

  public void saveClicked(View view) {
  }
}
