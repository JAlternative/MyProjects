package Main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String site = "https://skillbox.ru/";
    public static ArrayList<String> homePageSites = new ArrayList<>();
    public static void main(String[] args) {
        firstSite(site);

        int numOfThread = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfThread);
        for (String url : homePageSites){
            pool.invoke(new ForkJoin(url));
        }

        recordInFile(ForkJoin.allSites);
    }



    public static void firstSite(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a");
            for (Element element : elements) {
                String site = element.absUrl("href");
                if (site.trim().length() == 0) {
                    break;
                } else  {
                    homePageSites.add(site);
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public static void recordInFile(Set<String> link) {
        try {
            PrintWriter writer = new PrintWriter("data/file.txt");
            String tabulation = "\t";
            String tabulationPlusTabulation = "";
            for (String site : link){
                writer.write(tabulationPlusTabulation + site + "\n");
                tabulationPlusTabulation = tabulationPlusTabulation + tabulation;
            }

            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
