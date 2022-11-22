import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final String FILENAME = "data/maps.json";

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");
        ArrayList<Line> lines = new ArrayList<>();  //сюда линии пихаем
        namesOfLines.forEach(el -> {
            lines.add(new Line(el.text(), el.attr("data-line")));
        });

        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        Map<String, ArrayList<String>> stations = new HashMap<String, ArrayList<String>>();
        namesOfStations.forEach(el -> {
            ArrayList<String> nameStation = new ArrayList<>();
            final String[] numberLine = new String[1];
            el.children().forEach(element -> {
                nameStation.add(element.getElementsByClass("name").text()); // название станции
                numberLine[0] = el.attr("data-line"); // номер линии
            });
            stations.put(numberLine[0], nameStation);
        });

        List<List<Connection>> connections = new ArrayList<>();
        List<String> connectionStationList = new ArrayList<>();
        Elements namesOfConnection = doc.getElementsByClass("t-icon-metroln");
        namesOfConnection.forEach(el -> {
              String nameLine = el.text();
              String nameConnect = el.parent().getElementsByClass("name").text() + " " + el.attr("title");
              if (nameConnect.equals(" ")) {
                  connectMethod(connectionStationList, nameLine, connections);
              } else {
                  connectionStationList.add(nameConnect);
              }
          }
        );

        Metro metro = new Metro(lines, stations, connections);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("data/metro.json").toFile(), metro);

    }

    private static void connectMethod(List<String> connection, String nameStation, List<List<Connection>> connections) {
        List<Connection> listClassConnection = new ArrayList<>();
        listClassConnection.add(new Connection(nameStation, connection));
        connections.add(listClassConnection);
        connection.clear();
    }


}





