using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;
using Lab12.validator;

namespace Lab12.repository
{
    public class NotaRepo:Repository<KeyValuePair<Student,Tema>,Nota>
    {
        public NotaRepo(IValidator<Nota> val) : base(val) { }
    }
}
