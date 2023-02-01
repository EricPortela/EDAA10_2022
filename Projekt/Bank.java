import java.util.ArrayList;

public class Bank {

    private ArrayList<BankAccount> accounts;

    /** Skapar en ny bank utan konton. */
    public Bank() {
        this.accounts = new ArrayList();
    }

    /**
    * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare
    * med de givna uppgifterna ska inte en ny Customer skapas, utan istället
    * den befintliga användas. Det nya kontonumret returneras.
    */
    public int addAccount(String holderName, long idNr) {
        for (BankAccount acc: accounts) {
            
            Customer holder = acc.getHolder();

            //Finns redan en sådan kontoinnehavare
            if (holder.getName().equals(holderName) && holder.getIdNr() == idNr ) {
                BankAccount newAcc = new BankAccount(holder);
                accounts.add(newAcc);
                return newAcc.getAccountNumber();
            }
        }

        //Ingen sådan kontohavare hittad ==> skapa en ny
        Customer newCustomer = new Customer(holderName, idNr);
        BankAccount newAcc = new BankAccount(newCustomer);

        accounts.add(newAcc);

        return newAcc.getAccountNumber();
    }
    /**
    * Returnerar den kontoinnehavaren som har det givna id-numret,
    * eller null om ingen sådan finns.
    */
    public Customer findHolder(long idNr) {
        for (BankAccount acc: accounts) {
            if (acc.getHolder().getIdNr() == idNr) {
                return acc.getHolder();
            }
        }
        return null;
    }

    /**
    * Tar bort konto med nummer 'number' från banken. Returnerar true om
    * kontot fanns (och kunde tas bort), annars false.
    */
    public boolean removeAccount(int number) {
        for (BankAccount acc: accounts) {
            if (acc.getAccountNumber() == number) { //FOUND account!
                return accounts.remove(acc);
            }
        }
        return false;
    }

    /**
    * Returnerar en lista innehållande samtliga bankkonton i banken.
    * Listan är sorterad på kontoinnehavarnas namn.
    */
    public ArrayList<BankAccount> getAllAccounts() {

        ArrayList<BankAccount> sorted = new ArrayList<>();
		
		for (BankAccount account : accounts) {
			int i;
			for (i = 0; i < sorted.size(); i++) {
				if (sorted.get(i).getHolder().getName().compareToIgnoreCase(account.getHolder().getName()) > 0) {
					sorted.add(i, account);
					break;
				}
			}
			if (i >= sorted.size()) {
				sorted.add(account);
			}
		}
		
		return sorted;     
    }

    /**
    * Söker upp och returnerar bankkontot med kontonummer 'accountNumber'.
    * Returnerar null om inget sådant konto finns.
    */
    public BankAccount findByNumber(int accountNumber) {
        for (BankAccount acc: accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    /**
    * Söker upp alla bankkonton som innehas av kunden med id-nummer 'idNr'.
    * Kontona returneras i en lista. Kunderna antas ha unika id-nummer.
    */
    public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
        ArrayList<BankAccount> customersAllAccs = new ArrayList<>();

        for(BankAccount acc: accounts) {
            if (acc.getHolder().getIdNr() == idNr) {
                customersAllAccs.add(acc);
            }
        }
        return customersAllAccs;
    }

    /**
    * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla
    * personer vars namn innehåller strängen 'namePart' inkluderas i
    * resultatet, som returneras som en lista. Samma person kan förekomma
    * flera gånger i resultatet. Sökningen är "case insensitive", det vill
    * säga gör ingen skillnad på stora och små bokstäver.
    */
    public ArrayList<Customer> findByPartofName(String namePart) {
        ArrayList<Customer> foundCustomers = new ArrayList<>();

        for (BankAccount acc: accounts) {
            String name = acc.getHolder().getName().toLowerCase();
            if (name.contains(namePart.toLowerCase())) {
                foundCustomers.add(acc.getHolder());
            }
        }
        
        return foundCustomers;
    }
    
}
