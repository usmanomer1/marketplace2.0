package com.sftwrlabs.ubc_marketplace.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String name, String verificationUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        String html = new String(new ClassPathResource("templates/email/verification-email.html").getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        html = html.replace("{{name}}", name).replace("{{verificationUrl}}", verificationUrl);

        helper.setTo(to);
        helper.setSubject("Email Verification - UBC Marketplace");
        helper.setText(html, true);

        mailSender.send(message);
    }
}

