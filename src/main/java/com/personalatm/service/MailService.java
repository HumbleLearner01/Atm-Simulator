package com.personalatm.service;

import com.personalatm.helper.exception.AtmException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private MailContentBuilder contentBuilder;

    public void sendEmail(Email email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("learn.mike.helloworld@gmail.com");
            messageHelper.setTo(email.getRecipient());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(contentBuilder.build(email.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("email sent to '" + email.getRecipient() + "' successfully");
        } catch (MailException e) {
            throw new AtmException("error occurred while sending email to '" + email.getRecipient() + "'");
        }
    }
}