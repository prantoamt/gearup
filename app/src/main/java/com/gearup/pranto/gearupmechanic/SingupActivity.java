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

    EditText name, username, pass, email, phone;
    String category;
    Button sign_up;
    RadioGroup rgrp;
    RadioButton rbtn;
    ProgressDialog progress_dialog;
    TextInputLayout t_name, t_user_name, t_password, t_email, t_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        getContent();
    }


    public void getContent()
    {
        progress_dialog = new ProgressDialog(getApplicationContext());
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.user_name);
        pass = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        sign_up = (Button) findViewById(R.id.sign_up);
        rgrp = (RadioGroup) findViewById(R.id.rdgroup);
        t_name = (TextInputLayout) findViewById(R.id.textname);
        t_user_name = (TextInputLayout) findViewById(R.id.textusername);
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

        final String my_name = name.getText().toString();
        final String my_user_name = username.getText().toString();
        final String my_pass = pass.getText().toString();
        final String my_email = email.getText().toString();
        final String my_phone = phone.getText().toString();
        final String my_cat = category.toString();
        String url = "http://192.168.0.118/signup.php";
        if (checkName(my_name) && checkUsername(my_user_name) && checkPassword(my_pass) && checkEmail(my_email) && checkPhone(my_phone))
        {
            progress_dialog.setMessage("Please wait...");
            StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.toString().equals("Registration Successful"))
                    {
                        progress_dialog.dismiss();
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress_dialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }){
                protected Map<String, String> getParams()
                {
                    Map<String, String>  parr = new HashMap<String, String>();
                    parr.put("name", my_name);
                    parr.put("user_name", my_user_name);
                    parr.put("password", my_pass);
                    parr.put("email", my_email);
                    parr.put("phone", my_phone);
                    parr.put("category", my_cat);
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
        category = rbtn.getText().toString();

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
        }
        return is_valid;
    }

    public boolean checkUsername(String username)
    {
        boolean not_empty = false;
        if(username.isEmpty() == false)
        {
            t_user_name.setErrorEnabled(false);
            not_empty = true;
        }
        else
        {
            t_user_name.setError("User name is required");
            not_empty = false;
        }
        return not_empty;
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
        }
        return is_correct;
    }

    public boolean checkEmail(String email)
    {
        boolean is_valid = false;
        if(email.isEmpty())
        {
            is_valid = false;
            t_email.setError("Email address required");
        }
        else if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {
            is_valid = false;
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
            t_phone.setError("Phone no required");
        }
        else if(phone.isEmpty() == false)
        {
           if(phone.length() < 11 || phone.length() >11)
           {
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
        if(category.toString().isEmpty())
        {
            is_checked = false;
        }
        else if(category.toString().isEmpty() == false)
        {
            is_checked = true;
        }

        return is_checked;
    }

    public void makeToast(String string)
    {
        Toast.makeText(getApplicationContext(),string, Toast.LENGTH_LONG).show();
    }
}
