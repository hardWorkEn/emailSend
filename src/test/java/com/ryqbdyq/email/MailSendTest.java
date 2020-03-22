package com.ryqbdyq.email;

import com.ryqbdyq.email.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailSendTest {

    @Autowired
    private IMailService iMailService;

    @Test
    public void sendMail(){
        iMailService.sendSimpleMail("ryqbdyq@aliyun.com","主题：这是测试邮件","内容：第一次测试");
    }

    @Test
    public void sendMailForHTML(){
        iMailService.sendHTMLMail("ryqbdyq@aliyun.com","主题：这是测试HTML邮件","<h1>内容：第一次测试html邮件</h1>");
    }

    @Test
    public void sendMailForAtt(){
        iMailService.sendAttachmentMail("ryqbdyq@aliyun.com","主题：这是测试附件邮件","<h1>内容：第一次测试html邮件</h1>",
                "D:\\1.txt");
    }
}
