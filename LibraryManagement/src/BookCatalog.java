import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookCatalog {
    private Scanner input;
    private Map<Book, Integer> books;

    public BookCatalog() {
        this.input = new Scanner(System.in);
        this.books = new HashMap<>();
    }

    public void addPayload() {
        String filePath = "Java-Projects/LibraryManagement/lib/booklist.txt";
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("Error: File not found at " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("\\|");

                if (temp.length >= 4) { // Expecting: Title | Author | Genre | ID
                    try {
                        String title = temp[0].trim();
                        String author = temp[1].trim();
                        String genre = temp[2].trim();
                        int id = Integer.parseInt(temp[3].trim());

                        Book book = new Book(title, author, genre, id);
                        books.put(book, books.getOrDefault(book, 0) + 1);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid book ID format in line: " + line);
                    }
                }
            }
            System.out.println("Books successfully loaded from " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void addNewBook() {
        System.out.println("Enter the book title: ");
        String title = input.nextLine();
        System.out.println("Enter the author:");
        String author = input.nextLine();
        System.out.println("Enter the genre: ");
        String genre = input.nextLine();
        System.out.println("Enter the book id: ");
        int id = input.nextInt();
        input.nextLine();

        Book book = new Book(title, author, genre, id);
        books.put(book, books.getOrDefault(book, 0) + 1);
        System.out.println("Book added successfully.");
    }

    public void addOldBook(Book book) {
        books.put(book, books.getOrDefault(book, 0) + 1);
        System.out.println("Book successfully added back.");
    }

    public void removeBook() {
        System.out.println("Enter the book title to delete: ");
        String title = input.nextLine();
        boolean found = false;

        for (Book book : books.keySet()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                found = true;
                System.out.println("Book deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("This book was not found in the catalog.");
        }
    }

    public boolean searchBook() {
        System.out.println("Enter the book title to search: ");
        String title = input.nextLine();

        boolean found = false;
        for (Book book : books.keySet()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Your book has been found in the catalog: ");
                System.out.println(book.getInfo());
                System.out.println("Available copies: " + books.get(book));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("This book was not found in the catalog.");
        }
        return found;
    }

    public Book retrieveBook() {
        System.out.println("Enter the book title to get: ");
        String title = input.nextLine();

        for (Book book : books.keySet()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (books.get(book) > 0) {
                    System.out.println("Book found: " + book.getInfo());
                    int currentCount = books.get(book);
                    books.put(book, currentCount - 1);
                    System.out.println("One copy of the book has been checked out.");
                    return book;
                }
            }
        }

        System.out.println("Sorry, the book with title '" + title + "' was not found in the catalog.");
        return null;
    }

    public void getCatalog() {
        if (books.isEmpty()) {
            System.out.println("The catalog is empty.");
            return;
        }
        System.out.println("Catalog Contents: ");
        for (Book book : books.keySet()) {
            System.out.println(book.getInfo());
            System.out.println("Book Count: " + books.get(book));
        }
    }
}
