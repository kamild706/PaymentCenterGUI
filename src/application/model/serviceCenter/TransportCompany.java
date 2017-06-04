package application.model.serviceCenter;


import java.io.Serializable;

public class TransportCompany extends RecipientOfService implements Serializable {
    public TransportCompany(String name) {
        super(name);
    }

    /**
     * 
     * @return ALways "Firma transportowa"
     */
    @Override
    public String getTypeName() {
        return "Firma transportowa";
    }
}
