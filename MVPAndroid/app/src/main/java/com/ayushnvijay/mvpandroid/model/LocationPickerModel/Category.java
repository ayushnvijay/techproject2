package com.ayushnvijay.mvpandroid.model.LocationPickerModel;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public enum Category {
    FOOD_AND_BAR_ID("4d4b7105d754a06374d81259"),
    MEDICAL_FACILITY_ID("4bf58dd8d48988d104941735"),
    PUBLIC_TRANSIT_SYSTEM_ID ("4bf58dd8d48988d1fe931735,52f2ab2ebcbc57f1066b8b4f,4bf58dd8d48988d1fc931735,4bf58dd8d48988d1fd931735,4bf58dd8d48988d129951735,52f2ab2ebcbc57f1066b8b51"),
    ENTERTAINMETN_ID("4d4b7104d754a06370d81259");

    private String s;

    Category(String s) {
        this.s = s;
    }
}
