package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/13/2017.
 */

public class AvgView {
    private int teamNumber;
    private int lowBarX, chevalX, lowF, chevalF;
    private float lowBarT, chevalT;

    public AvgView(int teamNumber, int lowBarX, float lowBarT, int lowF, int chevalX, float chevalT, int chevalF){
        this.setNumber(teamNumber);
        this.setX(lowBarX, chevalX);
        this.setF(lowF, chevalF);
        this.setT(lowBarT, chevalT);
    }

    public int getNumber() { return teamNumber; }

    public void setNumber(int teamNumber) { this.teamNumber = teamNumber; }

    public int getLowX() { return lowBarX; }

    public int getChevalX() { return chevalX; }

    public void setX(int lowBarX, int chevalX) {
        this.lowBarX = lowBarX;
        this.chevalX = chevalX;
    }

    public float getLowT() { return lowBarT; }

    public float getChevalT() { return chevalT; }

    public void setT(float lowBarT, float chevalT) {
        this.lowBarT = lowBarT;
        this.chevalT = chevalT;
    }

    public int getLowF() { return lowF; }

    public int getChevalF() { return chevalF; }

    public void setF(int lowF, int chevalF) {
        this.lowF = lowF;
        this.chevalF = chevalF;
    }
}
