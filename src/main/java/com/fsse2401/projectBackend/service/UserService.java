package com.fsse2401.projectBackend.service;

import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData);
}
