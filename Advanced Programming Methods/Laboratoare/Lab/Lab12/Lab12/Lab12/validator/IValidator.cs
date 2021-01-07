using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.validator
{
    public interface IValidator<E>
    {
        void Validate(E e);
    }
}
