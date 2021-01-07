package bugs.persistance;


import bugs.model.User;

public interface IUserRepository extends IRepository<String, User> {
    User findOne(User user);

    Iterable<User> findAllProgrammers();
}
