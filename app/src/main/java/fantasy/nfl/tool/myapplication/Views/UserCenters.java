package fantasy.nfl.tool.myapplication.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fantasy.nfl.tool.myapplication.Tools.BruteLineupOptimizer;
import fantasy.nfl.tool.myapplication.Models.Player;
import fantasy.nfl.tool.myapplication.R;

//Holds and displays the four user selected point guards used for lineup optimazation.

public class UserCenters extends AppCompatActivity {

    private Player player1 = new Player("", 0, 0);
    private Player player2 = new Player("", 0, 0);
    private Player player3 = new Player("", 0, 0);
    private Player player4 = new Player("", 0, 0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_centers);
    }

    public void player1(View view) {
        Intent intent = new Intent(UserCenters.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 4);
        intent.putExtras(b);
        startActivityForResult(intent, 0);
    }

    public void player2(View view) {
        Intent intent = new Intent(UserCenters.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 4);
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }

    public void player3(View view) {
        Intent intent = new Intent(UserCenters.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 4);
        intent.putExtras(b);
        startActivityForResult(intent, 2);
    }

    public void player4(View view) {
        Intent intent = new Intent(UserCenters.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 4);
        intent.putExtras(b);
        startActivityForResult(intent, 3);
    }

    //Overrides the onActivityResult method to listen for callbacks from the Players Activity.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    Double projectedPoints = Double.parseDouble(data.getStringExtra("ProjectedPoints"));
                    int price = data.getIntExtra("Price", 1);
                    player1.setPrice(price);
                    player1.setProjectedPoints(projectedPoints);
                    player1.setName(newText);
                    Button p = (Button) findViewById(R.id.button1);
                    p.setText(newText);
                    break;
                }
            }
            case (1): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    Double projectedPoints = Double.parseDouble(data.getStringExtra("ProjectedPoints"));
                    int price = data.getIntExtra("Price", 1);
                    player2.setPrice(price);
                    player2.setProjectedPoints(projectedPoints);
                    player2.setName(newText);
                    Button p = (Button) findViewById(R.id.button2);
                    p.setText(newText);
                    break;
                }
            }
            case (2): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    Double projectedPoints = Double.parseDouble(data.getStringExtra("ProjectedPoints"));
                    int price = data.getIntExtra("Price", 1);
                    player3.setPrice(price);
                    player3.setProjectedPoints(projectedPoints);
                    player3.setName(newText);
                    Button p = (Button) findViewById(R.id.button3);
                    p.setText(newText);
                    break;
                }
            }
            case (3): {
                if(data != null) {
                    String newText = data.getStringExtra("Name");
                    Double projectedPoints = Double.parseDouble(data.getStringExtra("ProjectedPoints"));
                    int price = data.getIntExtra("Price", 1);
                    player4.setPrice(price);
                    player4.setProjectedPoints(projectedPoints);
                    player4.setName(newText);
                    Button p = (Button) findViewById(R.id.button4);
                    p.setText(newText);
                    break;
                }
            }
        }
    }

    //Advances the GUI to the user input shooting guard screen.
    //Passes the user selected point guards to the Players class
    public void Advance(View view) throws IOException {
        ArrayList<Player> players, optimal;
        List<List<Player>> allPlayers = new ArrayList<List<Player>>();

        Intent myIntent = getIntent();
        players = (ArrayList) myIntent.getSerializableExtra("Players");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        for(int i = 0; i < 20; i = i + 4){
            List<Player> current = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                current.add(players.get(j + i));
            }
            allPlayers.add(current);
        }

        Player player1 = new Player("Kyrie", 45, 8400);
        Player player2 = new Player("Russel", 10, 5600);
        Player player3 = new Player("Lowry", 0, 8100);
        Player player4 = new Player("Rubio", 10, 3000);
        Player Player5 = new Player("mclemore", 20, 4500);
        Player Player6 = new Player("butler", 0, 8600);
        Player Player7 = new Player("Kyrie", 1, 6900);
        Player Player8 = new Player("none", 11, 5200);
        Player Player9 = new Player("butler", 90, 8600);
        Player player10 = new Player("Wiggins", 0, 7600);
        Player player11 = new Player("bron", 0, 10500);
        Player player12 = new Player("barnes", 10, 5100);
        Player player13 = new Player("frye", 25, 4900);
        Player player14 = new Player("boogie", 0, 12100);
        Player player15 = new Player("kat", 0, 10000);
        Player player16 = new Player("taj", 0, 5000);
        Player player17 = new Player("boogie", 55, 5000);
        Player player18 = new Player("kat", 0, 10000);
        Player player19 = new Player("tristan", 0, 5500);
        Player Player20 = new Player("rolo", 0, 4800);

        ArrayList test = new ArrayList();
        ArrayList test1 = new ArrayList();
        ArrayList test2 = new ArrayList();
        ArrayList test3 = new ArrayList();
        ArrayList test4 = new ArrayList();
        ArrayList all = new ArrayList();

        test.add(player1);
        test.add(player2);
        test.add(player3);
        test.add(player4);
        test1.add(Player5);
        test1.add(Player6);
        test1.add(Player7);
        test1.add(Player8);
        test2.add(Player9);
        test2.add(player10);
        test2.add(player11);
        test2.add(player12);
        test3.add(player13);
        test3.add(player14);
        test3.add(player15);
        test3.add(player16);
        test4.add(player17);
        test4.add(player18);
        test4.add(player19);
        test4.add(Player20);
        all.add(test);
        all.add(test1);
        all.add(test2);
        all.add(test3);
        all.add(test4);



        //last Player in arraylist: totalteamcost is the price of Player & totalteampoint is the projected points of Player
        optimal = BruteLineupOptimizer.createLineup(allPlayers);
        for(int count = 0; count < 8 && optimal != null; count++){
            System.out.println(optimal.get(count).getName());
        }

        Intent intent = new Intent();
        intent.putExtra("Players", optimal);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}