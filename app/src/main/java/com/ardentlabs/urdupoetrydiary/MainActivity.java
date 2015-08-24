package com.ardentlabs.urdupoetrydiary;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pk.muneebahmad.client.data.PoetParser;
import pk.muneebahmad.client.data.SharedData;
import pk.muneebahmad.client.util.Dater;
import pk.muneebahmad.client.util.Log;

/**
 * @author muneebahmad
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button buttDailyPoetry;
    private Button buttByPoet;
    private Button buttByDate;
    private Button buttMoreApps;
    private Button buttTellAFrnd;

    private static final String MENU_SHARE = "Hey! I found this wonderful Urdu poetry Android app \'" +
            "Urdu Poetry Diary\' on the Google Play store, just check it out and enjoy!\n\n\n" +
            "https://play.google.com/store/apps/details?id=com.ardentlabs.urdupoetrydiary";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_top_push_in, R.anim.anim_top_push_in_2);
        setContentView(R.layout.activity_main);
        setToolbar();
        initUI();

        Log.log(Dater.getInstance().getDate());
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        SharedData.getInstance().setActivatedClasses(SharedData.ActivatedClasses.CLASS_MAIN);
    }

    private void initUI() {
        this.buttDailyPoetry = (Button) findViewById(R.id.butt_daily_poetry);
        this.buttByPoet = (Button) findViewById(R.id.butt_by_poet);
        this.buttByDate = (Button) findViewById(R.id.butt_by_date);
        this.buttMoreApps = (Button) findViewById(R.id.butt_more_apps);
        this.buttTellAFrnd = (Button) findViewById(R.id.butt_tell_a_friend);

        this.buttDailyPoetry.setOnClickListener(this);
        this.buttByPoet.setOnClickListener(this);
        this.buttByDate.setOnClickListener(this);
        this.buttMoreApps.setOnClickListener(this);
        this.buttTellAFrnd.setOnClickListener(this);
    }

    private void makeExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Dialog").
                setMessage("Do you really want to exit?").
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rateUsClicked();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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
        makeExitDialog();
    }

    private void moreAppsClicked() {
        try {
            startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse("market://search?q=pub:Ardent Labs")));
        } catch (ActivityNotFoundException e) {
            Log.log("PUB ActivityNotFoundException");
        }
    }

    private void rateUsClicked() {
        try {
            startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse("market://details?id=" +
                    "com.ardentlabs.urdupoetrydiary")));
        } catch (ActivityNotFoundException e) {
            Log.log("RATE US ActivityNotFoundException");
        }
    }

    private void makeShareDialog() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, MENU_SHARE);
        intent.setType("text/plain");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.log("SHARE INTENT ActivityNotFoundException");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == this.buttDailyPoetry) {
            SharedData.getInstance().setActivatedClasses(SharedData.ActivatedClasses.CLASS_DAILY_POETRY);
            startActivity(new Intent(getApplicationContext(), DailyPoetryActivity.class));
            finish();
        } else if (view == this.buttByPoet) {
            SharedData.getInstance().setActivatedClasses(SharedData.ActivatedClasses.CLASS_BY_POET);
            for (int i = 0; i < PoetParser.indexList.size(); i++) {
                Log.log("ARRAYLIST >>> " + PoetParser.indexList.get(i).getPoet());
            }
            startActivity(new Intent(this, ByPoetActivity.class));
            finish();
        } else if (view == this.buttByDate) {
            SharedData.getInstance().setActivatedClasses(SharedData.ActivatedClasses.CLASS_BY_DATE);

        } else if (view == this.buttMoreApps) {
            moreAppsClicked();
        } else if (view == this.buttTellAFrnd) {
            makeShareDialog();
        }
    }
}/** end class. */
