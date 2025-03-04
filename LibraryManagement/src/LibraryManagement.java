import java.util.Scanner;

public class LibraryManagement {
    private BookCatalog bookCatalog = new BookCatalog();
    private CustomerCatalog customerCatalog = new CustomerCatalog();
    private Librarian librarian = new Librarian(bookCatalog, customerCatalog);
    private Scanner input = new Scanner(System.in);

    public void runProgram() {
        while (true) {
            System.out.println("\n Library Management System");
            System.out.println("1 : Add Book");
            System.out.println("2 : Search Book");
            System.out.println("3 : List Books");
            System.out.println("4 : Add Customer");
            System.out.println("5 : Search Customer");
            System.out.println("6 : List Customers");
            System.out.println("7 : Customer Checkin");
            System.out.println("8 : Customer Checkout");
            System.out.println("9 : Exit");
            System.out.println("Choose an option: ");

            int opt = input.nextInt();
            input.nextLine();

            switch (opt) {
                case 1:
                    librarian.addBook();
                    break;
                case 2:
                    librarian.searchBook();
                    break;
                case 3:
                    librarian.viewBookCatalog();
                    break;
                case 4:
                    librarian.addCustomer();
                    break;
                case 5:
                    librarian.searchCustomer();
                    break;
                case 6:
                    librarian.viewBookCatalog();
                    break;
                case 7:
                    librarian.customerCheckin();
                    return;
                case 8:
                    librarian.customerCheckout();
                    return;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.runProgram();
    }
}