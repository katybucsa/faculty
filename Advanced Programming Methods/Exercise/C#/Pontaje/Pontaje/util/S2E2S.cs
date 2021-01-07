using Pontaje.domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.util
{
    public abstract class S2E2S
    {
        public static String AngajatToString(Angajat a)
        {
            return string.Format("{0},{1},{2},{3}",a.Id,a.Nume,a.VenitPeOra,a.NivelAngajat);
        }

        public static Angajat StringToAngajat(string str)
        {
            String[] list = str.Split(",");
            bool v1, v2;
            float venit;
            Nivel niv;
            if (list.Length == 4)
            {
                v1 = float.TryParse(list[2], out venit);
                v2 = Nivel.TryParse(list[3], out niv);
                if (v1 && v2)
                    return new Angajat(list[0], list[1], venit,niv);
            }
            return null;
        }

        public static String SarcinaToString(Sarcina s)
        {
            return string.Format("{0},{1},{2}", s.Id,s.Dificultate,s.NrOreEstimate);
        }

        public static Sarcina StringToSarcina(String str)
        {
            String[] list = str.Split(",");
            bool v1, v2;
            int ore;
            Dificulty dif;
            if (list.Length == 3)
            {
                v1 = Dificulty.TryParse(list[1], out dif);
                v2 = int.TryParse(list[2], out ore);
                if (v1 && v2)
                    return new Sarcina(list[0], dif,ore);
            }
            return null;
        }

    }
}
