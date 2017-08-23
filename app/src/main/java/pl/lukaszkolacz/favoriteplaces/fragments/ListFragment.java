package pl.lukaszkolacz.favoriteplaces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pl.lukaszkolacz.favoriteplaces.R;
import pl.lukaszkolacz.favoriteplaces.adapters.PlacesAdapter;

/**
 * Created by Lukasz Kolacz on 22.08.2017.
 */

public class ListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView itemView;
    PlacesAdapter placesAdapter;

    public ListFragment() {
    }

    public static ListFragment newInstance(int sectionNumber) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        ButterKnife.bind(this, rootView);

        itemView = rootView.findViewById(R.id.itemView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        itemView.setLayoutManager(linearLayoutManager);
        itemView.setHasFixedSize(true);
        placesAdapter = new PlacesAdapter();
        itemView.setAdapter(placesAdapter);

        return rootView;
    }

    
}
