package org.teamtators.tatorsheetshepherd;

import org.json.JSONObject;

interface AsyncResult
{
    void onResult(JSONObject object);
}