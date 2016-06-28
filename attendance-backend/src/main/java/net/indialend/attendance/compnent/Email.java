/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.compnent;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author jaspreetsingh
 */
@Component
public class Email {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * This method triggers the email in background thread i.e. the mail is sent
     * asynchronously
     *
     * @param from Email from which mail is triggered
     * @param to Email to whom mail has to be sent
     * @param subject Subject of the email Messages
     * @param msg Message body to be sent along with email
     * @throws InterruptedException
     */
    @Async
    public void sendMail(String from, String to, String subject, String msg) throws InterruptedException {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
