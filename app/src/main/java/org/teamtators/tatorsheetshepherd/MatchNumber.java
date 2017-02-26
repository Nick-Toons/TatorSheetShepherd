package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/23/2017.
 */

public class MatchNumber {
    private int matchNumber;

    public MatchNumber(int matchNumber){
        this.setMatch(matchNumber);
    }

    public int getMatch() { return matchNumber; }

    public void setMatch(int teamNumber) { this.matchNumber = teamNumber; }
}
