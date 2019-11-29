package com.tp.service.impl;

import com.tp.service.SendSimpleMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendSimpleMailImpl implements SendSimpleMail {

    @Autowired
    JavaMailSender js;

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Override
    public void SendSimpleMail( String to, String subject, String content) {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(MAIL_SENDER);
        simpleMailMessage.setTo(to+"@qq.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
      try{
          js.send(simpleMailMessage);
      }catch(Exception e){
          System.out.println("forbiden");
      }
    }
}
