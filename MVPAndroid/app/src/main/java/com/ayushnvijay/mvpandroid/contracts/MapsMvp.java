package com.ayushnvijay.mvpandroid.contracts;

import com.ayushnvijay.mvpandroid.model.MapsModel.Venue;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public interface MapsMvp {
    interface Modal{

    }

    interface View { //extends OnMapReadyCallback

        void setMap(GoogleMap mMap);
        GoogleMap getMap();

        Venue getVenue();
        void setVenue(Venue v);
    }

    interface Presenter extends OnMapReadyCallback{
        void setView(MapsMvp.View v);
        MapsMvp.View getView();
    }

}
