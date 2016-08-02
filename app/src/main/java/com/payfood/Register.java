package com.payfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends ActionBarActivity {

    private EditText edit_pass, edit_repass, edit_email, edit_user;
    private Toast toast;
    private InputMethodManager imm;
    private String email, username, pass, repass;

    public final static int REGISTRO_OK = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edit_email = (EditText) findViewById(R.id.register_email);
        edit_user = (EditText) findViewById(R.id.register_user);
        edit_pass = (EditText) findViewById(R.id.register_pass);
        edit_repass = (EditText) findViewById(R.id.register_repass);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void Registro(View v) {
        imm.hideSoftInputFromWindow(edit_email.getWindowToken(), 0);
        email = edit_email.getText().toString().trim();
        username = edit_user.getText().toString().trim();
        pass = edit_pass.getText().toString().trim();
        repass = edit_repass.getText().toString().trim();
        if (ValidateMail(email)) {
            if (ValidateUser(username)) {
                if (pass.length() >= 6) {
                    if (pass.equals(repass)) {
                        toast = Toast.makeText(Register.this, "Te has registrado con éxito", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();

                        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        final SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(MenUtil.KEY_EMAIL, email);
                        editor.putString(MenUtil.KEY_USER, username);
                        editor.putString(MenUtil.KEY_PASS, pass);
                        editor.putBoolean(MenUtil.KEY_SESION, true);
                        editor.commit();

                        final Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        toast = Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                } else {
                    toast = Toast.makeText(Register.this, "La contraseña debe ser mayor a 6 carácteres", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            } else {
                toast = Toast.makeText(Register.this, "Nombre de usuario no valido", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        } else {
            toast = Toast.makeText(Register.this, "El correo electrónico no es valido", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }

    private boolean ValidateMail(String email) {
        final String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        final CharSequence inputStr = email;
        final Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    private boolean ValidateUser(String user) {
        final String regExpn = "[a-zA-Z0-9._-]{6,25}";
        final CharSequence inputStr = user;
        final Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
