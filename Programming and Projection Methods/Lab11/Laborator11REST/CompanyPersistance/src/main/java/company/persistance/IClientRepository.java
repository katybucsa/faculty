package company.persistance;


import company.model.Client;

public interface IClientRepository extends IRepository<Integer, Client> {
    Client findLastAdded();
}
