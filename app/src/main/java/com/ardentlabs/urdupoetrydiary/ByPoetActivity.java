package com.ardentlabs.urdupoetrydiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import pk.muneebahmad.client.data.PoetParser;
import pk.muneebahmad.client.data.SharedData;
import pk.muneebahmad.client.ui.MyListViewLayout;
import pk.muneebahmad.client.util.Dater;
import pk.muneebahmad.client.util.PoetryIndex;

/**
 * Created by muneebahmad on 23/07/15.
 */
public class ByPoetActivity extends ActionBarActivity {

    private TextView titleView;
    private ScrollView scrollView;
    private LinearLayout mainLayout;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_bottom_push_in, R.anim.anim_bottom_push_in_2);
        setContentView(R.layout.by_poet_activity);
        setToolbar();
        initComponents();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.poet_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initComponents() {
        titleView = (TextView) findViewById(R.id.poet_tv);
        this.scrollView = (ScrollView) findViewById(R.id.poet_scrollview);
        this.mainLayout = (LinearLayout) findViewById(R.id.by_poet_linear_layout);
        if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_BY_POET) {
            titleView.setText("Poetry by Poet");
            populateByPoetData();
        } else if (SharedData.getInstance().getActivatedClasses() == SharedData.ActivatedClasses.CLASS_BY_DATE) {
            titleView.setText("Poetry of Date: " + Dater.getInstance().getDate());
        }
    }

    private void populateByPoetData() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 2, 0, 2);
        for (int i = 0; i < PoetParser.indexList.size(); i++) {
            this.mainLayout.addView(new MyListViewLayout(this, PoetParser.indexList.get(i).getPoetu(),
                    PoetParser.indexList.get(i).getPoet(),  i + "", i), params);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}/** end class. */
