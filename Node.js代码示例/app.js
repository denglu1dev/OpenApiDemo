/**
 * 登录易开放平台接入demo(nodejs)
 * 
 * @copyright denglu1 tech
 */

 /**
 * 第三方服务器状态码
 * 0        成功
 * -700     第三方服务器返回参数错误
 * -800     用户名或者密码错误
 * -801     用户名已经存在
 * -900     服务器错误
 * -901     网络错误
 */
const express = require('express');
const session = require('express-session');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const RSA = require('./rsa.js');
const uuid = require('uuid/v4');
const app = express();
const testUserArray = { 'testUser': 'testPass' };
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(session({
    secret: 'your_secret',
    cookie: { maxAge: 80000 },
    saveUninitialized: true,
    resave: true,
    genid: function () {
        return uuid();
    }
}));
let checkUser = function (userName, password) {
    if (testUserArray[userName] === password) {
        return true;
    }
    return false;
}
let checkModifyPass = function (userName, oldPassword, newPassword) {

}
/**
 * 在开放平台记录的登录地址
 * @param string userName           用户名    
 * @param string password           密码
 * @param string encryptedAESKey    经过RSA加密的AES密钥
 */
app.post('/open/api/login', (req, res) => {
    let userName = req.body.sUserName;
    let password = RSA.rsa_aes_decrypt(req.body.sPassword, req.body.sEncryptedAESKey);
    /**
     * @todo 检查用户名密码
     * */
    //成功
    if (checkUser(userName, password)) {
        //返回登录态
        req.session.userName = req.body.sUserName;
        req.session.userId = 'userId';
        // ...
        return res.send({ iCode: 0, sToken: req.sessionID });
    } else {
        return res.send({ iCode: -800, retMsg: '账号名或密码错误' });
    }
});
/**
 * 在开放平台记录的注册地址
 * @param string userName           用户名    
 * @param string password           密码
 * @param string encryptedAESKey    经过RSA加密的AES密钥
 */
app.post('/open/api/register', (req, res) => {
    let userName = req.body.sUserName;
    let password = RSA.rsa_aes_decrypt(req.body.sPassword, req.body.sEncryptedAESKey);
    /**
    * @todo 检查用户名密码
    * */
    //成功
    if (checkUser(userName, password)) {
        req.session.userName = req.body.sUserName;
        req.session.userId = 'userId';
        // ...
        //返回登录态
        return res.send({ iCode: 0, sToken: req.sessionID });
    } else {
        return res.send({ iCode: -801, retMsg: '用户名已存在' });
    }
});
/**
 * 在开放平台记录的修改密码地址
 * @param string userName           用户名    
 * @param string oldPassword        旧密码
 * @param string newPassword        新密码
 * @param string encryptedAESKey    经过RSA加密的AES密钥
 */
app.post('/open/api/modifyPass', (req, res) => {
    let userName = req.body.sUserName;
    let oldPassword = RSA.rsa_aes_decrypt(req.body.sOldPassword, req.body.sEncryptedAESKey);
    let newPassword = RSA.rsa_aes_decrypt(req.body.sNewPassword, req.body.sEncryptedAESKey);
    /**
     * @todo 检查用户名密码
     * */
    //成功
    if (checkModifyPass(userName, oldPassword, newPassword)) {
        req.session.userName = req.body.sUserName;
        req.session.userId = 'userId';
        // ...
        //返回登录态
        return res.send({ iCode: 0, sToken: req.sessionID });
    } else {
        return res.send({ iCode: -800, retMsg: '旧密码不正确' });
    }
});

/**
 * 在完成扫码提交后，指定浏览器重定向的页面，需要对前端传过来的sessionId做登录态检查
 * @param string token          账号密码校验成功后的会话ID
 */
app.get('/index', (req, res) => {
    let token = req.query.token;
    //转移会话到当前的session
    req.sessionStore.get(token, (err, session) => {
        if (err) {
            // error handle
            console.log(err)
        }
        if (session) {
            //将之前由服务器创建的会话状态转移到当前会话
            req.session.userName = session.userName;
            //删除之前的会话
            req.sessionStore.destroy(token, err => {
                if (err) {
                    // error handle
                    console.log(err)
                }
            });
            //获取会话成功
            res.send('登录成功，当前用户名是: ' + req.session.userName);
        } else {
            //如果token无效 判断是否存在会话
            if (req.session.userName) {
                res.send('登录成功，当前用户名是: ' + req.session.userName);
            } else {
                // 去到登录页面
                res.send('invald_token');
            }
        }
    });
});

app.listen(9090);