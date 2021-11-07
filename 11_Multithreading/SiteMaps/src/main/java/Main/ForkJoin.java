package Main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<List<String>> {
    private String pageUrl;
    private String[] linksDetails = formatMassive();
    public static Set<String> allSites = ConcurrentHashMap.newKeySet();

    public ForkJoin(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    protected List<String> compute() {
        List<String> urlsForkJoin = urlLinks(pageUrl);
        ArrayList<ForkJoin> secondForkJoins = new ArrayList();
        for (String site : urlsForkJoin) {
            if (newSiteFilter(site) == false && site.contains(Main.site) == true) {
                allSites.add(site);
                System.out.println(site);
                ForkJoin forkJoin = new ForkJoin(site);
                forkJoin.fork();
                secondForkJoins.add(forkJoin);
            }
        }
        for (ForkJoin task : secondForkJoins) {
            urlsForkJoin.addAll(task.join());
        }

        return urlsForkJoin;

        // Для каждого элемента из urls создаем ForkJoin задачу и вызываем метод fork. Складываем все эти задачи в список и в самом конце вызываем метод join чтобы собрать результаты из этих задач.

        // В итоге мы рекурсивно обойдем весь сайт и достанем все ссылки :) Но есть один нюанс, нужно как-то решить проблему зацикливания (когда мы ходим кругами по одним и тем же ссылкам) :)
    }

    public List<String> urlLinks(String url) {
        List<String> urls = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Thread.sleep(125);
            Elements elements = doc.select("a");
            for (Element element : elements) {
                String site = element.absUrl("href");

                urls.add(site);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return urls;
    }

    public String[] formatMassive() {
        String[] linksDetails = new String[8];
        linksDetails[0] = ".jpg";
        linksDetails[1] = ".png";
        linksDetails[2] = ".gif";
        linksDetails[3] = ".pdf";
        linksDetails[4] = ".bmp";
        linksDetails[5] = "#";
        linksDetails[6] = ".apk";
        linksDetails[7] = " ";

        return linksDetails;
    }

    public boolean newSiteFilter(String site) {
        if (allSites.contains(site)) {
            return true;
        }
        for (int a = 0; a < linksDetails.length; a++) {
            if (site.contains(linksDetails[a])) {
                return true;
            }
        }
        return false;
    }

}


