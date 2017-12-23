package com.gearup.pranto.gearupmechanic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class MyAccountFragment extends Fragment {

    TextView name, email, phone, service, address;
    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_account, container, false);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        service = view.findViewById(R.id.service);
        address = (TextView) view.findViewById(R.id.address);
        name.setText("Name: " + ((HomeActivity)getActivity()).mechanic.getName());
        email.setText("Email: " + ((HomeActivity)getActivity()).mechanic.email);
        phone.setText("Phone: " + ((HomeActivity)getActivity()).mechanic.getPhone());
        service.setText("Service: " + ((HomeActivity)getActivity()).mechanic.getService());
        address.setText("Address: " + ((HomeActivity)getActivity()).mechanic.getAddress());

        return view;
    }


}
