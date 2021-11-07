package bank;

public class BankKard extends PaymentAccount {
    public BankKard(int balance) {
        super(balance);
    }

    @Override
    public void putMoney(int money) {
        if (money > 0) {
            super.putMoney(money);
            System.out.println("Вы внесли на карту " + money + " рублей. При снятии суммы с карты будет действовать комиссия 1%");
        }
        else {
            System.out.println("Введена неправильная сумма");
        }

    }

    @Override
    public void takeMoney(int money) {
        if (money > 0) {
            money = money + (money / 100);
            super.takeMoney(money);
            System.out.println("Вы сняли со счета: " + money + " рублей. Остаток: " + getBalance() + " рублей");
        } else {
            System.out.println("Введена неправильная сумма");
        }
    }

}
