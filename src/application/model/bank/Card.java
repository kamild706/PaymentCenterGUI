package application.model.bank;



import application.model.exceptions.FundsException;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Card implements Serializable {

    private int cardNumber;
    private Customer cardOwner;

    public int getCardNumber() {
        return cardNumber;
    }

    public Customer getCardOwner() {
        return cardOwner;
    }

    public Card(int prefix, int number, Customer owner) {
        cardOwner = owner;
        cardNumber = Integer.parseInt(prefix + "" + number);
    }

    public abstract void charge(BigDecimal amount) throws FundsException;
}
