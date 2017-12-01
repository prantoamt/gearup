package com.gearup.pranto.gearupmechanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    EditText welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = (EditText) findViewById(R.id.welcome);
        welcome.setText("Welcome");
    }
}
