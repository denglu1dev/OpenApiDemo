using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Denglu1_demo.Models
{
    public class LoginByDenglu1Request
    {
        public string username { get; set; }
        public string password { get; set; }
        public string encryptedAesKey { get; set; }
    }

    public class RegisterByDenglu1Request
    {
        public string username { get; set; }
        public string password { get; set; }
        public string encryptedAesKey { get; set; }
        public string email { get; set; }
        public string phone { get; set; }
    }

    public class ResetPasswordByDenglu1Request
    {
        public string token { get; set; }
        public string username { get; set; }
        public string oldPassword { get; set; }
        public string newPassword { get; set; }
        public string encryptedAesKey { get; set; }
    }
}