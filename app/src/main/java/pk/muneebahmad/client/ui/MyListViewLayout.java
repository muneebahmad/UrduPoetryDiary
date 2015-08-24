package pk.muneebahmad.client.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Typeface;

import com.ardentlabs.urdupoetrydiary.R;

/**
 * Created by muneebahmad on 23/07/15.
 */
public class MyListViewLayout extends LinearLayout {


    /**
     *
     * @param context
     * @param urduVal
     * @param romanVal
     * @param numVal
     */
    public MyListViewLayout(Context context, String urduVal, String romanVal, String numVal) {
        super(context);
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

        LinearLayout vert = new LinearLayout(context);
        vert.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams vertParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        vertParams.setMargins(5, 5, 5, 5);

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
        LinearLayout.LayoutParams icParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        icParams.setMargins(5, 5, 5, 5);
        icPanel.setBackgroundResource(R.drawable.tv_bg);

        TextView nView = new TextView(context);
        nView.setTextColor(Color.WHITE);
        nView.setText(n);
        nView.setGravity(Gravity.CENTER);
        icPanel.addView(nView, icParams);

        addView(vert);
        addView(icPanel);
    }

}/** end class. */
