package com.fsse2401.projectBackend.data.user.dto.response;

import com.fsse2401.projectBackend.data.user.domainObject.UserResponseData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;

public class UserResponseDto {
    private Integer uid;
    private String email;
    private String firebaseUid;

    public UserResponseDto() {
    }

    public UserResponseDto(UserResponseData data){
        this.uid = data.getUid();
        this.email = data.getEmail();
        this.firebaseUid = data.getFirebaseUid();
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
