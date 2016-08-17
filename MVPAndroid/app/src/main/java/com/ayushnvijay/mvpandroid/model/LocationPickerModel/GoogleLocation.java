package com.ayushnvijay.mvpandroid.model.LocationPickerModel;

/**
 * Created by ayushnvijay on 8/15/16.
 */
//POJO
public class GoogleLocation {
    //Description of places
    private String description;

    //Url for icon
    private String icon;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {

        return icon;
    }

    public String getDescription() {
        return description;
    }
}
