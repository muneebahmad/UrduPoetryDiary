package com.ardentlabs.urdupoetrydiary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pk.muneebahmad.client.data.MyParser;
import pk.muneebahmad.client.data.SharedData;
import pk.muneebahmad.client.util.Dater;

/**
 * Created by muneebahmad on 17/05/15.
 */
public class RomanFragment extends Fragment {

    private TextView romanView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_roman, container, false);

        romanView = (TextView) rootView.findViewById(R.id.roman_tv1);
        romanView.setTextColor(Color.parseColor("#000000"));
        romanView.setTextSize(22.0f);

        romanView.setText("\n");

        if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_DAILY_POETRY) {
            if (MyParser.urduFontList.size() <= 1) {
                SharedData.getInstance().setPoet("Poet Name");
                SharedData.getInstance().setPoem("Poetry Title");
                SharedData.getInstance().setDate(Dater.getInstance().getDate());

                MyParser.romanFontList.add("Sorry! No data available for this date: " + Dater.getInstance().getDate() +
                        " Please check your internet connection");
            }

            for (int i = 0; i < MyParser.romanFontList.size(); i++) {
                romanView.append(MyParser.romanFontList.get(i) + "\n");
            }
        }

        return rootView;
    }
}/** end class. */
