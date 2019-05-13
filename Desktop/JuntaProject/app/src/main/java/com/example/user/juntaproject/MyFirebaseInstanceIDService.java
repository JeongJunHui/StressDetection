package com.example.user.juntaproject;

import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by user on 2018-08-02.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        // 토큰 자동 생성
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // Refreshed token: e9szoLeM1JQ :
        // APA91bG18c5K-3NPtq6ZL1Lo_8IwyCh6RTh_qUnExnlm_88ev6peOTC6NVP39Nte9szoLeM1JQ:APA91bG18c5K-3NPtq6ZL1Lo_8IwyCh6RTh_qUnExnlm_88ev6peOTC6NVP39NtjlOHZW46FouPsGLMXG9xaqcirtflsvNxZ6fIs2fh6DQLWACepFqe8y3EHKOG_akHS1znnldNVEC0ZuewIekK1dDu0Eb9Kv8S6fg

        Log.d("Refreshed token: " + refreshedToken, refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // 생성한 토큰을 서버로 보내 저장하기 위한 코드
        sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String refreshedToken){
    }
}
