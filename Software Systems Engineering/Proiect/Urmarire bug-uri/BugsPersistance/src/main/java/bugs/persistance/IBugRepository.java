package bugs.persistance;


import bugs.model.Bug;

public interface IBugRepository extends IRepository<Integer, Bug> {
    Iterable<Bug> findAllForProgrammer();
    Iterable<Bug> findAllForTester();
    Iterable<Bug> findAllSolvedBugs();
}
