import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    private static final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static final HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String fileName = "res/data-1572M.xml";
        long usage1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        SAXParser parser = factory.newSAXParser();
           SQLHandler sqlHandler = new SQLHandler();
//        parser.parse(new File(fileName), sqlHandler);
        sqlHandler.duplicatedVoters();


       /*SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        handler.workTimesVotingSAX();
        handler.printDuplicatedVoters(); */


          //parseFile(fileName);
          //DBConnection.printVoterCounts();
//        System.out.println("Voting station work times: ");
//
//        for (Integer votingStation : voteStationWorkTimes.keySet()) {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//
//        System.out.println("Duplicate voters: ");
//        for (Voter voter : voterCounts.keySet()) {
//            Integer count = voterCounts.get(voter);
//            if (count > 1) {
//                System.out.println("\t" + voter + " - " + count);
//            }
//        }

        long usage2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("До: " + converterMB(usage1));
        System.out.println("После: " + converterMB(usage2));
        System.out.println("Время: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " секунд");
    }

    public static String converterMB(long number) {
        String mb = String.valueOf(number / 1000 / 1000);
        String strNumber = String.valueOf(number);
        String kbAndByte = strNumber.replaceFirst(mb, "");
        String regex = "\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(kbAndByte);
        String KB = matcher.find() ? matcher.group() : String.valueOf(0);
        String bytes = kbAndByte.replaceFirst(KB, "");
        String convert = mb + " Мб " + KB + " Кб " + bytes + " байт ";
        return convert;
    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
       // fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        //считаем, кто голосовал два или более раза подряд
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            //Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());
            LocalDate birthDay = LocalDate.parse(attributes.getNamedItem("birthDay").getNodeValue(), XMLHandler.birthDayFormat);
            //String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
            Voter voter = new Voter(name, birthDay);
            Integer count = voterCounts.get(voter);
            voterCounts.put(voter, count == null ? 1 : count + 1);


        }
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        //расчитываем время работы каждого избирательного участка
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }


        /*-Xmx2500M
        -XX:+UnlockExperimentalVMOptions
        -XX:+UseEpsilonGC*/
    //-----------------------------------------------------------------------
    //---------------------------SAX----До-оптимизации-----------------------
    //---------------------------------1-------------------------------------
    //  До: 2 Мб 874 Кб 056 байт
    //  После: 2276 Мб 736 Кб 064 байт
    //  Время: 7.023 секунд
    //---------------------------------2-------------------------------------
    //    До: 3 Мб 034 Кб 872 байт
    //    После: 2274 Мб 058 Кб 120 байт
    //    Время: 9.35 секунд
    //---------------------------------3-------------------------------------
    //    До: 2 Мб 836 Кб 448 байт
    //    После: 2275 Мб 106 Кб 488 байт
    //    Время: 6.67 секунд
    //-----------------------------------------------------------------------

    //---------------------------DOM----До-оптимизации--------------------
    //---------------------------------1-------------------------------------
    //  До: 2 Мб 838 Кб 496 байт
    //  После: 2422 Мб 952 Кб 232 байт
    //  Время: 7.808 секунд
    //---------------------------------2-------------------------------------
    //  До: 2 Мб 836 Кб 448 байт
    //  После: 2427 Мб 239 Кб 888 байт
    //  Время: 7.468 секунд
    //---------------------------------3-------------------------------------
    //    До: 2 Мб 879 Кб 816 байт
    //    После: 2429 Мб 921 Кб 920 байт
    //    Время: 7.964 секунд

    //-----------------------------------------------------------------------
    //---------------------------SAX----После-оптимизации-----------------------
    //---------------------------------1-------------------------------------
    //    До: 3 Мб 036 Кб 920 байт
    //    После: 266 Мб 618 Кб 272 байт
    //    Время: 2.04 секунд
    //---------------------------------2-------------------------------------
    //    До: 3 Мб 036 Кб 920 байт
    //    После: 266 Мб 618 Кб 688 байт
    //    Время: 2.032 секунд
    //---------------------------------3-------------------------------------
    //    До: 3 Мб 036 Кб 920 байт
    //    После: 266 Мб 618 Кб 168 байт
    //    Время: 1.966 секунд
    //-----------------------------------------------------------------------

    //---------------------------DOM----После-оптимизации--------------------
    //---------------------------------1-------------------------------------
    //    До: 3 Мб 036 Кб 920 байт
    //    После: 420 Мб 949 Кб 544 байт
    //    Время: 2.133 секунд
    //---------------------------------2-------------------------------------
    //    До: 3 Мб 034 Кб 872 байт
    //    После: 420 Мб 947 Кб 664 байт
    //    Время: 2.022 секунд
    //---------------------------------3-------------------------------------
    //    До: 3 Мб 036 Кб 920 байт
    //    После: 421 Мб 590 Кб 648 байт
    //    Время: 2.039 секунд



}
