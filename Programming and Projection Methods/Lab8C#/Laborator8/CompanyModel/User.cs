using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyModel
{
    [Serializable]
    public class User:IHasID<string>
    {
        public string Id { get; set; }
        public string Password { get; set; }
       

        public User(string username,string password)
        {
            this.Id = username;
            this.Password = password;
        }
    }
}
