package com.puce.empat_api.service;

import com.puce.empat_api.model.User;
import com.puce.empat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public User registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public boolean sendPasswordResetEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Creación del correo electrónico
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("alan320p@gmail.com"); // Agrega la dirección de correo electrónico del remitente
            message.setTo(user.getEmail());
            message.setSubject("Recuperación de contraseña - Empat-e");
            message.setText("Tu contraseña es: " + user.getPassword());

            // Enviar el correo
            try {
                mailSender.send(message);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
