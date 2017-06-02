package application.model.serviceCenter;


import java.io.Serializable;

public abstract class RecipientOfService implements Serializable {

    private String name;
    private int recipientId;

    private static int nextRecipientId = 1;

    public String getName() {
        return name;
    }

    public RecipientOfService(String name) {
        this.name = name;
        this.recipientId = nextRecipientId;
        nextRecipientId++;
    }
}
