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


class API_SendLineup extends AsyncTask<String, Object, String> {

    private String result;


    @Override
    protected void onPreExecute() {
    }

    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line;

        //Connects to server to pull up the requested Players's stats.
        try {
            url = new URL("http://52.14.155.129/project/api_storelineup.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            //Pass the position and team abbreviation.

            writer.write("&player1=" + params[0]);
            writer.write("&player2=" + params[1]);
            writer.write("&player3=" + params[2]);
            writer.write("&player4=" + params[3]);
            writer.write("&player5=" + params[4]);
            writer.write("&player6=" + params[5]);
            writer.write("&player7=" + params[6]);
            writer.write("&player8=" + params[7]);
            writer.write("&player9=" + params[8]);
            writer.write("&email=" + params[9]);
            writer.write("&player1Salary=" + Double.parseDouble(params[10]));
            writer.write("&player2Salary=" + Double.parseDouble(params[11]));
            writer.write("&player3Salary=" + Double.parseDouble(params[12]));
            writer.write("&player4Salary=" + Double.parseDouble(params[13]));
            writer.write("&player5Salary=" + Double.parseDouble(params[14]));
            writer.write("&player6Salary=" + Double.parseDouble(params[15]));
            writer.write("&player7Salary=" + Double.parseDouble(params[16]));
            writer.write("&player8Salary=" + Double.parseDouble(params[17]));
            writer.write("&player9Salary=" + Double.parseDouble(params[18]));
            writer.write("&token=" + params[19]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            System.out.println("API_SendLineup Task: " + result);
            writer.close();
            reader.close();
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        } finally  {
            conn.disconnect();
        }
        return result;
    }

    public void onPostExecute(String result) {
    }

}

