package com.covoiturage.service;

import com.covoiturage.entity.User;
import com.covoiturage.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository =userRepository;
    }
    @Override
    public User inscrire(User u) {
        if(userRepository.findByEmail(u.getEmail()).isPresent()){
            throw new RuntimeException("Email already used");
        }

        return userRepository.save(u);

    }

    @Override
    public Optional<User> searchByMail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> searchById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User update(User u) {
        userRepository.findById(u.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        return userRepository.save(u);
    }

    @Override
    @Transactional
    public void delete(User u) {
        userRepository.findById(u.getUserId())
            .orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(u);
    }
}
