using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Laborator3
{
    public class Service
    {
        private SqlConnection connection1 = new SqlConnection("Data Source=DESKTOP-094779K\\SQLEXPRESS;Initial Catalog=Primarie;Integrated Security=True");
        private SqlConnection connection2 = new SqlConnection("Data Source=DESKTOP-094779K\\SQLEXPRESS;Initial Catalog=Primarie;Integrated Security=True");
        int count1 = 10, count2 = 10;

        public void UpdateTables1()
        {
            while (count1 != 0)
            {
                try
                {
                    Console.WriteLine("1");
                    SqlCommand cmd = new SqlCommand("deadlockScenario1", connection1);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("@name", "Daniel1");
                    cmd.Parameters.AddWithValue("@title", "Proiect autostrazi1");
                    connection1.Open();
                    cmd.ExecuteNonQuery();
                    connection1.Close();
                    count1 = 0;
                    Console.WriteLine("Tranzactia 1 s-a incheiat cu succes!");
                }
                catch (Exception e)
                {
                    Console.WriteLine("Tranzactia 1 a esuat\n" + e.Message);
                    connection1.Close();
                    count1--;
                }
            }
        }

        public void UpdateTables2()
        {
            while (count2 != 0)
            {
                try
                {
                    Console.WriteLine("2");
                    SqlCommand cmd = new SqlCommand("deadlockScenario2", connection2);
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("@title", "Proiect autostrazi2");
                    cmd.Parameters.AddWithValue("@name", "Daniel2");
                    connection2.Open();
                    cmd.ExecuteNonQuery();
                    connection2.Close();
                    count2 = 0;
                    Console.WriteLine("Tranzactia 2 s-a incheiat cu succes!");
                }
                catch (Exception e)
                {
                    Console.WriteLine("Tranzactia 2 a esuat\n" + e.Message);
                    connection2.Close();
                    count2--;
                }
            }
        }
    }
}
