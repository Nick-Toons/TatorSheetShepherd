package org.teamtators.tatorsheetshepherd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by idabs on 2/14/2017.
 */

public class MatchAdapter extends ArrayAdapter<ArrayList<String>> {

    Context context;
    private ArrayList<ArrayList<String>> matchShow;
    TextView highAccAuto;
    TextView highAccTele;
    TextView color;
    TextView cross;
    TextView ded;
    TextView defend;
    TextView defenseRating;
    TextView gearFailA;
    TextView gearFailT;
    TextView gearPlace;
    TextView gearPerPeg1;
    TextView gearPerPeg2;
    TextView gearPerPeg3;
    TextView highCyclesTele;
    TextView highShotsAuto;
    TextView highShotsTele;
    TextView hopper1Auto;
    TextView hopper2Auto;
    TextView hopper3Auto;
    TextView hopper4Auto;
    TextView hopper1Tele;
    TextView hopper2Tele;
    TextView hopper3Tele;
    TextView hopper4Tele;
    TextView hopper5Tele;
    TextView intermitt;
    TextView lowDumpAuto;
    TextView lowDumpTele;
    TextView matchNum;
    TextView noGears;
    TextView note;
    TextView scale;
    TextView scaleFailed;
    TextView scouter;
    TextView goodPick;
    TextView starting;
    TextView stuc;
    TextView teamNum;
    TextView tipp;
    TextView tooFastAuto;
    TextView tooFastTele;


    boolean[] matchBools = MainActivity.matchBool;

    public MatchAdapter(Context context, int textViewResourceId, ArrayList<ArrayList<String>> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.matchShow = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(MainActivity.layoutBools[0]){
                v = vi.inflate(R.layout.match_team_small, null);
            } else if (MainActivity.layoutBools[1]){
                v = vi.inflate(R.layout.match_team, null);
            } else if (MainActivity.layoutBools[2]){
                v = vi.inflate(R.layout.match_team_large, null);
            } else if (MainActivity.layoutBools[3]){
                v = vi.inflate(R.layout.match_team_xlarge, null);
            } else {
                v = vi.inflate(R.layout.match_team_small, null);
            }

        }
        teamNum = (TextView) v.findViewById(R.id.teamNumber);
        matchNum = (TextView) v.findViewById(R.id.matchNum);
        goodPick = (TextView) v.findViewById(R.id.goodPick);
        scouter = (TextView) v.findViewById(R.id.scoutName);
        starting = (TextView) v.findViewById(R.id.startPos);
        color = (TextView) v.findViewById(R.id.alliance);
        noGears = (TextView) v.findViewById(R.id.noGear);
        gearFailA = (TextView) v.findViewById(R.id.gearFail);
        gearPlace = (TextView) v.findViewById(R.id.gearPlacement);
        hopper1Auto = (TextView) v.findViewById(R.id.hopper1A);
        hopper2Auto = (TextView) v.findViewById(R.id.hopper2A);
        hopper3Auto = (TextView) v.findViewById(R.id.hopper3A);
        hopper4Auto = (TextView) v.findViewById(R.id.hopper4A);
        cross = (TextView) v.findViewById(R.id.crossLine);
        lowDumpAuto = (TextView) v.findViewById(R.id.lowDumpA);
        highShotsAuto = (TextView) v.findViewById(R.id.highAuto);
        highAccAuto = (TextView) v.findViewById(R.id.highAccuracy);
        tooFastAuto = (TextView) v.findViewById(R.id.tooFastA);
        hopper1Tele = (TextView) v.findViewById(R.id.hopper1T);
        hopper2Tele = (TextView) v.findViewById(R.id.hopper2T);
        hopper3Tele = (TextView) v.findViewById(R.id.hopper3T);
        hopper4Tele = (TextView) v.findViewById(R.id.hopper4T);
        hopper5Tele = (TextView) v.findViewById(R.id.hopper5T);
        lowDumpTele = (TextView) v.findViewById(R.id.lowDumpT);
        highShotsTele = (TextView) v.findViewById(R.id.highTele);
        highCyclesTele = (TextView) v.findViewById(R.id.highCycles);
        highAccTele = (TextView) v.findViewById(R.id.highAccuracyT);
        tooFastTele = (TextView) v.findViewById(R.id.tooFastT);
        gearFailT = (TextView) v.findViewById(R.id.gearDrop);
        gearPerPeg1 = (TextView) v.findViewById(R.id.gearPer1);
        gearPerPeg2 = (TextView) v.findViewById(R.id.gearPer2);
        gearPerPeg3 = (TextView) v.findViewById(R.id.gearPer3);
        defend = (TextView) v.findViewById(R.id.didDefense);
        defenseRating = (TextView) v.findViewById(R.id.defenseRate);
        scale = (TextView) v.findViewById(R.id.didScale);
        stuc = (TextView) v.findViewById(R.id.gotStuck);
        scaleFailed = (TextView) v.findViewById(R.id.didFailScale);
        tipp = (TextView) v.findViewById(R.id.gotTipped);
        ded = (TextView) v.findViewById(R.id.didDie);
        note = (TextView) v.findViewById(R.id.additionalNotes);
        intermitt = (TextView) v.findViewById(R.id.wasIntermittent);


