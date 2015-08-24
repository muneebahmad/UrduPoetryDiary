package pk.muneebahmad.client.data;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pk.muneebahmad.client.util.Log;

/**
 * Created by muneebahmad on 17/05/15.
 */
public class TestParser {

    public static ArrayList<String> loadedFromAssetsList = new ArrayList<>();

    /**
     *
     * @param context
     */
    public static void read(Context context) {
        AssetManager am = context.getAssets();

        String text;

        try {
            InputStream is = am.open("test.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();
            int i = 1;

            while (line != null) {
                text = line;

                loadedFromAssetsList.add(text);
                line = br.readLine();
                i++;
            }

            br.close();
            isr.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.log("Error in text file");
        }
    }

    public static void print() {
        for (int i = 0; i < loadedFromAssetsList.size(); i++) {
            Log.log("Text -> " + loadedFromAssetsList.get(i));
        }
    }

}/** end class. */
