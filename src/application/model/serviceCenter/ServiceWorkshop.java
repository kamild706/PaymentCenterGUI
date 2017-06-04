package application.model.serviceCenter;


import java.io.Serializable;

public class ServiceWorkshop extends RecipientOfService implements Serializable {

    public ServiceWorkshop(String name) {
        super(name);
    }

    @Override
    public String getTypeName() {
        return "Zakład usług";
    }
}
