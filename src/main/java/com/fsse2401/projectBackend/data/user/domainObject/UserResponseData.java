package com.fsse2401.projectBackend.data.user.domainObject;

import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import jakarta.persistence.Column;

public class UserResponseData{
    private Integer uid;
    private String email;
    private String firebaseUid;

    public UserResponseData() {
    }

    public UserResponseData(UserEntity entity){
        this.uid = entity.getUid();
        this.email = entity.getEmail();
        this.firebaseUid = entity.getFirebaseUid();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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


