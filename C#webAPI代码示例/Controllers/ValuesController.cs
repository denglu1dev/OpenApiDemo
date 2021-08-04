using Denglu1_demo.Base;
using Denglu1_demo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Http;

namespace Denglu1_demo.Controllers
{
    [RoutePrefix("api/values")]
    public class ValuesController : ApiController
    {
        //POST api/values/denglu1Login
        [HttpPost]
        [Route("denglu1Login")]
        public IHttpActionResult denglu1Login([FromBody]LoginByDenglu1Request request)
        {
            string username = request.username;

            byte[] aeskey = BaseFunctions.getAesKey(request.encryptedAesKey);
            byte[] passwordBytes = BaseFunctions.Base64Decode(request.password);
            string password = BaseFunctions.AesDecrypt(passwordBytes, aeskey);

            //Do login validation here.
            string result = "Login success!";

            return Json<dynamic>(result);
        }

        //POST api/values/denglu1Register
        [HttpPost]
        [Route("denglu1Register")]
        public IHttpActionResult denglu1Register([FromBody]RegisterByDenglu1Request request)
        {
            string username = request.username;

            byte[] aeskey = BaseFunctions.getAesKey(request.encryptedAesKey);
            byte[] passwordBytes = BaseFunctions.Base64Decode(request.password);
            string password = BaseFunctions.AesDecrypt(passwordBytes, aeskey);

            //Do register validation here.
            string result = "Register success!";

            return Json<dynamic>(result);
        }

        //POST api/values/denglu1ResetPassword
        [HttpPost]
        [Route("denglu1ResetPassword")]
        public IHttpActionResult denglu1ResetPassword([FromBody]ResetPasswordByDenglu1Request request)
        {
            string username = request.username;

            byte[] aeskey = BaseFunctions.getAesKey(request.encryptedAesKey);
            string oldPassword = BaseFunctions.AesDecrypt(BaseFunctions.Base64Decode(request.oldPassword), aeskey);
            string newPassword = BaseFunctions.AesDecrypt(BaseFunctions.Base64Decode(request.newPassword), aeskey);

            //Do reset password validation here.
            string result = "Reset password success!";

            return Json<dynamic>(result);
        }
    }
}
