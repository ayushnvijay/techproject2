package com.ayushnvijay.mvpandroid.model.MapsModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ayushnvijay on 8/12/16.
 */
public interface FoursquareService {
    String BASE_ENDPOINT = "https://api.foursquare.com/v2/venues/search?";

    @GET("/venues/search")
    Observable<List<Venue>> findNearbyVenues(@Query("categoryId") String category, @Query("ll") String longLat, @Query("v") long v);

  }

