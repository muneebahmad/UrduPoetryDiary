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
import java.net.URL;
import java.util.ArrayList;

import pk.muneebahmad.client.util.Dater;
import pk.muneebahmad.client.util.Log;

/**
 * Created by muneebahmad on 18/05/15.
 */
public class MyParser {

    public static ArrayList<String> urduFontList = new ArrayList<>();
    public static ArrayList<String> romanFontList = new ArrayList<>();

    private String date;
    private String poet;
    private String poem;

    private String poemu;
    private String poetu;

    private String url;

    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    /**
     * @param url {@link String}
     */
    public MyParser(String url) {
        this.url = url;
    }

    /**
     * @return
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @return
     */
    public String getPoet() {
        return this.poet;
    }

    /**
     * @return
     */
    public String getPoem() {
        return this.poem;
    }

    /**
     * @param myParser {@link XmlPullParser}
     */
    public void parseXMLAndStore(XmlPullParser myParser) {
        int event;
        String text = null;
        int i = 1;

        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equalsIgnoreCase("date")) {
                            this.date = myParser.getAttributeValue(null, "val");
                            Log.log("DATE IN XML >>>> " + date);
                            if (this.date.equalsIgnoreCase(Dater.getInstance().getDate())) {
                                SharedData.getInstance().setDate(date);
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (SharedData.getInstance().getDate() != null) {
                            if (SharedData.getInstance().getDate().equalsIgnoreCase(Dater.getInstance().getDate()))
                                text = myParser.getText();
                            else
                                Log.log("Date not matching in CASE TEXT");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (SharedData.getInstance().getDate() != null) {
                            if (SharedData.getInstance().getDate().equalsIgnoreCase(this.date)) {
                                if (name.equals("poet")) {
                                    this.poet = myParser.getAttributeValue(null, "val");
                                    SharedData.getInstance().setPoet(poet);
                                } else if (name.equals("poem")) {
                                    this.poem = myParser.getAttributeValue(null, "val");
                                    SharedData.getInstance().setPoem(poem);
                                } else if (name.equals("poemu")) {
                                    this.poemu = myParser.getAttributeValue(null, "val");
                                    SharedData.getInstance().setPoemu(poemu);
                                } else if (name.equals("poetu")) {
                                    this.poetu = myParser.getAttributeValue(null, "val");
                                    SharedData.getInstance().setPoetu(poetu);
                                } else if (name.equals("urdu")) {
                                    urduFontList.add(text);
                                } else if (name.equals("roman")) {
                                    romanFontList.add(text);
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:

                        break;
                }
                //Log.log("At line >>>> " + i);
                ++i;
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.log("XML IOException IOException ... ->");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.log("XML PARSER ERROR ... ->");
        }
    }

    /**
     *
     * @param url
     */
    public void fetchXmlFromWeb(final String url) {
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
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myNewParser = xmlFactoryObject.newPullParser();

                    myNewParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myNewParser.setInput(stream, null);
                    parseXMLAndStore(myNewParser);
                    stream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.log("WEB URL Retriving error");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.log("WEB URL Retriving error IOException");
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Log.log("WEB URL Retriving error XML PULL PARSER EXP");
                }
            }
        });

        pThread.start();
    }

    public void fetchFromAssets(Context context) {
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open("july.xml");

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myNewParser = xmlFactoryObject.newPullParser();

            myNewParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

            Log.log("Heppend until setInput(br)");

            myNewParser.setInput(is, null);
            Log.log("Heppend until setInput(br)");
            parseXMLAndStore(myNewParser);
            Log.log("Heppend until parse xml store");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log("Assets Retriving error");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.log("Assets URL Retriving error XML PULL PARSER EXP");
        }
    }

}/**
 * end class.
 */
