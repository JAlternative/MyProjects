import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static HashSet<String> hashSet = new HashSet<>();
    static TreeSet<String> treeSet = new TreeSet<>();

    static long arrayListNanoTime = 0;
    static long binarySearchNanoTime = 0;
    static long hashSetNanoTime = 0;
    static long treeSetNanoTime = 0;

    public static void main(String[] args) {

        String[] bukva = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        for (int a = 0; a < bukva.length; a++) {
            for (int b = 0; b < bukva.length; b++) {
                for (int c = 0; c < bukva.length; c++) {
                    for (int d = 1; d <= 197; d++) {
                        for (int e = 0; e <= 9; e++) {
                            String number = bukva[a] + e + "" + e + "" + e + bukva[b] + bukva[c] + "" + d;
                            list.add(number);
                            hashSet.add(number);
                            treeSet.add(number);
                        }
                    }
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();

        timeArrayList(number);
        timeBinarySearch(number);
        timeHashSet(number);
        timeTreeSet(number);
        maxNanoMin(arrayListNanoTime, binarySearchNanoTime, hashSetNanoTime, treeSetNanoTime);


    }

    private static void timeArrayList(String gosNumber) {
        long start = System.nanoTime();
        boolean isContains = list.contains(gosNumber);
        long end = System.nanoTime() - start;
        if (isContains == true) {
            System.out.println("Поиск перебором: номер " + gosNumber + " найден, поиск занял " + end + " наносекунд");
        } else {
            System.out.println("Поиск перебором: номер " + gosNumber + " не найден, поиск занял " + end + " наносекунд");
        }
        arrayListNanoTime = end;
    }

    private static void timeBinarySearch(String gosNumber) {
        long start = System.nanoTime();
        Collections.sort(list);
        long index = Collections.binarySearch(list, gosNumber);
        long end = System.nanoTime() - start;
        if (index >= 0) {
            System.out.println("Бинарный поиск: номер " + gosNumber + " найден, поиск занял " + end + " наносекунд");
        } else {
            System.out.println("Бинарный поиск: номер " + gosNumber + " не найден, поиск занял " + end + " наносекунд");
        }
        binarySearchNanoTime = end;
    }

    private static void timeHashSet(String gosNumber) {
        long start = System.nanoTime();
        boolean isContains = hashSet.contains(gosNumber);
        long end = System.nanoTime() - start;
        if (isContains == true) {
            System.out.println("Поиск в HashSet: номер " + gosNumber + " найден, поиск занял " + end + " наносекунд");
        } else {
            System.out.println("Поиск HashSet: номер " + gosNumber + " не найден, поиск занял " + end + " наносекунд");
        }
        hashSetNanoTime = end;
    }

    private static void timeTreeSet(String gosNumber) {
        long start = System.nanoTime();
        boolean isContains = treeSet.contains(gosNumber);
        long end = System.nanoTime() - start;
        if (isContains == true) {
            System.out.println("Поиск в TreeSet: номер " + gosNumber + " найден, поиск занял " + end + " наносекунд");
        } else {
            System.out.println("Поиск TreeSet: номер " + gosNumber + " не найден, поиск занял " + end + " наносекунд");
        }
        treeSetNanoTime = end;
    }

    private static void maxNanoMin(long arrayList, long binarySearch, long hashSet, long treeSet) {
        System.out.println();
        if (arrayList > binarySearch && arrayList > hashSet && arrayList > treeSet) {
            System.out.println("Самый медленный метод поиска: Поиск перебором - list.contains()");
        }
        if (binarySearch > arrayList && binarySearch > hashSet && binarySearch > treeSet) {
            System.out.println("Самый медленный метод поиска: Бинарный поиск - Collections.binarySearch()");
        }
        if (hashSet > arrayList && hashSet > binarySearch && hashSet > treeSet) {
            System.out.println("Самый медленный метод поиска: Поиск в HashSet - hashSet.contains()");
        }
        if (treeSet > arrayList && treeSet > binarySearch && treeSet > hashSet) {
            System.out.println("Самый медленный метод поиска: Поиск в TreeSet - treeSet.contains()");

        }


        if (arrayList < binarySearch && arrayList < hashSet && arrayList < treeSet) {
            System.out.println("Самый быстрый метод поиска: Поиск перебором - list.contains()");
        }
        if (binarySearch < arrayList && binarySearch < hashSet && binarySearch < treeSet) {
            System.out.println("Самый быстрый метод поиска: Бинарный поиск - Collections.binarySearch()");
        }
        if (hashSet < arrayList && hashSet < binarySearch && hashSet < treeSet) {
            System.out.println("Самый быстрый метод поиска: Поиск в HashSet - hashSet.contains()");
        }
        if (treeSet < arrayList && treeSet < binarySearch && treeSet < hashSet) {
            System.out.println("Самый быстрый метод поиска: Поиск в TreeSet - treeSet.contains()");

        }
    }


}