package com.teddybear6.toegeungil.authjwt.user.query.domain.sevice;

import com.teddybear6.toegeungil.authjwt.user.command.domain.model.UserEntity;
import com.teddybear6.toegeungil.authjwt.user.query.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserViewService {
    private final UserRepository userRepository;

    public UserViewService(UserRepository userRepository) {this.userRepository = userRepository;}

    public UserEntity findUserEmail(String userId){
        UserEntity userEntity = userRepository.findUserEmail(userId);
        return  userEntity;
    }
}
