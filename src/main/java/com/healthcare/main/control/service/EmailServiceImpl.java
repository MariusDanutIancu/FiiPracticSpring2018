package com.healthcare.main.control.service;

import com.healthcare.main.util.email.EmailUtil;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("freeMarkerConfigBean")
    private Configuration freemarkerConfig;

    @Override
    public void sendEmailText(EmailUtil email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText((String)email.getContent().get("message"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailHttp(EmailUtil email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try
        {
            Template t = freemarkerConfig.getTemplate("basic_freemarker_email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, email.getContent());

            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(html, true);
            javaMailSender.send(mimeMessage);
        }
        catch (TemplateNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
