package application.model.serviceCenter;


import application.model.bank.Card;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Transaction history item.
 * 
 */
public class Transaction implements Serializable {

    private BigDecimal requestedMoney;
    private GregorianCalendar date;
    private RecipientOfService requester;
    private Card card;

    /**
     * 
     * @return Transaction requester
     */
    public RecipientOfService getRequester() {
        return requester;
    }

    /**
     * 
     * @return reference to card which took part in transaction
     */
    public Card getCard() {
        return card;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * 
     * @return Date in yyyy/MM/dd HH:mm:ss format
     */
    public String getFormattedDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date.getTime());
    }

    /**
     * Used when displaying transaction date.
     * @return Transaction date in yyyy/MM/dd HH:mm:ss format
     */
    public StringProperty getDateProperty() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return new MySimpleStringProperty(dateFormat.format(date.getTime()));
    }

    /**
     * 
     * @return Amaount of paid money
     */
    public BigDecimal getRequestedMoney() {
        return requestedMoney;
    }

    /**
     * 
     * @return Amount of paid money
     */
    public StringProperty getRequestedMoneyProperty() {
        return new MySimpleStringProperty(requestedMoney.toString());
    }

    /**
     * New transaction history item constructor.
     * @param requestedMoney Amount of money
     * @param requester Reterence to requester
     * @param card Reference to charged card
     */
    public Transaction(BigDecimal requestedMoney, RecipientOfService requester, Card card) {
        this.requestedMoney = requestedMoney;
        this.requester = requester;
        this.date = new GregorianCalendar();
        this.card = card;
    }
}
