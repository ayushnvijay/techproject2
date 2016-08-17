package com.ayushnvijay.mvpandroid.contracts;

import java.util.ArrayList;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public interface LocationPickerMvp {

    interface Presenter {
        void processLocations(String str);
        void processGeoPoints(String add);

        void setView(LocationPickerMvp.View v);
        LocationPickerMvp.View getView();
    }

    interface View {
        String getCategory();

        void setAdapter(ArrayList<String> list);

        void setCategory(String str);

        //double getLatitude();

        //double getLongitude();

        void setLatitude(double latitude);

        void setLongitude(double longitude);




    }

    interface Modal {

    }
}
