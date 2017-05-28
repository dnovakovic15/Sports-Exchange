package fantasy.nfl.tool.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class User_Account extends AppCompatActivity {

    private List<ArrayList> points = new ArrayList<>();
    private List<Player> optimalPlayers = new ArrayList<Player>();
    private List<Date> dates = new ArrayList<>(4);
    private Player player1 = new Player("", 0, 0);
    private Player player2 = new Player("", 0, 0);
    private Player player3 = new Player("", 0, 0);
    private Player player4 = new Player("", 0, 0);
    private Player player5 = new Player("", 0, 0);
    private Player player6 = new Player("", 0, 0);
    private Player player7 = new Player("", 0, 0);
    private Player player8 = new Player("", 0, 0);
    private Player player9 = new Player("", 0, 0);
    private String email;
    private double salary = 50000;
    private boolean enabled = false;
    Date date;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);
        testTime();

        Intent myIntent = getIntent();
        email = myIntent.getStringExtra("email");

        if(myIntent.getSerializableExtra("Players") != null){
           optimalPlayers = (ArrayList) myIntent.getSerializableExtra("Players");
        }

        if(optimalPlayers.size() > 6){
            setPlayers(optimalPlayers);
        }
        else{
            setInfo(email);
        }

        setSalary();
    }

    //Set the user info. i.e. username, email, ppg, and ppg graph
    public void setInfo(String email){
        API_GetInfo asyncTask1 = new API_GetInfo();
        List user_stats = new ArrayList();
        List<Player> optimalPlayers = new ArrayList<>();

        //Start asynctask in order to fetch user info from server.
        try {
            user_stats = asyncTask1.execute(email, TokenSaver.getToken(getApplicationContext())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Set the different textViews and buttons on the the user_account xml file gotten from the API_GetInfo class.
        TextView usernameView = (TextView) findViewById(R.id.username);
        usernameView.setText(user_stats.get(2).toString());
        TextView emailView = (TextView) findViewById(R.id.email);
        emailView.setText(user_stats.get(0).toString());

        TextView ppgView = (TextView) findViewById(R.id.ppg);
        String str="<big>"+user_stats.get(1).toString()+"</big><small> ppg</small>";
        ppgView.setText(HtmlConverter.fromHtml(str));
        ppgView.setTypeface(ppgView.getTypeface(), Typeface.BOLD);
        Button optimizable = (Button) findViewById(R.id.optimize);
        optimizable.setEnabled(enabled);
        Button submitLineup = (Button) findViewById(R.id.submitLineup);
        submitLineup.setEnabled(enabled);

        if(enabled == false){
            optimizable.setTextColor(Color.GRAY);
            submitLineup.setTextColor(Color.GRAY);
        }



        //If there are preloaded players is checked by if the first player has a salary greater than 1000 since no player can have a salary lower than 3000.
        //If there are preloaded players inside the database then: Add the player names retreived from API_GetInfo to the players in this, User_Account, class and then add those players to the optimalPlayers Arraylist.
        if(Double.parseDouble((String) user_stats.get(11)) > 1000){
            player1.setName(user_stats.get(3).toString());
            player1.setPrice(Double.parseDouble((String) user_stats.get(11)));
            optimalPlayers.add(player1);
            player2.setName(user_stats.get(4).toString());
            player2.setPrice(Double.parseDouble((String) user_stats.get(12)));
            optimalPlayers.add(player2);
            player3.setName(user_stats.get(5).toString());
            player3.setPrice(Double.parseDouble((String) user_stats.get(13)));
            optimalPlayers.add(player3);
            player4.setName(user_stats.get(6).toString());
            player4.setPrice(Double.parseDouble((String) user_stats.get(14)));
            optimalPlayers.add(player4);
            player5.setName(user_stats.get(7).toString());
            player5.setPrice(Double.parseDouble((String) user_stats.get(15)));
            optimalPlayers.add(player5);
            player6.setName(user_stats.get(8).toString());
            player6.setPrice(Double.parseDouble((String) user_stats.get(16)));
            optimalPlayers.add(player6);
            player7.setName(user_stats.get(9).toString());
            player7.setPrice(Double.parseDouble((String) user_stats.get(17)));
            optimalPlayers.add(player7);
            player8.setName(user_stats.get(10).toString());
            player8.setPrice(Double.parseDouble((String) user_stats.get(18)));
            optimalPlayers.add(player8);
            player9.setName(user_stats.get(10).toString());
            player9.setPrice(Double.parseDouble((String) user_stats.get(19)));
            optimalPlayers.add(player9);

            setPlayersEasy(optimalPlayers);
        }

        //Determines if the setGraph method should be called based on the current orientation of the device.
        int orientation=this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            setGraph(user_stats.get(1).toString(), user_stats.get(19).toString(), user_stats.get(20).toString(), user_stats.get(21).toString());
        }
        else{
            return;
        }
    }

    //Sets the playerButton's names when passed an ArrayList filled with players. This method does the same as the setPlayers method further down, except it dosen't assign the player fields because it has been previously done.
    public void setPlayersEasy(List<Player> optimalPlayers){
        Button p0 = (Button) findViewById(R.id.qb);
        Button p1 = (Button) findViewById(R.id.rb0);
        Button p2 = (Button) findViewById(R.id.rb1);
        Button p3 = (Button) findViewById(R.id.wr0);
        Button p4 = (Button) findViewById(R.id.wr1);
        Button p5 = (Button) findViewById(R.id.wr2);
        Button p6 = (Button) findViewById(R.id.te);
        Button p7 = (Button) findViewById(R.id.defense);
        Button p8 = (Button) findViewById(R.id.flex);

        p0.setText(optimalPlayers.get(0).getName());
        p1.setText(optimalPlayers.get(1).getName());
        p2.setText(optimalPlayers.get(2).getName());
        p3.setText(optimalPlayers.get(3).getName());
        p4.setText(optimalPlayers.get(4).getName());
        p5.setText(optimalPlayers.get(5).getName());
        p6.setText(optimalPlayers.get(6).getName());
        p7.setText(optimalPlayers.get(7).getName());
        p8.setText(optimalPlayers.get(8).getName());

        enterLineup();
        setSalary();
    }



    //Sets the user graph using information retrieved from the server.
    public void setGraph(String ppg, String past1, String past2, String past3){
        List points = new ArrayList();
        points.add(past1);
        points.add(past2);
        points.add(past3);
        points.add(ppg);
        int maxPPG = Integer.parseInt(Collections.max(points).toString());
        int minPPG = Integer.parseInt(Collections.min(points).toString());

        //Set the current date as starting point.
        DataPoint[] dataPoints = new DataPoint[4];
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        //Set the dates ArrayList with the proper dates from the points ArrayList.
        for(int i = 0; i < 4; i++){
            setDate("1", Integer.toString(month - 3 + i), "2017");
        }

        //Set the dataPoints ArrayList.
        for(int i = 0; i < 4; i++){
            dataPoints[i] = new DataPoint(dates.get(i), Integer.parseInt((String) points.get(i)));
        }

        //Set the graph.
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.addSeries(series);

        //Set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(User_Account.this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
        graph.getGridLabelRenderer().setNumVerticalLabels(4); // only 4 because of the space

        //Set manual x bounds to have nice steps
        graph.getViewport().setMinX(dates.get(0).getTime());
        graph.getViewport().setMaxX(dates.get(dates.size() - 1).getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScalableY(false);
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(100);

        //Set manual y bounds to have nice steps
        graph.getViewport().setMinY(minPPG);
        graph.getViewport().setMaxY(maxPPG);
        graph.getViewport().setYAxisBoundsManual(true);


        //As we use dates as labels, the human rounding to nice readable numbers
        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    public void setDate(String day, String month, String year){
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        Date date = cal.getTime();
        dates.add(date);
    }

    public void setSalary(){
        salary = player1.getPrice() + player2.getPrice() + player3.getPrice() + player4.getPrice() + player5.getPrice() + player6.getPrice() + player7.getPrice() + player8.getPrice() + player9.getPrice();
        TextView salaryView = (TextView) findViewById(R.id.salary);
        String salaryString = NumberFormat.getNumberInstance(Locale.US).format(50000 - salary);
        salaryView.setText("Remaining Salary: " + salaryString);

        if(salary > 50000){
            salaryView.setText(HtmlConverter.fromHtml("Remaining Salary: " + "<font color=red>" + salaryString + "</font>"));
        }
    }


    //All the position methods below, i.e. pg, sg, etc., start the Players activity corresponding to the button that was clicked.
    public void qb(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 0);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 0);
    }

    public void rb0(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 1);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }

    public void rb1(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 1);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 2);
    }

    public void wr0(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 2);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 3);
    }

    public void wr1(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 2);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 4);
    }
    public void wr2(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 2);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 5);
    }
    public void te(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 3);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 6);
    }
    public void defense(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 4);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 7);
    }
    public void flex(View view){
        Intent intent = new Intent(User_Account.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 5);
        b.putInt("ActivityIdentity", 101);
        intent.putExtras(b);
        startActivityForResult(intent, 8);
    }

    public void optimize(View view){
        Intent intent = new Intent(User_Account.this, Quarterbacks.class);
        startActivityForResult(intent, 9);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if(data != null){
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player1.setPrice(price);
                    player1.setName(newText);
                    Button p = (Button) findViewById(R.id.qb);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (1): {
                if(data != null){
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player2.setPrice(price);
                    player2.setName(newText);
                    Button p = (Button) findViewById(R.id.rb0);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (2): {
                if(data != null){
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player3.setPrice(price);
                    player3.setName(newText);
                    Button p = (Button) findViewById(R.id.rb1);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (3): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player4.setPrice(price);
                    player4.setName(newText);
                    Button p = (Button) findViewById(R.id.wr0);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (4): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player5.setPrice(price);
                    player5.setName(newText);
                    Button p = (Button) findViewById(R.id.wr1);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (5): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player6.setPrice(price);
                    player6.setName(newText);
                    Button p = (Button) findViewById(R.id.wr2);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (6): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player7.setPrice(price);
                    player7.setName(newText);
                    Button p = (Button) findViewById(R.id.te);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (7): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player8.setPrice(price);
                    player8.setName(newText);
                    Button p = (Button) findViewById(R.id.defense);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (8): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    int price = data.getIntExtra("Price", 1);
                    player8.setPrice(price);
                    player8.setName(newText);
                    Button p = (Button) findViewById(R.id.flex);
                    p.setText(newText);
                    setSalary();
                    break;
                }
            }
            case (9): {
                if(data != null && optimalPlayers.size() > 7) {
                    List<Player> optimalPlayers;
                    optimalPlayers = (ArrayList) data.getSerializableExtra("Players");

                    //Checks to see if lineup is over 50000, and if it is tell the user
                    if (optimalPlayers.get(8).getName().equals("false")) {
                        Toast.makeText(getBaseContext(), "No Lineup Under 50k Could be Generated", Toast.LENGTH_LONG).show();
                        break;
                    }
                    setPlayers(optimalPlayers);
                    break;
                }
                else{
                    Toast.makeText(getBaseContext(), "Failed to Generate Lineup", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //Sets the playerButton's names when passed an ArrayList filled with players.
    public void setPlayers(List<Player> optimalPlayers){
        player1.setName(optimalPlayers.get(0).getName());
        player2.setName(optimalPlayers.get(1).getName());
        player3.setName(optimalPlayers.get(2).getName());
        player4.setName(optimalPlayers.get(3).getName());
        player5.setName(optimalPlayers.get(4).getName());
        player6.setName(optimalPlayers.get(5).getName());
        player7.setName(optimalPlayers.get(6).getName());
        player8.setName(optimalPlayers.get(7).getName());
        player9.setName(optimalPlayers.get(8).getName());
        player1.setPrice(optimalPlayers.get(0).getPrice());
        player2.setPrice(optimalPlayers.get(1).getPrice());
        player3.setPrice(optimalPlayers.get(2).getPrice());
        player4.setPrice(optimalPlayers.get(3).getPrice());
        player5.setPrice(optimalPlayers.get(4).getPrice());
        player6.setPrice(optimalPlayers.get(5).getPrice());
        player7.setPrice(optimalPlayers.get(6).getPrice());
        player8.setPrice(optimalPlayers.get(7).getPrice());
        player9.setPrice(optimalPlayers.get(8).getPrice());

        Button p0 = (Button) findViewById(R.id.qb);
        Button p1 = (Button) findViewById(R.id.rb0);
        Button p2 = (Button) findViewById(R.id.rb1);
        Button p3 = (Button) findViewById(R.id.wr0);
        Button p4 = (Button) findViewById(R.id.wr1);
        Button p5 = (Button) findViewById(R.id.wr2);
        Button p6 = (Button) findViewById(R.id.te);
        Button p7 = (Button) findViewById(R.id.defense);
        Button p8 = (Button) findViewById(R.id.flex);

        p0.setText(optimalPlayers.get(0).getName());
        p1.setText(optimalPlayers.get(1).getName());
        p2.setText(optimalPlayers.get(2).getName());
        p3.setText(optimalPlayers.get(3).getName());
        p4.setText(optimalPlayers.get(4).getName());
        p5.setText(optimalPlayers.get(5).getName());
        p6.setText(optimalPlayers.get(6).getName());
        p7.setText(optimalPlayers.get(7).getName());
        p8.setText(optimalPlayers.get(8).getName());

        enterLineup();
        setSalary();
    }

    //Send lineup created in this activity to the server-side.
    public void submit(View view){
        enterLineup();
    }

    public void enterLineup(){
        API_SendLineup asyncTask1 = new API_SendLineup();
        String status = "Fail";

        if(salary > 50000){
            Toast.makeText(getBaseContext(), "Lineup Exceeded Salary Limit", Toast.LENGTH_LONG).show();
            return;
        }
        else if(checkDuplicates() == false){
            Toast.makeText(getBaseContext(), "Lineup Has Duplicates", Toast.LENGTH_LONG).show();
            return;
        }
        else if(testPlayers() == false){
            Toast.makeText(getBaseContext(), "Incomplete Lineup", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            status = asyncTask1.execute(player1.getName(), player2.getName(), player3.getName(), player4.getName(), player5.getName(), player6.getName(), player7.getName(), player8.getName(), player9.getName(), email, Double.toString(player1.getPrice()), Double.toString(player2.getPrice()), Double.toString(player3.getPrice()), Double.toString(player4.getPrice()), Double.toString(player5.getPrice()), Double.toString(player6.getPrice()), Double.toString(player7.getPrice()), Double.toString(player8.getPrice()), Double.toString(player9.getPrice()), TokenSaver.getToken(getApplicationContext())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(status.indexOf("Pass") > -1){
            Toast.makeText(getBaseContext(), "Lineup Entered Succesfully", Toast.LENGTH_LONG).show();
        }
        else if(status.indexOf("Time") > -1){
            Toast.makeText(getBaseContext(), "Time Has Elapsed", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Lineup Not Complete", Toast.LENGTH_LONG).show();
        }
    }

    public void testTime(){
        API_GetStartTime startTimeAPI = new API_GetStartTime();
        String startTime = "0";

        try{
            startTime = startTimeAPI.execute(TokenSaver.getToken(getApplicationContext())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        startTime = startTime.substring(1);

        //Convert startTime into military time
        int startTimeInt = Integer.parseInt(startTime.trim());
        if(startTimeInt < 1100){
            startTimeInt = startTimeInt + 1200;
        }

        //Get hour of day multiply by 100 and add minutes, so that it is in the same format as startTimeInt, and compare to start time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int time = hour * 100 + minutes;

        enabled = time < startTimeInt;
    }

    public void logOut(View view){
        finish();
    }

    //Test if any player slot is empty
    public boolean testPlayers(){
        if(player1.getPrice() < 3000){
            return false;
        }
        else if(player2.getPrice() < 3000){
            return false;
        }
        else if(player3.getPrice() < 3000){
            return false;
        }
        else if(player4.getPrice() < 3000){
            return false;
        }
        else if(player5.getPrice() < 3000){
            return false;
        }
        else if(player6.getPrice() < 3000){
            return false;
        }
        else if(player7.getPrice() < 3000){
            return false;
        }
        else if(player8.getPrice() < 500){
            return false;
        }
        else return player9.getPrice() >= 3000;
    }

    public boolean checkDuplicates(){
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);

        Set<String> set = new HashSet<String>();

        for(Player player : players){
            if(!set.add(player.getName())){
                return false;
            }
        }
        return true;
    }
}