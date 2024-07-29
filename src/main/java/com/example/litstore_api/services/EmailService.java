package com.example.litstore_api.services;

import com.example.litstore_api.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    public void emailRegistered(User u) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariable("email", u.getEmail());


        String htmlContent = templateEngine.process("user/registered", context);

        helper.setTo(u.getEmail());
        helper.setSubject("Welcome to LitStore");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
