package pk.muneebahmad.client.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ardentlabs.urdupoetrydiary.RomanFragment;
import com.ardentlabs.urdupoetrydiary.UrduFragment;

/**
 * Created by muneebahmad on 17/05/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //ROMAN URDU ACTIIVTY
                return new RomanFragment();
            case 1:
                //URDU FONT ACTIVITY
                return new UrduFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence val = "";
        switch (position) {
            case 0:
                val = "Roman";
                break;
            case 1:
                val = "اردو";
                break;
        }
        return val;
    }
}/** end class. */
