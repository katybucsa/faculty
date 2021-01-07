package sample.repository;

import sample.domain.Student;

/**
 * StudentValidator implements Validator interface - E=Student
 */
public class StudentValidator implements Validator<Student> {

    /**
     * validates a student
     * @param student - student to be validated
     * @throws ValidationException if the id or the group number of the student is not valid
     */
    public void validate(Student student){
        String err="";
        if(student.getID()<1)
            err+="Id invalid!\n";
        if(student.getGrupa()<221 || student.getGrupa()>227) {
            err += "Grupa invalida!\n";
        }
        if(err!="")
            throw new ValidationException(err);
    }
}
