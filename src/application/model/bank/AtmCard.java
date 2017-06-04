package application.model.bank;


import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class AtmCard extends DebitCard implements Serializable {

    /**
     * 
     * @param currency Currency of account to which card is connected
     * @param owner Refrence to customer to which card belongs 
     */
    public AtmCard(Currencies currency, Customer owner) {
        super(currency, owner);
    }

    public AtmCard() {
        this(null, null);
    }

    /**
     * 
     * @return Always "Karta bankomatowa"
     */
    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta bankomatowa");
    }
}
