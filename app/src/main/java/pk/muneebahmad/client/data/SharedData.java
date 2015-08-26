package pk.muneebahmad.client.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import pk.muneebahmad.client.util.Log;

/**
 * Created by muneebahmad on 16/05/15.
 * Singleton
 */
public class SharedData {

    private static SharedData sInstance = null;

    private String date;
    private String poet;
    private String poem;
    private String poetu;
    private String poemu;

    private String searchByPoetPoet;
    private String searchByPoetPoem;
    private String searchByPoetDate;
    private String searchByPoetPoetu;
    private String searchByPoetPoemu;

    private String searchByDatePoet;
    private String searchByDateDate;
    private String searchByDatePoem;
    private String searchByDatePoemu;
    private String searchByDatePoetu;

    private int clickIndex = 0;

    public static enum ActivatedClasses {
        CLASS_DAILY_POETRY,
        CLASS_BY_POET,
        CLASS_BY_DATE,
        CLASS_MAIN
    }

    private int index = 0;

    private ActivatedClasses activatedClasses;

    public SharedData() {}

    /**
     *
     * @param activatedClasses
     */
    public void setActivatedClasses(ActivatedClasses activatedClasses) {
        this.activatedClasses = activatedClasses;
        Log.log(getActivatedClassName());
    }

    public String getActivatedClassName() {
        String val = "";
        if (this.activatedClasses == ActivatedClasses.CLASS_DAILY_POETRY) {
            val = "CLASS_DAILY_POETRY";
        } else if (this.activatedClasses == ActivatedClasses.CLASS_BY_POET) {
            val = "CLASS_BY_POET";
        } else if (this.activatedClasses == ActivatedClasses.CLASS_BY_DATE) {
            val = "CLASS_BY_DATE";
        } else if (this.activatedClasses == ActivatedClasses.CLASS_MAIN) {
            val = "CLASS_MAIN";
        } else {
            val = "CONFIG_NONE";
        }

        return val;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public void setPoemu(String poemu) {this.poemu = poemu;}

    public String getPoemu() {
        return this.poemu;
    }

    public void setPoetu(String poetu) { this.poetu = poetu; }

    public String getPoetu() { return this.poetu; }


    public String getSearchByPoetPoet() {
        return searchByPoetPoet;
    }

    public void setSearchByPoetPoet(String searchByPoetPoet) {
        this.searchByPoetPoet = searchByPoetPoet;
    }

    public String getSearchByPoetPoem() {
        return searchByPoetPoem;
    }

    public void setSearchByPoetPoem(String searchByPoetPoem) {
        this.searchByPoetPoem = searchByPoetPoem;
    }

    public String getSearchByPoetDate() {
        return searchByPoetDate;
    }

    public void setSearchByPoetDate(String searchByPoetDate) {
        this.searchByPoetDate = searchByPoetDate;
    }

    public String getSearchByPoetPoetu() {
        return searchByPoetPoetu;
    }

    public void setSearchByPoetPoetu(String searchByPoetPoetu) {
        this.searchByPoetPoetu = searchByPoetPoetu;
    }

    public String getSearchByPoetPoemu() {
        return searchByPoetPoemu;
    }

    public void setSearchByPoetPoemu(String searchByPoetPoemu) {
        this.searchByPoetPoemu = searchByPoetPoemu;
    }

    public String getSearchByDatePoet() {
        return searchByDatePoet;
    }

    public void setSearchByDatePoet(String searchByDatePoet) {
        this.searchByDatePoet = searchByDatePoet;
    }

    public String getSearchByDateDate() {
        return searchByDateDate;
    }

    public void setSearchByDateDate(String searchByDateDate) {
        this.searchByDateDate = searchByDateDate;
    }

    public String getSearchByDatePoem() {
        return searchByDatePoem;
    }

    public void setSearchByDatePoem(String searchByDatePoem) {
        this.searchByDatePoem = searchByDatePoem;
    }

    public String getSearchByDatePoemu() {
        return searchByDatePoemu;
    }

    public void setSearchByDatePoemu(String searchByDatePoemu) {
        this.searchByDatePoemu = searchByDatePoemu;
    }

    public String getSearchByDatePoetu() {
        return searchByDatePoetu;
    }

    public void setSearchByDatePoetu(String searchByDatePoetu) {
        this.searchByDatePoetu = searchByDatePoetu;
    }

    public int getClickIndex() {
        return this.clickIndex;
    }

    /**
     *
     * @param clickIndex
     */
    public void setClickIndex(int clickIndex) {
        this.clickIndex = clickIndex;
    }

    /**
     *
     * @param context
     */
    public static void makeExitDialog(final Activity activity, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit Dialog").
                setMessage("Do you really want to exit?").
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                }).setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rateUsClicked(activity);
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

    /**
     *
     * @param activity
     */
    private static void rateUsClicked(final Activity activity) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse("market://details?id=" +
                            "com.ardentlabs.urdupoetrydiary")));
        } catch (ActivityNotFoundException e) {
            Log.log("RATE US ActivityNotFoundException");
        }
    }

    /**
     *
     */
    public static void quitSystem() {
        System.exit(0);
    }

    /**
     *
     * @return activatedClasses
     */
    public ActivatedClasses getActivatedClasses() {
        return this.activatedClasses;
    }

    /**
     *
     * @param index int
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return index int
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Singleton instance.
     * @return new instance of {@link SharedData}
     */
    public static SharedData getInstance() {
        synchronized (SharedData.class) {
            if (sInstance == null) {
                sInstance = new SharedData();
            }
            return sInstance;
        }
    }

}/** end class. */
