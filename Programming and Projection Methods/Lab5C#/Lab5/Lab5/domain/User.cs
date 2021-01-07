using Lab5.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.domain
{
    public class User:IHasID<string>
    {
        public string Id { get; set; }
        public string Password { get; set; }
        public string Name { get; set; }

        public User(string username,string password,string name)
        {
            this.Id = username;
            this.Password = password;
            this.Name = name;
        }
    }
}
