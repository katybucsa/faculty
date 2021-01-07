using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;
using Lab12.validator;

namespace Lab12.repository
{
    public class TemaRepo:Repository<int,Tema>
    {
        public TemaRepo(IValidator<Tema> val) : base(val) { }
    }
}
