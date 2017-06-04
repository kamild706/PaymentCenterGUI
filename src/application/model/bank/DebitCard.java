package application.model.bank;



import application.model.exceptions.AccountBalanceException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class DebitCard extends Card implements Serializable {

    private Account account;

    /**
     * 
     * @param currency Card currency
     * @param owner Reference to card owner
     */
    public DebitCard(Currencies currency, Customer owner) {
        super(owner);
        account = new Account(currency);
    }

    public DebitCard() {
        this(null, null);
    }

    /**
     * Charges card.
     * @param amount Charged amount 
     * @throws AccountBalanceException Thrown in case of insufficient founds on card
     */
    @Override
    public void charge(BigDecimal amount) throws AccountBalanceException {
        try {
            account.charge(amount);
        }
        catch (AccountBalanceException e) {
            throw e;
        }
    }

    /**
     * Used for displaying card type
     * @return "Karta Debetowa" 
     */
    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta Debetowa");
    }

    /**
     * 
     * @return Card balance with currency
     */
    @Override
    public String getAvailableFounds() {
        return account.getBalanceWithCurrency();
    }

    /**
     * As debit cards have no limit of stored money albays "-" is returned.
     * @return "-"
     */
    @Override
    public String getCardLimit() {
        return "-";
    }

    /**
     * 
     * @return Account's, to which card is tied, current balance
     */
    @Override
    public String getMoney() {
        return account.getBalance().toString();
    }

    /**
     * Adds founds to account.
     * @param money 
     */
    public void deposit(BigDecimal money) {
        account.deposit(money);
    }

    /**
     * 
     * @return Account balance with currency
     */
    public String getBalanceWithCurrency() {
        return account.getBalanceWithCurrency();
    }

    /**
     * Sets amount of money.
     * @param money 
     */
    public void setMoney(String money) {
        account.setBalance(new BigDecimal(money));
    }

    /**
     * Sets account currency.
     * @param currency 
     */
    @Override
    public void setCurrency(Currencies currency) {
        account.setCurrency(currency);
    }

    @Override
    public String getCurrencySymbol() {
        return account.getCurrencySymbol();
    }
}
