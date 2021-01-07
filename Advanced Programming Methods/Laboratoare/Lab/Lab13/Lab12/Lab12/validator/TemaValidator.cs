using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;

namespace Lab12.validator
{
    class TemaValidator:IValidator<Tema>
    {
        public TemaValidator() { }
        public void Validate(Tema t)
        {
            String err = "";
            if (t.Id < 0 || t.Id > 14)
                err += "Numar tema invalid!\n";
            if (t.Deadline < 1 || t.Deadline > 14)
                err += "Termen limita de predare invalid!\n";
            if (t.SaptPrimire < 1 || t.SaptPrimire > 14)
                err += "Saptamana deprimire tema invalida!\n";
            if (t.SaptPrimire > t.Deadline)
                err += "Saptamana cu deadline-ul trebuie sa fie aceeasi sau sa urmeza saptamanii de primire!\n";
            if (err != "")
                throw new ValidationException(err);

        }
    }
}
