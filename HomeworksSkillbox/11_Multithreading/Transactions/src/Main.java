import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(1000);
        int a1 = random.nextInt(9999);

        Account account = new Account();
        Bank bank = new Bank();
        addAccount(bank);
        Bank.getBalance(bank.getAccountBank());
        System.out.println("_______________________________________________________");
        ArrayList<Thread> threads = new ArrayList<>();
        for (int a = 0; a < 4; a++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    start(bank, account);
                }
            }));
        }
        threads.forEach(t -> {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println();

        System.out.println("_______________________________________________________");
        Bank.getBalance(bank.getAccountBank());
        System.out.println("Счета, которые были заблокированны во время транзакций");
        Bank.getBalance(bank.getBlockAccountBank());


    }

    private static synchronized void start(Bank bank, Account account) {
        Random random = new Random();
        ArrayList<String> keys = new ArrayList<>();
        for (String key : bank.getAccountBank().keySet()) {
            keys.add(key);
        }

        for (int a = 0; a < 20; a++) {
            String firstNumber = keys.get(random.nextInt(keys.size()));
            String secondNumber = keys.get(random.nextInt(keys.size()));
            long amount = random.nextInt(100000);
            bank.transfer(firstNumber, secondNumber, amount, bank, account);
        }
    }

    private static void addAccount(Bank bank) {
        Random random = new Random();
        ArrayList<String> cardNumber = new ArrayList<>();

        Random random2 = new Random(1000);
        for (int a = 0; a < 100; a++) {
            String cardNumberStr =String.valueOf(random2.nextInt(9999)) + " " + String.valueOf(random2.nextInt(9999)) + " " +
                    String.valueOf(random2.nextInt(9999)) + " " + String.valueOf(random2.nextInt(9999));
            cardNumber.add(cardNumberStr);
        }
        HashMap<String, Account> accountBank = new HashMap<String, Account>();
        for (int a = 0; a < cardNumber.size(); a++) {
            long randonSum = random.nextInt(1000000);
            accountBank.put(cardNumber.get(a), new Account(randonSum, String.valueOf(a)));
        }
        bank.setAccountBank(accountBank);
    }
}