package com.ardentlabs.urdupoetrydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pk.muneebahmad.client.data.MyParser;
import pk.muneebahmad.client.data.PoetParser;

/**
 * Created by muneebahmad on 16/05/15.
 */
public class SplashActivity extends Activity {

    private ImageView splashView;
    private Animation logoAnim;
    private MyParser myParser;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_top_push_in, R.anim.anim_top_push_in_2);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_aplash);
        setSplashLogo();
        openFromAssets();
        pThread.start();
    }

    private void openFromAssets() {
        myParser = new MyParser("");
        //myParser.fetchFromAssets(getApplicationContext());
        myParser.fetchXmlFromWeb("http://thepoetrydiary.com/urdupoetry/august-15.xml");
        PoetParser.getsInstance("").fetchFromAssets(getApplicationContext());
    }

    private void setSplashLogo() {
        this.splashView = (ImageView) findViewById(R.id.splash_logo);
        this.logoAnim = AnimationUtils.loadAnimation(this, R.anim.anim_spl);
        this.splashView.setAnimation(logoAnim);
    }

    Thread pThread = new Thread() {
        @Override
        public void run() {
            try {
                sleep(3000);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }
    };

    @Override
    public void onBackPressed() {}

}/** end class. */
