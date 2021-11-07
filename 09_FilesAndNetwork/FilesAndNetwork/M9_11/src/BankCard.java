public class BankCard {
    String accountType;
    String numberType;
    String curency;
    String date;
    String transactionReference;
    String operationDisctription;
    String coming;
    String expenditure;



    public BankCard(String accountType, String numberType, String curency, String date, String transactionReference, String operationDisctription, String coming, String expenditure) {
        this.accountType = accountType;
        this.numberType = numberType;
        this.curency = curency;
        this.date = date;
        this.transactionReference = transactionReference;
        this.operationDisctription = operationDisctription;
        this.coming = coming;
        this.expenditure = expenditure;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getNumberType() {
        return numberType;
    }

    public String getCurency() {
        return curency;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public String getOperationDisctription() {
        return operationDisctription;
    }

    public String getComing() {
        return coming;
    }

    public String getExpenditure() {
        return expenditure;
    }




}
