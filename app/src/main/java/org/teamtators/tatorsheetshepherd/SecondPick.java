package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/24/2017.
 */

public class SecondPick {
    private boolean secondPick;

    public SecondPick(boolean secondPick){this.setPick(secondPick);}

    public boolean getPick(){return secondPick;}

    public void setPick(boolean secondPick) {this.secondPick = secondPick;}
}
