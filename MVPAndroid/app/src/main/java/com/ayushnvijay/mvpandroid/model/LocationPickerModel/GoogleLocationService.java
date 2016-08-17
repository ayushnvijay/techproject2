package com.ayushnvijay.mvpandroid.model.LocationPickerModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ayushnvijay on 8/15/16.
 */
public interface GoogleLocationService {


    @GET("/autocomplete/json")
    Observable<List<GoogleLocation>> getLocations(@Query("key") String key,
                                                  @Query("components") String countryComp,
                                                  @Query("input") String inputLocation);
}
