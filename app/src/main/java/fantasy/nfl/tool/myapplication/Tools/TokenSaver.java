package fantasy.nfl.tool.myapplication.Tools;

/**
 * Created by Darko on 5/15/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class TokenSaver {
    private final static String SHARED_PREF_NAME = "net.rouk1.SHARED_PREF_NAME";
    private final static String TOKEN_KEY = "net.rouk1.TOKEN_KEY";

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(TOKEN_KEY, "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

}
