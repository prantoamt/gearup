package com.gearup.pranto.gearupmechanic;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    EditText user_name, password;
    Button log_in;
    boolean logged_in;
    TextInputLayout t_user_name, t_pass;
    MyMechanic mechanic;

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
        t_user_name = (TextInputLayout) findViewById(R.id.textinputusername);
        t_pass = (TextInputLayout) findViewById(R.id.textinputpass);

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

        String url = "http://192.168.0.118/login.php";


        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (checkUser() && checkPassword())
                {
                    try {
                        JSONArray array = new JSONArray(response);
                        JSONObject object = null;
                        if(array != null)
                        {
                            object = array.getJSONObject(0);

                            mechanic = new MyMechanic();
                            mechanic.name = object.getString("name");
                            mechanic.user_name = object.getString("user_name");
                            mechanic.password = object.getString("pass");
                            mechanic.email = object.getString("email");
                            mechanic.phone = object.getString("phone");
                            mechanic.acc_type = object.getString("acc_type");
                            makeToast("Welcome..");
                            Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                            Bundle b = new Bundle();
                            b.putSerializable("mechanic", mechanic);
                            intent.putExtras(b);
                            startActivity(intent);
                            finish();

                        }
                    } catch (JSONException e) {
                        makeToast("Invalid user name or password");
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeToast(error.toString());

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

    public void goRegister(View view)
    {
        Intent i = new Intent(LogInActivity.this, SingupActivity.class);
        startActivity(i);
    }

    private boolean checkUser()
    {
        boolean is_valid = false;
        if(user_name.getText().toString().isEmpty())
        {
            t_user_name.setError("Please enter your user name");
            is_valid = false;
        }
        else
        {
            t_user_name.setErrorEnabled(false);
            is_valid = true;
        }

        return is_valid;
    }

    private boolean checkPassword()
    {
        boolean is_valid = false;
        if(password.getText().toString().isEmpty())
        {
            t_pass.setError("Please enter your password");
            is_valid = false;
        }
        else
        {
            t_pass.setErrorEnabled(false);
            is_valid = true;
        }

        return is_valid;
    }

    private void makeToast(String string)
    {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
    }

}
