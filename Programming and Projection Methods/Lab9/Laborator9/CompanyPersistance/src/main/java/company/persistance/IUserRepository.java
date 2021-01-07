package company.persistance;


import company.model.User;

public interface IUserRepository extends IRepository<String, User> {
    User findOne(User user);
}
