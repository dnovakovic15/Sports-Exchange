package fantasy.nfl.tool.myapplication;

/**
 * Created by Darko on 11/6/2016.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class API_StatFinder extends AsyncTask<String, Object, List<String>> {

    private ArrayList finalStats = new ArrayList();


    @Override
    protected void onPreExecute() {
    }

    protected List<String> doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line, result;

        //Adjusts the player names so that ProBasketballAPI can understand them.
        String firstName = params[0];
        String lastName = params[1];

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);

        //Connects to server to pull up the requested player's stats.
        try {
            url = new URL("http://52.14.155.129/project/api_usage.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            writer.write("&first=" + firstName);
            writer.write("&last=" + lastName);
            writer.write("&opponent=" + params[2]);
            writer.write("&token=" + params[3]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            result = result.replaceAll("<br>", ",");
            writer.close();
            reader.close();
            String [] testList = result.split(",");
            finalStats = new ArrayList<String>(Arrays.asList(testList));
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        } finally  {
            conn.disconnect();
        }

        return finalStats;
        }

    public void onPostExecute(String result) {
    }

}

