package io.example.financialledger;

public class TransactionalInfo {
    private String date;
    private String desc;
    private String incomeExpenditure;
    private String money;

    public TransactionalInfo(String date, String desc, String incomeExpenditure, String money) {
        this.date = date;
        this.desc = desc;
        this.incomeExpenditure = incomeExpenditure;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIncomeExpenditure() {
        return incomeExpenditure;
    }

    public void setIncomeExpenditure(String incomeExpenditure) {
        this.incomeExpenditure = incomeExpenditure;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
