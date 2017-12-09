package com.gearup.pranto.gearupmechanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email, phone, service;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getContent();
    }

    private void getContent()
    {
        Bundle b = getIntent().getExtras();
        MyMechanic mechanic = (MyMechanic) b.getSerializable("mechanic");
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        service = (TextView) findViewById(R.id.service);
        rating = (RatingBar) findViewById(R.id.rating);
        name.setText("Name: " + mechanic.getName());
        email.setText("Email: " + mechanic.getEmail());
        phone.setText("Phone No: " + mechanic.getPhone());
        service.setText("Service: " + mechanic.getService());
        rating.setMax(5);
        rating.setRating(3);
    }
}
