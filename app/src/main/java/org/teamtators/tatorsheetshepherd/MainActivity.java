package org.teamtators.tatorsheetshepherd;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private static final String DEBUG_TAG = "HttpExample";
    ArrayList<AvgView> avgTeams = new ArrayList<AvgView>();
    ArrayList<Accuracy> highAccAuto = new ArrayList<Accuracy>();
    ArrayList<Accuracy> highAccTele = new ArrayList<Accuracy>();
    ArrayList<AllianceColor> color = new ArrayList<AllianceColor>();
    ArrayList<CrossedLine> cross = new ArrayList<CrossedLine>();
    ArrayList<Dead> ded = new ArrayList<Dead>();
    ArrayList<Defended> defend = new ArrayList<Defended>();
    ArrayList<DefenseRating> defenseRating = new ArrayList<DefenseRating>();
    ArrayList<GearFail> gearFail = new ArrayList<GearFail>();
    ArrayList<GearPlacement> gearPlace = new ArrayList<GearPlacement>();
    ArrayList<GearsPerPeg> gearPerPeg1 = new ArrayList<GearsPerPeg>();
    ArrayList<GearsPerPeg> gearPerPeg2 = new ArrayList<GearsPerPeg>();
    ArrayList<GearsPerPeg> gearPerPeg3 = new ArrayList<GearsPerPeg>();
    ArrayList<HighCycles> highCyclesTele = new ArrayList<HighCycles>();
    ArrayList<HighShots> highShotsAuto = new ArrayList<HighShots>();
    ArrayList<HighShots> highShotsTele = new ArrayList<HighShots>();
    ArrayList<HopperTripped> hopper1Auto = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper2Auto = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper3Auto = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper4Auto = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper1Tele = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper2Tele = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper3Tele = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper4Tele = new ArrayList<HopperTripped>();
    ArrayList<HopperTripped> hopper5Tele = new ArrayList<HopperTripped>();
    ArrayList<Intermittent> intermitt = new ArrayList<Intermittent>();
    ArrayList<LowDumps> lowDumpAuto = new ArrayList<LowDumps>();
    ArrayList<LowDumps> lowDumpTele = new ArrayList<LowDumps>();
    ArrayList<MatchNumber> matchNum = new ArrayList<MatchNumber>();
    ArrayList<NoGear> noGears = new ArrayList<NoGear>();
    ArrayList<Notes> note = new ArrayList<Notes>();
    ArrayList<Scaled> scale = new ArrayList<Scaled>();
    ArrayList<ScaleFail> scaleFailed = new ArrayList<ScaleFail>();
    ArrayList<ScoutName> scouter = new ArrayList<ScoutName>();
    ArrayList<SecondPick> goodPick = new ArrayList<SecondPick>();
    ArrayList<StartPos> starting = new ArrayList<StartPos>();
    ArrayList<Stuck> stuc = new ArrayList<Stuck>();
    ArrayList<TeamNumber> teamNum = new ArrayList<TeamNumber>();
    ArrayList<Tipped> tipp = new ArrayList<Tipped>();
    ArrayList<TooFast> tooFastAuto = new ArrayList<TooFast>();
    ArrayList<TooFast> tooFastTele = new ArrayList<TooFast>();

    ListView listview;
    Button btnDownloadMatch;
    Button btnDownloadAvg;
    Button btnClear;
    Button btnFilter;
    String matchSheet = "1I7u5JiijpOV7-A_RJ-mEj0pqZ0Ib_P5l6UEmY08y8Ec";
    String avgSheet = "";
    final CharSequence[] filterTable = {" Team "," Match "," GoodPick "," Scouter "," StartingPos "," Alliance "," NoAutoGear "," FailAutoGear ",
            " AutoGearPlacement "," Hoppers "," AutoCrossLine "," AutoLowDumps "," AutoHighShots "," AutoAccuracy",
            " AutoTooFast "," TeleLowDumps ", " TeleHighShots "," HighCycles "," TeleAccuracy "," TelleTooFast ",
            " TeleGearFail "," GearsOnPeg1 "," GearsOnPeg2 "," GearsOnPeg3 "," Defended "," DefenseRating ",
            " Scaled/Failed "," Stuck "," Tipped "," Dead "," Intermittent "," Notes "};
    final boolean[] matchBool = new boolean[32];
    final ArrayList selectedItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        btnDownloadMatch = (Button) findViewById(R.id.btnDownloadMatch);
        btnDownloadAvg = (Button) findViewById(R.id.btnDownloadAvg);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownloadMatch.setEnabled(true);
            btnDownloadAvg.setEnabled(true);
        } else {
            btnDownloadMatch.setEnabled(false);
            btnDownloadAvg.setEnabled(false);
            Toast.makeText(getApplicationContext(), "There is no network connection. Please connect " +
                    "to a network to use this application.", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonMatchHandler(View view) {
        new ProcessJSON(matchSheet).Download();
    }

    public void buttonAvgHandler(View view){
        new ProcessJSON(avgSheet).Download();
    }

    public void buttonClearHandler(View view){
        int amountRemoved = avgTeams.size();
        avgTeams.clear();
        final AvgAdapter adapter = new AvgAdapter(this, R.layout.avg_team, avgTeams);
        listview.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), amountRemoved + " points of data cleared.", Toast.LENGTH_LONG).show();
    }

    public void buttonFilterHandler(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose your Filters");
        builder.setMultiChoiceItems(filterTable, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    selectedItems.add(which);
                    matchBool[which] = true;
                } else if(selectedItems.contains(which)){
                    selectedItems.remove(Integer.valueOf(which));
                    matchBool[which] = false;
                }
            }
        });

        builder.setPositiveButton("Match Data", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                buttonClearHandler(listview);
                buttonMatchHandler(listview);
            }
        });

        builder.setNegativeButton("Average Data", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                buttonClearHandler(listview);
                buttonAvgHandler(listview);
            }
        });
        builder.create();
        builder.show();
        //Toast.makeText(getApplicationContext(), "Not yet implemented.", Toast.LENGTH_LONG).show();
    }

    public void processMatch(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");

            for(int r = 1; r < rows.length(); ++r){
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                if(matchBool[0]){
                    int team = columns.getJSONObject(0).getInt("v");
                    TeamNumber a = new TeamNumber(team);
                    teamNum.add(a);
                }
                if(matchBool[1]){
                    int match = columns.getJSONObject(1).getInt("v");
                    MatchNumber a = new MatchNumber(match);
                    matchNum.add(a);
                }
                if(matchBool[2]){
                    boolean secondPick = columns.getJSONObject(43).getBoolean("v");//TODO: FIGURE OUT HOW THIS PRINTS
                    SecondPick a = new SecondPick(secondPick);
                    goodPick.add(a);
                }
                if(matchBool[3]){
                    String scout = columns.getJSONObject(2).getString("v");
                    ScoutName a = new ScoutName(scout);
                    scouter.add(a);
                }
                if(matchBool[4]){
                    String startingPos = columns.getJSONObject(4).getString("v");
                    StartPos a = new StartPos(startingPos);
                    starting.add(a);
                }
                if(matchBool[5]){
                    String alliance = columns.getJSONObject(5).getString("v");
                    AllianceColor a = new AllianceColor(alliance);
                    color.add(a);
                }
                if(matchBool[6]){
                    boolean noGear = columns.getJSONObject(13).getBoolean("v");
                    NoGear a = new NoGear(noGear);
                    noGears.add(a);
                }
                if(matchBool[7]){
                    boolean failGear = columns.getJSONObject(14).getBoolean("v");
                    GearFail a = new GearFail(failGear);
                    gearFail.add(a);
                }
                if(matchBool[8]){
                    boolean placeGear = columns.getJSONObject(8).getBoolean("v");
                    GearPlacement a = new GearPlacement(placeGear);
                    gearPlace.add(a);
                }
                if(matchBool[9]){
                    boolean autoHopper1 = columns.getJSONObject(9).getBoolean("v");
                    HopperTripped a = new HopperTripped(autoHopper1);
                    hopper1Auto.add(a);
                }
                if(matchBool[9]){
                    boolean autoHopper2 = columns.getJSONObject(10).getBoolean("v");
                    HopperTripped a = new HopperTripped(autoHopper2);
                    hopper2Auto.add(a);
                }
                if(matchBool[9]){
                    boolean autoHopper3 = columns.getJSONObject(11).getBoolean("v");
                    HopperTripped a = new HopperTripped(autoHopper3);
                    hopper3Auto.add(a);
                }
                if(matchBool[9]){
                    boolean autoHopper4 = columns.getJSONObject(12).getBoolean("v");
                    HopperTripped a = new HopperTripped(autoHopper4);
                    hopper4Auto.add(a);
                }
                if(matchBool[9]){
                    boolean crossed = columns.getJSONObject(15).getBoolean("v");
                    CrossedLine a = new CrossedLine(crossed);
                    cross.add(a);
                }
                if(matchBool[10]){
                    int autoLow = columns.getJSONObject(16).getInt("v");
                    LowDumps a = new LowDumps(autoLow);
                    lowDumpAuto.add(a);
                }
                if(matchBool[11]){
                    int autoHigh = columns.getJSONObject(17).getInt("v");
                    HighShots a = new HighShots(autoHigh);
                    highShotsAuto.add(a);
                }
                if(matchBool[12]){
                    String autocracy = columns.getJSONObject(18).getInt("v") + "%";
                    Accuracy a = new Accuracy(autocracy);
                    highAccAuto.add(a);
                }
                if(matchBool[13]){
                    boolean autoTooFast = columns.getJSONObject(25).getBoolean("v");//TODO: CHANGE JSON OBJECT VALUE
                    TooFast a = new TooFast(autoTooFast);
                    tooFastAuto.add(a);
                }
                if(matchBool[9]){
                    boolean teleHopper1 = columns.getJSONObject(20).getBoolean("v");
                    HopperTripped a = new HopperTripped(teleHopper1);
                    hopper1Tele.add(a);
                }
                if(matchBool[9]){
                    boolean teleHopper2 = columns.getJSONObject(21).getBoolean("v");
                    HopperTripped a = new HopperTripped(teleHopper2);
                    hopper2Tele.add(a);
                }
                if(matchBool[9]){
                    boolean teleHopper3 = columns.getJSONObject(22).getBoolean("v");
                    HopperTripped a = new HopperTripped(teleHopper3);
                    hopper3Tele.add(a);
                }
                if(matchBool[9]){
                    boolean teleHopper4 = columns.getJSONObject(23).getBoolean("v");
                    HopperTripped a = new HopperTripped(teleHopper4);
                    hopper4Tele.add(a);
                }
                if(matchBool[9]){
                    boolean teleHopper5 = columns.getJSONObject(24).getBoolean("v");
                    HopperTripped a = new HopperTripped(teleHopper5);
                    hopper5Tele.add(a);
                }
                if(matchBool[14]){
                    int teleLow = columns.getJSONObject(26).getInt("v");
                    LowDumps a = new LowDumps(teleLow);
                    lowDumpTele.add(a);
                }
                if(matchBool[15]){
                    int teleHigh = columns.getJSONObject(27).getInt("v");
                    HighShots a = new HighShots(teleHigh);
                    highShotsTele.add(a);
                }
                if(matchBool[16]){
                    int teleHighCycles = columns.getJSONObject(28).getInt("v");
                    HighCycles a = new HighCycles(teleHighCycles);
                    highCyclesTele.add(a);
                }
                if(matchBool[17]){
                    String telecracy = columns.getJSONObject(33).getInt("v") + "%";
                    Accuracy a = new Accuracy(telecracy);
                    highAccTele.add(a);
                }
                if(matchBool[18]){
                    boolean teleTooFast = columns.getJSONObject(25).getBoolean("v");//TODO: CHANGE JSON OBJECT VALUE
                    TooFast a = new TooFast(teleTooFast);
                    tooFastTele.add(a);
                }
                if(matchBool[19]){
                    boolean failDrop = columns.getJSONObject(29).getBoolean("v");
                    GearFail a = new GearFail(failDrop);
                    gearFail.add(a);
                }
                if(matchBool[20]){
                    int gear1 = columns.getJSONObject(30).getInt("v");
                    GearsPerPeg a = new GearsPerPeg(gear1);
                    gearPerPeg1.add(a);
                }
                if(matchBool[21]){
                    int gear2 = columns.getJSONObject(31).getInt("v");
                    GearsPerPeg a = new GearsPerPeg(gear2);
                    gearPerPeg2.add(a);
                }
                if(matchBool[22]){
                    int gear3 = columns.getJSONObject(32).getInt("v");
                    GearsPerPeg a = new GearsPerPeg(gear3);
                    gearPerPeg3.add(a);
                }
                if(matchBool[23]){
                    boolean defended = columns.getJSONObject(35).getBoolean("v");
                    Defended a = new Defended(defended);
                    defend.add(a);
                }
                if(matchBool[24]){
                    String dRating = columns.getJSONObject(36).getInt("v") + "%";
                    DefenseRating a = new DefenseRating(dRating);
                    defenseRating.add(a);
                }
                if(matchBool[25]){
                    boolean scaled = columns.getJSONObject(37).getBoolean("v");
                    Scaled a = new Scaled(scaled);
                    scale.add(a);
                }
                if(matchBool[26]){
                    boolean scaleFail = columns.getJSONObject(38).getBoolean("v");
                    ScaleFail a = new ScaleFail(scaleFail);
                    scaleFailed.add(a);
                }
                if(matchBool[27]){
                    boolean stuck = columns.getJSONObject(39).getBoolean("v");
                    Stuck a = new Stuck(stuck);
                    stuc.add(a);
                }
                if(matchBool[28]){
                    boolean tipped = columns.getJSONObject(40).getBoolean("v");
                    Tipped a = new Tipped(tipped);
                    tipp.add(a);
                }
                if(matchBool[29]){
                    boolean dead = columns.getJSONObject(41).getBoolean("v");
                    Dead a = new Dead(dead);
                    ded.add(a);
                }
                if(matchBool[30]){
                    boolean intermittent = columns.getJSONObject(42).getBoolean("v");
                    Intermittent a = new Intermittent(intermittent);
                    intermitt.add(a);
                }
                if(matchBool[31]){
                    String notes = columns.getJSONObject(44).getString("v");
                    Notes a = new Notes(notes);
                    note.add(a);
                }

            }
            /*for (int r = 1; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                int avgTeam = columns.getJSONObject(0).getInt("v");
                int lowBarX = columns.getJSONObject(1).getInt("v");
                int chevalX = columns.getJSONObject(3).getInt("v");
                int lowF = columns.getJSONObject(5).getInt("v");
                int chevalF = columns.getJSONObject(6).getInt("v");
                float lowBarT = (float) columns.getJSONObject(2).getInt("v");
                float chevalT = (float) columns.getJSONObject(4).getInt("v");
                AvgView robit = new AvgView(avgTeam, lowBarX, lowBarT, lowF, chevalX, chevalT, chevalF);
                avgTeams.add(robit);
            }*/

            //final AvgAdapter adapter = new AvgAdapter(this, R.layout.avg_team, avgTeams);
            //listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Average View File Downloaded with size " + avgTeams.size() + ".", Toast.LENGTH_LONG).show();
    }

    public void processAVG(JSONObject object){

    }
}

