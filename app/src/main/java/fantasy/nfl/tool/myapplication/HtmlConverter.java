package fantasy.nfl.tool.myapplication;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by Darko on 4/24/2017.
 */

public class HtmlConverter {

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
