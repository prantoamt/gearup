package com.gearup.pranto.gearupmechanic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MyMechanic mechanic;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    SectionsStatePagerAdapter adapter;
    ViewPager vp;
    UserSessionManager user_session;
    Intent share_intent;
    String share_body= "Thanks for sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        //getContent();
    }

    public void getContent()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        vp = (ViewPager) findViewById(R.id.viewpager);
        mtoggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(this);
        setupPager(vp);

    }

    public void init()
    {
        user_session = new UserSessionManager(getApplicationContext());
        if(user_session.cheackLogIn())
        {
            finish();
        }
            mechanic = new MyMechanic();
            HashMap<String, String> user = user_session.getUserDetails();
            String name = user.get(UserSessionManager.KEY_NAME);
            String email = user.get(UserSessionManager.KEY_EMAIL);
            String service = user.get(UserSessionManager.KEY_SERVICE);
            String rating = user.get(UserSessionManager.KEY_RATING);
            String phone_no = user.get(UserSessionManager.KEY_PHONE);
            String password = user.get(UserSessionManager.PASS);
            mechanic.setName(name);
            mechanic.setPhone(phone_no);
            mechanic.setEmail(email);
            mechanic.setPassword(password);
            mechanic.setService(service);
            mechanic.setRating(rating);
            getContent();

    }

    private void setupPager(ViewPager viewPager)
    {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        Fragment f1 = new HomeFragment();
        Fragment f2 = new MyAccountFragment();
        Fragment f3 = new ChangePasswordFragment();
        adapter.addFragment(f1, "Home fragment");
        adapter.addFragment(f2, "My account fragment");
        adapter.addFragment(f3, "Change password fragment");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        vp.setCurrentItem(fragmentNumber);
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

    protected void goOnline()
    {
        if(!runtime_permission()) {
            Intent i = new Intent(HomeActivity.this, LocationTracer.class);
            i.putExtra("phone", mechanic.getPhone());
            startService(i);
        }
    }

    protected void goOfline()
    {
        Intent i = new Intent(HomeActivity.this, LocationTracer.class);
        stopService(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.nav_home)
        {
            vp.setCurrentItem(0);
        }
        if (item.getItemId() == R.id.nav_my_account)
        {
            vp.setCurrentItem(1);
        }

        if(item.getItemId() == R.id.nav_change_pass)
        {
            vp.setCurrentItem(2);
        }

        if(item.getItemId() == R.id.nav_share)
        {
            ApplicationInfo api = getApplicationContext().getApplicationInfo();
            String apk_path = api.sourceDir;
            share_intent = new Intent(Intent.ACTION_SEND);
            share_intent.setType("application/vnd.android.package-archive");
            share_intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apk_path)));
            startActivity(Intent.createChooser(share_intent, "Share Using"));
        }

        if(item.getItemId() == R.id.nav_logout)
        {
            user_session.logOut();
            goOfline();
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void makeText(String text)
    {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
