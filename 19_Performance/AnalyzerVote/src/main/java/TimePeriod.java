import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod> {
    //private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static long day = 89900000; //1970.01.02

    private long from;
    private long to;

    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to) {
        this.from = from;
        this.to = to;
        //if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
        if (from / day != to / day)
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

//    public TimePeriod(Date from, Date to)
//    {
//        this.from = from.getTime();
//        this.to = to.getTime();
//        if(!dayFormat.format(from).equals(dayFormat.format(to)))
//            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
//    }

    public void appendTime(long visitTime) {
        //if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(visitTime.getTime()))))
        if (from / day != visitTime / day)
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        //long visitTimeTs = visitTime.getTime();
        if (visitTime < from) {
            from = visitTime;
        }
        if (visitTime > to) {
            to = visitTime;
        }
    }

    public String toString() {
        String from = dateFormat.format(this.from);
        String to = timeFormat.format(this.to);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period) {
//        Date current = new Date();
//        Date compared = new Date();
        Long current = from / day;
        Long compared = period.from / day;
//        try {
//            current = dayFormat.parse(dayFormat.format(new Date(from)));
//  compared = dayFormat.parse(dayFormat.format(new Date(period.from)));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return current.compareTo(compared);
    }
}
