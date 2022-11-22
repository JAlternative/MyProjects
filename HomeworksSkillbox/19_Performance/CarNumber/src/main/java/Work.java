import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Work implements Runnable {
    private PrintWriter writer = new PrintWriter("res/numbers.txt");
    private int regionCode;

    public Work(int regionNumber) throws FileNotFoundException {
        this.regionCode = regionNumber;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(50);
            if (regionCode % 20 == 0){
                //через каждые 20 регионов, создаётся новый файл и уже пишется туда
                String newFileName = "res/numbers" + increment() + ".txt";
                writer = new PrintWriter(newFileName);
            }
        }
        catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        StringBuilder builder = new StringBuilder();
        for (int number = 1; number < 1000; number++) {

            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");
                    }
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public int increment(){
        Loader.counter = Loader.counter + 1;
        return Loader.counter;
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }

}
