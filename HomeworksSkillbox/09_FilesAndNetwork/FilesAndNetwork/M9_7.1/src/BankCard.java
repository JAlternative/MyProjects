public class BankCard {
    String accountType;
    String numberType;
    String curency;
    String date;
    String transactionReference;
    String operationDisctription;
    String coming;
    String expenditure;

    protected BankCard(String accountType, String numberType, String curency, String date, String transactionReference, String operationDisctription, String coming, String expenditure) {
        this.accountType = accountType;
        this.numberType = numberType;
        this.curency = curency;
        this.date = date;
        this.transactionReference = transactionReference;
        this.operationDisctription = operationDisctription;
        this.coming = coming;
        this.expenditure = expenditure;
    }

    protected String getAccountType() {
        return accountType;
    }

    protected String getNumberType() {
        return numberType;
    }

    protected String getCurency() {
        return curency;
    }

    protected String getDate() {
        return date;
    }

    protected String getTransactionReference() {
        return transactionReference;
    }

    protected String getOperationDisctription() {
        return operationDisctription;
    }

    protected Double getComing() {
        if (coming.indexOf(",") > 0){
            String replacementExpenditure = coming.replaceAll(",", ".");
            return Double.parseDouble(replacementExpenditure);
        } else {
            return Double.parseDouble(coming.replaceAll("[^0-9]", ""));
        }
    }

    protected Double getExpenditure() {
        if (expenditure.indexOf(",") > 0){
            String replacementExpenditure = expenditure.replaceAll(",", ".");
            return Double.parseDouble(replacementExpenditure);
        } else {
            return Double.parseDouble(expenditure.replaceAll("[^0-9]", ""));
        }

    }
    protected static String operationMCC(String operationDiscriptions) {
        String[] massiv = operationDiscriptions.split("\\s+");
        String[] out = massiv[1].split("\\\\");
        if (out[0].equals("")){
            return out[1];
        } else {
            return out[0];
        }
    }




}
