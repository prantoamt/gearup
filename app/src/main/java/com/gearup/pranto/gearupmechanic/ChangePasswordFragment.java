package com.gearup.pranto.gearupmechanic;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordFragment extends Fragment {

    EditText old_pass, new_pass, confirm_pass;
    Button save_changes;
    String old_pass_string, new_pass_string, confirm_pass_string;
    TextInputLayout t_old_pass, t_new_pass, t_confirm_pass;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        old_pass = (EditText) view.findViewById(R.id.old_password);
        new_pass = (EditText) view.findViewById(R.id.new_password);
        confirm_pass = (EditText) view.findViewById(R.id.confirm_password);
        save_changes = (Button) view.findViewById(R.id.save_changes);
        t_old_pass = (TextInputLayout) view.findViewById(R.id.old_password_text_input);
        t_new_pass = (TextInputLayout) view.findViewById(R.id.new_password_text_input);
        t_confirm_pass = (TextInputLayout) view.findViewById(R.id.confirm_password_text_input);

        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                old_pass_string = old_pass.getText().toString();
                new_pass_string = new_pass.getText().toString();
                confirm_pass_string = confirm_pass.getText().toString();

                if(checkOldPass() && checkNewPass() && checkConfirmPass())
                {
                    updatePass();
                }
            }
        });
        return view;
    }

    private void updatePass()
    {
        String url = "http://192.168.0.118/query.php";
        StringRequest rq = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
                if(response.toString().equals("Password Changed Successfully"))
                {
                    old_pass.setText("");
                    new_pass.setText("");
                    confirm_pass.setText("");
                    ((HomeActivity)getActivity()).mechanic.setPassword(new_pass_string);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parr = new HashMap<String, String>();
                parr.put("phone", ((HomeActivity)getActivity()).mechanic.getPhone().toString());
                parr.put("new_pass", new_pass_string);
                parr.put("method", "changepass");
                return parr;
            }
        };
        AppController.getInstance(getActivity()).addToRequestQueue(rq);
    }

    private boolean checkOldPass()
    {
        boolean old_pass_match = false;
        if (old_pass_string.equals(((HomeActivity)getActivity()).mechanic.getPassword()))
        {
            old_pass_match = true;
            t_old_pass.setErrorEnabled(false);
        }
        else
        {
            old_pass_match = false;
            t_old_pass.setError("Invalid password");
        }

        return old_pass_match;
    }

    private boolean checkNewPass()
    {
        boolean not_used_before = false;
        if(((HomeActivity)getActivity()).mechanic.getPassword().equals(new_pass_string))
        {
            not_used_before = false;
            t_new_pass.setError("This password is currently in use");
        }
        else if(new_pass_string == null || new_pass_string == "")
        {
            not_used_before = false;
            t_new_pass.setError("New password can't be empty");
        }
        else
        {
            not_used_before = true;
            t_new_pass.setErrorEnabled(false);
        }

        return not_used_before;
    }

    private boolean checkConfirmPass()
    {
        boolean pass_confirmed = false;
        if(new_pass_string.equals(confirm_pass_string))
        {
            pass_confirmed = true;
            t_confirm_pass.setErrorEnabled(false);
        }
        else
        {
            pass_confirmed = false;
            t_confirm_pass.setError("Password doesn't match");
        }

        return pass_confirmed;
    }

}
