package sample.repository;


import sample.domain.Student;

/**
 * StudentRepository - repository that stores objects of Student type
 * extends AbstractRepository - ID=Integer, E=Student
 */
public class StudentRepository extends AbstractRepository<Integer, Student> {
    public StudentRepository(Validator<Student> v){
        super(v);
    }
}

