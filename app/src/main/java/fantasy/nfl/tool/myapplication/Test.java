package fantasy.nfl.tool.myapplication;

import android.os.Bundle;

/**
 * Created by Darko on 11/16/2016.
 */

class Test extends MainActivity {


    protected void onCreate(Bundle savedInstanceState) {
        //Creates layout.
        super.onCreate(savedInstanceState);
        System.out.println("Test");
        setContentView(R.layout.log_in);
    }
}
