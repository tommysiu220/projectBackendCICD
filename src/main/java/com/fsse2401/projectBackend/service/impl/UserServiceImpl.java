package com.fsse2401.projectBackend.service.impl;

import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import com.fsse2401.projectBackend.repository.UserRepository;
import com.fsse2401.projectBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
//        Optional<UserEntity> userEntityOptional = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());
//        if(userEntityOptional.isEmpty()){
//            UserEntity userEntity = new UserEntity((firebaseUserData));
//            return userRepository.save(userEntity);
//        } else {
//            return userEntityOptional.get();
//        }

        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).orElseGet(
                () -> userRepository.save(new UserEntity(firebaseUserData))
        );
    }
}
