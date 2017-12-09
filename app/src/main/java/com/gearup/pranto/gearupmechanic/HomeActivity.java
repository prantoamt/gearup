package com.gearup.pranto.gearupmechanic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MyMechanic mechanic;
    TextView welcome;
    Button online, ofline;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getContent();
    }

    private void getContent()
    {
        Bundle b = getIntent().getExtras();
        mechanic = (MyMechanic) b.getSerializable("mechanic");
        welcome = (TextView) findViewById(R.id.welcome);
        online = (Button) findViewById(R.id.online);
        ofline = (Button) findViewById(R.id.ofline);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mtoggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(this);
        welcome.setText("Welcome " + mechanic.getName() +
                ".\nYou are now in the Home page of Gear Up Mechanic. " +
                "To start getting job requests from clients, press the Online " +
                "button below.");

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!runtime_permission())
                {
                    goOnline();
                }
            }
        });

        ofline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOfline();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mtoggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean runtime_permission()
    {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return  true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                goOnline();
            }
            else
            {
                runtime_permission();
            }
        }
    }

    private void goOnline()
    {
        online.setClickable(false);
        ofline.setClickable(true);
        Intent i = new Intent(HomeActivity.this, LocationTracer.class);
        i.putExtra("phone", mechanic.getPhone());
        startService(i);
    }

    private void goOfline()
    {
        online.setClickable(true);
        ofline.setClickable(false);
        Intent i = new Intent(HomeActivity.this, LocationTracer.class);
        stopService(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_my_account)
        {
            Bundle b = new Bundle();
            b.putSerializable("mechanic", mechanic);
            Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
        return false;
    }
}
