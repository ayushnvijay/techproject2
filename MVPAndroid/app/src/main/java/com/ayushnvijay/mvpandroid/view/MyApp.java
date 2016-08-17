package com.ayushnvijay.mvpandroid.view;

import android.app.Application;

import com.ayushnvijay.mvpandroid.modules.AppComponent;
import com.ayushnvijay.mvpandroid.modules.AppModule;
import com.ayushnvijay.mvpandroid.modules.DaggerAppComponent;

/**
 * Created by ayushnvijay on 8/16/16.
 */
public class MyApp extends Application {

    private AppComponent component;
    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent(){
        return component;
    }

}
