package com.ayushnvijay.mvpandroid.modules;

import com.ayushnvijay.mvpandroid.presentation.LocationPickerPresenter;
import com.ayushnvijay.mvpandroid.presentation.MapsPresenter;
import com.ayushnvijay.mvpandroid.view.MyApp;
import com.ayushnvijay.mvpandroid.view.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ayushnvijay on 8/16/16.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class})

public interface AppComponent {
    void inject(LocationPickerPresenter vp);
    void inject(MapsPresenter mp);
    void inject(MyApp app);
    void inject(BaseActivity ba);
}
