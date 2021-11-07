import java.util.*;

public class Bank {
    private HashMap<String, Account> accountBank = new HashMap<String, Account>();

    public HashMap<String, Account> getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(HashMap<String, Account> accountBank) {
        this.accountBank = accountBank;
    }

    private HashMap<String, Account> blockAccountBank = new HashMap<String, Account>();

    public HashMap<String, Account> getBlockAccountBank() {
        return blockAccountBank;
    }

    public void setBlockAccountBank(HashMap<String, Account> blockAccountBank) {
        this.blockAccountBank = blockAccountBank;
    }

    public static Random getRandom() {
        return random;
    }

    private static final Random random = new Random();

    public Bank() {
    }


    public static synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);

        return random.nextBoolean();
    }

    public static void getBalance(HashMap<String, Account> account) {
        long startBalance = 0;
        ArrayList<Account> values = new ArrayList<Account>(account.values());
        values.sort(Comparator.comparing(Account::getAccNumber));
        for (int a = 0; a < account.size(); a++) {
            long balance = values.get(a).getMoney();
            startBalance = startBalance + balance;
            System.out.println("Баланс счета №" + values.get(a).getAccNumber() + " - " + ": " + balance + " руб");
        }
        System.out.println("Баланс со всех карт: " + startBalance + " руб");

    } //++

    public void transfer(String fromAccountNum, String toAccountNum, long amount, Bank bank, Account account) {

        if (accountBank.get(fromAccountNum) == null || accountBank.get(toAccountNum) == null) {
            return;
        }

        long moneyFromAccountNum = accountBank.get(fromAccountNum).getMoney();
        long moneyToAccountNum = accountBank.get(toAccountNum).getMoney();

        synchronized (fromAccountNum) {
            synchronized (toAccountNum) {

                if (moneyFromAccountNum < amount) {
                    System.out.println("Недостаточно средств на карте №" + fromAccountNum);
                } else if (moneyToAccountNum < amount) {
                    System.out.println("Недостаточно средств на карте №" + toAccountNum);
                } else if (amount >= 50000) { //Если сумма перевода больше 50к, то отправляем в службу безопасности
                    try {
                        boolean isFraundResult = isFraud(fromAccountNum, toAccountNum, amount);
                        if (isFraundResult) {
                            blockAccount(fromAccountNum, toAccountNum, bank); //если получаем true, то идём блокировать аккаунты
                        } else {
                            //если нет, то переводим
                            fromAccountNum(account, amount);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //если нет, то идём всё переводим
                    fromAccountNum(account, amount);
                }
            }
        }


//            }
//        }


    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    private synchronized static void blockAccount(String fromAccountNum, String toAccountNum, Bank bank) {
        HashMap<String, Account> blockAccountBank = bank.getBlockAccountBank(); //получаем предыдущий блокированный список
        Account account1 = bank.getAccountBank().get(fromAccountNum); //значение
        Account account2 = bank.getAccountBank().get(toAccountNum); //значение
        blockAccountBank.put(fromAccountNum, account1); //добавляем новые значения
        blockAccountBank.put(toAccountNum, account2); // новые
        bank.setBlockAccountBank(blockAccountBank); // и присваиваем новые значения
        bank.getAccountBank().remove(fromAccountNum); //а из обычного хеша удаляем удаляем
        bank.getAccountBank().remove(toAccountNum);

    }

    private static void fromAccountNum(Account account, long sum) {
        account.depositMoney(sum);
        account.withdrawMoney(sum);
    }


}
