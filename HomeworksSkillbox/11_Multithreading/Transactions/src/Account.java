public class Account {
    public Account() {

    }

    private long money;
    private String accNumber;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public long getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void depositMoney(long sum) {
        money = money + sum;
    }

    public void withdrawMoney(long sum) {
        money = money - sum;
    }


}
