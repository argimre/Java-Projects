import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private int phone;
    private int id;
    private List<Order> history;

    public Customer(String name, String email, int phone, int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.history = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public List<Order> getHistory() {
        return history;
    }

    public void checkOut(Book book) {
        Order order = new Order(book);
        history.add(order);
    }

    public Book checkInBook(String title) {
        Book book;
        for (Order order : history) {
            book = order.getBook();
            if (order.getBook().getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public String getInfo() {
        return "ID: " + this.id + ", Name: " + this.name + ", Email: " + this.email + ", Phone: " + this.phone;
    }
}