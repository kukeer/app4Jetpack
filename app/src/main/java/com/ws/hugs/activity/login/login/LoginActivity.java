package com.ws.hugs.activity.login.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.os.Bundle;

import com.ws.hugs.R;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    FingerprintManagerCompat compat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        compat = FingerprintManagerCompat.from(this);
//        compat.authenticate(
//                null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
//                    @Override
//                    public void onAuthenticationError(int errMsgId, CharSequence errString) {
//                        super.onAuthenticationError(errMsgId, errString);
//                        Log.i(TAG,"onAuthenticationError");
//                    }
//
//                    @Override
//                    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
//                        super.onAuthenticationHelp(helpMsgId, helpString);
//                        Log.i(TAG,"onAuthenticationHelp");
//                    }
//
//                    @Override
//                    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
//                        super.onAuthenticationSucceeded(result);
//                        Log.i(TAG,"onAuthenticationSucceeded");
//                    }
//
//                    @Override
//                    public void onAuthenticationFailed() {
//                        super.onAuthenticationFailed();
//                        Log.i(TAG,"onAuthenticationFailed");
//                    }
//                },null
//        );

    }

    @Override
    protected void onDestroy() {
//        setResult();
        super.onDestroy();
    }
}
