package application.model.bank;


import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private StringProperty name;
    private StringProperty lastname;
    private StringProperty pesel;
    private ArrayList<Card> cards = new ArrayList<>();

    public Customer() {
        this(null, null, null);
    }

    public Customer(String name, String lastname, String pesel) {
        this.name = new MySimpleStringProperty(name);
        this.lastname = new MySimpleStringProperty(lastname);
        this.pesel = new MySimpleStringProperty(pesel);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public StringProperty getLastnameProperty() {
        return lastname;
    }

    public StringProperty getPeselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
