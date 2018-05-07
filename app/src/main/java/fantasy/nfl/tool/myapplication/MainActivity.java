package fantasy.nfl.tool.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fantasy.nfl.tool.myapplication.Views.Signup_Activity;
import fantasy.nfl.tool.myapplication.Views.User_Account;

/**
 * GUI for log in screen.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    public void login(View v) {
        login();
    }

    public void login() {

        //Delete line below later. There to skip log in process.
        //onLoginSuccess("dnovakovic21@yahoo.com");
        //Testing

        Log.d(TAG, "Login");

        onLoginSuccess("dnovakovic21@yahoo.com");

//        if (!validate()) {
//            onLoginSuccess("dnovakovic21@yahoo.com");
//            return;
//        }

//        findViewById(R.id.btn_login).setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
//                R.style.AppTheme);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();
//
//        final String email = ((EditText)findViewById(R.id.input_email)).getText().toString();
//        final String password = ((EditText)findViewById(R.id.input_password)).getText().toString();
//
//        // TODO: Implement your own authentication logic here.
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        API_Log_in asyncTask1 = new API_Log_in();
//                        String verification = null;
//
//                        try {
//                            verification = asyncTask1.execute(email, password).get();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                        if(verification.indexOf("pass") > -1){
//                            TokenSaver.setToken(getApplicationContext(), verification.substring(8));
//                            System.out.println("Token: " + TokenSaver.getToken(getApplicationContext()));
//                            onLoginSuccess(email);
//                        }
//                        else{
//                            onLoginFailed();
//                        }
//                        progressDialog.dismiss();
//                    }
//                }, 300);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String email) {
        System.out.println("WE JERE");
        findViewById(R.id.btn_login).setEnabled(true);
        Toast.makeText(getBaseContext(), "Log in Successful", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, User_Account.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        findViewById(R.id.btn_login).setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = ((EditText) findViewById(R.id.input_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            final EditText e_mail = (EditText) findViewById(R.id.input_email);
            e_mail.setError("Enter a valid email address");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            final EditText pass = (EditText) findViewById(R.id.input_password);
            pass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }

        return valid;
    }

    public void link_signup(View view){
        startActivity(new Intent(MainActivity.this, Signup_Activity.class));
    }
}