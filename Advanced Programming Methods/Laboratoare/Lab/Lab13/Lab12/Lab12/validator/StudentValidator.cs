using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;

namespace Lab12.validator
{
    class StudentValidator:IValidator<Student>
    {
        public StudentValidator() { }
        public void Validate(Student student)
        {
            String err = "";
            if (student.Id < 1)
                err += "Id invalid!\n";
            if (student.Grupa < 221 || student.Grupa > 227)
            {
                err += "Grupa invalida!\n";
            }
            if (err != "")
                throw new ValidationException(err);
        }
    }
}
