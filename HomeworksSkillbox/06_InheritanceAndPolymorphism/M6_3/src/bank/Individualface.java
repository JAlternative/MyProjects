package bank;

public class Individualface extends Client {
    public Individualface(long paymentAccount) {
        super(paymentAccount);
    }
    private final int CONSTANT_MONEY = 1000;
    private final int COMISSION_ONE_HUNDRED_PERCENT = 100;
    private final int COMISSION_FIFTY_PERCENT = 50;


    @Override
    public void abstractMethods(String text, int moneys) {

        if (text.equals("Внести деньги")) {
            if (moneys < CONSTANT_MONEY) {
                moneys = moneys - (moneys / COMISSION_ONE_HUNDRED_PERCENT);
                super.putMoney(moneys);
            } else {
                moneys = moneys - (moneys / COMISSION_FIFTY_PERCENT);
                super.putMoney(moneys);
            }
        } else if (text.equals("Снять деньги")) {
            super.takeMoney(moneys);
        } else {
            System.out.println("Неправильная команда");
        }


    }
}
