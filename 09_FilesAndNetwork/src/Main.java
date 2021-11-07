import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        for (; ; ) {
            try {
                ArrayList<File> directory = new ArrayList<>();
                long size = 0;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите букву жесткого диска");
                String disk = scanner.nextLine();
                System.out.println("Введите название папки");
                String directoryName = scanner.nextLine();
                File file = new File(disk + args[0] + directoryName);
                File file1[] = file.listFiles();
                myComputerFiles(file1, directory); //получаем на вход массив с первыми папками
                directoryFiles(directory);
                size = outPut(size, directory);
                final int CONSTANT = 1024;
                System.out.println();
                System.out.println("Размер Папки " + disk + ":/" + directoryName + " в байтах: " + size + " байт");
                System.out.println("Размер Папки " + disk + ":/" + directoryName + " в килобайтах: " + size / CONSTANT + " килобайт");
                System.out.println("Размер Папки " + disk + ":/" + directoryName + " в мегайт: " + size / (CONSTANT * CONSTANT) + " мегабайт");
                System.out.println("Размер Папки " + disk + ":/" + directoryName + " в гигабайт: " + size / (CONSTANT * CONSTANT * CONSTANT) + " гигабайт");

                ArrayList<Files> copyFile = directory;
                break;
            } catch (Exception ex) {
                System.out.println("Неверное имя диска или папки, попробуйте снова");
                System.out.println("______________________________________________");
                continue;
            }
        }

    }

    private static ArrayList myComputerFiles(File[] directorys, ArrayList<File> badDirectory) { //Проверяем наличие папок, которые пришли изначально и добавляем их в список всех папок
        ArrayList<File> directory = badDirectory;
        for (int a = 0; a < directorys.length; a++) {
            if (directorys[a].isDirectory()) {
                directory.add(directorys[a]);
            }

        }
        return directory;
    }

    private static void directoryFiles(ArrayList<File> files) { //Собираем все папки какие есть в лист
        for (int a = 0; a < files.size(); a++) {
            if (files.get(a).isDirectory()) {
                File[] filesDirectory = files.get(a).listFiles();
                myComputerFiles(filesDirectory, files);
            }
        }
    }

    private static long outPut(long size, ArrayList<File> directory) {
        for (int a = 0; a < directory.size(); a++) {
            System.out.println("_____________________________________________________________________________________________________________________________");
            System.out.println();
            System.out.println("Путь папки: " + directory.get(a));
            File[] filer = directory.get(a).listFiles();
            System.out.println("Файлы: ↓↓↓");
            System.out.println();
            for (int b = 0; b < filer.length; b++) {
                size = size + filer[b].length();
                System.out.println(filer[b]);
            }
        }
        return size;
    }
}



