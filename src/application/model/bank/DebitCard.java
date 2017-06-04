package application.model.bank;



import application.model.exceptions.AccountBalanceException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class DebitCard extends Card implements Serializable {

    private Account account;

    public DebitCard(Currencies currency, Customer owner) {
        super(owner);
        account = new Account(currency);
    }

    public DebitCard() {
        this(null, null);
    }

    @Override
    public void charge(BigDecimal amount) throws AccountBalanceException {
        try {
            account.charge(amount);
        }
        catch (AccountBalanceException e) {
            throw e;
        }
    }

    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta Debetowa");
    }

    @Override
    public String getAvailableFounds() {
        return account.getBalanceWithCurrency();
    }

    @Override
    public String getCardLimit() {
        return "-";
    }

    @Override
    public String getMoney() {
        return account.getBalance().toString();
    }

    public void deposit(BigDecimal money) {
        account.deposit(money);
    }

    public String getBalanceWithCurrency() {
        return account.getBalanceWithCurrency();
    }

    public void setMoney(String money) {
        account.setBalance(new BigDecimal(money));
    }

    @Override
    public void setCurrency(Currencies currency) {
        account.setCurrency(currency);
    }

    @Override
    public String getCurrencySymbol() {
        return account.getCurrencySymbol();
    }
}
