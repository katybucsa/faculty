package repository;

import domain.Client;

public interface ClientRepositoryInterface extends RepositoryInterface<Integer, Client> {
    Client findLastAdded();
}
