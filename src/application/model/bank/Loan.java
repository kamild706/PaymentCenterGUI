package application.model.bank;



import application.model.exceptions.LoanLimitException;

import java.io.Serializable;
import java.math.BigDecimal;

public class Loan implements Serializable {

    private BigDecimal balance;
    private BigDecimal loan;
    private Currency currency;

    /**
     * Used for loaning money.
     * @param loan
     * @param currency 
     */
    public Loan(BigDecimal loan, Currencies currency) {
        this.balance = new BigDecimal(0);
        this.loan = loan;
        this.currency = new Currency(currency);
    }

    /**
     * Sets loan currency.
     * @param currency 
     */
    public void setCurrency(Currencies currency) {
        this.currency = new Currency(currency);
    }

    /**
     * Sets loaned money.
     * @param loan 
     */
    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    /**
     * Sets loan balance to 0.
     */
    public void resetBalance() {
        balance = new BigDecimal(0);
    }

    /**
     * Charges loaned money.
     * @param money
     * @throws LoanLimitException Thrown in case insufficient Founds
     */
    public void charge(BigDecimal money) throws LoanLimitException {
        BigDecimal difference = loan.subtract(balance);
        if (money.compareTo(difference) > 0)
            throw new LoanLimitException("Requested money exceeds available founds "
                    + difference.toString() + currency.getSymbol());

        balance = balance.add(money);
    }

    /**
     * 
     * @return Balance with currency symbol
     */
    public String getBalanceWithCurrency() {
        return balance.toString() + currency.getSymbol();
    }

    /**
     * 
     * @return Balance without curency
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 
     * @return Loaned money with currency symbol
     */
    public String getLoanWithCurrency() {
        return loan.toString() + currency.getSymbol();
    }

    /**
     * 
     * @return Loaned money without currency
     */
    public BigDecimal getLoan() {
        return loan;
    }

    /**
     * 
     * @return Loaned money currency symbol
     */
    public String getCurrencySymbol() {
        return currency.getSymbol();
    }
}
