import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static ArrayList<String> todoList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String[] massiv = new String[5];

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите команду: \n1) CLOSE - Завершает работу программы." + "\n2) ADD - Добавляет заметку в конец списка." +
                    "\n3) LIST - Выводит список ваших дел на экран." +
                    "\n4) ADD 1 - Добавит дело в список на первом место." +
                    "\n5) DELETE 1 - Удаляет дело, которое на первом месте" +
                    "\n6) EDIT 1 - Заменяет дело, которое на первом месте");
            massiv[0] = "ADD";
            massiv[1] = "LIST";
            massiv[2] = "DELETE";
            massiv[3] = "CLOSE";
            massiv[4] = "EDIT";

            String command = scanner.nextLine();

            int index = parseIndexFromCommand(command);
            String todoElement = parseTodoElementFromCommand(command);


            if (todoElement == null) {
                System.out.println("Неверная командна, попробуйте еще раз!");
                continue;
            }
            if (todoElement.equals("CLOSE")) {
                System.out.println("Завершение работы");
                break;
            }
            if (todoElement.equals("ADD")) {
                if (index == -1) {
                    add(index);
                } else if (index >= 1) {
                    add(index);
                } else {
                    System.out.println("Вы ввели неправильную цифру");
                }
            }
            if (todoElement.equals("LIST")) {
                list("Вы использовали команду «LIST»");
                continue;
            }
            if (todoElement.equals("EDIT")) {
                edit(index, todoElement);
                continue;
            }
            if (todoElement.equals("DELETE")) {
                delete(index);
                continue;
            }
        }

    }


    private static int parseIndexFromCommand(String index) {
        String s = index.replaceAll("[^0-9]", "");
        if (s.equals("")) {
            return -1;
        } else {
            return Integer.parseInt(s);
        }
    }

    private static String parseTodoElementFromCommand(String command) {
        String result = null;
        for (int a = 0; a < massiv.length; a++) {
            Pattern d = Pattern.compile(massiv[a]);
            Matcher m = d.matcher(command);

            if (m.find() == true) {
                result = massiv[a];
                break;
            } else {
                continue;
            }
        }

        return result;
    }


    public static void add(int index) {
        System.out.println();
        System.out.println("1) Введите дело, которое хотите добавить в список");
        String text = scanner.nextLine();
        if (index == -1 || index > todoList.size()) {
            System.out.println("Вы добавили дело в конец списка");
            todoList.add(text);
        } else if (index >= todoList.size()) {
            todoList.add(text);
        } else {
            todoList.add(index, text);
        }

    }

    public static void list(String list) {
        if (todoList.size() >= 1) {
            for (int a = 1; a <= todoList.size(); a++) {
                System.out.println(a + "-" + todoList.get(a - 1));
            }
        } else {
            System.out.println("У вас нет дел.");
        }

    }

    public static void edit(int editNumber, String todoElement) {

        if (editNumber > 0 && editNumber <= todoList.size() - 1) {
            System.out.println("Введите дело, которое хотите поставить на место предыдущего");
            String s = scanner.nextLine();
            todoList.set(editNumber - 1, s);
        } else {
            System.out.println("Невозможно заменить дело, так как его нет. Проверьте вводимое число");
        }

    }

    public static void delete(int deleteNumber) {
        if (deleteNumber <= todoList.size() - 1 && deleteNumber > 0) {
            System.out.println("Удаление дела " + deleteNumber);
            todoList.remove(deleteNumber - 1);
        } else {
            System.out.println("Невозможно удалить дело, так как число, которое вы ввели, нет в списке.");
        }

    }
}
