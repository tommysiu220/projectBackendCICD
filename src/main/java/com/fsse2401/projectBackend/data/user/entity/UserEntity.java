package com.fsse2401.projectBackend.data.user.entity;

import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(nullable = false)
    private String email;
    @Column(name = "firebase_uid",nullable = false)
    private String firebaseUid;

    public UserEntity(FirebaseUserData data) {
        this.email = data.getEmail();
        this.firebaseUid = data.getFirebaseUid();
    }

    public UserEntity() {
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
