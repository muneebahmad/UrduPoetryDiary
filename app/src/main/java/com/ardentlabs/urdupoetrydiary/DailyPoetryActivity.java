package com.ardentlabs.urdupoetrydiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pk.muneebahmad.client.data.SharedData;
import pk.muneebahmad.client.ui.MyPagerAdapter;

/**
 * Created by muneebahmad on 17/05/15.
 */
public class DailyPoetryActivity extends ActionBarActivity implements ActionBar.TabListener,
        View.OnClickListener {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private ActionBar actionBar;
    private PagerTitleStrip titleStrip;

    private TextView poetView;
    private TextView poetryView;
    private TextView dateView;

    private Button leftButt;
    private Button rightButt;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_bottom_push_in, R.anim.anim_bottom_push_in_2);
        setContentView(R.layout.activity_daily_poetry);
        setToolbar();

        viewPager = (ViewPager) findViewById(R.id.daily_poetry_pager);
        titleStrip = (PagerTitleStrip) findViewById(R.id.daily_pager_title_strip);
        actionBar = getSupportActionBar();
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    poetryView.setText(SharedData.getInstance().getPoem());
                    poetView.setText(SharedData.getInstance().getPoet());
                } else if (position == 1) {
                    poetryView.setText(SharedData.getInstance().getPoemu());
                    poetView.setText(SharedData.getInstance().getPoetu());
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initComponents();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.daily_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initComponents() {
        this.poetView = (TextView) findViewById(R.id.daily_poetry_poet_name);
        this.poetryView = (TextView) findViewById(R.id.daily_poetry_poem_name);
        this.dateView = (TextView) findViewById(R.id.daily_poetry_date);

        this.leftButt = (Button) findViewById(R.id.daily_poetry_butt_left);
        this.rightButt = (Button) findViewById(R.id.daily_poetry_butt_right);

        this.leftButt.setOnClickListener(this);
        this.rightButt.setOnClickListener(this);

        this.dateView.setText(SharedData.getInstance().getDate());
        this.poetView.setText(SharedData.getInstance().getPoet());
        this.poetryView.setText(SharedData.getInstance().getPoem());
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        if (tab.getText().equals("Roman")) {
            this.poetryView.setText(SharedData.getInstance().getPoem());
            this.poetView.setText(SharedData.getInstance().getPoet());
        } else if (tab.getText().equals("Urdu")) {
            this.poetryView.setText(SharedData.getInstance().getPoemu());
            this.poetView.setText(SharedData.getInstance().getPoetu());
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
        } else if (id == R.id.action_quit) {
            SharedData.getInstance().makeExitDialog(this, getApplicationContext());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == this.leftButt) {

        } else if (view == this.rightButt) {

        }
    }
}/** end class. */
