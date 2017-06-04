package application.model.serviceCenter;


import application.model.bank.Card;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {

    private BigDecimal requestedMoney;
    private GregorianCalendar date;
    private RecipientOfService requester;
    private Card card;

    public RecipientOfService getRequester() {
        return requester;
    }

    public Card getCard() {
        return card;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getFormattedDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date.getTime());
    }

    public StringProperty getDateProperty() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return new MySimpleStringProperty(dateFormat.format(date.getTime()));
    }

    public BigDecimal getRequestedMoney() {
        return requestedMoney;
    }

    public StringProperty getRequestedMoneyProperty() {
        return new MySimpleStringProperty(requestedMoney.toString());
    }

    public Transaction(BigDecimal requestedMoney, RecipientOfService requester, Card card) {
        this.requestedMoney = requestedMoney;
        this.requester = requester;
        this.date = new GregorianCalendar();
        this.card = card;
    }
}
