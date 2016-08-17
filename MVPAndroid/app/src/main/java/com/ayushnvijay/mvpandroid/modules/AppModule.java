package com.ayushnvijay.mvpandroid.modules;

import android.app.Application;

import com.ayushnvijay.mvpandroid.presentation.LocationPickerPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.internal.util.SubscriptionList;

/**
 * Created by ayushnvijay on 8/16/16.
 */
@Module
public class AppModule {
    Application application;
    SubscriptionList subscriptionList;

    public AppModule(Application application){
        this.application = application;
        this.subscriptionList = new SubscriptionList();
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }

    @Provides
    @Singleton
    SubscriptionList providesSubscriptions(){
        return subscriptionList;
    }

    @Provides
    @Singleton
    LocationPickerPresenter providesLocationPresenter(Application context){
        return new LocationPickerPresenter(context);
    }
}
