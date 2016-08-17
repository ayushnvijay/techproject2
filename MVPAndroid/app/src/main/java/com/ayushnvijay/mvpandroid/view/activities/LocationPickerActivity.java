package com.ayushnvijay.mvpandroid.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayushnvijay.mvpandroid.Constants;
import com.ayushnvijay.mvpandroid.R;
import com.ayushnvijay.mvpandroid.contracts.LocationPickerMvp;
import com.ayushnvijay.mvpandroid.presentation.LocationPickerPresenter;
import com.ayushnvijay.mvpandroid.view.MyApp;
import com.ayushnvijay.mvpandroid.view.adapters.GooglePlacesAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public class LocationPickerActivity extends BaseActivity implements LocationPickerMvp.View {
    @BindView(R.id.auto_complete) AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    GooglePlacesAdapter placesAdapter;

    @Inject Context context;
    @Inject LocationPickerPresenter presenter;

    double latitude;
    double longitude;
    String category;



    //Lifecycle methods. Refactor it to remove unused methods
    @Override
    public void onCreate(Bundle onSavedInstance){
        super.onCreate(onSavedInstance);
        ((MyApp)getApplication()).getComponent().inject(this);
        presenter.setView(this);

        placesAdapter = new GooglePlacesAdapter(this, R.layout.list_item);
        autoCompleteTextView.setAdapter(placesAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String str = (String) parent.getItemAtPosition(position);
                presenter.processGeoPoints(str);
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.processLocations(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        MasonryAdapter adapter = new MasonryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override public void onStop(){
        super.onStop();
    }
    @Override
    public void onDestroy(){
        presenter.setView(null);
        super.onDestroy();
    }


    //View interface. We dont need many of them
    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setAdapter(ArrayList<String> list) {
        placesAdapter.clear();
        placesAdapter.addAll(list);
        placesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategory(String str) {
        this.category = str;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    class MasonryView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        public MasonryView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Item clicked at position " + getAdapterPosition());
            String text = textView.getText().toString();
            if(text.equals("F&B")){
                LocationPickerActivity.this.category = Constants.FOOD_AND_BAR_ID;
            }
            else if(text.equals("Entertainment")){
                LocationPickerActivity.this.category = Constants.ENTERTAINMETN_ID;
            }
            else if(text.equals("Medical")){
                LocationPickerActivity.this.category = Constants.MEDICAL_FACILITY_ID;
            }
            else LocationPickerActivity.this.category = Constants.PUBLIC_TRANSIT_SYSTEM_ID;
            /*
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            intent.putExtra(LATITUDE,object.getLatitude());
            intent.putExtra(LONGITUDE,object.getLongitude());
            intent.putExtra(CATEGORY,object.getCategory());
            startActivity(intent);
             */

        }
    }
    class MasonryAdapter extends RecyclerView.Adapter<MasonryView> {
        int[] imgList = {R.drawable.fb, R.drawable.entertainment, R.drawable.medical, R.drawable.transit};
        String[] nameList = {"F&B", "Entertainment", "Medical", "Public transit stations"};
        private Context context;
        public MasonryAdapter(Context context) {
            this.context = context;
        }
        @Override
        public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiles_item, parent, false);
            MasonryView masonryView = new MasonryView(layoutView);
            return masonryView;
        }

        @Override
        public void onBindViewHolder(MasonryView holder, int position) {
            holder.imageView.setImageResource(imgList[position]);
            holder.textView.setText(nameList[position]);
        }

        @Override
        public int getItemCount() {
            return nameList.length;
        }
    }

}
