public class Customer {
    
    private String name;
    private long idNr;
    private int customerNbr;
    private static int customers = 101;

    /**
    * Skapar en kund (kontoinnehavare) med namnet 'name' och id-nummer 'idNr'.
    * Kunden tilldelas också ett unikt kundnummer.
    */
    public Customer(String name, long idNr) {
        this.name = name;
        this.idNr = idNr;

        this.customerNbr = customers;
        customers ++;
    }

    /** Tar reda på kundens namn. */
    public String getName() {
        return name;
    }

    /** Tar reda på kundens personnummer. */
    public long getIdNr() {
        return idNr;
    }

    /** Tar reda på kundens kundnummer. */
    public int getCustomerNr() {
        return customerNbr;
    }

    /** Returnerar en strängbeskrivning av kunden. */
    public String toString() {
        return name + "\tID#: " + idNr + "\t" + "Customer#: " + customerNbr + "\t";
    }

}
