package com.ayushnvijay.mvpandroid.presentation;

import android.app.Application;
import android.content.Context;

import com.ayushnvijay.mvpandroid.Constants;
import com.ayushnvijay.mvpandroid.GeoLocation;
import com.ayushnvijay.mvpandroid.contracts.LocationPickerMvp;
import com.ayushnvijay.mvpandroid.model.LocationPickerModel.GoogleLocation;
import com.ayushnvijay.mvpandroid.model.LocationPickerModel.GoogleLocationService;
import com.ayushnvijay.mvpandroid.modules.DaggerAppComponent;
import com.ayushnvijay.mvpandroid.modules.NetModule;
import com.ayushnvijay.mvpandroid.view.MyApp;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.internal.util.SubscriptionList;
import rx.schedulers.Schedulers;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public class LocationPickerPresenter implements LocationPickerMvp.Presenter {
    LocationPickerMvp.View view;


    @Inject GoogleLocationService locationService;
    @Inject Application application;
    @Inject SubscriptionList subscriptions;
    @Inject Context context;

    String BASE_ENDPOINT = "https://maps.googleapis.com/maps/api/place";

    public LocationPickerPresenter(Application context){
        ((MyApp)context).getComponent().inject(this);
        DaggerAppComponent.builder().netModule(new NetModule(BASE_ENDPOINT)).build().inject(this);
    }

    @Override
    public void processLocations(String str) {
        //Better way would be to have an RxView which watches text sends text observables. We can apply map operator with it to get list of locations
        Subscription s = locationService.getLocations(Constants.GOOGLE_API_KEY, "country:us", str)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<GoogleLocation>>() {
                    @Override
                    public void call(List<GoogleLocation> locations) {
                        if(locations!=null && !locations.isEmpty()) {
                            ArrayList<String> list = new ArrayList<String>(locations.size());
                            for (int i = 0; i < locations.size(); i++) {
                                list.add(locations.get(i).getDescription());
                            }
                            view.setAdapter(list);
                        }
                    }
                });
        subscriptions.add(s);
    }

    @Override
    public void processGeoPoints(String add) {
        GeoLocation point = new GeoLocation();
        Barcode.GeoPoint geoPoint = point.getLocationFromAddress(add, context);
        view.setLatitude(geoPoint.lat);
        view.setLongitude(geoPoint.lng);
    }

    @Override
    public void setView(LocationPickerMvp.View v) {
        this.view = v;

    }

    @Override
    public LocationPickerMvp.View getView() {
        return view;
    }


}
//Dagger
//Retrofit with github Foursquare
//