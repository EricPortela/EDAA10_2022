import java.util.*;

public class BankApplication {

    public static void main(String[] args) {
        //ArrayList<BankAccount> accounts = new ArrayList<>();

        Bank bank = new Bank();
        // bank.addAccount("Eric", 1);
        // bank.addAccount("Per", 2);
        // bank.addAccount("Axel", 3);
        // bank.addAccount("Anton", 4);

        // System.out.println(bank.getAllAccounts());
       
        String[] options = {
            "\n\n1. Hitta konto utifrån innehavare", 
            "2. Sök kontoinnehavare utifrån (del av) namn", 
            "3. Sätt in",
            "4. Ta ut", 
            "5. Överföring",
            "6. Skapa konto",
            "7. Ta bort konto",
            "8. Skriv ut konton",
            "9. Avsluta"
        };


        //FRÅGOR
        /*
         * static för att hålla reda på nummer
         * klassuppdelning, skriv ner med alla jävla nummer
         * viktigt att lägga tillbaka efter withdrawal, om det är så att man har för lite pengar
         * 
         */

        Scanner scan = new Scanner(System.in);


        Long id;
        int accountNbr;

        Double amount;
        Double balance;

        int account1;
        int account2;

        BankAccount fromAcc;
        BankAccount toAcc;

        Boolean quit = false;

        while (!quit) {

            System.out.println("----------------------------------------------------");
            for (String option: options) {
                System.out.println(option);
            }

            System.out.println("val: " );
            int choice = scan.nextInt();
            
            switch(choice) {
                case 1: //Hitta konto utifrån ID
                    System.out.println("\nID: ");
                    scan.nextLine();

                    id = scan.nextLong(); //Attribute

                    ArrayList<BankAccount> accountsFound = bank.findAccountsForHolder(id);

                    if (accountsFound.size() != 0) {
                        for (BankAccount acc: accountsFound) {
                            System.out.println(acc.toString());
                        }
                    } else {
                        System.out.println("Inga resultat hittades!");
                    }

                    break;

                case 2: //Sök kontoinnehavare
                    System.out.println("\nVad vill du söka på? ");
                    scan.nextLine();
                    String searchWord = scan.nextLine();

                    ArrayList<Customer> results = bank.findByPartofName(searchWord);

                    for (Customer customer: results) { 
                        System.out.println(customer.toString());
                    }

                    break;

                case 3:
                    System.out.println("Från konto: ");
                    scan.nextLine();
                    accountNbr = scan.nextInt(); //Attribute         
                    fromAcc = bank.findByNumber(accountNbr);

                    System.out.println("Belopp: ");
                    scan.nextLine();
                    amount = scan.nextDouble(); //Attribute
                    fromAcc.deposit(amount);

                    break;

                case 4: //Ta ut
                    System.out.println("Från konto: ");
                    scan.nextLine();
                    accountNbr = scan.nextInt(); //Attribute         
                    fromAcc = bank.findByNumber(accountNbr);

                    System.out.println("Belopp: ");
                    scan.nextLine();
                    amount = scan.nextDouble(); //Attribute
                    fromAcc.withdraw(amount);

                    balance = fromAcc.getAmount(); //Attribute

                    if (balance < 0) {
                        fromAcc.deposit(amount);
                        balance = fromAcc.getAmount();
                        System.out.println("Uttaget misslyckades, endast " + balance + " på kontot!");
                    } else {
                        System.out.println("Uttaget lyckades!" + "Ditt saldo är " + balance );
                    }

                    break;

                case 5: //Överföring
                    System.out.println("Från konto: ");
                    scan.nextLine();
                    account1 = scan.nextInt(); //Attribute      
                    fromAcc = bank.findByNumber(account1); //Attribute


                    System.out.println("Till konto: ");
                    scan.nextLine();
                    account2 = scan.nextInt(); //Attribute
                    toAcc = bank.findByNumber(account2); //Attribute
                    
                    System.out.println("Belopp: ");
                    amount = scan.nextDouble(); //Attribute

                    fromAcc.withdraw(amount);
                    
                    if (fromAcc.getAmount() > 0) { //VIKTIGT!!
                        toAcc.deposit(amount);

                        System.out.println(fromAcc.toString());
                        System.out.println(toAcc.toString());

                    } else {
                        fromAcc.deposit(amount);
                        System.out.println("För lågt saldo!");
                    }

                    break;

                case 6: //Skapa nytt konto
                    System.out.println("Namn: ");
                    scan.nextLine();
                    String name = scan.nextLine();                

                    System.out.println("id: ");
                    //scan.nextLine();
                    id = scan.nextLong(); //Attribute
                    
                    int nbr = bank.addAccount(name, id);

                    System.out.println("Konto skapat: " + nbr);

                    break;

                case 7: //Ta bort konto
                    System.out.println("Konto: ");
                    scan.nextLine();
                    account1 = scan.nextInt();

                    Boolean removed = bank.removeAccount(account1); //VIKTIGT!!

                    if (!removed) {
                        System.out.println("Felaktigt kontonummer!");
                    }

                    break;

                case 8: //Skriv ut konton i ordning
                    ArrayList<BankAccount> SortedAccounts = bank.getAllAccounts();

                    for (BankAccount acc: SortedAccounts) {
                        System.out.println(acc);
                    }

                    break;

                case 9:
                    quit = true;

                    break;

                default:
                    System.out.println("");
            }
        }



        //System.out.println(BankApplication.sortTest(accounts));
        //BankApplication.sortTest(accounts);
    }

    



    public static ArrayList<BankAccount> sortTest(ArrayList<BankAccount> accounts) {
        ArrayList<BankAccount> sorted = new ArrayList<BankAccount>();

        for (BankAccount account: accounts) {
            System.out.println("\nNästa konto:");
            
			int i;
			for (i = 0; i < sorted.size(); i++) { //i antar ändå villkorets värde
				if (sorted.get(i).getHolder().getName().compareToIgnoreCase(account.getHolder().getName()) > 0) {
                    System.out.println("Vi har INTE nått slutet!");
                    System.out.println(i + " och " + account.getHolder().getName());
					sorted.add(i, account);
					break;
				}
			}

            System.out.println(sorted.size());


			if (i >= sorted.size()) {
                System.out.println("Vi har nått slutet!");
                System.out.println(i + " och " + account.getHolder().getName());
				sorted.add(account);
			}
		}

        return sorted;
    }
    
}
