import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    public static int counter;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        long start = System.currentTimeMillis();

        for (int regionCodes = 0; regionCodes < 100; regionCodes++)
            executorService.submit(new Work(regionCodes));
            executorService.shutdown();
            System.out.println("all task");
            executorService.awaitTermination(1, TimeUnit.DAYS);


        System.out.println((System.currentTimeMillis() - start) + " ms");
    }


}