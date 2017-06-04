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

    /**
     * Main constructor for new customer 
     * @param name
     * @param lastname
     * @param pesel 
     */
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

    /**
     * New name setter. 
     * @param name  
     */
    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    /**
     * New lastname setter.
     * @param lastname 
     */
    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    /**
     * 
     * @return ArrayList of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Adds new card to customer 
     * @param card New added card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    public StringProperty getLastnameProperty() {
        return lastname;
    }

    public StringProperty getPeselProperty() {
        return pesel;
    }

    /**
     * Pesel setter.
     * @param pesel 
     */
    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    /**
     * Sets new ArrayList of cards.
     * @param cards ArrayList of cards 
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getFullName() {
        return name.get() + " " + lastname.get();
    }
}
