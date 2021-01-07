using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;

namespace Lab12.validator
{
    class NotaValidator:IValidator<Nota>
    {
        public NotaValidator() { }
        public void Validate(Nota nota)
        {
            String err = "";
            if (nota.Grade < 1 || nota.Grade > 10)
                err += "Nota invalida!\n";
            if (err != "")
                throw new ValidationException(err);
        }
    }
}
