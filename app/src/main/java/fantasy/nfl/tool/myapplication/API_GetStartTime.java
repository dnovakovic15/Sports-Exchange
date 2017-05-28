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


class API_GetStartTime extends AsyncTask<String, Object, String> {

    private String result;
    private ArrayList user_info = new ArrayList();


    @Override
    protected void onPreExecute() {
    }

    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line;
        try {
            url = new URL("http://52.14.155.129/project/api_get_time.php.");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            writer.write("&token=" + params[0]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            writer.close();
            reader.close();
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

        return result;
    }

    public void onPostExecute(String result) {
    }

}

