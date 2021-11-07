import bank.BankKard;
import bank.DesiponitaryAccount;
import bank.PaymentAccount;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PaymentAccount paymentAccount = new PaymentAccount(0);
        BankKard bankKard = new BankKard(0);
        DesiponitaryAccount desiponitaryAccount = new DesiponitaryAccount(0);


        for (; ; ) {
            System.out.println("Введите операцию, которую хотите совершить" +
                    "\n1) Внести деньги" +
                    "\n2) Снять деньги" +
                    "\n3) Баланс");

            String text = scanner.nextLine();
            if (text.equals("Внести деньги")) {
                System.out.println("Куда хотите внести деньги " +
                        "\n1) Обычный счет" +
                        "\n2) Депозитарный счет" +
                        "\n3) Банковская карта");
                String s4et = scanner.nextLine();
                if (s4et.equals("Обычный счет")) {
                    System.out.println("Введите сумму в рублях, которую хотите внести");
                    int rub = scanner.nextInt();
                    if (rub > 0) {
                        paymentAccount.putMoney(rub);
                        System.out.println("Вы внесли на счёт " + rub + " рублей. Ваш баланс составляет " + paymentAccount.getBankAccount() + " рублей.");

                    } else {
                        System.out.println("Введена неправильная сумма");
                    }
                }
                if (s4et.equals("Депозитарный счет")) {
                    System.out.println("Введите сумму в рублях, которую хотите внести");
                    int rub = scanner.nextInt();
                    desiponitaryAccount.putMoney(rub);

                }
                if (s4et.equals("Банковская карта")) {
                    System.out.println("Введите сумму в рублях, которую хотите внести");
                    int rub = scanner.nextInt();
                    bankKard.putMoney(rub);

                }
                continue;
            }
            if (text.equals("Снять деньги")) {
                System.out.println("С какого счета хотите снять деньги?" +
                        "\n1) Обычный счет" +
                        "\n2) Депозитарный счет" +
                        "\n3) Банковская карта");
                String s4et = scanner.nextLine();
                if (s4et.equals("Обычный счет")) {
                    System.out.println("Введите сумму, которую хотите снять со счёта");
                    int rub = scanner.nextInt();
                    if (rub > 0 && rub < paymentAccount.getBankAccount()) {
                        paymentAccount.takeMoney(rub);
                        System.out.println("Вы сняли со счёта " + rub + " рублей. Ваш баланск составляет " + paymentAccount.getBankAccount() + " рублей.");

                    } else {
                        System.out.println("Недостаточно средств! Ваш баланс " + paymentAccount.getBankAccount() + " рублей.");
                    }

                }
                if (s4et.equals("Депозитарный счет")) {
                    System.out.println("Введите сумму");
                    int rub = scanner.nextInt();
                    desiponitaryAccount.takeMoney(rub);

                }
                if (s4et.equals("Банковская карта")) {
                    System.out.println("Введите сумму, которую хотите снять с карты (Комиссия 1%)");
                    int rub = scanner.nextInt();
                    bankKard.takeMoney(rub);
                }
                continue;

            }


            if (text.equals("Баланс")) {
                System.out.println("Обычный счет " + paymentAccount.getBankAccount() + " рублей.");
                System.out.println("Депозитарный счет " + desiponitaryAccount.getBankAccount() + " рублей.");
                System.out.println("Банковская карта " + bankKard.getBankAccount() + " рублей.");
            }
        }
    }
}


