import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CustomerCatalog {
    private Scanner input;
    private Set<Customer> customers;

    public CustomerCatalog() {
        this.input = new Scanner(System.in);
        this.customers = new HashSet<>();
    }

    public void addCustomer() {
        System.out.println("Enter Customer Name: ");
        String name = input.nextLine();
        System.out.println("Enter Customer Email: ");
        String email = input.nextLine();
        System.out.println("Enter Customer Phone: ");
        int phone = input.nextInt();
        System.out.println("Enter Customer ID: ");
        int id = input.nextInt();
        input.nextLine();

        Customer customer = new Customer(name, email, phone, id);
        if (!customers.contains(customer)) {
            customers.add(customer);
        } else {
            System.out.println("Customer already exists");
        }
        System.out.println("Customer added successfully!");
    }

    public void removeCustomer() {
        System.out.println("Enter customer name to remove: ");
        String name = input.nextLine();

        boolean found = searchCustomer();
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                found = true;
                customers.remove(customer);
                System.out.println("Customer sucessfully removed");
                break;
            }
        }
        if (!found) {
            System.out.println("Customer could not be found in the catalog");
        }
    }

    public boolean searchCustomer() {
        System.out.println("Enter customer name to search: ");
        String name = input.nextLine();

        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                found = true;
                System.out.println("Customer foundd");
                break;
            }
        }
        if (!found) {
            System.out.println("Customer could not be found in the catalog");
        }
        return found;
    }

    public void getCatalog() {
        if (customers.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }

        System.out.println("Catalog Contents: ");
        for (Customer customer : customers) {
            System.out.println(customer.getInfo());
        }
    }

    public Customer getCustomer() {
        System.out.println("What is the customer name?: ");
        String name = input.nextLine();
        Customer customer = null;

        for (Customer temp : customers) {
            if (temp.getName().equalsIgnoreCase(name)) {
                customer = temp;
                break;
            }
        }
        return customer;
    }
}