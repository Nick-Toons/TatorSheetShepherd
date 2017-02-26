package org.teamtators.tatorsheetshepherd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by idabs on 2/24/2017.
 */

public class ProcessJSON {
    String sheet;

    public ProcessJSON(String sheet){
        this.sheet = sheet;
    }

    public void Download(){
        new DownloadWebpage(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                Process(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=" + sheet);
    }

    public void Process(JSONObject object){
        if(sheet.equals("1I7u5JiijpOV7-A_RJ-mEj0pqZ0Ib_P5l6UEmY08y8Ec")) {
            new MainActivity().processMatch(object);
        } else {
            new MainActivity().processAVG(object);
        }
    }
}
