package application.model.bank;


import java.io.Serializable;

/**
 * Graphical symbols of currencies: PLN, USD, EUR, GBP.
 */
public class Currency implements Serializable {

    Currencies currency;

    public Currency(Currencies currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        switch (currency) {
            case EUR: return "\u20ac";
            case GBP: return "\u00a3";
            case USD: return "$";
            case PLN: return "zł";
            default: return null;
        }
    }
}
