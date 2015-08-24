package pk.muneebahmad.client.util;

/**
 * Created by muneebahmad on 12/07/15.
 */
public class PoetryIndex {

    private String date;
    private String poet;
    private String filename;

    /**
     *
     * @param date
     * @param poet
     * @param filename
     */
    public PoetryIndex(String date, String poet, String filename) {
        this.date = date;
        this.poet = poet;
        this.filename = filename;
    }

    /**
     *
     * @return Date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return poet
     */
    public String getPoet() {
        return poet;
    }

    /**
     *
     * @return filename
     */
    public String getFilename() {
        return filename;
    }
}/** end class. */
