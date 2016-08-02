package com.payfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.Timer;
import java.util.TimerTask;


public class Main extends ActionBarActivity {

    //private EditText user, pass;
    //private String nuser, npass;

    private static final long SPLASH_SCREEN_DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //user = (EditText) findViewById(R.id.login_user);
        //pass = (EditText) findViewById(R.id.login_pass);

    }

    @Override
    protected void onResume() {
        super.onResume();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
// Start the next activity
                final Intent mainIntent = new Intent(Main.this, Home.class);
                //mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
                finish();
            }
        };
// Simulate a long loading process on application startup.
        final Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    /*public void Login(View v) {
        // startActivity(new Intent(this, Home.class));
        // finish();

        nuser = user.getText().toString().trim();
        npass = pass.getText().toString().trim();

        if (nuser != null && nuser.equals("santiago") && npass.equals("blandon")) {
            startActivity(new Intent(this, Home.class));
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }*/


}
