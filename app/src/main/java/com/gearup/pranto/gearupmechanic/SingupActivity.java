package com.gearup.pranto.gearupmechanic;

import android.app.ProgressDialog;
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
        if (checkName(my_name) == false)
        {
            makeToast("Please Enter a valid Name");
        }
        else if (checkUsername(my_user_name) == false)
        {
            makeToast("Please Enter a Valid User Name");
        }
        else if(checkPassword(my_pass) == false)
        {
             makeToast("Password length can't be less than 8");
        }
        else if(checkEmail(my_email) == false)
        {
            makeToast("Invalid Email");
        }
        else if(checkPhone(my_phone) == false)
        {
            makeToast("Enter a valid Phone No");
        }
        else if(checkRadioButton() == false)
        {
            makeToast("Select a mechanic type");
        }
        else
        {   progress_dialog.setMessage("Please wait...");
            StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progress_dialog.dismiss();
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
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
        boolean not_empty = false;
        if(name.isEmpty() == false)
        {
            not_empty = true;
        }
        else
        {
            not_empty = false;
        }
        return not_empty;
    }

    public boolean checkUsername(String username)
    {
        boolean not_empty = false;
        if(username.isEmpty() == false)
        {
            not_empty = true;
        }
        else
        {
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
                is_correct = false;
            }
            else
            {
                is_correct = true;
            }
        }
        else
        {
            is_correct = false;
        }
        return is_correct;
    }

    public boolean checkEmail(String email)
    {
        boolean is_valid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return is_valid;
    }

    public boolean checkPhone(String phone)
    {
        boolean valid = false;
        if(phone.isEmpty())
        {
            valid = false;
        }
        else if(phone.isEmpty() == false)
        {
           if(phone.length() < 11 || phone.length() >11)
           {
               valid = false;
           }
           else
               valid = true;
        }

        return valid;
    }

    public boolean checkRadioButton()
    {
        boolean is_checked = false;
        if(rgrp.getCheckedRadioButtonId() == -1)
        {
            is_checked = false;
        }
        else
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
