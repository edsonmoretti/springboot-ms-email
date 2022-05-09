package br.com.edsonmoretti.ms.email.services;

import br.com.edsonmoretti.ms.email.enums.Status;
import br.com.edsonmoretti.ms.email.models.Email;
import br.com.edsonmoretti.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public Email send(Email email) {
        email.setCreatedAt(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);
            email.setSent(true); //Same of setStatus(Status.SENT);
        } catch (MailException e) {
            email.setStatus(Status.ERROR); //Same of setSent(false);
            String error = e.getMessage();
            email.setError(e.getMessage());
        }
        return emailRepository.save(email);
    }
}
