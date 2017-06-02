package application.model.serviceCenter;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {

    private BigDecimal requestedMoney;
    private GregorianCalendar date;
    private RecipientOfService requester;

    public RecipientOfService getRequester() {
        return requester;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getFormattedDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date.getTime());
    }

    public BigDecimal getRequestedMoney() {
        return requestedMoney;
    }

    public Transaction(BigDecimal requestedMoney, RecipientOfService requester) {
        this.requestedMoney = requestedMoney;
        this.requester = requester;
        this.date = new GregorianCalendar();
    }
}
