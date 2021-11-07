import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static HashMap<String, Long> hashMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("1) Для завершения программы введите «CLOSE»" +
                "\n2) Открыть список ваших контактов введите «LIST»" +
                "\n3) Вводите номер телефона или имя контакта");
        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("CLOSE")) {
                break;
            }
            if (command.equals("LIST")) {
                printMap(hashMap);
                continue;
            }
            if (parseIndexFromCommand(command) == -1) {
                if (hashMap.containsKey(command) == false) {
                    System.out.println("Имя контакта: " + command + "\nВведите номер телефона");
                    long telephoneVoid = scanner.nextLong();
                    hashMap.put(command, telephoneVoid);
                    System.out.println("Контакт сохранен");
                    continue;
                } else if (hashMap.containsKey(command) == true) {
                    System.out.println("Этот контакт уже существует");
                    System.out.println("Имя: " + command + "\nНомер телефона: " + hashMap.get(command));
                    continue;
                }
            } else {
                long number = parseIndexFromCommand(command);
                if (hashMap.containsValue(number) == false) {
                    System.out.println("Телефон контакта: " + number + "\nВведите имя контакта");
                    String nick = scanner.nextLine();
                    hashMap.put(nick, number);
                    System.out.println("Контакт сохранен");
                    continue;
                } else if (hashMap.containsValue(number) == true) {
                    System.out.println("Этот контакт уже существует");
                    for (Map.Entry<String, Long> entry : hashMap.entrySet()) {
                        if (entry.getValue().equals(number)) {
                            System.out.println("Имя: " + entry.getKey() + "\nНомер телефона: " + number);
                            break;
                        }
                    }

                    continue;
                }
            }
        }
    }

    private static void printMap(HashMap<String, Long> map) {
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println("Имя: " + entry.getKey() + "\nНомер телефона: " + entry.getValue());
            System.out.println();
        }

    }

    private static long parseIndexFromCommand(String commands) {
        String copyText = commands;
        String s = commands.replaceAll("[^0-9]", "");
        if (s.isEmpty()) {
            return -1;
        } else {
            return Long.parseLong(s);
        }
    }

}
