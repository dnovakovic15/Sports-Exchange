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


class API_GetInfo extends AsyncTask<String, Object, List<String>> {

    private ArrayList user_info = new ArrayList();


    @Override
    protected void onPreExecute() {
    }

    protected List<String> doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line, result;
        try {
            url = new URL("http://52.14.155.129/project/api_get_info.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            writer.write("&email=" + params[0]); //Position is indicated by a number starting with 0. 0 corresponds to the point guard position & 4 corresponds to the Center Position.
            writer.write("&token=" + params[1]);
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
            user_info = new ArrayList<String>(Arrays.asList(testList));
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        }
        finally  {
            conn.disconnect();
        }

        System.out.println("userinfo " + user_info);
        return user_info;
    }

    public void onPostExecute(String result) {
    }

}

