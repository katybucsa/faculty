package company.persistance;


import company.model.Clientj;

public interface IClientRepository extends IRepository<Integer, Clientj> {
    Clientj findLastAdded();
}
