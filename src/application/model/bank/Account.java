package application.model.bank;


import application.model.exceptions.AccountBalanceException;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {

    private BigDecimal balance;
    private Currency currency;

    public String getCurrencySymbol() {
        return currency.getSymbol();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(Currencies currency) {
        this.currency = new Currency(currency);
    }

    public Account(Currencies currency) {
        this.currency = new Currency(currency);
        balance = new BigDecimal(0);
    }

    public void deposit(BigDecimal money) {
        balance = balance.add(money);
    }

    public void charge(BigDecimal money) throws AccountBalanceException {
        if (balance.compareTo(money) < 0)
            throw new AccountBalanceException("Requested money exceeds account's balance " + getBalanceWithCurrency());

        balance = balance.subtract(money);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getBalanceWithCurrency() {
        return balance.toString() + currency.getSymbol();
    }
}
