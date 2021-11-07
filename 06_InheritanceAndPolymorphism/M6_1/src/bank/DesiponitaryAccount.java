package bank;

import java.time.LocalDate;

public class DesiponitaryAccount extends PaymentAccount {
    public DesiponitaryAccount(int balance) {
        super(balance);
    }

    private LocalDate putDate;

    private int currentMonth = 0;

    private int endMonth = 0;

    @Override

    public void putMoney(int money) {
        putDate = LocalDate.now();

        endMonth = putDate.getMonthValue() + 1;


        super.putMoney(money);
    }

    @Override
    public void takeMoney(int money) {
        LocalDate currentTime = LocalDate.now();

        currentMonth = currentTime.getMonthValue();


        if (currentMonth >= endMonth) {
            super.takeMoney(money);
            System.out.println("Вы сняли с депозитарного счета " + money + " рублей.");
        } else {
            System.out.println("Последний взнос был произведен: " + putDate + " \nВы не сможете снять деньги: " + putDate.plusMonths(1));
        }

    }


}

