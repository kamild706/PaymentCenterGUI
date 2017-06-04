package application.model.bank;



import application.model.exceptions.CardNotFoundException;
import application.model.exceptions.FundsException;
import application.model.exceptions.PaymentRefusedException;
import application.util.MySimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Bank implements Serializable {

    private final StringProperty name;
    private final int bankPrefix = new Random().nextInt(90000) + 10000;
    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Each bank has it's own prefix which is used do identify to which bank card belongs
     * @return 
     */
    public int getBankPrefix() {
        return bankPrefix;
    }

    /**
     * 
     * @return Bank's name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Used for displaying bank's name 
     * @return Bank's name 
     */
    public StringProperty getNameProperty() {
        return name;
    }

    /**
     * 
     * @param name New name 
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * 
     * @return Quantity of customers in bank 
     */
    public int getCustomersNumber() {
        return customers.size();
    }

    /**
     * 
     * @return reference to ArrayList of customers 
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * 
     * @param customer New created customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Setting 
     * @param customers 
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public Bank() {
        this(null);
    }

    /**
     * 
     * @param name Name of created bank 
     */
    public Bank(String name) {
        this.name = new MySimpleStringProperty(name);
    }

    /**
     * 
     * @param cardNumber 
     * @param amount
     * @return Reference to charged card
     * @throws FundsException Thrown in case of insufficient founds on accaount
     * @throws PaymentRefusedException Thrown when bank refuses connection
     * @throws CardNotFoundException Thrown when card is not found
     */
    public Card chargeCard(String cardNumber, BigDecimal amount)
            throws FundsException, PaymentRefusedException, CardNotFoundException {
        if (new Random().nextInt(100) + 1 > 20) {
            Card card = findCard(cardNumber);
            card.charge(amount);

            return card;
        }
        else {
            throw new PaymentRefusedException("Bank has refused transaction");
        }
    }

    /**
     * Used for searching card by it's number
     * @param cardNumber
     * @return Reference to found card
     * @throws CardNotFoundException Thrown when card is not found 
     */
    public Card findCard(String cardNumber) throws CardNotFoundException {
        for (Customer customer : customers) {
            for (Card card : customer.getCards())
                if (card.getCardNumber().equals(cardNumber)) return card;
        }

        throw new CardNotFoundException("Card of ID " + cardNumber + " does not exists");
    }
}
