package application.model.bank;


import java.io.Serializable;

public class AtmCard extends DebitCard implements Serializable {

    public AtmCard(Currencies currency, int bankPrefix, int number) {
        super(currency, bankPrefix, number);
    }
}
