package com.gearup.pranto.gearupmechanic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class HomeFragment extends Fragment {

    TextView welcome;
    Button online, ofline;
    ImageView indicator;
    boolean is_online = false;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        welcome = (TextView) view.findViewById(R.id.welcome);
        online = (Button) view.findViewById(R.id.online);
        ofline = (Button) view.findViewById(R.id.ofline);
        indicator = (ImageView) view.findViewById(R.id.indicator);
        welcome.setText("Welcome " + ((HomeActivity)getActivity()).mechanic.getName() +
                "\nYou are now on the Home page of Gear Up Mechanic. " +
                "To start getting job requests from clients, press the Online " +
                "button below.\"");
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).goOnline();
                online.setClickable(false);
                ofline.setClickable(true);
                if(is_online == false)
                {
                    indicator.setImageResource(R.drawable.icon_online);
                    is_online = true;
                }
            }
        });

        ofline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).goOfline();
                ofline.setClickable(false);
                online.setClickable(true);
                if(is_online)
                {
                    indicator.setImageResource(R.drawable.icon_offline);
                    is_online = false;
                }
            }
        });

        return  view;
    }

}
