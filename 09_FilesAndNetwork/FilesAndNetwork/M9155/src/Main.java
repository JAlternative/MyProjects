import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILENAME = "data/maps.json";

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        JSONObject nameLineAndNumber = new JSONObject();
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");

        namesOfLines.forEach(el -> {
            nameLineAndNumber.put(el.attr("data-line"), el.text());
        });

        JSONObject numberLineAndNameStation = new JSONObject();
        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        namesOfStations.forEach(el -> {

            JSONArray station = new JSONArray();
            final String[] numberLine = new String[1];
            el.children().forEach(element -> {
                station.add(element.getElementsByClass("name").text()); // название станции
                numberLine[0] = el.attr("data-line");

            });
            numberLineAndNameStation.put(numberLine[0], station);
        });
        System.out.println();

        JSONObject metro = new JSONObject();
        for (Object key : nameLineAndNumber.keySet()) {
            JSONObject stationOnLine = new JSONObject();            stationOnLine.put(nameLineAndNumber.get(key), keyStation(key, numberLineAndNameStation)); // тут мы делаем ключом линии, а значениями станции
            metro.put(key, stationOnLine); //тут ключи это номера линии, значения
        }
        for (Object s : metro.keySet()) {
            System.out.println("Ключ " + s + " Значение " + metro.get(s));
        }
        ArrayList<JSONObject> list = new ArrayList<>();

        for (int i = 0; i < metro.size(); i++) {
            list.add((JSONObject) metro.get(i));
        }

        PrintWriter writer = new PrintWriter(FILENAME);
        metro.writeJSONString(writer);
        writer.flush();
        writer.close();


    }


    private static Object keyStation(Object key, JSONObject numberLineAndNameStation) {
        for (Object keyStation : numberLineAndNameStation.keySet()) {
            // System.out.println("Ключ " + key + " Значение " + numberLineAndNameStation.get(keyStation));
            if (key.equals(keyStation)) {
                return numberLineAndNameStation.get(keyStation);
            } else {
                continue;
            }
        }
        return null;
    }
}

