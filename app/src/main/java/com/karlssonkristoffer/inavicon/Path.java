package com.karlssonkristoffer.inavicon;

/**
 * Created by Kristoffer on 2017-04-06.
 * Class repressents a path. This class is now a placeholder with a hardcoded path.
 * Here should the logic for picking path be added.
 * It should in the end take an start-node and a stop-node.
 */

public class Path {
    private Chechpoint[] checkpoints;
    private int position = 0;

    /**
     * Creates a hard coded path.
     * The path is inside an array that with this class gets the functunality of a queue.
     */
    public Path() {
        this.checkpoints = new Chechpoint[15];
        addChecpoint(new Chechpoint(OurGeofences.FIRST_DOOR, R.drawable.door));
        addChecpoint(new Chechpoint(OurGeofences.FIRE_EXTINGUISHER, R.drawable.fireextinguisher, "poop"));
        addChecpoint(new Chechpoint(OurGeofences.STAIR, R.drawable.stairs, "1 Floor"));
        addChecpoint(new Chechpoint(OurGeofences.DOOR_C3, R.drawable.door, "C3"));
        addChecpoint(new Chechpoint(OurGeofences.EMERGENCY_DOOR, R.drawable.emergencyexit));
        addChecpoint(new Chechpoint(OurGeofences.THREE_DOORS, R.drawable.door, "Two doors"));
        addChecpoint(new Chechpoint(OurGeofences.TV, R.drawable.television));
        addChecpoint(new Chechpoint(OurGeofences.I_DOOR, R.drawable.door, "i"));
        addChecpoint(new Chechpoint(OurGeofences.BRIDGE, R.drawable.bridge));
        position = 0;
    }

    /**
     * Returns the following checkPoint
     * @return Following checkpoint
     */
    public Chechpoint getNext() {
        return checkpoints[position+1];
    }

    /**
     * Return the currrent checkpoint
     * @return current checkpoint
     */
    public Chechpoint getCurrent() {
        return checkpoints[position];
    }

    public int getCurrentIcon() {
        return checkpoints[position].getIcon();
    }

    public Boolean hasNextCheckPointInstructions() {
        return checkpoints[position+1].hasInstruction();
    }

    public String getNextCheckPointInstructions() {
        return checkpoints[position+1].getInstruction();

    }
/**
    public Boolean currentIsPassed() {
        return checkpoints[position].isPassed();
    }
*/
    public void lookForNext() {
        position++;
    }

    private void addChecpoint(Chechpoint chechpoint) {
        checkpoints[position] = chechpoint;
        position++;
    }

}
