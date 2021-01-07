package sample.domain;

import javafx.util.Pair;
import sample.repository.HasID;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rent implements HasID<Pair<Client, Book>> {
    private Client client;
    private Book book;
    private LocalDate rentDate;

    public Rent(Client client, Book book, LocalDate startDate) {
        this.client = client;
        this.book = book;
        this.rentDate = startDate;
    }

    @Override
    public Pair<Client, Book> getID() {
        return new Pair<>(client,book);
    }

    @Override
    public void setID(Pair<Client, Book> clientBookPair) {
            this.client=clientBookPair.getKey();
            this.book=clientBookPair.getValue();
    }


    public String getClientName(){
        return client.getName();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "client=" + client +
                ", book=" + book +
                ", rentDate=" + rentDate +
                '}';
    }
}
