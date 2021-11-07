package bank;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Client {
    public Client(long paymentAccount) {
    }

    private long min = 410010000000000000L; // Минимальное число для диапазона
    private long max = 410099999999999999L; // Максимальное число для диапазона


    public long paymentAccount = ThreadLocalRandom.current().nextLong(min, max);

    private int balance;


    public Client(int balance) {
        this.balance = balance;
    }


    public int getBalance() {
        return balance;
    }

    public int getBankAccount() {
        return balance;
    }

    protected void putMoney(int money) {

        if (money > 0) {
            this.balance += money;
        } else {
            System.out.println("Введена неправильная сумма!");
        }
    }

    protected void takeMoney(int money) {
        if (money <= getBalance()) {
            this.balance -= money;
        } else {
            System.out.println("Недостаточно средств!");
        }
    }

    public abstract void abstractMethods(String text, int moneys);


}
