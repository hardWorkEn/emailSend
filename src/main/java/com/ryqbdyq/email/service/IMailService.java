package com.ryqbdyq.email.service;

public interface IMailService {

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to ,String subject,String content);

    /**
     * 发送html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHTMLMail(String to,String subject,String content);

    /**
     * 发送附件邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    void sendAttachmentMail(String to,String subject,String content,String filePath);
}
