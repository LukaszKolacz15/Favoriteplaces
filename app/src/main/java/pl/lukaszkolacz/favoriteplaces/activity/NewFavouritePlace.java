package pl.lukaszkolacz.favoriteplaces.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import pl.lukaszkolacz.favoriteplaces.Place;
import pl.lukaszkolacz.favoriteplaces.R;

public class NewFavouritePlace extends AppCompatActivity {
    private Realm realm;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextNote)
    EditText editTextNote;
    Location mLocation;
    LocationManager mLocationManager;
    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            mLocation = location;
            Toast.makeText(NewFavouritePlace.this, "Have location", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favourite_place);
        ButterKnife.bind(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        realm = Realm.getDefaultInstance();

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 1000, (float) 500.0, mLocationListener);

        findViewById(R.id.buttonAddPlace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave();
            }
        });
    }

    @OnClick(R.id.buttonAddPlace)
    public void onClickSave() {
        Place place = new Place(0, editTextName.getText().toString(), editTextNote.getText().toString(), mLocation.getLatitude(), mLocation.getLongitude());

        realm.beginTransaction();
        realm.copyToRealm(place);
        realm.commitTransaction();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        NewFavouritePlace.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(mLocationListener);

    }
}
