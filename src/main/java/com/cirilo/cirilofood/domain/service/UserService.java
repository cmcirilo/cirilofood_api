package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.UserNotFoundException;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = find(userId);

        if (user.passwordDoesNotMatchsWith(currentPassword)) {
            throw new BusinessException("Current Password does not match with actual password.");
        }

        user.setPassword(newPassword);
    }

    public User find(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
