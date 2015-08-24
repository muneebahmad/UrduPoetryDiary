package pk.muneebahmad.client.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by muneebahmad on 17/05/15.
 */
public class DP {

    public DP() {}

    /**
     *
     * @param context {@link Context}
     * @param dp int
     * @return pixels (px) converted from DP in int
     */
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     *
     * @param context {@link Context}
     * @param px int
     * @return DP converted from Pixels(px) in int
     */
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

}/** end class. */
