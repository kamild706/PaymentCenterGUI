package application.model.bank;


import java.io.Serializable;

public class AtmCard extends DebitCard implements Serializable {

    public AtmCard(Currencies currency, int bankPrefix) {
        super(currency, bankPrefix);
    }
}
