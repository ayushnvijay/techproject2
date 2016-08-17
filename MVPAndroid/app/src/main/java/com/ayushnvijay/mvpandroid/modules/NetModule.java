package com.ayushnvijay.mvpandroid.modules;

import com.ayushnvijay.mvpandroid.model.LocationPickerModel.GoogleLocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ayushnvijay on 8/16/16.
 */
@Module
public class NetModule {
    String baseUrl;

    public NetModule(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GoogleLocationService provideGoogleLocationService(Retrofit retrofit){
        return retrofit.create(GoogleLocationService.class);
    }
}
