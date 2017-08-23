package pl.lukaszkolacz.favoriteplaces.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.lukaszkolacz.favoriteplaces.Place;
import pl.lukaszkolacz.favoriteplaces.R;

/**
 * Created by Lukasz Kolacz on 22.08.2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    private Realm realm;
    List<Place> mPlaces = new ArrayList<>();

    public PlacesAdapter() {
        realm = Realm.getDefaultInstance();
        getItems();
    }
    private void getItems(){
        RealmResults<Place> places = realm.where(Place.class).findAll();
        if (places != null && places.size() > 0) {
            for (Place place : places) {
                mPlaces.add(place);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place Place = mPlaces.get(position);
        holder.textViewTitle.setText(Place.name);
        holder.textViewNote.setText(Place.note);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewNote;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewNote = itemView.findViewById(R.id.note);
        }
    }
}
