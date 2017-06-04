package application.model.bank;


import application.model.exceptions.AccountBalanceException;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Customer's account class.
 */
public class Account implements Serializable {

    private BigDecimal balance;
    private Currency currency;

    /**
     * Sets new account balance.
     * @param balance  
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Sets account currency.
     * @param currency 
     */
    public void setCurrency(Currencies currency) {
        this.currency = new Currency(currency);
    }

    /**
     * Creates new empty account.
     * @param currency Defines currency of account
     */
    public Account(Currencies currency) {
        this.currency = new Currency(currency);
        balance = new BigDecimal(0);
    }

    /**
     * Adding money to account.
     * @param money Amount of added money
     */
    public void deposit(BigDecimal money) {
        balance = balance.add(money);
    }

    /**
     * Substracting money from account.
     * @param money Amount of money
     * @throws AccountBalanceException Thrown in case of insuffficient account balance
     */
    public void charge(BigDecimal money) throws AccountBalanceException {
        if (balance.compareTo(money) < 0)
            throw new AccountBalanceException("Requested money exceeds account's balance " + getBalanceWithCurrency());

        balance = balance.subtract(money);
    }

    /**
     * 
     * @return Current account balance without information about currency
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 
     * @return Account balance and currency 
     */
    public String getBalanceWithCurrency() {
        return balance.toString() + currency.getSymbol();
    }
}
