package application.model.serviceCenter;



import application.model.bank.Bank;
import application.model.exceptions.FundsException;
import application.model.exceptions.PaymentRefusedException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ServiceCenter implements Serializable {

    /*private ArrayList<Bank> banks = new ArrayList<>();
    private ArrayList<RecipientOfService> recipients = new ArrayList<>();

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public ArrayList<RecipientOfService> getRecipients() {
        return recipients;
    }

    public void addRecipient(RecipientOfService recipient) {
        recipients.add(recipient);
    }

    public void chargeCard(int cardNumber, BigDecimal amount) {
        Bank bank = findBank(String.valueOf(cardNumber));
        if (bank != null) {
            try {
                bank.chargeCard(cardNumber, amount);
            }
            catch (FundsException | PaymentRefusedException e) {
                e.printStackTrace();
            }
        }
    }

    private Bank findBank(String cardNumber) {
        for (Bank bank : banks) {
            if (cardNumber.startsWith(String.valueOf(bank.getBankPrefix())))
                return bank;
        }

        return null;
    }

    public void createCustomer() {}

    public void createCard() {}*/

}
