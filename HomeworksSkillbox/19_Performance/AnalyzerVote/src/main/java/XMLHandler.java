import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private StringBuilder sqlRequest = new StringBuilder();
    private Voter voter;
    public static DateTimeFormatter birthDayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private HashMap<Voter, Byte> voterCounts = new HashMap<>();
    private static HashMap<Integer, WorkTime> voteStationWorkTime = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                LocalDate birthDay = LocalDate.parse(attributes.getValue("birthDay"), birthDayFormat);
                voter = new Voter(attributes.getValue("name"), birthDay);
            } else if (qName.equals("visit") && voter != null) {
                byte count = voterCounts.getOrDefault(voter, (byte) 0);
                voterCounts.put(voter, (byte) (count + 1));
                Integer station = Integer.parseInt(attributes.getValue("station"));
                long time = visitDateFormat.parse(attributes.getValue("time")).getTime();
                WorkTime workTime = voteStationWorkTime.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTime.put(station, workTime);
                }
                workTime.addVisitTime(time);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void workTimesVotingSAX() {
        for (Integer votingStation : voteStationWorkTime.keySet()) {
            WorkTime workTime = voteStationWorkTime.get(votingStation);
            System.out.println(votingStation + " - " + workTime);
        }
    }

    public void printDuplicatedVoters() {
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
