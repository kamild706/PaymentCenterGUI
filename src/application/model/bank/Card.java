package application.model.bank;



import application.model.exceptions.FundsException;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Card implements Serializable {

    private int cardNumber;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public int getCardNumber() {
        return cardNumber;
    }

    public Card(int prefix, int number) {
        cardNumber = Integer.parseInt(prefix + "" + number);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public abstract void charge(BigDecimal amount, RecipientOfService requester) throws FundsException;
}
