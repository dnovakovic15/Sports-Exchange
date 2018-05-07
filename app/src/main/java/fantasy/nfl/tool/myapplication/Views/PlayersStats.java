package fantasy.nfl.tool.myapplication.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fantasy.nfl.tool.myapplication.API.API_DVP;
import fantasy.nfl.tool.myapplication.API.API_StatFinder;
import fantasy.nfl.tool.myapplication.MainActivity;
import fantasy.nfl.tool.myapplication.R;
import fantasy.nfl.tool.myapplication.Tools.TokenSaver;

/**
 * Created by Darko on 11/16/2016.
 */

public class PlayersStats extends MainActivity {

    private String firstKeyName, opponent;
    private int position, price, activityIdentity;
    private List finalStats = new ArrayList();
    private List dvpStats = new ArrayList();
    private List positionMap = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        //Creates layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stats);

        //Gets previous intent in order to get the Players name and position. Sets the Players name in the header.
        Intent myIntent = getIntent(); // gets the previously created intent
        firstKeyName = myIntent.getStringExtra("FirstName");
        position = myIntent.getIntExtra("Position", 0);
        price = myIntent.getIntExtra("Price", 0);
        activityIdentity = myIntent.getIntExtra("ActivityIdentity", 0);
        TextView p1 = (TextView) findViewById(R.id.Players);
        p1.setText(firstKeyName);

        //Gets the Players's stats and updates the GUI to display the Players's advanced stats.
        API_StatFinder stats = new API_StatFinder();
        API_DVP dvp = new API_DVP();
        opponent = myIntent.getStringExtra("Opponent");
        String[] split = firstKeyName.split(" ");
        if(split.length > 2){
            split[1] = split[1] + " " + split[2];
        }

        //Maps the position integer, received from the Players Intent, and maps it to the corresponding SQL database name for each position.
        positionMap.add("qb");
        positionMap.add("rb");
        positionMap.add("wr");
        positionMap.add("te");
        positionMap.add("d");

        try {
            finalStats = stats.execute(split[0], split[1], opponent, TokenSaver.getToken(getApplicationContext())).get();
            dvpStats = dvp.execute(opponent, positionMap.get(position).toString(), TokenSaver.getToken(getApplicationContext())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("finalStats: " + finalStats.get(0));

        //Formatt the stats to have the same significat figures.
        for(int count = 0; count < 8 && finalStats.get(count).toString().length() > 2; count++){
            DecimalFormat format = new DecimalFormat("0.00");
            String formatted = format.format(Double.parseDouble((String)finalStats.get(count)));
            finalStats.set(count, formatted);
        }

        //Sets all the Players stats for the GUI.
        TextView usgRate = (TextView) findViewById(R.id.textView11);
        TextView oReb = (TextView) findViewById(R.id.textView12);
        TextView dReb = (TextView) findViewById(R.id.textView13);
        TextView assist = (TextView) findViewById(R.id.textView14);
        usgRate.setText(finalStats.get(0).toString() + "%");
        dReb.setText(finalStats.get(1).toString() + "%");
        oReb.setText(finalStats.get(2).toString() + "%");
        assist.setText(finalStats.get(3).toString() + "%");

        //Sets the Players vs. opponent stats for the GUI.
        TextView opp = (TextView) findViewById(R.id.opponent);
        TextView usgRateOpp = (TextView) findViewById(R.id.textView24);
        TextView oRebOpp = (TextView) findViewById(R.id.textView25);
        TextView dRebOpp = (TextView) findViewById(R.id.textView26);
        TextView dvpOpp = (TextView) findViewById(R.id.textView27);
        opp.setText("vs. " + myIntent.getStringExtra("Opponent"));

        //Makes sure that the finalStats array contains usage values, a.k.a it checks if the Players has played this team this years, and posts the stats to the GUI.
        if (finalStats.size() > 6) {
            if (finalStats.get(4).toString().length() < 2){
                usgRateOpp.setText("N/A");
                oRebOpp.setText("N/A");
                dRebOpp.setText("N/A");
                dvpOpp.setText("N/A");
            }
            else {
                usgRateOpp.setText(finalStats.get(4).toString() + "%");
                dRebOpp.setText(finalStats.get(5).toString() + "%");
                oRebOpp.setText(finalStats.get(6).toString() + "%");
                dvpOpp.setText(dvpStats.get(0).toString() + "th");
                System.out.println("dvp: " + dvpStats.get(0).toString());


                if(Integer.parseInt(dvpStats.get(0).toString()) < 10){
                    dvpOpp.setTextColor(Color.RED);
                }
                else if(Integer.parseInt(dvpStats.get(0).toString()) > 10){
                    dvpOpp.setTextColor(Color.GREEN);
                }
            }
        }
        else{
            usgRateOpp.setText("N/A");
            oRebOpp.setText("N/A");
            dRebOpp.setText("N/A");
            dvpOpp.setText("N/A");
        }
    }

    //Runs when the user selects the "+" sign on their screen.
    //Adds selected Players to the user selected Players pool.
    public void add(View view) throws IOException {

        final EditText et = new EditText(this);

        if(activityIdentity < 1){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            et.setGravity(Gravity.CENTER);

            TextView title = new TextView(this);
            title.setText("Projected Points");
            title.setBackgroundColor(Color.DKGRAY);
            title.setPadding(10, 10, 10, 10);
            title.setGravity(Gravity.CENTER);
            title.setTextColor(Color.WHITE);
            title.setTextSize(20);

            alertDialogBuilder.setView(et);

            alertDialogBuilder.setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(PlayersStats.this, PlayersStats.class);
                    intent.putExtra("Name", firstKeyName);
                    intent.putExtra("ProjectedPoints", et.getText().toString());
                    intent.putExtra("Price", price);
                    setResult(1, intent);
                    finish();
                }
            });

            alertDialogBuilder.setCustomTitle(title);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else{
            Intent intent = new Intent(PlayersStats.this, PlayersStats.class);
            intent.putExtra("Name", firstKeyName);
            intent.putExtra("ProjectedPoints", et.getText().toString());
            intent.putExtra("Price", price);
            setResult(1, intent);
            finish();
        }
    }
    public void back(View view) throws IOException {
        setResult(0);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //This is for later if I want to add more stats on to a button called moreStats.
//    public void moreStats(View view) throws IOException{
//        TextView usgRateOppText = (TextView) findViewById(R.id.textView20);
//        TextView oRebOppText = (TextView) findViewById(R.id.textView21);
//        TextView dRebOppText = (TextView) findViewById(R.id.textView22);
//        TextView assistOppText = (TextView) findViewById(R.id.textView23);
//        TextView usgRateOpp = (TextView) findViewById(R.id.textView24);
//        TextView oRebOpp = (TextView) findViewById(R.id.textView25);
//        TextView dRebOpp = (TextView) findViewById(R.id.textView26);
//        TextView assistOpp = (TextView) findViewById(R.id.textView27);
//
//        usgRateOppText.setText("Points Allowed Off of Turnovers:");
//        dRebOppText.setText("Points Allowed in the Paint:");
//        oRebOppText.setText("Second Chance Points Allowed:");
//        assistOppText.setText("Blocks:");
//        usgRateOpp.setText(finalStats.get(4).toString().substring(0, 4) + "%");
//        dRebOpp.setText(finalStats.get(5).toString().substring(0, 4) + "%");
//        oRebOpp.setText(finalStats.get(6).toString().substring(0, 4) + "%");
//        assistOpp.setText(finalStats.get(7).toString().substring(0, 4) + "%");
//    }
    }
