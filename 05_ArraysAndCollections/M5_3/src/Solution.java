import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.w3c.dom.ls.LSOutput;


public class Solution {
    static HashSet<String> emails = new HashSet<>();

    public static void main(String[] args) {
        for (; ; ) {
            System.out.println("________________________________________________________");
            System.out.println("1) Если хотите добавить эмейл в список введите «ADD» " +
                    " \n2) Если хотите вывести список ваших эмейлов, то введите «LIST»" +
                    " \n3) Если хотите завершить работу программы, то введите «CLOSE»");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.equals("LIST")) {
                for (String list : emails) {
                    System.out.println(list);
                }
                continue;
            }
            if (text.equals("ADD")) {
                System.out.println("Введите эмейл, который хотите добавить в список");
                isValidEmail(scanner.nextLine());
                continue;
            }
            if (text.equals("CLOSE")) {
                System.out.println("Вы закрыли программу");
                break;
            } else {
                System.out.println("Неверная команда!!!");
            }
        }
    }

    public static void isValidEmail(String email) {
        int dogSimbol = email.indexOf("@");
        int point = email.indexOf(".");
        int lenghtText = email.length();
        for (; ; ) {
            if (emails.contains(email) == true) {
                System.out.println("Этот эмейл уже содержится в списке, введите другой");
                break;
            } else if (dogSimbol > 1 && point > dogSimbol + 1 && point < lenghtText - 1) { /*Если точка идёт после собаки и после точки есть хотя бы буква, то всё норм*/
                emails.add(email);
                System.out.println(email + " добавлен в список");
                break;
            } else {
                System.out.println("Эмейл невалидный! Введите эмейл, подобный этому «junior@skillbox.ru»");
                break;
            }
        }
    }
}


