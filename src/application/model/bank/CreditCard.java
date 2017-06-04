package application.model.bank;


import application.model.exceptions.LoanLimitException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreditCard extends Card implements Serializable {

    private Loan loan;

    public CreditCard(Currencies currency, Customer owner) {
        super(owner);
        loan = new Loan(new BigDecimal(5000), currency);
    }

    public CreditCard() {
        this(null, null);
    }

    public void setLoan(BigDecimal loan) {
        this.loan.setLoan(loan);
    }

    @Override
    public void charge(BigDecimal amount) throws LoanLimitException {
        try {
            loan.charge(amount);
        }
        catch (LoanLimitException e) {
            throw e;
        }
    }

    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta Kredytowa");
    }

    @Override
    public String getAvailableFounds() {
        return loan.getLoan().subtract(loan.getBalance()).toString() + loan.getCurrencySymbol();
    }

    @Override
    public String getCardLimit() {
        return loan.getLoan().toString() + "" + loan.getCurrencySymbol();
    }

    @Override
    public String getCurrencySymbol() {
        return loan.getCurrencySymbol();
    }

    @Override
    public String getMoney() {
        return loan.getLoan().toString();
    }

    public void resetBalance() {
        loan.resetBalance();
    }

    public void setMoney(String money) {
        loan.setLoan(new BigDecimal(money));
    }

    @Override
    public void setCurrency(Currencies currency) {
        loan.setCurrency(currency);
    }

}
