package com.karlssonkristoffer.inavicon;

/**
 * Created by Kristoffer on 2017-04-06.
 */

public class Path {
    private Chechpoint[] checkpoints;
    private int position = 0;

    public Path() {
        this.checkpoints = new Chechpoint[15];
        addChecpoint(new Chechpoint(OurGeofences.FIRST_DOOR, R.drawable.door));
        addChecpoint(new Chechpoint(OurGeofences.FIRE_EXTINGUISHER, R.drawable.fireextinguisher));
        addChecpoint(new Chechpoint(OurGeofences.STAIR, R.drawable.stairs));
        addChecpoint(new Chechpoint(OurGeofences.DOOR_C3, R.drawable.door));
        addChecpoint(new Chechpoint(OurGeofences.EMERGENCY_DOOR, R.drawable.emergencyexit));
        addChecpoint(new Chechpoint(OurGeofences.THREE_DOORS, R.drawable.door));
        addChecpoint(new Chechpoint(OurGeofences.TV, R.drawable.television));
        addChecpoint(new Chechpoint(OurGeofences.I_DOOR, R.drawable.door));
        addChecpoint(new Chechpoint(OurGeofences.BRIDGE, R.drawable.bridge));
        position = 0;
    }

    public Chechpoint getNext() {
        return checkpoints[position+1];
    }

    public Chechpoint getCurrent() {
        return checkpoints[position];
    }

    public int getCurrentIcon() {
        return checkpoints[position].getIcon();
    }

    public Boolean currentIsPassed() {
        return checkpoints[position].isPassed();
    }

    public void lookForNext() {
        position++;
    }

    private void addChecpoint(Chechpoint chechpoint) {
        checkpoints[position] = chechpoint;
        position++;
    }

}
