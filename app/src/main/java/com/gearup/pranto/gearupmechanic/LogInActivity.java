package com.gearup.pranto.gearupmechanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    EditText user_name, password;
    Button log_in, sign_up;
    CheckBox accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getContent();
    }


    private void getContent()
    {
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.pass);
        log_in = (Button) findViewById(R.id.log_in);
        accept = (CheckBox) findViewById(R.id.accept);
        sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogInActivity.this, SingupActivity.class);
                startActivity(i);
            }
        });

        log_in.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onLogIn();
                    }
                }
        );
    }

    private void onLogIn()
    {
         final String username = user_name.getText().toString();
         final String pass = password.getText().toString();

        String url = "localhost/login.php";


        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams()
            {
                Map<String, String>  parr = new HashMap<String, String>();
                parr.put("user_name", username);
                parr.put("password", pass);


                return parr;
            }
        };

        AppController.getInstance(getApplicationContext()).addToRequestQueue(rq);

    }
}
