package fantasy.nfl.tool.myapplication.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import fantasy.nfl.tool.myapplication.API.API_Signup;
import fantasy.nfl.tool.myapplication.MainActivity;
import fantasy.nfl.tool.myapplication.R;


public class Signup_Activity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    public void signup(View v) {
        signup();
    }

    public void signup() {
        Log.d(TAG, "signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        findViewById(R.id.btn_signup).setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup_Activity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = ((EditText)findViewById(R.id.input_email)).getText().toString();
        final String password = ((EditText)findViewById(R.id.input_password)).getText().toString();
        final String name = ((EditText)findViewById(R.id.input_name)).getText().toString();

        // TODO: Implement your own authentication logic here.

        System.out.println("Here0");

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        API_Signup asyncTask1 = new API_Signup();
                        String verification = null;

                        try {
                            verification = asyncTask1.execute(email, password, name).get();
                            System.out.println(verification);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        if(verification.contains("pass")){
                            onSignupSuccess(email);
                        }
                        else{
                            onSignupFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 300);
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

    public void onSignupSuccess(String email) {
        Toast.makeText(getBaseContext(), "Signup Success", Toast.LENGTH_LONG).show();
        findViewById(R.id.btn_signup).setEnabled(true);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup Failed", Toast.LENGTH_LONG).show();

        findViewById(R.id.btn_signup).setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = ((EditText) findViewById(R.id.input_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();
        String name = ((EditText) findViewById(R.id.input_name)).getText().toString();

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

        if (name.isEmpty()) {
            final EditText pass = (EditText) findViewById(R.id.input_password);
            pass.setError("Enter a valid name");
            valid = false;
        }

        return valid;
    }

    public void link_login(View view){
        startActivity(new Intent(Signup_Activity.this, MainActivity.class));
    }
}
