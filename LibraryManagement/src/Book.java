public class Book {
    private String title;
    private String author;
    private String genre;
    private int id;

    public Book(String title, String author, String genre, int id) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getId() {
        return this.id;
    }

    public String getInfo() {
        return "ID: " + this.id + ", Title: " + this.title + ", Author: " + this.author + ", Genre: " + this.genre;
    }
}
