package application.model.bank;



import application.model.exceptions.AccountBalanceException;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class DebitCard extends Card implements Serializable {

    private Account account;

    public DebitCard(Currencies currency, int bankPrefix, int number, Customer owner) {
        super(bankPrefix, number, owner);
        account = new Account(currency);
    }

    @Override
    public void charge(BigDecimal amount) throws AccountBalanceException {
        try {
            account.charge(amount);
        }
        catch (AccountBalanceException e) {
            throw e;
        }
    }

    public void deposit(BigDecimal money) {
        account.deposit(money);
    }

    public String getBalanceWithCurrency() {
        return account.getBalanceWithCurrency();
    }
}
