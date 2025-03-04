import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private Book book;
    private Date date;

    public Order(Book book) {
        this.book = book;
        this.date = Date.valueOf(LocalDate.now());
    }

    public Book getBook() {
        return this.book;
    }

    public Date getDate() {
        return this.date;
    }

    public String getOrderHistory() {
        return "Book: " + book.toString() + ", Order Date: " + date.toString();
    }
}