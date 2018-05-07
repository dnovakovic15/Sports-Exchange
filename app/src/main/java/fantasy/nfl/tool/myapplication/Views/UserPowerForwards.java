package fantasy.nfl.tool.myapplication.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fantasy.nfl.tool.myapplication.Models.Player;
import fantasy.nfl.tool.myapplication.R;

//Holds and displays the four user selected point guards used for lineup optimazation.

public class UserPowerForwards extends AppCompatActivity {

    private Player player1 = new Player("", 0, 0);
    private Player player2 = new Player("", 0, 0);
    private Player player3 = new Player("", 0, 0);
    private Player player4 = new Player("", 0, 0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_power_forwards);
    }

    public void player1(View view) {
        Intent intent = new Intent(UserPowerForwards.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 3);
        intent.putExtras(b);
        startActivityForResult(intent, 0);
    }
    public void player2(View view) {
        Intent intent = new Intent(UserPowerForwards.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 3);
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }
    public void player3(View view) {
        Intent intent = new Intent(UserPowerForwards.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 3);
        intent.putExtras(b);
        startActivityForResult(intent, 2);
    }
    public void player4(View view) {
        Intent intent = new Intent(UserPowerForwards.this, Players.class);
        Bundle b = new Bundle();
        b.putInt("Position", 3);
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
            case (4): {
                if(data != null) {
                    ArrayList<Player> optimal;
                    optimal = (ArrayList) data.getSerializableExtra("Players");

                    Intent intent = new Intent();
                    intent.putExtra("Players", optimal);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        }
    }

    //Advances the GUI to the user input shooting guard screen.
    //Passes the user selected point guards to the Players class
    public void Advance(View view){
        ArrayList<Player> players;

        Intent myIntent = getIntent();
        players = (ArrayList) myIntent.getSerializableExtra("Players");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        Intent intent = new Intent(UserPowerForwards.this, UserCenters.class);
        intent.putExtra("Players", players);
        startActivityForResult(intent, 4);
        setResult(Activity.RESULT_OK, intent);
    }
}