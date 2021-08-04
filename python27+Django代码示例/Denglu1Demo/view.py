# -*- coding:utf-8 -*-
import sys
import params
import cipherFunctions
import functions
from django.http import HttpResponse

if sys.getdefaultencoding() != 'utf-8':
    reload(sys)
    sys.setdefaultencoding('utf-8')

def login(request):
    return_result="login failed"
    if request.POST:
        username=request.POST['username']
        encryptedAesKey=request.POST['encryptedAESKey']
        aesKey=cipherFunctions.RSA_Decrypt(encryptedAesKey.encode('utf-8'), params.RSA_PRIVATE_KEY)
        encryptedPassword=request.POST['password']
        password=cipherFunctions.AES_Decrypt(aesKey, encryptedPassword.encode('utf-8'))
        return_result=functions.login(username, password)
    return HttpResponse(return_result)

def register(request):
    return_result="register failed"
    if request.POST:
        username=request.POST['username']
        email=request.POST['email']
        encryptedAesKey=request.POST['encryptedAESKey']
        aesKey=cipherFunctions.RSA_Decrypt(encryptedAesKey, params.RSA_PRIVATE_KEY)
        encryptedPassword=request.POST['password']
        password=cipherFunctions.AES_Decrypt(aesKey, encryptedPassword)
        return_result=functions.register(username, password, email)
    return HttpResponse(return_result)

def resetPassword(request):
    return_result="reset password"
    if request.POST:
        username=request.POST['username']
        encryptedAesKey=request.POST['encryptedAESKey']
        aesKey=cipherFunctions.RSA_Decrypt(encryptedAesKey, params.RSA_PRIVATE_KEY)
        encryptedPassword=request.POST['password']
        password=cipherFunctions.AES_Decrypt(aesKey, encryptedPassword)
        encryptedPassword=request.POST['newPassword']
        newPassword=cipherFunctions.AES_Decrypt(aesKey, encryptedPassword)
        return_result=functions.resetPassword(username, password, newPassword)
    return HttpResponse(return_result)