import bank.Client;
import bank.Individualface;
import bank.Legalface;
import bank.Physicalface;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int a = scanner.nextInt();
        Physicalface physicalface = new Physicalface(0);
        Legalface legalface = new Legalface(0);
        Individualface individualface = new Individualface(0);

        String phisicalNumber = Long.toString(physicalface.paymentAccount);
        String legalfaceNumber = Long.toString(legalface.paymentAccount);
        String individualfaceNumber = Long.toString(individualface.paymentAccount);

        System.out.println("Расчётный счет физического лица: " + phisicalNumber);
        System.out.println("Расчётный счет юридического лица: " + legalfaceNumber);
        System.out.println("Расчётный счет индивидуального предпринимателя: " + individualfaceNumber);


        String operation = " Введите операцию, которую хотите совершить. " +
                "\n1) Баланс" +
                "\n2) Снять деньги" +
                "\n3) Внести деньги ";

        for (; ; ) {
            System.out.println("Введите номер расчетного счёта, с которым хотите провести операции");
            String number = scanner.nextLine();
            if (number.equals(phisicalNumber)) {
                System.out.println("Вы открыли расчётный счет физического лица." + operation);
                String command = scanner.nextLine();
                if (command.equals("Баланс")) {
                    System.out.println("Ваш баланс: " + physicalface.getBalance() + "р.");
                } else if (command.equals("Снять деньги")) {
                    System.out.println("Введите сумму, которую хотите снять со счёта: ");
                    int sum = scanner.nextInt();
                    physicalface.abstractMethods(command, sum);
                } else if (command.equals("Внести деньги")) {
                    System.out.println("Введите сумму, которую хотите внести на счёт: ");
                    int sum = scanner.nextInt();
                    physicalface.abstractMethods(command, sum);
                } else {
                    System.out.println("Неверная команда!");

                }
                continue;
            }

            if (number.equals(legalfaceNumber)) {
                System.out.println("Вы открыли расчётный счет юридического предпринимателя." + operation);
                String command = scanner.nextLine();
                if (command.equals("Баланс")) {
                    System.out.println("Ваш баланс: " + legalface.getBalance() + "р.");
                } else if (command.equals("Снять деньги")) {
                    System.out.println("Введите сумму, которую хотите снять со счёта: ");
                    int sum = scanner.nextInt();
                    legalface.abstractMethods(command, sum);
                } else if (command.equals("Внести деньги")) {
                    System.out.println("Введите сумму, которую хотите внести на счёт: ");
                    int sum = scanner.nextInt();
                    legalface.abstractMethods(command, sum);
                } else {
                    System.out.println("Неверная команда!");
                    continue;
                }
                continue;
            }

            if (number.equals(individualfaceNumber)) {
                System.out.println("Вы открыли расчётный счет индивидуального предпринимателя." + operation);
                String command = scanner.nextLine();
                if (command.equals("Баланс")) {
                    System.out.println("Ваш баланс: " + individualface.getBalance() + "р.");
                } else if (command.equals("Снять деньги")) {
                    System.out.println("Введите сумму, которую хотите снять со счёта: ");
                    int sum = scanner.nextInt();
                    individualface.abstractMethods(command, sum);
                } else if (command.equals("Внести деньги")) {
                    System.out.println("Введите сумму, которую хотите внести на счёт: ");
                    int sum = scanner.nextInt();
                    individualface.abstractMethods(command, sum);
                } else {
                    System.out.println("Неверная команда!");
                    continue;
                }
                continue;
            }
        }


    }
}
