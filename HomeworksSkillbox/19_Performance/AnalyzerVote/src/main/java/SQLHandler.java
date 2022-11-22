import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SQLHandler extends DefaultHandler {
    private StringBuilder sqlRequest = new StringBuilder();
    private Voter voter;
    public static DateTimeFormatter birthDayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private int COUNT = 0;
    private int LIMIT = 3_000_000;
    private String startSQLRequest = "INSERT INTO voter_count(name, birthDate) VALUES";

    @Override
    public void startDocument() {
        sqlRequest.append(startSQLRequest);
    }

    @Override
    public void endDocument() {

//        try {
//            sqlRequest.setCharAt(sqlRequest.lastIndexOf(","), ';');
//            DBConnection.writeVoters(sqlRequest);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

   }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (COUNT < LIMIT){
            if (qName.equals("voter") && voter == null) {
                LocalDate birthDay = LocalDate.parse(attributes.getValue("birthDay"), birthDayFormat);
                voter = new Voter(attributes.getValue("name"), birthDay);
            } else if (qName.equals("visit") && voter != null) {
                sqlRequest.append("('")
                        .append(voter.getName()).append("', '")
                        .append(birthDayFormat.format(voter.getBirthDay())).append("'),");
                COUNT++;
                if (COUNT % 300_000 == 0){
                    try {
                        clearStringBuilder();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void clearStringBuilder() throws SQLException {
        sqlRequest.setCharAt(sqlRequest.lastIndexOf(","), ';');
        DBConnection.writeVoters(sqlRequest);
        sqlRequest.delete(0, sqlRequest.length());
        sqlRequest.append(startSQLRequest);
    }
    public void duplicatedVoters() throws SQLException {
        DBConnection.printVoterCounts();
    }

}
