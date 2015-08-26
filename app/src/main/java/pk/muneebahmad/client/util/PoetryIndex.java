package pk.muneebahmad.client.util;

/**
 * Created by muneebahmad on 12/07/15.
 */
public class PoetryIndex {

    private String date;
    private String poet;
    private String poetu;
    private String filename;

    /**
     *
     * @param poet
     * @param poetu
     * @param date
     * @param filename
     */
    public PoetryIndex(String poet, String poetu, String date, String filename) {
        this.date = date;
        this.poet = poet;
        this.filename = filename;
        this.poetu = poetu;
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
     * @return
     */
    public String getPoetu() {
        return this.poetu;
    }

    /**
     *
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param poet
     */
    public void setPoet(String poet) {
        this.poet = poet;
    }

    /**
     *
     * @param poetu
     */
    public void setPoetu(String poetu) {
        this.poetu = poetu;
    }

    /**
     *
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Compares one PoetryIndex Object with other.
     *
     * @param poetryIndex {@link pk.muneebahmad.client.util.PoetryIndex}
     * @return true or false depends upon comparison.
     */
    public boolean compareIndexes(PoetryIndex poetryIndex) {
        return (this.date.equalsIgnoreCase(poetryIndex.getDate())) &&
                (this.poet.equalsIgnoreCase(poetryIndex.getPoet())) &&
                (this.poetu.equalsIgnoreCase(poetryIndex.getPoetu())) &&
                (this.filename.equalsIgnoreCase(poetryIndex.getFilename()));
    }
}/** end class. */
