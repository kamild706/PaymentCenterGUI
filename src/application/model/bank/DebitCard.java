package application.model.bank;



import application.model.exceptions.AccountBalanceException;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class DebitCard extends Card implements Serializable {

    private Account account;

    public DebitCard(Currencies currency, int bankPrefix) {
        super(bankPrefix);
        account = new Account(currency);
    }

    @Override
    public void charge(BigDecimal amount, RecipientOfService requester) throws AccountBalanceException {
        Transaction transaction = new Transaction(amount, requester);

        try {
            account.charge(amount);
        }
        catch (AccountBalanceException e) {
            throw e;
        }
        addTransaction(transaction);
    }

    public void deposit(BigDecimal money) {
        account.deposit(money);
    }

    public String getBalanceWithCurrency() {
        return account.getBalanceWithCurrency();
    }
}
