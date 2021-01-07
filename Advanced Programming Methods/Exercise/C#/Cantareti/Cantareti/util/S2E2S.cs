using Cantareti.domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.util
{
    public abstract class S2E2S
    {
        public static String ArtistToString(Artist a)
        {
            return string.Format("{0},{1},{2}",a.Id,a.Nume,a.Varsta);
        }

        public static Artist StringToArtist(string str)
        {
            String[] list = str.Split(",");
            bool v;
            double vrs;
            if (list.Length == 3)
            {
                v = double.TryParse(list[2], out vrs);
                if (v)
                    return new Artist(list[0], list[1],vrs);
            }
            return null;
        }

        /*public static String CantaretToString(Cantaret s)
        {
            return string.Format("{0},{1}", s.Id,s.Gen);
        }

        public static Client StringToClient(String str)
        {
            String[] list = str.Split(",");
            if (list.Length == 2)
            {
                    return new Client(list[0],list[1]);
            }
            return null;
        }*/

    }
}