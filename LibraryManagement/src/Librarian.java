import java.util.Scanner;

public class Librarian {
    private BookCatalog bookCatalog;
    private CustomerCatalog customerCatalog;
    private Scanner input;

    public Librarian(BookCatalog bookCatalog, CustomerCatalog customerCatalog) {
        this.bookCatalog = bookCatalog;
        bookCatalog.addPayload();
        this.customerCatalog = customerCatalog;
        this.input = new Scanner(System.in);
    }

    public void addBook() {
        bookCatalog.addNewBook();
    }

    public void removeBook() {
        bookCatalog.removeBook();
    }

    public boolean searchBook() {
        boolean result = bookCatalog.searchBook();
        if (result)
            System.out.println("Book was found in the catalog");
        return result;
    }

    public void viewBookCatalog() {
        bookCatalog.getCatalog();
    }

    public void addCustomer() {
        customerCatalog.addCustomer();
    }

    public void removeCustomer() {
        customerCatalog.removeCustomer();
    }

    public boolean searchCustomer() {
        boolean result = customerCatalog.searchCustomer();
        if (result)
            System.out.println("Customer was found in the catalog");
        return result;
    }

    public void viewCustomerCatalog() {
        customerCatalog.getCatalog();
    }

    public void customerCheckout() {
        Customer customer = customerCatalog.getCustomer();
        System.out.println("what is the book title?: ");

        Book book = bookCatalog.retrieveBook();
        if (customer != null) {
            if (book != null) {
                customer.checkOut(book);
            } else {
                System.out.println("Book not found in the catalog");
            }
        } else {
            System.out.println("Customer not found in the catalog: ");
        }
    }

    public void customerCheckin() {
        Customer customer = customerCatalog.getCustomer();
        System.out.println("what is the book title?: ");
        String title = input.nextLine();

        if (customer != null) {
            Book book = customer.checkInBook(title);
            if (book != null) {
                bookCatalog.addOldBook(book);
            }
        } else {
            System.out.println("Customer not found in the catalog: ");
        }
    }
}
