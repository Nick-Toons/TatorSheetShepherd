package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/24/2017.
 */

public class CrossedLine {
    private boolean crossed;

    public CrossedLine(boolean crossed){this.setCrossed(crossed);}

    public boolean getCrossed(){return crossed;}

    public void setCrossed(boolean crossed) {this.crossed = crossed;}
}
