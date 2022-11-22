package bank;

public class Physicalface extends Client {
    public Physicalface(long paymentAccount) {
        super(paymentAccount);
    }

    @Override
    public void abstractMethods(String text, int moneys) {

        if (text.equals("Внести деньги")) {
            super.putMoney(moneys);
        } else if (text.equals("Снять деньги")) {
            super.takeMoney(moneys);
        } else {
            System.out.println("Неправильная команда");
        }

    }

}
