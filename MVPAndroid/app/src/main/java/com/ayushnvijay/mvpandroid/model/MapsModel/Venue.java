package com.ayushnvijay.mvpandroid.model.MapsModel;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by ayushnvijay on 8/13/16.
 */
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
