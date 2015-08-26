package pk.muneebahmad.client.data;

import android.content.Context;
import android.content.res.AssetManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import pk.muneebahmad.client.util.Log;
import pk.muneebahmad.client.util.PoetryIndex;

/**
 * Created by muneebahmad on 15/07/15.
 */
public class PoetParser {
    
    public static java.util.ArrayList<PoetryIndex> indexList = new java.util.ArrayList<>();
    public static java.util.ArrayList<PoetryIndex> addIndexList = new java.util.ArrayList<>();

    private static PoetParser sInstance = null;

    private String url;
    private String id;
    private String poet;
    private String poetu;

    private int counter;

    private XmlPullParserFactory xmlPullParserFactory;
    public volatile boolean parsingComplete = true;

    /**
     *
     * @param url String
     */
    public PoetParser(final String url) {
        this.url = url;
    }

    /**
     *
     * @return
     */
    public String getId() { return this.id; }

    /**
     *
     * @return
     */
    public String getPoet() { return this.poet; }

    /**
     *
     * @param parser
     */
    private void parseXML(XmlPullParser parser) throws IOException, XmlPullParserException {
        int event;
        String text = null;
        String name = null;
        String nameu = null;
        String date = null;
        String filename = null;
        int i = 1;

        event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String n = parser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (n.equals("name")) {
                        name = parser.getAttributeValue(null, "val");
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if (n.equals("nameu")) {
                        nameu = text;
                    } else if (n.equals("date")) {
                        date = text;
                    } else if (n.equals("filename")) {
                        filename = text;
                        indexList.add(new PoetryIndex(name, nameu, date, filename));
                    }
                    break;
            }
            //Log.log("At KKKK >>> " + i);
            ++i;
            event = parser.next();
        }
        parsingComplete = false;
    }

    /**
     *
     * @param context
     */
    public void fetchFromAssets(final Context context) {
        AssetManager am = context.getAssets();
        try {
            java.io.InputStream is = am.open("poetry_index.xml");
            xmlPullParserFactory= XmlPullParserFactory.newInstance();
            XmlPullParser myNewParser = xmlPullParserFactory.newPullParser();
            myNewParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myNewParser.setInput(is, null);
            parseXML(myNewParser);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void fetchFromWeb() {
        Thread pThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urls = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlPullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser myNewParser = xmlPullParserFactory.newPullParser();

                    myNewParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myNewParser.setInput(stream, null);
                    parseXML(myNewParser);
                    stream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    addToAddedList();
                }
            }
        });
        pThread.start();
    }

    private void addToAddedList() {
        if (indexList.size() > 0) {
            for (int i = 0; i < indexList.size(); i++) {
                if (addIndexList.size() > 0) {
                    for (int j = 0; j < addIndexList.size(); j++) {
                        if (indexList.get(i).compareIndexes(addIndexList.get(j))) {
                            Log.log("ADDED LIST >>>> " + j);
                        }
                    }
                } else {
                    addIndexList.add(indexList.get(i));
                    Log.log("ELSE IN ADDED LIST");
                }
            }
        }
    }

    /**
     *
     * @param url String url of file web/assets
     * @return Singleton instance of {@link PoetParser} if instance is null.
     */
    public static PoetParser getsInstance(final String url) {
        synchronized (PoetParser.class) {
            if (sInstance == null) {
                sInstance = new PoetParser(url);
            }
            return sInstance;
        }
    }

}/** end class. */
