package com.notificationmail.mail.service.impl;

import com.notificationmail.mail.model.entity.Confirmation;
import com.notificationmail.mail.model.entity.User;
import com.notificationmail.mail.repository.ConfirmationRepository;
import com.notificationmail.mail.repository.UserRepository;
import com.notificationmail.mail.service.EmailService;
import com.notificationmail.mail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        // TODO: send email to user with token
//        emailService.sendSimpleMailMessage(user.getName(),user.getEmail(),confirmation.getToken());
//        emailService.sendMimeMessageWithAttachment(user.getName(),user.getEmail(),confirmation.getToken());
//        emailService.sendMimeMessageWithEmbeddedImages(user.getName(),user.getEmail(),confirmation.getToken());
        emailService.sendHtmlEmail(user.getName(),user.getEmail(),confirmation.getToken());

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
//        confirmationRepository.delete(confirmation);

        return Boolean.TRUE;
    }
}
