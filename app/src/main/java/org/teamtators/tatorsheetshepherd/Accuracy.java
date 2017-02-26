package org.teamtators.tatorsheetshepherd;

/**
 * Created by idabs on 2/24/2017.
 */

public class Accuracy {
    private String rating;

    public Accuracy(String rating){
        this.setAccuracy(rating);
    }

    public String getAccuracy() { return rating; }

    public void setAccuracy(String rating) { this.rating = rating; }
}
