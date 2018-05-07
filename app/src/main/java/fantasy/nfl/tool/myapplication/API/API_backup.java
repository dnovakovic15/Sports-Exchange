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


class API_backup extends AsyncTask<Object, Object, List<String>> {

    private ArrayList finalPlayers = new ArrayList();


    @Override
    protected void onPreExecute() {
    }

    protected List<String> doInBackground(Object... params) {
        URL url;
        HttpURLConnection conn = null;
        String line, result;

        try {
            url = new URL("http://52.14.56.54/project/test.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
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
            finalPlayers = new ArrayList<String>(Arrays.asList(testList));
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        } finally  {
            conn.disconnect();
        }

        return finalPlayers;
    }

    public void onPostExecute(String result) {
    }

}

