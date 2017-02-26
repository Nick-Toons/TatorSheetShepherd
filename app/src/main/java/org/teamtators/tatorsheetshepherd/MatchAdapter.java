package org.teamtators.tatorsheetshepherd;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by idabs on 2/23/2017.
 */

public class MatchAdapter extends ArrayAdapter {
    Context context;
    private ArrayList<TeamNumber> matchTeams;

    public MatchAdapter(Context context, int textViewResourceId, ArrayList<TeamNumber> teams) {
        super(context, textViewResourceId, teams);
        this.context = context;
        this.matchTeams = teams;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.avg_team, null);
        }
        TeamNumber o = matchTeams.get(position);
        if (o != null) {
            TextView teamNumber = (TextView) v.findViewById(R.id.teamNumber);
            /*TextView lowBarX = (TextView) v.findViewById(R.id.lowBarX);
            TextView lowBarT = (TextView) v.findViewById(R.id.lowBarT);
            TextView lowF = (TextView) v.findViewById(R.id.lowF);
            TextView chevalX = (TextView) v.findViewById(R.id.chevalX);
            TextView chevalT = (TextView) v.findViewById(R.id.chevalT);
            TextView chevalF = (TextView) v.findViewById(R.id.chevalF);*/


            teamNumber.setText(String.valueOf(o.getNumber()));
            /*lowBarX.setText(String.valueOf(o.getLowX()));
            lowBarT.setText(String.valueOf(o.getLowT()));
            lowF.setText(String.valueOf(o.getLowF()));
            chevalX.setText(String.valueOf(o.getChevalX()));
            chevalT.setText(String.valueOf(o.getChevalT()));
            chevalF.setText(String.valueOf(o.getChevalF()));*/
        }
        return v;
    }
}
