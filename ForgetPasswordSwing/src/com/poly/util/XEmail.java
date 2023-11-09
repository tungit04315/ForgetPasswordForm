/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import javax.swing.JOptionPane;



/**
 *
 * @author tungt
 */
public class XEmail {

    public void sendEmail(String emailToAddress, String textMessage) {
         try {

                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                p.put("mail.smtp.port", 587);

                String user = "tungto753@gmail.com";
                String pas = "jiszmmgmqcgwlvjx";

                Session s = Session.getInstance(p,
                        new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pas);
                    }
                });
                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(user));
                
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));

                msg.setSubject("Thông tin mật khẩu");

                msg.setContent("Mật Khẩu Đăng Nhập Hệ Thống Là : " + textMessage, "text/html; charset=utf-8");
              
                Transport.send(msg);
                //MsgBox.alert(null, "Gửi Thành Công Đến Email " + emailToAddress);
                JOptionPane.showMessageDialog(null, "Gửi Thành Công Đến Email " + emailToAddress);
            } catch (Exception e) {
                //MsgBox.alert(null, String.valueOf(e));
                JOptionPane.showMessageDialog(null, String.valueOf(e));
                System.out.println(e);
            }

    }
}
