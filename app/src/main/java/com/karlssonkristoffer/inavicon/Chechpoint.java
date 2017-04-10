package com.karlssonkristoffer.inavicon;

/**
 * Created by Kristoffer on 2017-04-06.
 */

public class Chechpoint {
    private String geofenceName;
    private int icon;
    private String instruction;
    private Boolean instructionFlag;

    public Chechpoint (String geofenceName, int icon) {
        this.geofenceName = geofenceName;
        this.icon = icon;
        instructionFlag = false;
    }
    public Chechpoint(String geofenceName, int icon, String instruction) {
        this.geofenceName = geofenceName;
        this.icon = icon;
        this.instruction = instruction;
        instructionFlag = true;
    }

    public Boolean hasInstruction() {
        return instructionFlag;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getGeofenceName() {
        return geofenceName;
    }

    public int getIcon() {
        return icon;
    }







}
