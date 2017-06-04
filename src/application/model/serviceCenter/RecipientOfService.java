package application.model.serviceCenter;


import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Random;

public class RecipientOfService implements Serializable {

    private StringProperty name;
    private int recipientId = new Random().nextInt(90000) + 1;

    private int typeID;

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getId() {
        return recipientId;
    }

    public String getName() {
        return name.get();
    }

    public RecipientOfService(String name) {
        this.name = new MySimpleStringProperty(name);
    }

    public RecipientOfService() {
        this(null);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTypeName() {
        return null;
    }
}
