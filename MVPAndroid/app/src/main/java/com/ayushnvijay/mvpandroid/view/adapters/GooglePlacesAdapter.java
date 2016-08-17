package com.ayushnvijay.mvpandroid.view.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.ayushnvijay.mvpandroid.Constants;
import com.ayushnvijay.mvpandroid.model.LocationPickerModel.GoogleLocation;
import com.ayushnvijay.mvpandroid.model.LocationPickerModel.GoogleLocationService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ayushnvijay on 8/14/16.
 */
public class GooglePlacesAdapter extends ArrayAdapter implements Filterable {
    @Inject GoogleLocationService locService;
    ArrayList<String> arrayList;

    public GooglePlacesAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public String getItem(int index){
        return arrayList.get(index);
    }

    @Override
    public int getCount(){
        return arrayList.size();
    }

    @Override
    public Filter getFilter(){
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults results = new FilterResults();
                if(constraint!=null){
                    locService.getLocations(Constants.GOOGLE_API_KEY,"country:us",constraint.toString())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribe(new Action1<List<GoogleLocation>>() {
                        @Override
                        public void call(List<GoogleLocation> nearbyLocations) {

                            results.values = arrayList;
                            results.count = arrayList.size();
                        }
                    });

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count > 0){
                    notifyDataSetChanged();
                }
                else notifyDataSetInvalidated();
            }
        };
        return filter;
    }
}