        teamNum.setWidth(200);
        if(!matchBools[1]) {
            matchNum.setWidth(0);
            matchNum.setTextColor(0);
        } else { matchNum.setWidth(200); }

        if(!matchBools[2]) {
            goodPick.setWidth(0);
            goodPick.setTextColor(0);
        } else { goodPick.setWidth(200); }

        if(!matchBools[15]) {
            scouter.setWidth(0);
            scouter.setTextColor(0);
        } else { scouter.setWidth(200); }

        if(!matchBools[14]) {
            starting.setWidth(0);
            starting.setTextColor(0);
        } else { starting.setWidth(200); }

        if(!matchBools[17]) {
            color.setWidth(0);
            color.setTextColor(0);
        } else { color.setWidth(200); }

        if(!matchBools[6]) {
            noGears.setWidth(0);
            noGears.setTextColor(0);
        } else { noGears.setWidth(200); }

        if(!matchBools[4]) {
            gearFailA.setWidth(0);
            gearFailA.setTextColor(0);
        } else { gearFailA.setWidth(200); }

        if(!matchBools[3]) {
            gearPlace.setWidth(0);
            gearPlace.setTextColor(0);
        } else { gearPlace.setWidth(200); }

        if(!matchBools[16]) {
            hopper1Auto.setWidth(0);
            hopper2Auto.setWidth(0);
            hopper3Auto.setWidth(0);
            hopper4Auto.setWidth(0);
            hopper1Tele.setWidth(0);
            hopper2Tele.setWidth(0);
            hopper3Tele.setWidth(0);
            hopper4Tele.setWidth(0);
            hopper5Tele.setWidth(0);
            hopper1Auto.setTextColor(0);
            hopper2Auto.setTextColor(0);
            hopper3Auto.setTextColor(0);
            hopper4Auto.setTextColor(0);
            hopper1Tele.setTextColor(0);
            hopper2Tele.setTextColor(0);
            hopper3Tele.setTextColor(0);
            hopper4Tele.setTextColor(0);
            hopper5Tele.setTextColor(0);
        } else {
            hopper1Auto.setWidth(200);
            hopper2Auto.setWidth(200);
            hopper3Auto.setWidth(200);
            hopper4Auto.setWidth(200);
            hopper1Tele.setWidth(200);
            hopper2Tele.setWidth(200);
            hopper3Tele.setWidth(200);
            hopper4Tele.setWidth(200);
            hopper5Tele.setWidth(200);
        }

        if(!matchBools[5]){
            cross.setWidth(0);
            cross.setTextColor(0);
        } else { cross.setWidth(200);}

        if(!matchBools[18]){
            lowDumpAuto.setWidth(0);
            lowDumpAuto.setTextColor(0);
        } else { lowDumpAuto.setWidth(200);}

        if(!matchBools[19]){
            highShotsAuto.setWidth(0);
            highShotsAuto.setTextColor(0);
        } else {highShotsAuto.setWidth(200);}

        if(!matchBools[20]){
            highAccAuto.setWidth(0);
            highAccAuto.setTextColor(0);
        } else { highAccAuto.setWidth(200);}

        if(!matchBools[21]){
            tooFastAuto.setWidth(0);
            tooFastAuto.setTextColor(0);
        } else { tooFastAuto.setWidth(200);}

        if(!matchBools[22]){
            lowDumpTele.setWidth(0);
            lowDumpTele.setTextColor(0);
        } else { lowDumpTele.setWidth(200);}

        if(!matchBools[23]){
            highShotsTele.setWidth(0);
            highShotsTele.setTextColor(0);
        } else { highShotsTele.setWidth(200);}

        if(!matchBools[24]){
            highCyclesTele.setWidth(0);
            highCyclesTele.setTextColor(0);
        } else { highCyclesTele.setWidth(200);}

        if(!matchBools[25]){
            highAccTele.setWidth(0);
            highAccTele.setTextColor(0);
        } else { highAccTele.setWidth(200);}

        if(!matchBools[26]){
            tooFastTele.setWidth(0);
            tooFastTele.setTextColor(0);
        } else { tooFastTele.setWidth(200);}

