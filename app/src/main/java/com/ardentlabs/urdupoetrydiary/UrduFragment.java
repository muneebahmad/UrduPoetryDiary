package com.ardentlabs.urdupoetrydiary;

import android.graphics.Color;
import android.graphics.Typeface;
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
public class UrduFragment extends Fragment {

    private TextView urduView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_urdu, container, false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "asunaskh.ttf");
        this.urduView = (TextView) rootView.findViewById(R.id.urdu_tv1);
        //urduView.setTypeface(tf);
        urduView.setTypeface(tf, Typeface.BOLD);
        urduView.setTextSize(22.0f);
        //urduView.setTextColor(Color.parseColor("#45bcd3"));
        urduView.setTextColor(Color.BLACK);
        urduView.setText("\n");

        if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_DAILY_POETRY) {
            if (MyParser.urduFontList.size() <= 1) {
                SharedData.getInstance().setPoetu("شاعر");
                SharedData.getInstance().setPoemu("نزم");
                SharedData.getInstance().setDate(Dater.getInstance().getDate());

                MyParser.urduFontList.add("معزرت اس تا ریخ :" + Dater.getInstance().getDate() + " کے لیے کوئ زخیرہ موجود نہیں۔ " +
                        "از راہ کرم اپنا بینالاقوامی ربطہ بحال کریں۔");
            }

            for (int i = 0; i < MyParser.urduFontList.size(); i++) {
                urduView.append(MyParser.urduFontList.get(i) + "\n");
            }
        } else if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_BY_DATE) {

        } else if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_BY_POET) {

        }

        return rootView;
    }

}/** end class. */
