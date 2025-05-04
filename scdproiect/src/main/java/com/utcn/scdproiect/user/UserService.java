package com.utcn.scdproiect.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User entity){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptedPassword = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(bCryptedPassword);

        return userRepository.save(entity);
    }

    
}
