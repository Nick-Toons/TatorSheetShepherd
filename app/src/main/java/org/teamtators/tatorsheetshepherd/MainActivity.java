package org.teamtators.tatorsheetshepherd;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    ArrayList<AvgView> avgTeams = new ArrayList<>();
    ListView listview;
    Button btnDownload;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnClear = (Button) findViewById(R.id.btnClear);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
        } else {
            btnDownload.setEnabled(false);
            Toast.makeText(getApplicationContext(), "There is no network connection. Please connect " +
                    "to a network to use this application.", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonClickHandler(View view) {
        new DownloadWebpage(new AsyncResult(){
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=18eTxSWiuOzZcZJXmtLY6UdQCpzsYMBzGEp7iNoSNirM");
    }

    public void buttonClearHandler(View view){
        int amountRemoved = avgTeams.size();
        avgTeams.clear();
        final AvgAdapter adapter = new AvgAdapter(this, R.layout.avg_team, avgTeams);
        listview.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), amountRemoved + " points of data cleared.", Toast.LENGTH_LONG).show();
    }

    public void buttonFilterHandler(View view){
        Toast.makeText(getApplicationContext(), "Not yet implemented.", Toast.LENGTH_LONG).show();
    }

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 1; r < rows.length(); ++r) {
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
            }

            final AvgAdapter adapter = new AvgAdapter(this, R.layout.avg_team, avgTeams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "File Downloaded with size " + avgTeams.size() + ".", Toast.LENGTH_LONG).show();
    }
}

