package com.example.s10170577_week11prac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewUserActivity extends AppCompatActivity {
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        db = new DbHandler(this, null, null, 1);
    }

    public void onCreateUser(View v){
        EditText editTextUser = findViewById(R.id.editTextUser);
        EditText editTextPass = findViewById(R.id.editTextPass);

        String username = editTextUser.getText().toString();
        String password = editTextPass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*");

        Matcher userMatcher = userPattern.matcher(username);
        Matcher passMatcher = passPattern.matcher(password);

        if (userMatcher.matches() && passMatcher.matches()){
            //Add Account to Database
            Account a = new Account(username, password);
            db.addAccount(a);

            //Add Account to Shared Preferences
            SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
            editor.putString("Username", username);
            editor.putString("Password", password);
            editor.apply();

            //Create Toast
            Toast bread = Toast.makeText(CreateNewUserActivity.this, "New User Created Successfully", Toast.LENGTH_LONG);
            bread.show();

            //Redirect to main page
            Intent in = new Intent(CreateNewUserActivity.this, MainActivity.class);
            startActivity(in);
        }
        else{
            Toast bread = Toast.makeText(CreateNewUserActivity.this, "Invalid User Creation. Please try again!", Toast.LENGTH_LONG);
            bread.show();
        }
    }

    public void onBack(View v){
        Intent in = new Intent(CreateNewUserActivity.this, MainActivity.class);
        startActivity(in);
    }
}
