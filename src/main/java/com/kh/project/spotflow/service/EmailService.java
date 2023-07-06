package com.kh.project.spotflow.service;

import com.kh.project.spotflow.config.email.EmailConfig;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService implements EmailServiceInterface{
     EmailConfig mailConfig = new EmailConfig();
     JavaMailSender emailSender = mailConfig.javaMailService();
     private String ePw; // 인증번호
     private static Map<String, String> confirmKey = new HashMap<>(); // 발급된 키 값을 저장하는 객체 생성
     
     // 메일 내용 작성
     @Override
     public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
          System.out.println("보내는 대상 : " + to);
          System.out.println("인증 번호 : " + ePw);
          
          MimeMessage message = emailSender.createMimeMessage();
          
          message.addRecipients(Message.RecipientType.TO, to); // 보내는 대상
          message.setSubject("회원가입 이메일 인증");
          
          String msg = "";
          msg += "<div style='margin:100px;'>";
          msg += "<h1> 안녕하세요</h1>";
          msg += "<h1> spot<span style='color:skyblue;'>F</span>low 입니다.";
          msg += "<br>";
          msg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요</p>";
          msg += "<br>";
          msg += "<div align='center' style='border:1px solid grey; font-family:verdana; padding: 10px; border-radius: 30px;'>";
          msg += "<h3 style='color:skyblue;'>회원가입 인증 코드 입니다.</h3>";
          msg += "<div style='font-size:130%'>";
          msg += "CODE : <strong>";
          msg += ePw + "</strong></div><br>";
          msg += "</div></div>";
          
          message.setText(msg, "utf-8", "html");
          message.setFrom(new InternetAddress("husik927@naver.com", "spotFlow"));
          
          return message;
     }
     
     // 인증키 발급
     @Override
     public String createKey() {
          StringBuffer key = new StringBuffer();
          Random rnd = new Random();
          
          for (int i = 0; i < 8; i++) {
               int index = rnd.nextInt(3);
               switch (index) {
                    case 0 :
                         key.append((char) ((int) (rnd.nextInt(26)) + 97 )); // a~z
                         break;
                    case 1 :
                         key.append((char) ((int) (rnd.nextInt(26)) + 65 )); // A-Z
                         break;
                    case 2 :
                         key.append((rnd.nextInt(10))); // 0~9
                         break;
               }
          }
          return key.toString();
     }
     
     // 메일발송
     // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
     // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
     // 그리고 javaMail 객체를 사용해서 이메일 send!!
     @Override
     public String sendSimpleMessage(String to) throws Exception {
          ePw = createKey();
          // TODO Auto-generated method stub
          MimeMessage message = createMessage(to);
          try {
               setConKey(to, ePw);
               emailSender.send(message);
          } catch (MailException e) {
               e.printStackTrace();
               throw new IllegalArgumentException();
          }
          return ePw;
     }
     
     
     // 인증키 저장
     public void setConKey(String email, String key) {
          confirmKey.put(email, key);
     }
     // 인증키 호출
     public String getConKey(String email) {
          String key = confirmKey.get(email);
          return key;
     }
     // 인증키 삭제
     public void deleteConKey(String email) {
          confirmKey.remove(email);
          System.out.println(email + "의 키 코드값이 삭제 되었습니다.");
     }
}
