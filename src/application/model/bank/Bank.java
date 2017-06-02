package application.model.bank;



import application.model.exceptions.FundsException;
import application.model.exceptions.PaymentRefusedException;
import application.model.serviceCenter.RecipientOfService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Bank implements Serializable {

    private final StringProperty name;
    private final int bankPrefix;
    private ArrayList<Customer> customers = new ArrayList<>();
    private static int nextBankPrefix = 1000;

    public int getBankPrefix() {
        return bankPrefix;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCustomersNumber() {
        return customers.size();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Bank() {
        this(null);
    }

    public Bank(String name) {
        this.name = new SimpleStringProperty(name);
        bankPrefix = nextBankPrefix;
        nextBankPrefix++;
    }

    public void chargeCard(int cardNumber, BigDecimal amount, RecipientOfService requester)
            throws FundsException, PaymentRefusedException {
        if (new Random().nextInt(100) + 1 > 20) {
            Card card = findCard(cardNumber);
            if (card == null) throw new PaymentRefusedException("Card not found");
            card.charge(amount, requester);
        }
        else {
            throw new PaymentRefusedException("Bank has refused transaction");
        }
    }

    public Card findCard(int cardNumber) {
        for (Customer customer : customers) {
            for (Card card : customer.getCards())
                if (card.getCardNumber() == cardNumber) return card;
        }

        return null;
    }
}
