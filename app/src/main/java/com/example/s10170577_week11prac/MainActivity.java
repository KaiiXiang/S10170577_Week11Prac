package com.example.s10170577_week11prac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCH = findViewById(R.id.textViewClickHere);
        textViewCH.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent in = new Intent(MainActivity.this, CreateNewUserActivity.class);
                startActivity(in);
                return true;
            }
        });
    }

    public void onLogin(View v){
        EditText editTextUser = findViewById(R.id.editTextUser);
        EditText editTextPass = findViewById(R.id.editTextPass);

        String username = editTextUser.getText().toString();
        String password = editTextPass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*");

        Matcher userMatcher = userPattern.matcher(username);
        Matcher passMatcher = passPattern.matcher(password);

        if (userMatcher.matches() && passMatcher.matches()){
            SharedPreferences sharedPref = getSharedPreferences("MY_GLOBAL_PREFS",MODE_PRIVATE);
            String user = sharedPref.getString("Username","");
            String pass = sharedPref.getString("Password","");
            if (username.equals(user) && password.equals(pass)) {
                Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_LONG).show();
                //Intent in = new Intent(MainActivity.this, WeatherForecastActivity.class);
                //startActivity(in);
            }
            else{
                Toast bread = Toast.makeText(MainActivity.this,"Invalid",Toast.LENGTH_LONG);
                bread.show();
            }
        }
        else{
            Toast bread = Toast.makeText(MainActivity.this,"Username or Password does not match requirements",Toast.LENGTH_LONG);
            bread.show();
        }
    }
}
