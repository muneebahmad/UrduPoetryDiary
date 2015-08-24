package pk.muneebahmad.client.util;

import java.util.Calendar;

/**
 * Created by muneebahmad on 09/07/15.
 */
public class Dater {

    private int day;
    private int month;
    private int finalMonth;
    private int year;

    private final Calendar calendar = Calendar.getInstance();

    private static final String JAN = "January";
    private static final String FEB = "February";
    private static final String MAR = "March";
    private static final String APR = "April";
    private static final String MAY = "May";
    private static final String JUN = "June";
    private static final String JUL = "July";
    private static final String AUG = "August";
    private static final String SEP = "September";
    private static final String OCT = "October";
    private static final String NOV = "November";
    private static final String DEC = "December";

    public Dater() {}

    public String getDate() {
        this.day = getDateFromCalendar();
        this.month = getMonthFromCalendar();
        this.year = calendar.get(Calendar.YEAR);

        this.finalMonth = month++;

        String finalYear = year + "";
        String retYear = finalYear.substring(2);

        return "" + day + "-" + month + "-" + retYear;
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    private int getDateFromCalendar() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMonthFromCalendar() {
        return calendar.get(Calendar.MONTH);
    }

    /**
     *
     * @return new instance of {@link Dater} {@code class}
     */
    public static Dater getInstance() {
        return new Dater();
    }

}/** end class. */
