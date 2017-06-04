package application.model.bank;



import application.model.exceptions.FundsException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

/**
 * Abstract card class.
 */
public abstract class Card implements Serializable {

    private StringProperty cardNumber;
    private Customer cardOwner;

    public String getCardNumber() {
        return cardNumber.get();
    }

    /**
     * Used for displaying card number.
     * @return 
     */
    public StringProperty getCardNumberProperty() {
        return cardNumber;
    }

    /**
     * 
     * @return Reference to card owner
     */
    public Customer getCardOwner() {
        return cardOwner;
    }

    /**
     * 
     * @param owner Reference to created card owner
     */
    public Card(Customer owner) {
        cardOwner = owner;
        cardNumber = new MySimpleStringProperty(String.valueOf(new Random().nextInt(90000000) + 10000000));
    }

    /**
     * Changes card owner.
     * @param customer Reference to card owner
     */
    public void setCardOwner(Customer customer) {
        cardOwner = customer;
    }

    public abstract void charge(BigDecimal amount) throws FundsException;
    public abstract StringProperty getType();
    public abstract String getAvailableFounds();
    public abstract String getCardLimit();
    public abstract String getMoney();
    public abstract void setMoney(String money);
    public abstract void setCurrency(Currencies currency);
}
