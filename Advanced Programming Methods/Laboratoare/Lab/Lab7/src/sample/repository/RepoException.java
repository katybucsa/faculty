package sample.repository;


/**
 * own class of exceptions occurred in Repo
 * RepoException extended from RuntimeException
 */
public class RepoException extends RuntimeException{
    public RepoException(String err){
        super(err);
    }
}