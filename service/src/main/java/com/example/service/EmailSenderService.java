package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.util.Properties;

@Component
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,
                          String subject,
                          String body)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);

        System.err.println("Sent successfully");
    }

    public static void checkMail(String username, String password) {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] msgs = inbox.getMessages();

            for (Message msg : msgs) {
                try {
                    Address[] in = msg.getFrom();
                    for (Address address : in) {
                        //if(address.toString().equals("e.kostandini@forward.al"))
                        System.err.println("FROM:" + address.toString());
                    }
                    Multipart mp = (Multipart) msg.getContent();
                    BodyPart bp = mp.getBodyPart(0);
                   /* System.out.println("SENT DATE:" + msg.getSentDate());
                    System.out.println("SUBJECT:" + msg.getSubject());
                    System.out.println("CONTENT:" + bp.getContent());*/
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            inbox.close(false);
            store.close();

        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

}
