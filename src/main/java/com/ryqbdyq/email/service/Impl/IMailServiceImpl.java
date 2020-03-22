package com.ryqbdyq.email.service.Impl;

import com.ryqbdyq.email.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class IMailServiceImpl implements IMailService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
     */

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 配置文件中我的qq邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送者
        simpleMailMessage.setFrom(from);
        //邮件接收者
        simpleMailMessage.setTo(to);
        //邮件主题
        simpleMailMessage.setSubject(subject);
        //邮件内容
        simpleMailMessage.setText(content);
        //发送邮件
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHTMLMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            //邮件发送者
            mimeMessageHelper.setFrom(from);
            //邮件接受者
            mimeMessageHelper.setTo(to);
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(content,true);
            //发送
            mailSender.send(mimeMessage);
            logger.info("邮件发送成功！");
        }catch (MessagingException e){
            logger.error("发送邮件时发生异常",e);
        }
    }

    /**
     * 发送附件邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName,fileSystemResource);
            mailSender.send(mimeMessage);
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常",e);
        }
    }
}
