package com.example.mvvm;

import android.util.Patterns;

public class LoginUser {

    private String strEmailAddress;
    private String strPassword;

    String UsuarioCorrecto="jairamezcuamacias1@gmail.com",PassCorrecto="JAIRNAAT123456";


    public LoginUser(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

    public Boolean setValidaUsu() {
        if((strEmailAddress.equals(UsuarioCorrecto) && strPassword.equals(PassCorrecto))){
            return true;
        }else{
            return false;
        }
    }

}