package application.model.bank;



import application.model.exceptions.LoanLimitException;

import java.io.Serializable;
import java.math.BigDecimal;

public class Loan implements Serializable {

    private BigDecimal balance;
    private BigDecimal loan;
    private Currency currency;

    public Loan(BigDecimal loan, Currencies currency) {
        this.balance = new BigDecimal(0);
        this.loan = loan;
        this.currency = new Currency(currency);
    }

    public void setCurrency(Currencies currency) {
        this.currency = new Currency(currency);
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    public void resetBalance() {
        balance = new BigDecimal(0);
    }

    public void charge(BigDecimal money) throws LoanLimitException {
        BigDecimal difference = loan.subtract(balance);
        if (money.compareTo(difference) > 0)
            throw new LoanLimitException("Requested money exceeds available founds "
                    + difference.toString() + currency.getSymbol());

        balance = balance.add(money);
    }

    public String getBalanceWithCurrency() {
        return balance.toString() + currency.getSymbol();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getLoanWithCurrency() {
        return loan.toString() + currency.getSymbol();
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public String getCurrencySymbol() {
        return currency.getSymbol();
    }
}
