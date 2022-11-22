import java.io.File;
import java.util.ArrayList;

public class Main {
    private static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\MARVEL\\Desktop\\src";
        String dstFolder = "C:\\Users\\MARVEL\\Desktop\\dst";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        int processor = Runtime.getRuntime().availableProcessors();
        int mid = files.length / processor;
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        for (int i = 0; i < processor; i++) {
            threadArrayList.add(new Thread(resizer(mid, files, dstFolder, start, i)));
        }
        for (Thread listThread : threadArrayList){
            listThread.start();
            try {
                listThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static ImageResizer resizer(int mid, File[] files, String dstFolder, long start, int forI) {
        int srcPos = mid * forI;
        File[] files1 = new File[mid];
        System.arraycopy(files, srcPos, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
        return resizer1;
    }

}
