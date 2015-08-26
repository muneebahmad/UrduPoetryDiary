package pk.muneebahmad.client.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Typeface;

import com.ardentlabs.urdupoetrydiary.R;

import pk.muneebahmad.client.data.SharedData;
import pk.muneebahmad.client.util.DP;
import pk.muneebahmad.client.util.Log;

/**
 * Created by muneebahmad on 23/07/15.
 */
public class MyListViewLayout extends LinearLayout implements View.OnClickListener {

    private Context context;
    private int i = 0;

    /**
     *
     * @param context
     * @param urduVal
     * @param romanVal
     * @param numVal
     * @param i index
     */
    public MyListViewLayout(Context context, String urduVal, String romanVal, String numVal, int i) {
        super(context);
        this.context = context;
        this.i = i;
        this.setOnClickListener(this);
        make(context, urduVal, romanVal, numVal);
    }

    /**
     *
     * @param context
     * @param u
     * @param r
     * @param n
     */
    private void make(final Context context, String u, String r, String n) {
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setBackgroundResource(R.drawable.tv_bg);
        this.setWeightSum(4);

        LinearLayout vert = new LinearLayout(context);
        vert.setOrientation(LinearLayout.VERTICAL);
        vert.setGravity(Gravity.RIGHT);
        LinearLayout.LayoutParams vertParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        vertParams.setMargins(5, 5, 5, 5);
        vertParams.weight = 3;
        vert.setLayoutParams(vertParams);


        TextView urduView = new TextView(context);
        urduView.setTextColor(Color.parseColor("#45BCD3"));
        urduView.setTypeface(Typeface.createFromAsset(context.getAssets(), "asunaskh.ttf"), Typeface.BOLD);
        urduView.setText(u);
        urduView.setGravity(Gravity.RIGHT);
        vert.addView(urduView, vertParams);

        TextView romanView = new TextView(context);
        romanView.setTextColor(Color.BLACK);
        romanView.setText(r);
        romanView.setGravity(Gravity.RIGHT);
        vert.addView(romanView, vertParams);

        LinearLayout icPanel = new LinearLayout(context);
        icPanel.setOrientation(LinearLayout.VERTICAL);
        icPanel.setGravity(Gravity.RIGHT);
        LinearLayout.LayoutParams icParams = new LinearLayout.LayoutParams(DP.dpToPx(context, 60),
                DP.dpToPx(context, 60));
        icParams.setMargins(1, 1, 1, 1);
        icParams.weight = 1;
        icPanel.setBackgroundResource(R.drawable.tv_bg2);

        TextView nView = new TextView(context);
        nView.setTextColor(Color.WHITE);
        nView.setText(n);
        nView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        icPanel.addView(nView);

        addView(vert);
        addView(icPanel, icParams);
    }

    @Override
    public void onClick(View view) {
        if (view == this) {
            Log.log("Hello >> " + (i + 1) + " Clicked!...");
            SharedData.getInstance().setClickIndex(i);
        }
    }
}/** end class. */
