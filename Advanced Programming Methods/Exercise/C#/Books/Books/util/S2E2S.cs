using Books.domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Books.util
{
    public abstract class S2E2S
    {
        public static String BookToString(Book a)
        {
            return string.Format("{0},{1},{2},{3}",a.Id,a.Author,a.Nume,a.Type);
        }

        public static Book StringToBook(string str)
        {
            String[] list = str.Split(",");
            bool v;
            Tip niv;
            if (list.Length == 4)
            {
                v = Tip.TryParse(list[3], out niv);
                if (v)
                    return new Book(list[0], list[1],list[2],niv);
            }
            return null;
        }

        public static String ClientToString(Client s)
        {
            return string.Format("{0},{1}", s.Id,s.Name);
        }

        public static Client StringToClient(String str)
        {
            String[] list = str.Split(",");
            if (list.Length == 2)
            {
                    return new Client(list[0],list[1]);
            }
            return null;
        }

    }
}