package com.ayushnvijay.mvpandroid.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ayushnvijay.mvpandroid.view.MyApp;

import javax.inject.Inject;

import rx.internal.util.SubscriptionList;

/**
 * Created by ayushnvijay on 8/17/16.
 */
public class BaseActivity extends AppCompatActivity {

    //Singleton Subscription list
    @Inject protected SubscriptionList subscriptions;


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        ((MyApp)getApplication()).getComponent().inject(this);
    }
    @Override
    public void onDestroy(){
        subscriptions.unsubscribe();
        super.onDestroy();
    }

}
