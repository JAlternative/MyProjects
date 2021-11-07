import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static String movementList = "data/movementList.csv";
    private static Map<String, Double> expenditureForMMC = new HashMap<>();
    private static final int ACCOUNT_TYPE = 0;

    public static void main(String[] args) {
        ArrayList<BankCard> bankCards = parseMovementList();
        money(bankCards);
        System.out.println();
        for (String s : expenditureForMMC.keySet()) {
            System.out.println("MCC:" + s + " - " + expenditureForMMC.get(s) + " рублей.");
        }
    }


    private static ArrayList<BankCard> parseMovementList() {
        ArrayList<BankCard> bankCards = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(movementList));
            for (String line : lines) {
                String[] fragments = line.split(",");


                if (fragments.length != 8) {
                    if (fragments.length == 9) {
                        bankCards.add(new BankCard(fragments[ACCOUNT_TYPE], fragments[1], fragments[2], fragments[3], fragments[4],
                                fragments[5], fragments[6], fragments[7].replaceAll("[^0-9]", "") +
                                "," + fragments[8].replaceAll("[^0-9]", "")));
                    } else {
                        System.out.println("Wrong line: " + line);
                    }

                    continue;
                }
                bankCards.add(new BankCard(fragments[ACCOUNT_TYPE], fragments[1], fragments[2], fragments[3],
                        fragments[4], fragments[5], fragments[6], fragments[7]));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankCards;
    }

    private static void money(ArrayList<BankCard> bankCards) {
        double expenditure = 0.0;
        double coming = 0.0;
        for (int a = 1; a < bankCards.size(); a++) {
            try {
                double moneyExpenditure = bankCards.get(a).getExpenditure();
                double moneyComing = bankCards.get(a).getComing();
                if (moneyExpenditure > 0.0) {
                    String nameOperation = BankCard.operationMCC(bankCards.get(a).getOperationDisctription());
                    if (expenditureForMMC.containsKey(nameOperation) == true) {
                        double sum = expenditureForMMC.get(nameOperation);
                        expenditureForMMC.put(nameOperation, moneyExpenditure + sum);
                    } else {
                        expenditureForMMC.put(nameOperation, moneyExpenditure);
                    }
                }
                expenditure = expenditure + moneyExpenditure;
                coming = coming + moneyComing;
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        System.out.println("Сумма расходов: " + expenditure + " руб.");
        System.out.println("Сумма доходов: " + coming + " руб.");
    }


}
