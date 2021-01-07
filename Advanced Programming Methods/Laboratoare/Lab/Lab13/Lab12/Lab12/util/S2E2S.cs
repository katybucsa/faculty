using Lab12.domain;
using Lab12.repository;
using System;

namespace Lab12.util
{
    public abstract class S2E2S
    {
        public static String StudentToString(Student s)
        {
            return string.Format("{0}|{1}|{2}|{3}|{4}", s.Id, s.Nume, s.Grupa, s.Email, s.Indrumator);
        }

        public static Student StringToStudent(string str)
        {
            String[] list = str.Split("|");
            bool v1,v2;
            int id, grupa;
            if (list.Length == 5)
            {
                v1 = int.TryParse(list[0], out id);
                v2 = int.TryParse(list[2], out grupa);
                if (v1 && v2)
                    return new Student(id, list[1], grupa, list[3], list[4]);
            }
            return null;
        }

        public static String TemaToString(Tema t)
        {
            return string.Format("{0}|{1}|{2}|{3}", t.Id, t.Descriere, t.Deadline, t.SaptPrimire);
        }

        public static Tema StringToTema(String str)
        {
            String[] list = str.Split("|");
            bool v1, v2, v3;
            int id, deadline, saptprim;
            if (list.Length == 4)
            {
                v1 = int.TryParse(list[0], out id);
                v2 = int.TryParse(list[2], out deadline);
                v3 = int.TryParse(list[3], out saptprim);
                if (v1 && v2 && v3)
                    return new Tema(id, list[1], deadline, saptprim);
            }
            return null;
        }

        public static string NotaToString(Nota n)
        {
            return string.Format("{0}|{1}|{2}|{3}", n.Id.Key.Id, n.Id.Value.Id, n.Grade, n.Date);
        }
    }
}