        if(!matchBools[10]){
            gearFailT.setWidth(0);
            gearFailT.setTextColor(0);
        } else { gearFailT.setWidth(200);}

        if(!matchBools[7]){
            gearPerPeg1.setWidth(0);
            gearPerPeg1.setTextColor(0);
        } else { gearPerPeg1.setWidth(200);}

        if(!matchBools[8]){
            gearPerPeg2.setWidth(0);
            gearPerPeg2.setTextColor(0);
        } else { gearPerPeg2.setWidth(200);}

        if(!matchBools[9]){
            gearPerPeg3.setWidth(0);
            gearPerPeg3.setTextColor(0);
        } else { gearPerPeg3.setWidth(200);}

        if(!matchBools[11]){
            defend.setWidth(0);
            defend.setTextColor(0);
        } else { defend.setWidth(200);}

        if(!matchBools[12]){
            defenseRating.setWidth(0);
            defenseRating.setTextColor(0);
        } else { defenseRating.setWidth(200);}

        if(!matchBools[13]){
            scale.setWidth(0);
            scale.setTextColor(0);
            scaleFailed.setWidth(0);
            scaleFailed.setTextColor(0);
        } else {
            scale.setWidth(200);
            scaleFailed.setWidth(200);
        }

        if(!matchBools[27]){
            stuc.setWidth(0);
            stuc.setTextColor(0);
        } else { stuc.setWidth(200);}

        if(!matchBools[28]){
            tipp.setWidth(0);
            tipp.setTextColor(0);
        } else { tipp.setWidth(200);}

        if(!matchBools[29]){
            ded.setWidth(0);
            ded.setTextColor(0);} else { ded.setWidth(200);}

        if(!matchBools[30]){
            intermitt.setWidth(0);
            intermitt.setTextColor(0);
        } else { intermitt.setWidth(200);}

        if(!matchBools[31]){
            //note.setWidth(0);
            note.setHeight(0);
            note.setTextColor(0);
        } else {
            note.setWidth(600);
        }

        teamNum.setText(matchShow.get(0).get(position));
        matchNum.setText(matchShow.get(1).get(position));
        goodPick.setText(matchShow.get(2).get(position));
        if(matchBools[15]) scouter.setText(matchShow.get(3).get(position));
        starting.setText(matchShow.get(4).get(position));
        color.setText(matchShow.get(5).get(position));
        noGears.setText(matchShow.get(6).get(position));
        gearFailA.setText(matchShow.get(7).get(position));
        gearPlace.setText(matchShow.get(8).get(position));
        hopper1Auto.setText(matchShow.get(9).get(position));
        hopper2Auto.setText(matchShow.get(10).get(position));
        hopper3Auto.setText(matchShow.get(11).get(position));
        hopper4Auto.setText(matchShow.get(12).get(position));
        cross.setText(matchShow.get(18).get(position));
        lowDumpAuto.setText(matchShow.get(19).get(position));
        highShotsAuto.setText(matchShow.get(20).get(position));
        highAccAuto.setText(matchShow.get(21).get(position));
        tooFastAuto.setText(matchShow.get(22).get(position));
        hopper1Tele.setText(matchShow.get(13).get(position));
        hopper2Tele.setText(matchShow.get(14).get(position));
        hopper3Tele.setText(matchShow.get(15).get(position));
        hopper4Tele.setText(matchShow.get(16).get(position));
        hopper5Tele.setText(matchShow.get(17).get(position));
        lowDumpTele.setText(matchShow.get(23).get(position));
        highShotsTele.setText(matchShow.get(24).get(position));
        highCyclesTele.setText(matchShow.get(25).get(position));
        highAccTele.setText(matchShow.get(26).get(position));
        tooFastTele.setText(matchShow.get(27).get(position));
        gearFailT.setText(matchShow.get(28).get(position));
        gearPerPeg1.setText(matchShow.get(29).get(position));
        gearPerPeg2.setText(matchShow.get(30).get(position));
        gearPerPeg3.setText(matchShow.get(31).get(position));
        defend.setText(matchShow.get(32).get(position));
        defenseRating.setText(matchShow.get(33).get(position));
        scale.setText(matchShow.get(34).get(position));
        stuc.setText(matchShow.get(36).get(position));
        scaleFailed.setText(matchShow.get(35).get(position));
        tipp.setText(matchShow.get(37).get(position));
        ded.setText(matchShow.get(38).get(position));
        intermitt.setText(matchShow.get(39).get(position));
        if(matchBools[31]) note.setText(matchShow.get(40).get(position));

        return v;
    }
}
