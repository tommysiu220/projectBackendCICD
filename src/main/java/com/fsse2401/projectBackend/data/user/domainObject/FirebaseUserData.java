package com.fsse2401.projectBackend.data.user.domainObject;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class FirebaseUserData {
    private String email;
    private String firebaseUid;

    public FirebaseUserData(JwtAuthenticationToken jwtToken){
        this.firebaseUid = (String)jwtToken.getTokenAttributes().get("user_id");
        this.email = (String) jwtToken.getTokenAttributes().get("email");
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
