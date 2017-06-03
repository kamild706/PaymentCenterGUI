package application.model.bank;


import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class AtmCard extends DebitCard implements Serializable {

    public AtmCard(Currencies currency, Customer owner) {
        super(currency, owner);
    }

    public AtmCard() {
        this(null, null);
    }

    @Override
    public StringProperty getType() {
        return new MySimpleStringProperty("Karta bankomatowa");
    }
}
