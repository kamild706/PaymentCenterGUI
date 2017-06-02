package application.model.bank;



import application.model.exceptions.LoanLimitException;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreditCard extends Card implements Serializable {

    private Loan loan;

    public CreditCard(Currencies currency, int prefix, int number) {
        super(prefix, number);
        loan = new Loan(new BigDecimal(5000), currency);
    }

    public void setLoan(BigDecimal loan) {
        this.loan.setLoan(loan);
    }

    @Override
    public void charge(BigDecimal amount, RecipientOfService requester) throws LoanLimitException {
        Transaction transaction = new Transaction(amount, requester);

        try {
            loan.charge(amount);
        }
        catch (LoanLimitException e) {
            throw e;
        }
        addTransaction(transaction);
    }

    public void resetBalance() {
        loan.resetBalance();
    }

}
