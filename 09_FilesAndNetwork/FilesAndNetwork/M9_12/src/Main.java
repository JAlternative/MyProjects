import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");
        HashMap<String, String> numberLineAndNameLine = new HashMap<>();
        namesOfLines.forEach(el -> {
            //el.text();
            //el.attr("data-line"); // номер
            numberLineAndNameLine.put(el.attr("data-line"), el.text());
        });
//        for (String line : numberLineAndNameLine.keySet()){
//            System.out.println("Номер линии " + line + " Название линии: " + numberLineAndNameLine.get(line));
//        }
        HashMap<String, String> numberLineAndNameStation = new HashMap<>();
        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        namesOfStations.forEach(el -> {
            el.children().forEach(element -> {
                numberLineAndNameStation.put(element.getElementsByClass("name").text(), el.attr("data-line"));
                //element.getElementsByClass("name").text(); // название станции
               // el.attr("data-line"); // номер линии

            });
        });
                for (String line : numberLineAndNameStation.keySet()){
            System.out.println("Номер линии " + line + " Название станции: " + numberLineAndNameLine.get(line));
        }

    }
}