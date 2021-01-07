package repository;

import domain.User;

public interface UserRepositoryInterface extends RepositoryInterface<String,User> {
    public User findOne(String username,String password);
}
