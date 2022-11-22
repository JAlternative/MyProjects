import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        File myComputerFile;
        File myComputerFile2;
        Scanner scanner = new Scanner(System.in);
        for (;;){
            System.out.println("Введите путь к папке, которую хотите скопировать" +
                    "\nПример: D:/Copy");
            String path = scanner.nextLine();
            if (new File(path).isDirectory()){
                myComputerFile = new File(path);
                break;
            } else {
                System.out.println("Неверный путь к папке, попробуйте еще раз");
                continue;
            }
        }
        for (;;){
            System.out.println("Введите путь к папке, в которую будете копировать");
            String path = scanner.nextLine();
            if (new File(path).isDirectory()){
                myComputerFile2 = new File(path);
                break;
            } else {
                System.out.println("Неверный путь к папке, попробуйтие еще раз");
                continue;
            }
        }
        try {
            copyFile(myComputerFile, myComputerFile2);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void copyFile(File source, File dest) throws IOException {
        if (source.isDirectory()){
            FileUtils.copyDirectory(source,dest);
        }
        if (source.isFile()){
            FileUtils.copyFile(source, dest);
        }
    }
}
