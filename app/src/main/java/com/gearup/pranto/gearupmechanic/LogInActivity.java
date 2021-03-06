package com.gearup.pranto.gearupmechanic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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

    EditText phone, password;
    Button log_in;
    UserSessionManager session;
    TextInputLayout t_phone, t_pass;
    MyMechanic mechanic;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getContent();
    }


    private void getContent()
    {
        session = new UserSessionManager(getApplicationContext());
        pd = new ProgressDialog(LogInActivity.this);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.pass);
        log_in = (Button) findViewById(R.id.log_in);
        t_phone = (TextInputLayout) findViewById(R.id.textinputphone);
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
        showPD();
        final String phone_no = phone.getText().toString();
        final String pass = password.getText().toString();

        String url = Links.QUERY_URL;


        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissPD();
                if (checkPhone() && checkPassword())
                {
                    try {
                        JSONArray array = new JSONArray(response);
                        JSONObject object = null;
                        if(array != null)
                        {
                            object = array.getJSONObject(0);

                            String name = object.getString("fullName");
                            String password = object.getString("password");
                            String email = object.getString("email");
                            String phone = object.getString("phoneNumber");
                            String service = object.getString("sType");
                            String Address = object.getString("Address");
                            session.createUserSession(name, phone, password, email, service, Address);
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();

                        }
                    } catch (JSONException e) {
                        dismissPD();
                        makeToast("Invalid user name or password");
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissPD();
                makeToast(error.toString());

            }
        }) {
            protected Map<String, String> getParams()
            {
                Map<String, String>  parr = new HashMap<String, String>();
                parr.put("phone", phone_no);
                parr.put("password", pass);
                parr.put("method", "login");
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

    private boolean checkPhone()
    {
        boolean is_valid = false;
        if(phone.getText().toString().isEmpty())
        {
            dismissPD();
            t_phone.setError("Please enter your Phone No");
            is_valid = false;
        }
        else
        {
            t_phone.setErrorEnabled(false);
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
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    private void showPD()
    {
        pd.setTitle("Log in");
        pd.setMessage("Checking user info");
        pd.setCancelable(false);
        pd.show();
    }

    private void dismissPD()
    {
        if(pd.isShowing())
        {
            pd.dismiss();
        }
    }

}
