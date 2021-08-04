using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;

namespace Denglu1_demo.Base
{
    public class BaseFunctions
    {
        public static byte[] AesEncrypt(string pToEncrypt, byte[] sKey)
        {
            if (string.IsNullOrEmpty(pToEncrypt))
                return new byte[0];

            byte[] result = null;
            byte[] IVByte = getAesIVByteArray();

            Byte[] toEncryptArray = Encoding.UTF8.GetBytes(pToEncrypt);

            RijndaelManaged rm = new RijndaelManaged
            {
                Key = sKey,
                Mode = CipherMode.CFB,
                Padding = PaddingMode.None,
                IV = IVByte,
                FeedbackSize = 8,
                BlockSize = 128
            };

            ICryptoTransform cTransform = rm.CreateEncryptor();
            result = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

            return result;
        }

        public static string AesDecrypt(byte[] pToDecrypt, byte[] sKey)
        {
            if (pToDecrypt == null || pToDecrypt.Length == 0)
                return null;

            string result = "";
            byte[] IVByte = getAesIVByteArray();

            RijndaelManaged rm = new RijndaelManaged
            {
                Key = sKey,
                Mode = CipherMode.CFB,
                Padding = PaddingMode.None,
                IV = IVByte,
                FeedbackSize = 8
            };

            ICryptoTransform cTransform = rm.CreateDecryptor();
            result = Encoding.UTF8.GetString(cTransform.TransformFinalBlock(pToDecrypt, 0, pToDecrypt.Length));

            return result;
        }

        private static byte[] getAesIVByteArray()
        {
            return HexStringToBytes(BaseParams.AESIV_String);
        }

        public static byte[] HexStringToBytes(string s)
        {
            int l = s.Length / 2;
            byte[] sBytes = new byte[l];
            for (int i = 0; i < l; i++)
            {
                sBytes[i] = Convert.ToByte(s.Substring(i * 2, 2), 16);
            }
            return sBytes;
        }

        public static string RSAEncrypt(string pToEncrypt)
        {
            RSACryptoServiceProvider rsa = new RSACryptoServiceProvider();
            byte[] cipherbytes;
            rsa.FromXmlString(BaseParams.RSAPublicKey2048_xml);

            cipherbytes = rsa.Encrypt(Encoding.UTF8.GetBytes(pToEncrypt), false);
            return Base64Encode(cipherbytes);
        }

        public static string RSADecrypt(string pToDecrypt)
        {
            RSACryptoServiceProvider rsa = new RSACryptoServiceProvider();
            byte[] cipherbytes;
            rsa.FromXmlString(BaseParams.RSAPrivateKey2048_xml);
            cipherbytes = rsa.Decrypt(Base64Decode(pToDecrypt), false);
            return Encoding.UTF8.GetString(cipherbytes);
        }

        public static string Base64Encode(byte[] bytes)
        {
            if (bytes == null || bytes.Length == 0) return "";
            return Convert.ToBase64String(bytes);
        }

        public static byte[] Base64Decode(string text)
        {
            if (string.IsNullOrEmpty(text)) return new byte[0];
            return Convert.FromBase64String(text);
        }

        public static byte[] getAesKey(string encryptedAesKey)
        {
            if (string.IsNullOrEmpty(encryptedAesKey))
            {
                //set a default AES Key here.
                return Base64Decode(BaseParams.DEFAULT_AES_KEY);
            }
            else
            {
                string AesKey = RSADecrypt(encryptedAesKey);
                return Base64Decode(AesKey);
            }
        }
        
    }
}