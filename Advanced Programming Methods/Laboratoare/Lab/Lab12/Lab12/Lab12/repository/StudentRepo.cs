using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;
using Lab12.validator;

namespace Lab12.repository
{
    public class StudentRepo:Repository<int,Student>
    {
        public StudentRepo(IValidator<Student> val) : base(val) { }
    }
}
