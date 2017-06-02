package application.util;


import javafx.beans.property.SimpleStringProperty;

import java.io.*;

public class MySimpleStringProperty extends SimpleStringProperty implements Serializable {

    /**
     * Contains names of serialized fields.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class),
    };

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        ObjectOutputStream.PutField fields = out.putFields();

        fields.put("name", get());

        out.writeFields();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        ObjectInputStream.GetField fields = in.readFields();
        set((String) fields.get("name", ""));
    }

    @Override
    public String toString()
    {
        return String.format("{name: %s}", get());
    }

}
