package application.util;


import javafx.beans.property.SimpleStringProperty;

import java.io.*;

public class MySimpleStringProperty extends SimpleStringProperty implements Serializable {

    /**
     * Contains names of serialized fields.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class)
    };

    public MySimpleStringProperty() {
    }

    public MySimpleStringProperty(String initialValue) {
        super(initialValue);
    }

    public MySimpleStringProperty(Object bean, String name) {
        super(bean, name);
    }

    public MySimpleStringProperty(Object bean, String name, String initialValue) {
        super(bean, name, initialValue);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();

        fields.put("name", get());

        out.writeFields();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        set((String) fields.get("name", ""));
    }

    @Override
    public String toString() {
        return String.format("{name: %s}", get());
    }

}
