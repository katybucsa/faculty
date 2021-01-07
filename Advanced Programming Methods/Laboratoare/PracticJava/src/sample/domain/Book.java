package sample.domain;

import sample.repository.HasID;

public class Book implements HasID<String> {
    private String bookId;
    private String name;
    private String author;
    private Type type;

    public Book(String bookId, String name, String author, Type type) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.type = type;
    }

    public String getID() {
        return bookId;
    }

    public void setID(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                '}';
    }
}
