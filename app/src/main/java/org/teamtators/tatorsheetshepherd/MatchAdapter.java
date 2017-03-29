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

    TextView rzFoul;
    TextView rzClearGear;
    TextView rzDropGear;
    TextView gearsStuck;
    TextView noChoking;
    TextView trueHolder6;


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
            if (MainActivity.layoutBools[0]) {
                v = vi.inflate(R.layout.match_team_small, null);
            } else if (MainActivity.layoutBools[1]) {
                v = vi.inflate(R.layout.match_team, null);
            } else if (MainActivity.layoutBools[2]) {
                v = vi.inflate(R.layout.match_team_large, null);
            } else if (MainActivity.layoutBools[3]) {
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

        rzFoul = (TextView) v.findViewById(R.id.retrieveFoul);
        rzClearGear = (TextView) v.findViewById(R.id.retrieveGear);
        rzDropGear = (TextView) v.findViewById(R.id.retrieveDropped);
        gearsStuck = (TextView) v.findViewById(R.id.canNotHandleThese);
        noChoking = (TextView) v.findViewById(R.id.didNotChoke);
        //trueHolder6 = (TextView) v.findViewById(R.id.placeholder6);

        teamNum.setText(matchShow.get(0).get(position));

        teamNum.setWidth(200);
        if (!matchBools[1]) {
            matchNum.setWidth(0);
            matchNum.setTextColor(0);
            matchNum.setText("");
        } else {
            matchNum.setWidth(200);
            matchNum.setText(matchShow.get(1).get(position));
        }

        if (!matchBools[2]) {
            goodPick.setWidth(0);
            goodPick.setTextColor(0);
            goodPick.setText("");
        } else {
            goodPick.setWidth(200);
            goodPick.setText(matchShow.get(2).get(position));
        }

        if (!matchBools[15]) {
            scouter.setWidth(0);
            scouter.setTextColor(0);
            scouter.setText("");
        } else {
            scouter.setWidth(200);
            scouter.setText(matchShow.get(3).get(position));
        }

        if (!matchBools[14]) {
            starting.setWidth(0);
            starting.setTextColor(0);
            starting.setText("");
        } else {
            starting.setWidth(200);
            starting.setText(matchShow.get(4).get(position));
        }

        if (!matchBools[17]) {
            color.setWidth(0);
            color.setTextColor(0);
            color.setText("");
        } else {
            color.setWidth(200);
            color.setText(matchShow.get(5).get(position));
        }

        if (!matchBools[6]) {
            noGears.setWidth(0);
            noGears.setTextColor(0);
            noGears.setText("");
        } else {
            noGears.setWidth(200);
            noGears.setText(matchShow.get(6).get(position));
        }

        if (!matchBools[4]) {
            gearFailA.setWidth(0);
            gearFailA.setTextColor(0);
            gearFailA.setText("");
        } else {
            gearFailA.setWidth(200);
            gearFailA.setText(matchShow.get(7).get(position));
        }

        if (!matchBools[3]) {
            gearPlace.setWidth(0);
            gearPlace.setTextColor(0);
            gearPlace.setText("");
        } else {
            gearPlace.setWidth(200);
            gearPlace.setText(matchShow.get(8).get(position));
        }

        if (!matchBools[16]) {
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
            hopper1Auto.setText("");
            hopper2Auto.setText("");
            hopper3Auto.setText("");
            hopper4Auto.setText("");
            hopper1Tele.setText("");
            hopper2Tele.setText("");
            hopper3Tele.setText("");
            hopper4Tele.setText("");
            hopper5Tele.setText("");
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
            hopper1Auto.setText(matchShow.get(9).get(position));
            hopper2Auto.setText(matchShow.get(10).get(position));
            hopper3Auto.setText(matchShow.get(11).get(position));
            hopper4Auto.setText(matchShow.get(12).get(position));
            hopper1Tele.setText(matchShow.get(13).get(position));
            hopper2Tele.setText(matchShow.get(14).get(position));
            hopper3Tele.setText(matchShow.get(15).get(position));
            hopper4Tele.setText(matchShow.get(16).get(position));
            hopper5Tele.setText(matchShow.get(17).get(position));
        }

        if (!matchBools[5]) {
            cross.setWidth(0);
            cross.setTextColor(0);
            cross.setText("");
        } else {
            cross.setWidth(200);
            cross.setText(matchShow.get(18).get(position));
        }

        if (!matchBools[18]) {
            lowDumpAuto.setWidth(0);
            lowDumpAuto.setTextColor(0);
            lowDumpAuto.setText("");
        } else {
            lowDumpAuto.setWidth(200);
            lowDumpAuto.setText(matchShow.get(19).get(position));
        }

        if (!matchBools[19]) {
            highShotsAuto.setWidth(0);
            highShotsAuto.setTextColor(0);
            highShotsAuto.setText("");
        } else {
            highShotsAuto.setWidth(200);
            highShotsAuto.setText(matchShow.get(20).get(position));
        }

        if (!matchBools[20]) {
            highAccAuto.setWidth(0);
            highAccAuto.setTextColor(0);
            highAccAuto.setText("");
        } else {
            highAccAuto.setWidth(200);
            highAccAuto.setText(matchShow.get(21).get(position));
        }

        if (!matchBools[21]) {
            tooFastAuto.setWidth(0);
            tooFastAuto.setTextColor(0);
            tooFastAuto.setText("");
        } else {
            tooFastAuto.setWidth(200);
            tooFastAuto.setText(matchShow.get(22).get(position));
        }

        if (!matchBools[22]) {
            lowDumpTele.setWidth(0);
            lowDumpTele.setTextColor(0);
            lowDumpTele.setText("");
        } else {
            lowDumpTele.setWidth(200);
            lowDumpTele.setText(matchShow.get(23).get(position));
        }

        if (!matchBools[23]) {
            highShotsTele.setWidth(0);
            highShotsTele.setTextColor(0);
            highShotsTele.setText("");
        } else {
            highShotsTele.setWidth(200);
            highShotsTele.setText(matchShow.get(24).get(position));
        }

        if (!matchBools[24]) {
            highCyclesTele.setWidth(0);
            highCyclesTele.setTextColor(0);
            highCyclesTele.setText("");
        } else {
            highCyclesTele.setWidth(200);
            highCyclesTele.setText(matchShow.get(25).get(position));
        }

        if (!matchBools[25]) {
            highAccTele.setWidth(0);
            highAccTele.setTextColor(0);
            highAccTele.setText("");
        } else {
            highAccTele.setWidth(200);
            highAccTele.setText(matchShow.get(26).get(position));
        }

        if (!matchBools[26]) {
            tooFastTele.setWidth(0);
            tooFastTele.setTextColor(0);
            tooFastTele.setText("");
        } else {
            tooFastTele.setWidth(200);
            tooFastTele.setText(matchShow.get(27).get(position));
        }

        if (!matchBools[10]) {
            gearFailT.setWidth(0);
            gearFailT.setTextColor(0);
            gearFailT.setText("");
        } else {
            gearFailT.setWidth(200);
            gearFailT.setText(matchShow.get(28).get(position));
        }

        if (!matchBools[7]) {
            gearPerPeg1.setWidth(0);
            gearPerPeg1.setTextColor(0);
            gearPerPeg1.setText("");
        } else {
            gearPerPeg1.setWidth(200);
            gearPerPeg1.setText(matchShow.get(32).get(position));
        }

        if (!matchBools[8]) {
            gearPerPeg2.setWidth(0);
            gearPerPeg2.setTextColor(0);
            gearPerPeg2.setText("");
        } else {
            gearPerPeg2.setWidth(200);
            gearPerPeg2.setText(matchShow.get(33).get(position));
        }

        if (!matchBools[9]) {
            gearPerPeg3.setWidth(0);
            gearPerPeg3.setTextColor(0);
            gearPerPeg3.setText("");
        } else {
            gearPerPeg3.setWidth(200);
            gearPerPeg3.setText(matchShow.get(34).get(position));
        }

        if (!matchBools[11]) {
            defend.setWidth(0);
            defend.setTextColor(0);
            defend.setText("");
        } else {
            defend.setWidth(200);
            defend.setText(matchShow.get(35).get(position));
        }

        if (!matchBools[12]) {
            defenseRating.setWidth(0);
            defenseRating.setTextColor(0);
            defenseRating.setText("");
        } else {
            defenseRating.setWidth(200);
            defenseRating.setText(matchShow.get(36).get(position));
        }

        if (!matchBools[13]) {
            scale.setWidth(0);
            scale.setTextColor(0);
            scaleFailed.setWidth(0);
            scaleFailed.setTextColor(0);
            scale.setText("");
            scaleFailed.setText("");
        } else {
            scale.setWidth(200);
            scaleFailed.setWidth(200);
            scale.setText(matchShow.get(37).get(position));
            scaleFailed.setText(matchShow.get(38).get(position));
        }

        if (!matchBools[27]) {
            stuc.setWidth(0);
            stuc.setTextColor(0);
            stuc.setText("");
        } else {
            stuc.setWidth(200);
            stuc.setText(matchShow.get(39).get(position));
        }

        if (!matchBools[28]) {
            tipp.setWidth(0);
            tipp.setTextColor(0);
            tipp.setText("");
        } else {
            tipp.setWidth(200);
            tipp.setText(matchShow.get(40).get(position));
        }

        if (!matchBools[29]) {
            ded.setWidth(0);
            ded.setTextColor(0);
            ded.setText("");
        } else {
            ded.setWidth(200);
            ded.setText(matchShow.get(41).get(position));
        }

        if (!matchBools[30]) {
            intermitt.setWidth(0);
            intermitt.setTextColor(0);
            intermitt.setText("");
        } else {
            intermitt.setWidth(200);
            intermitt.setText(matchShow.get(42).get(position));
        }

        if (!matchBools[31]) {
            note.setHeight(0);
            note.setTextColor(0);
            note.setText("");
        } else {
            note.setWidth(600);
            note.setText(matchShow.get(45).get(position));
        }

        if (!matchBools[32]) {
            rzFoul.setWidth(0);
            rzFoul.setTextColor(0);
            rzFoul.setText("");
        } else {
            rzFoul.setWidth(200);
            rzFoul.setText(matchShow.get(29).get(position));
        }
        if (!matchBools[33]) {
            rzClearGear.setWidth(0);
            rzClearGear.setTextColor(0);
            rzClearGear.setText("");
        } else {
            rzClearGear.setWidth(200);
            rzClearGear.setText(matchShow.get(30).get(position));
        }
        if (!matchBools[34]) {
            rzDropGear.setWidth(0);
            rzDropGear.setTextColor(0);
            rzDropGear.setText("");
        } else {
            rzDropGear.setWidth(200);
            rzDropGear.setText(matchShow.get(31).get(position));
        }
        if (!matchBools[35]) {
            gearsStuck.setWidth(0);
            gearsStuck.setTextColor(0);
            gearsStuck.setText("");
        } else {
            gearsStuck.setWidth(200);
            gearsStuck.setText(matchShow.get(43).get(position));
        }
        if (!matchBools[36]) {
            noChoking.setWidth(0);
            noChoking.setTextColor(0);
            noChoking.setText("");
        } else {
            noChoking.setWidth(200);
            noChoking.setText(matchShow.get(44).get(position));
        }
        /*if (!matchBools[37]) {
            trueHolder6.setWidth(0);
            trueHolder6.setTextColor(0);
            starting.setText("");
        } else {
            trueHolder6.setWidth(200);
            starting.setText(matchShow.get(4).get(position));
        }*/

        return v;
    }
}
