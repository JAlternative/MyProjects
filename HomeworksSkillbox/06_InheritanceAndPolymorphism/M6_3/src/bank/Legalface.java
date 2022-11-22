package bank;

public class Legalface extends Client {
    public Legalface(long paymentAccount) {
        super(paymentAccount);
    }

    @Override
    public void abstractMethods(String text, int moneys) {

        if (text.equals("Внести деньги")) {
            super.putMoney(moneys);
        } else if (text.equals("Снять деньги")) {
            moneys = moneys + (moneys / 100);
            super.takeMoney(moneys);

        } else {
            System.out.println("Неправильная команда");
        }


    }


}
