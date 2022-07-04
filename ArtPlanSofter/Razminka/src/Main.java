import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Введите строку:");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String str = reader.readLine();
    Integer[] param = {1000, 10_000, 100_000};
    Arrays.stream(param).forEach(m -> reverseString(m, str));
  }

  public static void reverseString(Integer param, String value) {
    Long start = System.currentTimeMillis();
    for (int a = 0; a < param; a++) {
      new StringBuilder(value).reverse();
    }
    Long finish = System.currentTimeMillis() - start;
    System.out.println("--------------------------------------");
    System.out.println("Строка: " + value);
    System.out.println("Развернутая строка: " + new StringBuilder(value).reverse());
    System.out.println("Время работы: " + finish + "мс." + " Количество повторений: " + param);
    System.out.println("--------------------------------------");
  }
}
