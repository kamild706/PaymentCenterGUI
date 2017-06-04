package application.model.bank;


import application.model.exceptions.LoanLimitException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreditCard extends Card implements Serializable {

    private Loan loan;

    /**
     * New card constuctor.
     * @param currency Founds currency
     * @param owner Reference to card owner
     */
    public CreditCard(Currencies currency, Customer owner) {
        super(owner);
        loan = new Loan(new BigDecimal(5000), currency);
    }

    public CreditCard() {
        this(null, null);
    }

    /**
     * 
     * @param loan Amount of loaned money
     */
    public void setLoan(BigDecimal loan) {
        this.loan.setLoan(loan);
    }

    /**
     * Charges card.
     * @param amount 
     * @throws LoanLimitException Thrown in case of insufficient founds 
     */
    @Override
    public void charge(BigDecimal amount) throws LoanLimitException {
        try {
            loan.charge(amount);
        }
        catch (LoanLimitException e) {
            throw e;
        }
    }

    /**
     * Used for dilplaying card type
     * @return "Karta Kredytowa"
     */
    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta Kredytowa");
    }

    /**
     * 
     * @return Amount of avalible founds and currency
     */
    @Override
    public String getAvailableFounds() {
        return loan.getLoan().subtract(loan.getBalance()).toString() + loan.getCurrencySymbol();
    }

    /**
     * 
     * @return Card limit with currency
     */
    @Override
    public String getCardLimit() {
        return loan.getLoan().toString() + "" + loan.getCurrencySymbol();
    }

    /**
     * 
     * @return Avalible founds
     */
    @Override
    public String getCurrencySymbol() {
        return loan.getCurrencySymbol();
    }

    @Override
    public String getMoney() {
        return loan.getLoan().toString();
    }

    /**
     * Changes card balance to 0.
     */
    public void resetBalance() {
        loan.resetBalance();
    }

    /**
     * Sets amount of money on card.
     * @param money 
     */
    public void setMoney(String money) {
        loan.setLoan(new BigDecimal(money));
    }

    /**
     * Changes card currency.
     * @param currency New currency
     */
    @Override
    public void setCurrency(Currencies currency) {
        loan.setCurrency(currency);
    }

}
