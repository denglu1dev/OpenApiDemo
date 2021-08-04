const NodeRSA = require('node-rsa')
const CFB8 = require('./CFB8_support.js')

//app公钥 需要在开放平台上传
const serverPublicKeyPem =
    `-----BEGIN PUBLIC KEY-----
    MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4HkatCAO3PNZbq7zMNwuhUNKz
    spo5qJPrbMGPx5lol4fJuNT8pJiBZv3NUBQ3BpMaGiX/rSoS9+0d1k1Fd2xuoj7Z
    C2daAvMV734fDuiMuKEpRW4R4794GFs9d2JtyxV1xVEVJEKcuFsajoquM1siA7+0
    Up6YsGxgGUegMlcvnwIDAQAB
    -----END PUBLIC KEY-----
    `
//app私钥 请保存好私钥，不要泄露
const serverPrivateKeyPem =
    `-----BEGIN PRIVATE KEY-----
    MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALgeRq0IA7c81lur
    vMw3C6FQ0rOymjmok+tswY/HmWiXh8m41PykmIFm/c1QFDcGkxoaJf+tKhL37R3W
    TUV3bG6iPtkLZ1oC8xXvfh8O6Iy4oSlFbhHjv3gYWz13Ym3LFXXFURUkQpy4WxqO
    iq4zWyIDv7RSnpiwbGAZR6AyVy+fAgMBAAECgYBxHTCgh0b5ISg0g4rnD35Wqff4
    rGZxu+68eUFlmuPVzRqARMydNmX3QhX1OBhjQefH5n6n2HP72VNUvF8WqWr0djwR
    IzOVxeBAXdSNSionBhhpZC9G+AepJ317kjpD5i3pWHP+VKAjy0vaqV8oKBMPScvL
    IhzO6Nso9Lte8dbaAQJBAOJijGEqvDZQ5AiD//CpqFA6uxZj8FY0kHVsSZG3atSM
    5knPo2BAVsNW3ueXwtDioMGRrS2u/1lb6wYglK4qZQECQQDQNEABDaeqEf1RyFLL
    AfrBWte52pIfbwkjByUwDbcjgzQbRA6x1yy821mfjJMZglHVan6qjcPqrPpraJZa
    PnSfAkEApvCVJHInKMat4bm1WvtBKyikHfbEnE1W2cBo4BOpq+PbTkLX73qCgLnm
    1lGLUA5PDW8njbCmjHbD8VjFS0noAQJAa7JbWTAjBkn/6wngKbqAZvwCktSIuVHK
    PCA6ED1H+kqn7WKq5zsSnS4pkeo5PPy4L9jCZJ6oNP/9GQokm9GqqQJARd+62W/X
    S8UTwFPEzDBD5CC2vG/Ip1v/NBrlz5IYtSbTUt9IfQvqb7yAhxDoFuc2pulWY6/k
    OnxQ70SuKofFxA==
    -----END PRIVATE KEY-----
    `
const publicKey = new NodeRSA(serverPublicKeyPem, 'pkcs8-public-pem', { encryptionScheme: 'pkcs1' })
const privateKey = new NodeRSA(serverPrivateKeyPem, 'pkcs8-private-pem', { encryptionScheme: 'pkcs1' })

const rsaDecrypt = function (encrypted) {
    return privateKey.decrypt(encrypted, 'utf-8')
}
const aesDecrypt = function (encrypted, key) {
    key = CFB8.CryptoJS.enc.Base64.parse(key)
    return CFB8.CryptoJS.AES.decrypt(encrypted, key, CFB8.vi).toString(CFB8.CryptoJS.enc.Utf8);
}
const aesEncrypt = function (massage, key) {
    key = CFB8.CryptoJS.enc.Base64.parse(key)
    return CFB8.CryptoJS.AES.encrypt(massage, key, CFB8.vi).toString();
}

const rsa_aes_decrypt = function (text, aesKey) {
    return aesDecrypt(text, rsaDecrypt(aesKey));
}

exports.rsa_aes_decrypt = rsa_aes_decrypt;
