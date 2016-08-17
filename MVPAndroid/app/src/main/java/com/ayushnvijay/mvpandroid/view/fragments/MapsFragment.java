package com.ayushnvijay.mvpandroid.view.fragments;

import com.ayushnvijay.mvpandroid.contracts.MapsMvp;
import com.ayushnvijay.mvpandroid.model.MapsModel.Venue;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public class MapsFragment implements MapsMvp.View {
    @Override
    public void setMap(GoogleMap mMap) {

    }

    @Override
    public GoogleMap getMap() {
        return null;
    }

    @Override
    public Venue getVenue() {
        return null;
    }

    @Override
    public void setVenue(Venue v) {

    }
}
