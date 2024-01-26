package com.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.enums.StatusEmail;
import com.email.models.EmailModel;
import com.email.repositories.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
    final EmailRepository emailRepository;

    final JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    EmailService(JavaMailSender emailSender, EmailRepository emailRepository) {
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;
    }

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            System.out.println("Email enviado com sucesso!");
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e) {
            System.out.println("deu ruim");
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            System.out.println("Email salvo no banco de dados");
            return emailRepository.save(emailModel);
        }
    }

}
