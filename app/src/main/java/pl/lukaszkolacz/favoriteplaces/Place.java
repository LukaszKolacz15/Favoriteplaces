package pl.lukaszkolacz.favoriteplaces;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

/**
 * Created by Lukasz Kolacz on 22.08.2017.
 */

public class Place extends RealmObject {
    public int position;
    public String name;
    public String note;
    public double lat;
    public double lon;

    public Place() {
    }

    public Place(int position, String name, String note, LatLng location) {
        this.position = position;
        this.name = name;
        this.note = note;
        this.lat = location.latitude;
        this.lon = location.longitude;
    }

    public Place(int position, String name, String note, double lat, double lon) {
        this.position = position;
        this.name = name;
        this.note = note;
        this.lat = lat;
        this.lon = lon;
    }
}
