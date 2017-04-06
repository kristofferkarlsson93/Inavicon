package com.karlssonkristoffer.inavicon;

import android.widget.ImageView;

/**
 * Created by Kristoffer on 2017-04-06.
 */

public class Chechpoint {
    private String geofenceName;
    private int icon;
    private Boolean passed = false;

    public Chechpoint(String geofenceName, int icon) {
        this.geofenceName = geofenceName;
        this.icon = icon;
    }

    public void setPassed(Boolean newStatus) {
        passed = newStatus;
    }

    public String getGeofenceName() {
        return geofenceName;
    }

    public int getIcon() {
        return icon;
    }

    public Boolean isPassed() {
        return passed;
    }






}
