package bank;

public class PaymentAccount {

    private int balance;


    public PaymentAccount(int balance){
        this.balance = balance;
    }



    public int getBalance() {
        return balance;
    }

    public int getBankAccount(){
        return balance;
    }
    public void putMoney(int money){

        if (money > 0){
            this.balance += money;
        } else {
            System.out.println("Введена неправильная сумма");
        }
    }
    public void takeMoney(int money){
        if (money <= getBalance()){
            this.balance -= money;
        } else {
            System.out.println("Недостаточно средств");
        }
    }

}
