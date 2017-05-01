package org.teamtators.tatorsheetshepherd;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> highAccAuto   =  new ArrayList<>();
    ArrayList<String> highAccTele   =  new ArrayList<>();
    ArrayList<String> color         =  new ArrayList<>();
    ArrayList<String> cross         =  new ArrayList<>();
    ArrayList<String> ded           =  new ArrayList<>();
    ArrayList<String> defend        =  new ArrayList<>();
    ArrayList<String> defenseRating =  new ArrayList<>();
    ArrayList<String> gearFailA     =  new ArrayList<>();
    ArrayList<String> gearFailT     =  new ArrayList<>();
    ArrayList<String> gearPlace     =  new ArrayList<>();
    ArrayList<String> gearPerPeg1   =  new ArrayList<>();
    ArrayList<String> gearPerPeg2   =  new ArrayList<>();
    ArrayList<String> gearPerPeg3   =  new ArrayList<>();
    ArrayList<String> highCyclesTele=  new ArrayList<>();
    ArrayList<String> highShotsAuto =  new ArrayList<>();
    ArrayList<String> highShotsTele =  new ArrayList<>();
    ArrayList<String> hopper1Auto   =  new ArrayList<>();
    ArrayList<String> hopper2Auto   =  new ArrayList<>();
    ArrayList<String> hopper3Auto   =  new ArrayList<>();
    ArrayList<String> hopper4Auto   =  new ArrayList<>();
    ArrayList<String> hopper1Tele   =  new ArrayList<>();
    ArrayList<String> hopper2Tele   =  new ArrayList<>();
    ArrayList<String> hopper3Tele   =  new ArrayList<>();
    ArrayList<String> hopper4Tele   =  new ArrayList<>();
    ArrayList<String> hopper5Tele   =  new ArrayList<>();
    ArrayList<String> intermitt     =  new ArrayList<>();
    ArrayList<String> lowDumpAuto   =  new ArrayList<>();
    ArrayList<String> lowDumpTele   =  new ArrayList<>();
    ArrayList<String> matchNum      =  new ArrayList<>();
    ArrayList<String> noGears       =  new ArrayList<>();
    ArrayList<String> note          =  new ArrayList<>();
    ArrayList<String> scale         =  new ArrayList<>();
    ArrayList<String> scaleFailed   =  new ArrayList<>();
    ArrayList<String> scouter       =  new ArrayList<>();
    ArrayList<String> goodPick      =  new ArrayList<>();
    ArrayList<String> starting      =  new ArrayList<>();
    ArrayList<String> stuc          =  new ArrayList<>();
    ArrayList<String> teamNum       =  new ArrayList<>();
    ArrayList<String> tipp          =  new ArrayList<>();
    ArrayList<String> tooFastAuto   =  new ArrayList<>();
    ArrayList<String> tooFastTele   =  new ArrayList<>();

    ArrayList<String> retrievalFoul  =  new ArrayList<>();
    ArrayList<String> retrievalClearGear  =  new ArrayList<>();
    ArrayList<String> retrievalDropGear  =  new ArrayList<>();
    ArrayList<String> gearStuckBot  =  new ArrayList<>();
    ArrayList<String> avoidChoke  =  new ArrayList<>();
    //ArrayList<String> truePHolder6  =  new ArrayList<>();

    private int mSelected = -1;

    public boolean metch = false;
    public boolean ayvg = false;
    public String indvTeam = "N/A";
    ArrayList<String> pl = new ArrayList<>();
    public static boolean[] layoutBools = new boolean[4];
    public static ArrayList<ArrayList<String>> matchList = new ArrayList<>();
    public static ArrayList<ArrayList<String>> avgList = new ArrayList<>(20);
    GridView listview;
    Button btnDownloadMatch;
    Button btnDownloadAvg;
    Button btnClear;
    Button btnFilter;
    final CharSequence[] filterTable = {"View One Team "," Match "," GoodPick ",
            " AutoGearPlacement "," FailAutoGear "," CrossedLine ",
            " NoAutoGear "," GearsOnPeg1 "," GearsOnPeg2 "," GearsOnPeg3 ",
            " TeleGearFail "," DidDefense "," DefenseRating "," Scaled/Failed ",
            " StartingPosition "," Scouter "," Hoppers "," AllianceColor ",
            " AutoLowDumps "," AutoHighShots "," AutoAccuracy"," AutoTooFast ",
            " TeleLowDumps "," TeleHighShots "," HighCycles "," TeleAccuracy ",
            " TeleTooFast "," Stuck "," Tipped "," Dead "," Intermittent ",
            " Notes ","RetrievalFouls","RetrievalClearedGears","RetrievalDroppedGear","GearJams",
            "TrafficJams"};//,"Placeholder"};
    final CharSequence[] smortTable = {" Match "," Good Pick "," Auto Gears ",
            " Fail Auto Gears "," No Auto Gears"," Teleop Gears "," Fail Teleop Gears ",
            " Did Defense "," Defense Rating "," Scaled Good "," Scaled Bad ",
            " Hoppers "," Auto High Shots "," Auto Accuracy "," Teleop High Shots ",
            " Teleop Accuracy "," Stuck "," Tipped "," Dead "," Intermittent ",
            " Retrieval Fouls "," Cleared Gears "," Dropped Gears "," Gear Jams "," Avoided Traffic "};

    final static boolean[] smortBool = new boolean[25];
    final static boolean[] matchBool = new boolean[37];
    final static boolean[] avgBool = new boolean[37];
    final ArrayList<Integer> selectedItems = new ArrayList<>();//USED ONLY IN THE FILTERING METHOD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            setContentView(R.layout.activity_main);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_NORMAL){
            setContentView(R.layout.activity_main);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE){
            setContentView(R.layout.activity_main);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main);
        }
        listview = (GridView) findViewById(R.id.listview);
        btnDownloadMatch = (Button) findViewById(R.id.btnDownloadMatch);
        btnDownloadAvg = (Button) findViewById(R.id.btnDownloadAvg);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownloadMatch.setEnabled(true);
            btnDownloadAvg.setEnabled(true);
            btnFilter.setEnabled(true);
        } else {
            btnDownloadMatch.setEnabled(false);
            btnDownloadAvg.setEnabled(false);
            btnFilter.setEnabled(false);
            Toast.makeText(getApplicationContext(), "There is no network connection. Please connect " +
                    "to a network to use this application.", Toast.LENGTH_LONG).show();
        }
    }

    public void singleTeamNumberHandler(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Input a Team Number");
        alert.setMessage("Hit 'Cancel' if you want to view all of the teams");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                indvTeam = input.getText().toString();
                indvTeam = indvTeam.replaceAll("[^\\d.]", "");
                if(indvTeam.equals("")) indvTeam = "N/A";
                btnSort(listview);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                indvTeam = "N/A";

                btnSort(listview);
            }
        });
        if(matchBool[0]) alert.show();
        else btnSort(listview);
    }

    public void buttonMatchHandler(View view) {
        buttonMatchClearHandler(view);
        for(int i = 0; i < matchBool.length; i++){
            matchBool[i] = true;
        }
        new DownloadWebpage(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processMatch(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1j95MSzB2gVebuPpJT0AVd_0NvbcvqtXmqhytMKfS4J0");
    }

    public void buttonMatchForFilter(View view) {
        new DownloadWebpage(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processMatch(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1j95MSzB2gVebuPpJT0AVd_0NvbcvqtXmqhytMKfS4J0");
    }

    public void buttonAvgHandler(View view){
        buttonAVGClearHandler(view);
        for(int i = 0; i < avgBool.length; i++){
            avgBool[i] = true;
        }
        new DownloadWebpage(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processAVG(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1GhBUrw-7G0Mm6U0SmcU11m9Z9juLxwFAY46SsW7r-r8");
    }
    public void buttonAvgForFilter(View view){
        new DownloadWebpage(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processAVG(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1GhBUrw-7G0Mm6U0SmcU11m9Z9juLxwFAY46SsW7r-r8");
    }

    public void btnSort(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Not Yet Functional, Please Press 'No Thanks'");
        //builder.setTitle("What would you like to sort by?");
        builder.setMultiChoiceItems(smortTable, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    if ((mSelected != -1) && (mSelected != which)) {
                        smortBool[which] = true;
                        final int oldVal = mSelected;
                        smortBool[oldVal] = false;
                        final AlertDialog alert = (AlertDialog)dialog;
                        final ListView list = alert.getListView();
                        list.setItemChecked(oldVal, false);
                    }
                    mSelected = which;
                } else
                    mSelected = -1;
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(metch){
                    buttonMatchClearHandler(listview);
                    buttonMatchForFilter(listview);
                } else {
                    buttonAVGClearHandler(listview);
                    buttonAvgForFilter(listview);
                }
            }
        });

        builder.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(metch){
                    buttonMatchClearHandler(listview);
                    buttonMatchForFilter(listview);
                } else {
                    buttonAVGClearHandler(listview);
                    buttonAvgForFilter(listview);
                }
            }
        });
        //builder.create();
        //builder.show();
        if(metch){
            buttonMatchClearHandler(listview);
            buttonMatchForFilter(listview);
        } else {
            buttonAVGClearHandler(listview);
            buttonAvgForFilter(listview);
        }
    }

    public void sortForSmortMatch(){
    }


    public void sortForSmortAVG(){
    }

    public void buttonMatchClearHandler(View view){
        for(ArrayList<String> match: matchList) {
            match.clear();
        }
        matchList.clear();
        for(ArrayList<String> avg: avgList) {
            avg.clear();
        }
        avgList.clear();
        final MatchAdapter adapter;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            adapter = new MatchAdapter(this, R.layout.match_team_small, matchList);
            layoutBools[0] = true;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_NORMAL){
            adapter = new MatchAdapter(this, R.layout.match_team, matchList);
            layoutBools[0] = false;
            layoutBools[1] = true;
            layoutBools[2] = false;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE){
            adapter = new MatchAdapter(this, R.layout.match_team_large, matchList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = true;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            adapter = new MatchAdapter(this, R.layout.match_team_xlarge, matchList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = true;
        } else {
            adapter = new MatchAdapter(this, R.layout.match_team_small, matchList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = false;
        }
        listview.setAdapter(adapter);
    }

    public void buttonAVGClearHandler(View view){
        for(ArrayList<String> avg: avgList) {
            avg.clear();
        }
        for(ArrayList<String> match: matchList) {
            match.clear();
        }
        matchList.clear();
        avgList.clear();
        final AVGAdapter adapter;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            adapter = new AVGAdapter(this, R.layout.avg_team_small, avgList);
            layoutBools[0] = true;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_NORMAL){
            adapter = new AVGAdapter(this, R.layout.avg_team, avgList);
            layoutBools[0] = false;
            layoutBools[1] = true;
            layoutBools[2] = false;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE){
            adapter = new AVGAdapter(this, R.layout.avg_team_large, avgList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = true;
            layoutBools[3] = false;
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            adapter = new AVGAdapter(this, R.layout.avg_team_xlarge, avgList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = true;
        } else {
            adapter = new AVGAdapter(this, R.layout.avg_team_small, avgList);
            layoutBools[0] = false;
            layoutBools[1] = false;
            layoutBools[2] = false;
            layoutBools[3] = false;
        }

        listview.setAdapter(adapter);
    }

    public void buttonFilterHandler(View view){
        for(int i = 0; i < matchBool.length; i++){
            matchBool[i] = false;
        }
        for(int i = 0; i < avgBool.length; i++){
            avgBool[i] = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose The Data You Wand to View");
        builder.setMultiChoiceItems(filterTable, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), filterTable[which] + "checked", Toast.LENGTH_LONG).show();
                    selectedItems.add(which);
                    matchBool[which] = true;
                    avgBool[which] = true;
                } else if(selectedItems.contains(which)){
                    Toast.makeText(getApplicationContext(), filterTable[which] + "unchecked", Toast.LENGTH_LONG).show();
                    selectedItems.remove(Integer.valueOf(which));
                    matchBool[which] = false;
                    avgBool[which] = false;
                }
            }
        });

        builder.setPositiveButton("Match Data", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                singleTeamNumberHandler(listview);
                metch = true;
                ayvg = false;
                indvTeam = "N/A";
            }
        });

        builder.setNegativeButton("Average Data", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                singleTeamNumberHandler(listview);
                ayvg = true;
                metch = false;
                indvTeam = "N/A";
            }
        });
        builder.create();
        builder.show();
    }

    public void processMatch(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            teamNum.add("Team#");
            matchNum.add("Match#");
            goodPick.add("GoodPick");
            scouter.add("Scouter");
            starting.add("StartPos");
            color.add("Alliance");
            noGears.add("NoAutoGear");
            gearFailA.add("FailedGearAuto");
            gearPlace.add("GoodGearAuto");
            hopper1Auto.add("Hopper1Auto");
            hopper2Auto.add("Hopper2Auto");
            hopper3Auto.add("Hopper3Auto");
            hopper4Auto.add("Hopper4Auto");
            cross.add("LineCrossed");
            lowDumpAuto.add("LowDumpsAuto");
            highShotsAuto.add("HighShotsAuto");
            highAccAuto.add("AutoAccuracy");
            tooFastAuto.add("TooFastInAuto");
            hopper1Tele.add("Hopper1Tele");
            hopper2Tele.add("Hopper2Tele");
            hopper3Tele.add("Hopper3Tele");
            hopper4Tele.add("Hopper4Tele");
            hopper5Tele.add("Hopper5Tele");
            lowDumpTele.add("LowDumpsTele");
            highShotsTele.add("HighShotsTele");
            highCyclesTele.add("HighCyclesTele");
            highAccTele.add("TeleAccuracy");
            tooFastTele.add("TooFastInTele");
            gearFailT.add("TeleGearFails");
            gearPerPeg1.add("GearsOnPeg1");
            gearPerPeg2.add("GearsOnPeg2");
            gearPerPeg3.add("GearsOnPeg3");
            defend.add("DidDefense");
            defenseRating.add("DefensiveRating");
            scale.add("Scaled");
            scaleFailed.add("FailedScaling");
            stuc.add("Stuck");
            tipp.add("Tipped");
            ded.add("Dead");
            intermitt.add("Intermittent");
            note.add("Notes");

            retrievalFoul.add("RetrievalZoneFoul");
            retrievalClearGear.add("RetrievalZoneClearedGears");
            retrievalDropGear.add("RetrievalZoneDroppedGears");
            gearStuckBot.add("GearsGotStuck");
            avoidChoke.add("GotTrafficJammed");
            //truePHolder6.add("Placeholder");

            for(int r = 0; r < rows.length(); ++r){
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                int team = columns.getJSONObject(0).getInt("v");
                String tem = Integer.toString(team);

                int match = columns.getJSONObject(1).getInt("v");
                String mat = Integer.toString(match);

                int secondPick = columns.getJSONObject(40).getInt("v");
                String secPic = Integer.toString(secondPick);

                String scout = columns.getJSONObject(2).getString("v");

                String startingPos = columns.getJSONObject(4).getString("v");

                String alliance = columns.getJSONObject(5).getString("v");

                int noGear = columns.getJSONObject(12).getInt("v");
                String nG = Integer.toString(noGear);

                int failGear = columns.getJSONObject(13).getInt("v");
                String fG = Integer.toString(failGear);

                int placeGear = columns.getJSONObject(6).getInt("v");
                String pG = Integer.toString(placeGear);

                int autoHopper1 = columns.getJSONObject(8).getInt("v");
                String aH1 = Integer.toString(autoHopper1);

                int autoHopper2 = columns.getJSONObject(9).getInt("v");
                String aH2 = Integer.toString(autoHopper2);

                int autoHopper3 = columns.getJSONObject(10).getInt("v");
                String aH3 = Integer.toString(autoHopper3);

                int autoHopper4 = columns.getJSONObject(11).getInt("v");
                String aH4 = Integer.toString(autoHopper4);

                int crossed = columns.getJSONObject(14).getInt("v");
                String croS = Integer.toString(crossed);

                int autoLow = columns.getJSONObject(15).getInt("v");
                String aLow = Integer.toString(autoLow);

                int autoHigh = columns.getJSONObject(16).getInt("v");
                String aHigh = Integer.toString(autoHigh);

                String autocracy = columns.getJSONObject(17).getInt("v") + "%";

                int autoTooFast = columns.getJSONObject(7).getInt("v");
                String aTF = Integer.toString(autoTooFast);

                int teleHopper1 = columns.getJSONObject(18).getInt("v");
                String tH1 = Integer.toString(teleHopper1);

                int teleHopper2 = columns.getJSONObject(19).getInt("v");
                String tH2 = Integer.toString(teleHopper2);

                int teleHopper3 = columns.getJSONObject(20).getInt("v");
                String tH3 = Integer.toString(teleHopper3);

                int teleHopper4 = columns.getJSONObject(21).getInt("v");
                String tH4 = Integer.toString(teleHopper4);

                int teleHopper5 = columns.getJSONObject(22).getInt("v");
                String tH5 = Integer.toString(teleHopper5);

                int teleLow = columns.getJSONObject(24).getInt("v");
                String tLow = Integer.toString(teleLow);

                int teleHigh = columns.getJSONObject(25).getInt("v");
                String tHigh = Integer.toString(teleHigh);

                int teleHighCycles = columns.getJSONObject(26).getInt("v");
                String tHighC = Integer.toString(teleHighCycles);

                String telecracy = columns.getJSONObject(31).getInt("v") + "%";

                int teleTooFast = columns.getJSONObject(23).getInt("v");
                String tTF = Integer.toString(teleTooFast);

                int failDrop = columns.getJSONObject(27).getInt("v");
                String fDrop = Integer.toString(failDrop);

                int gear1 = columns.getJSONObject(28).getInt("v");
                String g1 = Integer.toString(gear1);

                int gear2 = columns.getJSONObject(29).getInt("v");
                String g2 = Integer.toString(gear2);

                int gear3 = columns.getJSONObject(30).getInt("v");
                String g3 = Integer.toString(gear3);

                int defended = columns.getJSONObject(32).getInt("v");
                String deF = Integer.toString(defended);

                String dRating = columns.getJSONObject(33).getInt("v") + "%";

                int scaled = columns.getJSONObject(34).getInt("v");
                String scaL = Integer.toString(scaled);

                int scaleFail = columns.getJSONObject(35).getInt("v");
                String scaF = Integer.toString(scaleFail);

                int stuck = columns.getJSONObject(36).getInt("v");
                String stuC = Integer.toString(stuck);

                int tipped = columns.getJSONObject(37).getInt("v");
                String tiPP = Integer.toString(tipped);

                int dead = columns.getJSONObject(38).getInt("v");
                String daed = Integer.toString(dead);

                int intermittent = columns.getJSONObject(39).getInt("v");
                String inte = Integer.toString(intermittent);

                String notes = columns.getJSONObject(41).getString("v");


                int rzF = columns.getJSONObject(44).getInt("v");
                String rzFoul = Integer.toString(rzF);

                int rzCG = columns.getJSONObject(42).getInt("v");
                String rzClear = Integer.toString(rzCG);

                int rzD = columns.getJSONObject(43).getInt("v");
                String rzDrop = Integer.toString(rzD);

                int true4 = columns.getJSONObject(45).getInt("v");
                String truePlace4 = Integer.toString(true4);

                int true5 = columns.getJSONObject(46).getInt("v");
                String truePlace5 = Integer.toString(true5);

                //String truePlace6 = columns.getJSONObject(47?).getString("v");

                teamNum.add(tem);
                matchNum.add(mat);
                goodPick.add(secPic);
                scouter.add(scout);
                starting.add(startingPos);
                color.add(alliance);
                noGears.add(nG);
                gearFailA.add(fG);
                gearPlace.add(pG);
                hopper1Auto.add(aH1);
                hopper2Auto.add(aH2);
                hopper3Auto.add(aH3);
                hopper4Auto.add(aH4);
                cross.add(croS);
                lowDumpAuto.add(aLow);
                highShotsAuto.add(aHigh);
                highAccAuto.add(autocracy);
                tooFastAuto.add(aTF);
                hopper1Tele.add(tH1);
                hopper2Tele.add(tH2);
                hopper3Tele.add(tH3);
                hopper4Tele.add(tH4);
                hopper5Tele.add(tH5);
                lowDumpTele.add(tLow);
                highShotsTele.add(tHigh);
                highCyclesTele.add(tHighC);
                highAccTele.add(telecracy);
                tooFastTele.add(tTF);
                gearFailT.add(fDrop);
                gearPerPeg1.add(g1);
                gearPerPeg2.add(g2);
                gearPerPeg3.add(g3);
                defend.add(deF);
                defenseRating.add(dRating);
                scale.add(scaL);
                scaleFailed.add(scaF);
                stuc.add(stuC);
                tipp.add(tiPP);
                ded.add(daed);
                intermitt.add(inte);
                note.add(notes);

                retrievalFoul.add(rzFoul);
                retrievalClearGear.add(rzClear);
                retrievalDropGear.add(rzDrop);
                gearStuckBot.add(truePlace4);
                avoidChoke.add(truePlace5);
                //truePHolder6.add(truePlace6);

                pl.add("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int test = teamNum.size();
        for(int i = test - 1; i >= 1; i--){
            if(indvTeam.equals("N/A") || teamNum.get(i).equals(indvTeam)) {
            } else {
                teamNum.remove(i);
                matchNum.remove(i);
                goodPick.remove(i);
                scouter.remove(i);
                starting.remove(i);
                color.remove(i);
                noGears.remove(i);
                gearFailA.remove(i);
                gearPlace.remove(i);
                hopper1Auto.remove(i);
                hopper2Auto.remove(i);
                hopper3Auto.remove(i);
                hopper4Auto.remove(i);
                hopper1Tele.remove(i);
                hopper2Tele.remove(i);
                hopper3Tele.remove(i);
                hopper4Tele.remove(i);
                hopper5Tele.remove(i);
                cross.remove(i);
                lowDumpAuto.remove(i);
                highShotsAuto.remove(i);
                highAccAuto.remove(i);
                tooFastAuto.remove(i);
                lowDumpTele.remove(i);
                highShotsTele.remove(i);
                highCyclesTele.remove(i);
                highAccTele.remove(i);
                tooFastTele.remove(i);
                gearFailT.remove(i);
                retrievalFoul.remove(i);
                retrievalClearGear.remove(i);
                retrievalDropGear.remove(i);
                gearPerPeg1.remove(i);
                gearPerPeg2.remove(i);
                gearPerPeg3.remove(i);
                defend.remove(i);
                defenseRating.remove(i);
                scale.remove(i);
                scaleFailed.remove(i);
                stuc.remove(i);
                tipp.remove(i);
                ded.remove(i);
                intermitt.remove(i);
                gearStuckBot.remove(i);
                avoidChoke.remove(i);
            }
        }

        sortForSmortMatch();

        for(int i = teamNum.size() - 1; i < 45; i++){
            teamNum.add("");
            matchNum.add("");
            goodPick.add("");
            scouter.add("");
            starting.add("");
            color.add("");
            noGears.add("");
            gearFailA.add("");
            gearPlace.add("");
            hopper1Auto.add("");
            hopper2Auto.add("");
            hopper3Auto.add("");
            hopper4Auto.add("");
            hopper1Tele.add("");
            hopper2Tele.add("");
            hopper3Tele.add("");
            hopper4Tele.add("");
            hopper5Tele.add("");
            cross.add("");
            lowDumpAuto.add("");
            highShotsAuto.add("");
            highAccAuto.add("");
            tooFastAuto.add("");
            lowDumpTele.add("");
            highShotsTele.add("");
            highCyclesTele.add("");
            highAccTele.add("");
            tooFastTele.add("");
            gearFailT.add("");
            retrievalFoul.add("");
            retrievalClearGear.add("");
            retrievalDropGear.add("");
            gearPerPeg1.add("");
            gearPerPeg2.add("");
            gearPerPeg3.add("");
            defend.add("");
            defenseRating.add("");
            scale.add("");
            scaleFailed.add("");
            stuc.add("");
            tipp.add("");
            ded.add("");
            intermitt.add("");
            gearStuckBot.add("");
            avoidChoke.add("");
            pl.add("");
        }

        matchList.add(teamNum);
        matchList.add(matchNum);
        matchList.add(goodPick);
        matchList.add(scouter);
        matchList.add(starting);
        matchList.add(color);
        matchList.add(noGears);
        matchList.add(gearFailA);
        matchList.add(gearPlace);
        matchList.add(hopper1Auto);
        matchList.add(hopper2Auto);
        matchList.add(hopper3Auto);
        matchList.add(hopper4Auto);
        matchList.add(hopper1Tele);
        matchList.add(hopper2Tele);
        matchList.add(hopper3Tele);
        matchList.add(hopper4Tele);
        matchList.add(hopper5Tele);
        matchList.add(cross);

        //matchList.add(truePHolder6);

        matchList.add(lowDumpAuto);
        matchList.add(highShotsAuto);
        matchList.add(highAccAuto);
        matchList.add(tooFastAuto);
        matchList.add(lowDumpTele);
        matchList.add(highShotsTele);
        matchList.add(highCyclesTele);
        matchList.add(highAccTele);
        matchList.add(tooFastTele);
        matchList.add(gearFailT);

        matchList.add(retrievalFoul);
        matchList.add(retrievalClearGear);
        matchList.add(retrievalDropGear);

        matchList.add(gearPerPeg1);
        matchList.add(gearPerPeg2);
        matchList.add(gearPerPeg3);
        matchList.add(defend);
        matchList.add(defenseRating);
        matchList.add(scale);
        matchList.add(scaleFailed);
        matchList.add(stuc);
        matchList.add(tipp);
        matchList.add(ded);
        matchList.add(intermitt);

        matchList.add(gearStuckBot);
        matchList.add(avoidChoke);

        matchList.add(note);

        for(int i = 46; i < teamNum.size() - 1; i++){
            matchList.add(pl);
        }

        final MatchAdapter adapter;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            adapter = new MatchAdapter(this, R.layout.match_team_small, matchList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_NORMAL){
            adapter = new MatchAdapter(this, R.layout.match_team, matchList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE){
            adapter = new MatchAdapter(this, R.layout.match_team_large, matchList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            adapter = new MatchAdapter(this, R.layout.match_team_xlarge, matchList);
        } else {
            adapter = new MatchAdapter(this, R.layout.match_team, matchList);
        }
        listview.setAdapter(adapter);
        indvTeam = "N/A";
    }

    public void processAVG(JSONObject object){ //TODO: SEE IF PLACEHOLDERS ARE NEEDED IN processAVG()
        try {
            JSONArray rows = object.getJSONArray("rows");
            teamNum.add("Team#");
            matchNum.add("MatchesPlayed");
            gearPlace.add("GoodGearAuto");
            gearFailA.add("FailedGearAuto");
            cross.add("LineCrossed");
            gearFailT.add("TeleGearFails");
            gearPerPeg1.add("GearsOnPeg1");
            gearPerPeg2.add("GearsOnPeg2");
            gearPerPeg3.add("GearsOnPeg3");
            scale.add("Scaled");
            scaleFailed.add("FailedScaling");
            defend.add("DidDefense");
            defenseRating.add("DefensiveRating");


            for(int r = 1; r < rows.length(); r++){
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                int team = columns.getJSONObject(0).getInt("v");
                String tem = Integer.toString(team);

                int match = columns.getJSONObject(1).getInt("v");
                String mat = Integer.toString(match);

                int failGear = columns.getJSONObject(3).getInt("v");
                String fG = Integer.toString(failGear);

                int placeGear = columns.getJSONObject(2).getInt("v");
                String pG = Integer.toString(placeGear);

                int crossed = columns.getJSONObject(4).getInt("v");
                String croS = Integer.toString(crossed);

                int gear1 = columns.getJSONObject(5).getInt("v");
                String g1 = Integer.toString(gear1);

                int gear2 = columns.getJSONObject(6).getInt("v");
                String g2 = Integer.toString(gear2);

                int gear3 = columns.getJSONObject(7).getInt("v");
                String g3 = Integer.toString(gear3);

                int defended = columns.getJSONObject(11).getInt("v");
                String deF = Integer.toString(defended);

                String dRating = (int) columns.getJSONObject(12).getDouble("v") + "%";

                int scaled = columns.getJSONObject(8).getInt("v");
                String scaL = Integer.toString(scaled);

                int scaleFail = columns.getJSONObject(10).getInt("v");
                String scaF = Integer.toString(scaleFail);

                int failDrop = columns.getJSONObject(9).getInt("v");
                String fDrop = Integer.toString(failDrop);

                String plholder = " ";


                teamNum.add(tem);
                matchNum.add(mat);
                gearFailA.add(fG);
                gearPlace.add(pG);
                cross.add(croS);
                gearFailT.add(fDrop);
                gearPerPeg1.add(g1);
                gearPerPeg2.add(g2);
                gearPerPeg3.add(g3);
                defend.add(deF);
                defenseRating.add(dRating);
                scale.add(scaL);
                scaleFailed.add(scaF);
                pl.add(plholder);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int test = teamNum.size();
        for(int i = test - 1; i >= 1; i--) {
            if (indvTeam.equals("N/A") || teamNum.get(i).equals(indvTeam)) {
            } else {
                teamNum.remove(i);
                matchNum.remove(i);
                gearFailA.remove(i);
                gearPlace.remove(i);
                cross.remove(i);
                gearFailT.remove(i);
                gearPerPeg1.remove(i);
                gearPerPeg2.remove(i);
                gearPerPeg3.remove(i);
                defend.remove(i);
                defenseRating.remove(i);
                scale.remove(i);
                scaleFailed.remove(i);
            }
        }

        sortForSmortAVG();

        for(int i = teamNum.size() - 1; i < 45; i++){
            teamNum.add("");
            matchNum.add("");
            gearFailA.add("");
            gearPlace.add("");
            cross.add("");
            gearFailT.add("");
            gearPerPeg1.add("");
            gearPerPeg2.add("");
            gearPerPeg3.add("");
            defend.add("");
            defenseRating.add("");
            scale.add("");
            scaleFailed.add("");
        }

        avgList.add(teamNum);
        avgList.add(matchNum);
        avgList.add(gearFailA);
        avgList.add(gearPlace);
        avgList.add(cross);
        avgList.add(gearFailT);
        avgList.add(gearPerPeg1);
        avgList.add(gearPerPeg2);
        avgList.add(gearPerPeg3);
        avgList.add(defend);
        avgList.add(defenseRating);
        avgList.add(scale);
        avgList.add(scaleFailed);

        for(int i = 12; i < teamNum.size() - 1; i++){
            avgList.add(pl);
        }

        final AVGAdapter adapter;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            adapter = new AVGAdapter(this, R.layout.avg_team_small, avgList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_NORMAL){
            adapter = new AVGAdapter(this, R.layout.avg_team, avgList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE){
            adapter = new AVGAdapter(this, R.layout.avg_team_large, avgList);
        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            adapter = new AVGAdapter(this, R.layout.avg_team_xlarge, avgList);
        } else {
            adapter = new AVGAdapter(this, R.layout.avg_team, avgList);
        }
        listview.setAdapter(adapter);
        indvTeam = "N/A";
    }
}
