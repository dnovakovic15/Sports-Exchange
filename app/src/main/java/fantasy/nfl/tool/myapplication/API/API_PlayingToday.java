package fantasy.nfl.tool.myapplication.API;

/**
 * Created by Darko on 11/6/2016.
 */

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fantasy.nfl.tool.myapplication.Models.Player;
import fantasy.nfl.tool.myapplication.Tools.PriceComparator;


public class API_PlayingToday extends AsyncTask<Object, Object, List<String>> {

    private List finalPlayers = new ArrayList();


    @Override
    protected void onPreExecute() {
    }

    protected List<String> doInBackground(Object... params) {
        URL url;
        HttpURLConnection conn = null;
        String line, result;
        try {
            url = new URL("http://52.14.155.129/project/api_players.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            writer.write("&position=" + params[0]); //Position is indicated by a number starting with 0. 0 corresponds to the point guard position & 4 corresponds to the Center Position.
            writer.write("&token=" + params[1]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            writer.close();
            reader.close();
            System.out.println("result: " + result);
            JSONArray jArray = new JSONArray(result);
            List<Player> players = new ArrayList<Player>();

            for(int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String name = jObject.getString("name");
                Integer cost = jObject.getInt("salary");
                Double ppg = jObject.getDouble("ppg");
                String game = jObject.getString("game");
                String team = jObject.getString("team");
                String position = jObject.getString("position");
                Player today = new Player(name, cost, ppg, game, team, position);
                players.add(today);
            }

            System.out.println("params: " + params[0]);
            if((int) params[0] > 4){
                PriceComparator sorted = new PriceComparator(players);
                finalPlayers = sorted.getSortedPlayers();
            }
            else{
                finalPlayers = players;
            }
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        }
        catch (JSONException e) {
            e.printStackTrace();}
        finally  {
            conn.disconnect();
        }

        return finalPlayers;
    }

    public void onPostExecute(String result) {
    }

}

