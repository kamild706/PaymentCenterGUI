package application.model.serviceCenter;


import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Random;

/**
 * General recipient of service class.
 * 
 */
public class RecipientOfService implements Serializable {

    private StringProperty name;
    private int recipientId = new Random().nextInt(90000) + 1;

    private int typeID;

    /**
     * 
     * @return Recipient's type ID
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * Sets type ID
     * @param typeID 
     */
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    /**
     * Recipient's ID
     * @return 
     */
    public int getId() {
        return recipientId;
    }

    /**
     * 
     * @return Recipient's name
     */
    public String getName() {
        return name.get();
    }

    public RecipientOfService(String name) {
        this.name = new MySimpleStringProperty(name);
    }

    public RecipientOfService() {
        this(null);
    }

    /**
     * 
     * @return Recipient's name
     */
    public StringProperty getNameProperty() {
        return name;
    }

    /**
     * Sets secipient's name
     * @param name 
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * 
     * @return Always null
     */
    public String getTypeName() {
        return null;
    }
}
