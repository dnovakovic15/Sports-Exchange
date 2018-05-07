package fantasy.nfl.tool.myapplication.API;

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


public class API_DVP extends AsyncTask<String, Object, List<String>> {

    private ArrayList finalStats = new ArrayList();

    @Override
    protected void onPreExecute() {
    }

    protected List<String> doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line, result;

        //Adjusts the Players names so that ProBasketballAPI can understand them.
        String firstName = params[0].replace("'","");
        firstName = firstName.replace("-","");
        firstName = firstName.replace(".","");
        String lastName = params[1].replace("'","");
        lastName = lastName.replace("-","");
        lastName = lastName.replace(".","");
        System.out.println(firstName);
        System.out.println(lastName);

        //Connects to server to pull up the requested Players's stats.
        try {
            url = new URL("http://52.14.155.129/project/get_dvp.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            //Pass the position and team abbreviation.
            writer.write("&token=" + params[2]);
            writer.write("&position=" + params[1]);
            writer.write("&team=" + params[0]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            result = result.replaceAll("<br>", ",");
            System.out.println(result);
            writer.close();
            reader.close();
            String[] testList = result.split(",");
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

        System.out.println("finalstats[0]: " + finalStats.get(0));
        return finalStats;
    }

    public void onPostExecute(String result) {
    }

}

