package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/24/2017.
 */

public class HopperTripped {
    private boolean tripped;

    public HopperTripped(boolean tripped){this.setTrip(tripped);}

    public boolean getTrip(){return tripped;}

    public void setTrip(boolean tripped) {this.tripped = tripped;}
}
