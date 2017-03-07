package org.teamtators.tatorsheetshepherd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by idabs on 2/14/2017.
 */

public class AVGAdapter extends ArrayAdapter<ArrayList<String>> {

    Context context;
    private ArrayList<ArrayList<String>> avgShow;
    TextView cross;
    TextView defend;
    TextView defenseRating;
    TextView gearFailA;
    TextView gearFailT;
    TextView gearPlace;
    TextView gearPerPeg1;
    TextView gearPerPeg2;
    TextView gearPerPeg3;
    TextView matchNum;
    TextView scale;
    TextView scaleFailed;
    TextView teamNum;
    boolean[] avgBools = MainActivity.avgBool;

    public AVGAdapter(Context context, int textViewResourceId, ArrayList<ArrayList<String>> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.avgShow = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.avg_team, null);
        }
        teamNum = (TextView) v.findViewById(R.id.teamNumber);
        matchNum = (TextView) v.findViewById(R.id.matchNum);
        gearFailA = (TextView) v.findViewById(R.id.gearFail);
        gearPlace = (TextView) v.findViewById(R.id.gearPlacement);
        cross = (TextView) v.findViewById(R.id.crossLine);
        gearFailT = (TextView) v.findViewById(R.id.gearDrop);
        gearPerPeg1 = (TextView) v.findViewById(R.id.gearPer1);
        gearPerPeg2 = (TextView) v.findViewById(R.id.gearPer2);
        gearPerPeg3 = (TextView) v.findViewById(R.id.gearPer3);
        defend = (TextView) v.findViewById(R.id.didDefense);
        defenseRating = (TextView) v.findViewById(R.id.defenseRate);
        scale = (TextView) v.findViewById(R.id.didScale);
        scaleFailed = (TextView) v.findViewById(R.id.didFailScale);

        teamNum.setWidth(200);
        if(!avgBools[1]) {
            matchNum.setWidth(0);
            matchNum.setTextColor(0);
        } else { matchNum.setWidth(200); }

        if(!avgBools[4]) {
            gearFailA.setWidth(0);
            gearFailA.setTextColor(0);
        } else { gearFailA.setWidth(200); }

        if(!avgBools[3]) {
            gearPlace.setWidth(0);
            gearPlace.setTextColor(0);
        } else { gearPlace.setWidth(200); }

        if(!avgBools[5]){
            cross.setWidth(0);
            cross.setTextColor(0);
        } else { cross.setWidth(200);}

        if(!avgBools[10]){
            gearFailT.setWidth(0);
            gearFailT.setTextColor(0);
        } else { gearFailT.setWidth(200);}

        if(!avgBools[7]){
            gearPerPeg1.setWidth(0);
            gearPerPeg1.setTextColor(0);
        } else { gearPerPeg1.setWidth(200);}

        if(!avgBools[8]){
            gearPerPeg2.setWidth(0);
            gearPerPeg2.setTextColor(0);
        } else { gearPerPeg2.setWidth(200);}

        if(!avgBools[9]){
            gearPerPeg3.setWidth(0);
            gearPerPeg3.setTextColor(0);
        } else { gearPerPeg3.setWidth(200);}

        if(!avgBools[11]){
            defend.setWidth(0);
            defend.setTextColor(0);
        } else { defend.setWidth(200);}

        if(!avgBools[12]){
            defenseRating.setWidth(0);
            defenseRating.setTextColor(0);
        } else { defenseRating.setWidth(200);}

        if(!avgBools[13]) {
            scale.setWidth(0);
            scale.setTextColor(0);
            scaleFailed.setWidth(0);
            scaleFailed.setTextColor(0);
        } else {
            scale.setWidth(200);
            scaleFailed.setWidth(200);
        }

        teamNum.setText(avgShow.get(0).get(position));
        matchNum.setText(avgShow.get(1).get(position));
        gearFailA.setText(avgShow.get(2).get(position));
        gearPlace.setText(avgShow.get(3).get(position));
        cross.setText(avgShow.get(4).get(position));
        gearFailT.setText(avgShow.get(5).get(position));
        gearPerPeg1.setText(avgShow.get(6).get(position));
        gearPerPeg2.setText(avgShow.get(7).get(position));
        gearPerPeg3.setText(avgShow.get(8).get(position));
        defend.setText(avgShow.get(9).get(position));
        defenseRating.setText(avgShow.get(10).get(position));
        scale.setText(avgShow.get(11).get(position));
        scaleFailed.setText(avgShow.get(12).get(position));

        return v;
    }
}
