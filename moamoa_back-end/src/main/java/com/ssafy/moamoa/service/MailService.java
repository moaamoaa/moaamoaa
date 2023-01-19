package com.ssafy.moamoa.service;

import com.ssafy.moamoa.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service()
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public int makeRandomNum() {
        Random random = new Random();
        return random.nextInt(888888) + 111111;
    }

    public void sendEmail(Mail mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        helper.setFrom(mail.getMailFrom());
        helper.setTo(mail.getMailTo());
        helper.setSubject(mail.getMailSubject());
        helper.setText(mail.getMailContent(), true);

        mailSender.send(mimeMessage);
    }

    // 회원 가입 시 메일 인증
    public String joinEmail(String email) throws MessagingException {
        int checkNum = makeRandomNum();
        Mail mail = new Mail();
        String setFrom = "moamoaofficial0@gmail.com";
        String toMail = email;
        String subject = "[MoaMoa] 회원 가입 인증 이메일 입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>"
                + "인증 번호는 " + checkNum + "입니다." + "<br>" + "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        mail.setMailFrom(setFrom);
        mail.setMailTo(email);
        mail.setMailSubject(subject);
        mail.setMailContent(content);
        sendEmail(mail);
        return Integer.toString(checkNum);
    }
}

