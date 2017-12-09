package com.gearup.pranto.gearupmechanic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SingupActivity extends AppCompatActivity {

    EditText name, pass, email, phone;
    String service;
    Button sign_up;
    RadioGroup rgrp;
    RadioButton rbtn;
    ProgressDialog progress_dialog;
    TextInputLayout t_name, t_password, t_email, t_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        getContent();
    }


    public void getContent()
    {
        progress_dialog = new ProgressDialog(SingupActivity.this);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        sign_up = (Button) findViewById(R.id.sign_up);
        rgrp = (RadioGroup) findViewById(R.id.rdgroup);
        t_name = (TextInputLayout) findViewById(R.id.textname);
        t_password = (TextInputLayout) findViewById(R.id.textpassword);
        t_email = (TextInputLayout) findViewById(R.id.textemail);
        t_phone = (TextInputLayout) findViewById(R.id.textphone);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    public void registerUser()
    {
        showPD();
        final String my_name = name.getText().toString();
        final String my_pass = pass.getText().toString();
        final String my_email = email.getText().toString();
        final String my_phone = phone.getText().toString();
        final String my_ser = service.toString();
        String url = "http://192.168.0.118/signup.php";
        if (checkName(my_name) && checkPassword(my_pass) && checkEmail(my_email) && checkPhone(my_phone))
        {
            StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dismissPD();
                    if(response.toString().equals("Registration Successful"))
                    {
                        progress_dialog.dismiss();
                        makeToast(response.toString());
                        finish();
                    }
                    else
                    {
                        makeToast(response.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dismissPD();
                    makeToast(error.toString());
                }
            }){
                protected Map<String, String> getParams()
                {
                    Map<String, String>  parr = new HashMap<String, String>();
                    parr.put("name", my_name);
                    parr.put("password", my_pass);
                    parr.put("email", my_email);
                    parr.put("phone", my_phone);
                    parr.put("service", my_ser);
                    return parr;
                }
            };

            AppController.getInstance(getApplicationContext()).addToRequestQueue(rq);
        }


    }


    public void typeSelected(View view)
    {
        int radio_button_id = rgrp.getCheckedRadioButtonId();
        rbtn = (RadioButton) findViewById(radio_button_id);
        service = rbtn.getText().toString();

    }

    public boolean checkName(String name)
    {
        boolean is_valid = false;
        if(name.isEmpty() == false)
        {
            t_name.setErrorEnabled(false);
            is_valid = true;
        }
        else
        {
            t_name.setError("Name is required");
            is_valid = false;
            dismissPD();
        }
        return is_valid;
    }


    public boolean checkPassword(String pass)
    {
        boolean is_correct = false;
        if(pass.isEmpty() == false)
        {

            if(pass.length() < 8)
            {
                t_password.setError("Password length must be grater than 7");
                is_correct = false;
                dismissPD();
            }
            else
            {
                t_password.setErrorEnabled(false);
                is_correct = true;
            }
        }
        else
        {
            t_password.setError("Invalid Password");
            is_correct = false;
            dismissPD();
        }
        return is_correct;
    }

    public boolean checkEmail(String email)
    {
        boolean is_valid = false;
        if(email.isEmpty())
        {
            is_valid = false;
            dismissPD();
            t_email.setError("Email address required");
        }
        else if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {
            is_valid = false;
            dismissPD();
            t_email.setError("Please enter a valid email id");
        }
        else
        {
            t_email.setErrorEnabled(false);
            is_valid = true;
        }
        return is_valid;
    }

    public boolean checkPhone(String phone)
    {
        boolean valid = false;
        if(phone.isEmpty())
        {
            valid = false;
            dismissPD();
            t_phone.setError("Phone no required");
        }
        else if(phone.isEmpty() == false)
        {
           if(phone.length() < 11 || phone.length() >11)
           {
               dismissPD();
               t_phone.setError("Please enter a valid phone number");
               valid = false;
           }
           else if (phone.length() == 11)
           {
               t_phone.setErrorEnabled(false);
               valid = true;
           }
                       }

        return valid;
    }

    public boolean checkRadioButton()
    {
        boolean is_checked = false;
        if(service.toString().isEmpty())
        {
            is_checked = false;
        }
        else if(service.toString().isEmpty() == false)
        {
            is_checked = true;
        }

        return is_checked;
    }

    public void makeToast(String string)
    {
        Toast.makeText(getApplicationContext(),string, Toast.LENGTH_LONG).show();
    }

    private void showPD()
    {
        progress_dialog.setTitle("Sign up");
        progress_dialog.setMessage("Registering user info");
        progress_dialog.setCancelable(false);
        progress_dialog.show();
    }
    private void dismissPD()
    {
        if(progress_dialog.isShowing())
        {
            progress_dialog.dismiss();
        }
    }
}
