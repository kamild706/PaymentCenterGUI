package application.model.bank;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Main {

    public static void main(String[] args) throws IOException {
        /*Customer customer = new Customer("Kamil", "Domurat", "97082101851");
        DebitCard debitcard = new DebitCard(Currencies.PLN, 1000);
        debitcard.deposit(new BigDecimal("2000.40"));
        customer.addCard(debitcard);
        RecipientOfService shop = new Shop("Sexshop");
        try {
            debitcard.charge(new BigDecimal("1000"), shop);
        }
        catch (AccountBalanceException e) {
            e.printStackTrace();
        }

       *//* ArrayList<Transaction> transactions = customer.getCards().get(0).getTransactions();
        System.out.println(transactions.get(0).getFormattedDateAndTime());*//*

        FileOutputStream fout = new FileOutputStream("/home/kamil/Projects/Java/CardServiceCenter/app.state");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(customer);*/
/*

        FileInputStream fin = new FileInputStream("/home/kamil/Projects/Java/CardServiceCenter/app.state");
        ObjectInputStream ois = new ObjectInputStream(fin);

        Customer customer = null;
        try {
            customer = (Customer) ois.readObject();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert customer != null;
        Transaction transaction = customer.getCards().get(0).getTransactions().get(0);
        System.out.println(((DebitCard) customer.getCards().get(0)).getBalanceWithCurrency());
        System.out.println(transaction.getRequestedMoney() + " " + transaction.getFormattedDateAndTime());
*/


    }
}
