import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static String movementList = "data/movementList.csv";
    private static Map<String, Double> expenditureForMMC = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<BankCard> bankCards = bankCard();
        money(bankCards);
        System.out.println();
        for (String s : expenditureForMMC.keySet()) {
            System.out.println("MCC:" + s + " - " + expenditureForMMC.get(s) + " рублей.");
        }

    }

    private static ArrayList<BankCard> bankCard() {
        ArrayList<BankCard> bankCards = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(movementList));
            for (String line : lines) {
                String[] fragments = line.split(",");


                if (fragments.length != 8) {
                    if (fragments.length == 9) {
                        bankCards.add(new BankCard(fragments[0], fragments[1], fragments[2], fragments[3], fragments[4],
                                fragments[5], fragments[6], fragments[7].replaceAll("[^0-9]", "") +
                                "," + fragments[8].replaceAll("[^0-9]", "")));
                    } else {
                        System.out.println("Wrong line: " + line);
                    }

                    continue;
                }
                bankCards.add(new BankCard(fragments[0], fragments[1], fragments[2], fragments[3],
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
                double moneyExpenditure = Double.parseDouble(bankCards.get(a).getExpenditure());
                double moneyComing = Double.parseDouble(bankCards.get(a).getComing());
                if (moneyExpenditure > 0.0) {
                    String nameOperation = operationMCC(bankCards.get(a).getOperationDisctription());
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
                if (bankCards.get(a).getExpenditure().indexOf(",") > 0) {
                    String replacementExpenditure = bankCards.get(a).getExpenditure().replaceAll(",", ".");
                    double moneyExpenditure = Double.parseDouble(replacementExpenditure);
                    if (moneyExpenditure > 0.0) {
                    }
                    String nameOperation = operationMCC(bankCards.get(a).getOperationDisctription());
                    if (expenditureForMMC.containsKey(nameOperation) == true) {
                        double sum = expenditureForMMC.get(nameOperation);
                        expenditureForMMC.put(nameOperation, moneyExpenditure + sum);
                    } else {
                        expenditureForMMC.put(nameOperation, moneyExpenditure);
                    }
                    expenditure = expenditure + moneyExpenditure;
                }
            }
        }
        System.out.println("Сумма расходов: " + expenditure + " руб.");
        System.out.println("Сумма доходов: " + coming + " руб.");
    }

    private static String operationMCC(String operationDiscriptions) {
        String[] massiv = operationDiscriptions.split("\\s+");
        String[] out = massiv[1].split("\\\\");
        if (out[0].equals("")) {
            return out[1];
        } else {
            return out[0];
        }
    }


}
