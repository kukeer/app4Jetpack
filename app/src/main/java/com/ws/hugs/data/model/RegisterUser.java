package com.ws.hugs.data.model;

public class RegisterUser extends User {

    private String code;
    private String VerifyPw;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVerifyPw() {
        return VerifyPw;
    }

    public void setVerifyPw(String verifyPw) {
        VerifyPw = verifyPw;
    }
}
