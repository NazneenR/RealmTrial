package com.example.realm.realmdemo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realm.realmdemo.R;
import com.example.realm.realmdemo.adapters.LocalityAdapter;
import com.example.realm.realmdemo.repository.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentPropertyActivity extends AppCompatActivity {

  public static final String IS_LOCALITIES_LOADED_KEY = "IS_LOCALITIES_LOADED_KEY";
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
    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
    ButterKnife.bind(this);
    loadLocalities();
    SharedPreferences.Editor edit = sharedPref.edit();
    edit.putBoolean(IS_LOCALITIES_LOADED_KEY, true);
    edit.commit();
    System.out.println(repository.getAllLocations());
    localityView.setAdapter(new LocalityAdapter(this, android.R.layout.select_dialog_item, repository.getAllLocations(), repository));

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
      Log.e("Error", "error while reading localities");
    }
  }

  public void saveClicked(View view) {
    repository.addApartment(localityView.getText().toString(), Integer.parseInt(rentValueEditText.getText().toString()),
        ownerNameEditText.getText().toString(), completeAddressEditText.getText().toString());
    Toast.makeText(RentPropertyActivity.this, "Property Saved", Toast.LENGTH_SHORT).show();
    finish();
  }
}
