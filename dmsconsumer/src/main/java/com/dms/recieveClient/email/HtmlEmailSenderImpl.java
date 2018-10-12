package com.dms.recieveClient.email;

import com.dms.recieveClient.model.EmailLog;
import com.dms.recieveClient.repository.EmailLogRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.io.*;
import java.util.Map;
import java.util.Properties;

public class HtmlEmailSenderImpl implements HtmlEmailSender {

    @Inject
    private EmailLogRepository emailLogRepository;

    private static final String CHARSET = "UTF-8";
    private Configuration freemarkerMailConfiguration;
    private JavaMailSender mailSender;
    private EmailConfig emailConfig;

    private final static String TEMPLATE_PATH = "templates/email";

    @Override
    @Async
    public void send(Email email, EmailLog emailLog) throws Exception {

        final String eml = email.getFrom();
        final String from = email.getFromEmail();
        final String to = email.getTo();
        final String subject = email.getSubject();
        final Map<String, String> templateTokens = email.getTemplateTokens();
        if (isValidEmailAddress(to)) {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

                    JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;
                    // if email configuration is present in Database, use the
                    // same
                    if (emailConfig != null) {
                        impl.setProtocol(emailConfig.getProtocol());
                        impl.setHost(emailConfig.getHost());
                        impl.setPort(Integer.parseInt(emailConfig.getPort()));
                        impl.setUsername(emailConfig.getUsername());
                        impl.setPassword(emailConfig.getPassword());

                        Properties prop = new Properties();
                        prop.put("mail.smtp.auth", emailConfig.isSmtpAuth());
                        prop.put("mail.smtp.starttls.enable", emailConfig.isStarttls());
                        impl.setJavaMailProperties(prop);
                    }

                    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    InternetAddress inetAddress = new InternetAddress();

                    inetAddress.setPersonal(eml);
                    inetAddress.setAddress(from);

                    mimeMessage.setFrom(inetAddress);
                    mimeMessage.setSubject(subject);

                    Multipart mp = new MimeMultipart("alternative");

                    // Create a "text" Multipart message
                    BodyPart textPart = new MimeBodyPart();
                    freemarkerMailConfiguration.setClassForTemplateLoading(HtmlEmailSenderImpl.class, "/");
                    Template textTemplate = freemarkerMailConfiguration.getTemplate(
                            new StringBuilder(TEMPLATE_PATH).append("").append("/").append(email.getTemplateName()).toString());
                    final StringWriter textWriter = new StringWriter();
                    try {
                        textTemplate.process(templateTokens, textWriter);
                    } catch (TemplateException e) {
                        throw new MailPreparationException("Can't generate text mail", e);
                    }
                    String filename = email.getFileName();
                    textPart.setDataHandler(new DataHandler(new DataSource() {
                        public InputStream getInputStream() throws IOException {
                            // return new StringBufferInputStream(textWriter
                            // .toString());
                            return new ByteArrayInputStream(textWriter.toString().getBytes(CHARSET));
                        }

                        public OutputStream getOutputStream() throws IOException {
                            throw new IOException("Read-only data");
                        }

                        public String getName() {
                            return "main";
                        }

                        public String getContentType() {
                            if(filename != null && !filename.equals("")) {
                                return "text/html";
                            } else {
                                return "text/plain";
                            }
                        }
                    }));
                    mp.addBodyPart(textPart);

                    // Create a "HTML" Multipart message
                    Multipart htmlContent = new MimeMultipart("related");
                    BodyPart htmlPage = new MimeBodyPart();
                    freemarkerMailConfiguration.setClassForTemplateLoading(HtmlEmailSenderImpl.class, "/");
                    freemarkerMailConfiguration.setLogTemplateExceptions(true);
                    Template htmlTemplate = freemarkerMailConfiguration.getTemplate(
                            new StringBuilder(TEMPLATE_PATH).append("").append("/").append(email.getTemplateName()).toString());
                    final StringWriter htmlWriter = new StringWriter();
                    try {
                        htmlTemplate.process(templateTokens, htmlWriter);
                    } catch (TemplateException e) {
                        throw new MailPreparationException("Can't generate HTML mail", e);
                    }
                    htmlPage.setDataHandler(new DataHandler(new DataSource() {
                        public InputStream getInputStream() throws IOException {
                            // return new StringBufferInputStream(htmlWriter
                            // .toString());
                            return new ByteArrayInputStream(textWriter.toString().getBytes(CHARSET));
                        }

                        public OutputStream getOutputStream() throws IOException {
                            throw new IOException("Read-only data");
                        }

                        public String getContentType() {
                            return "text/html";
                        }

                        public String getName() {
                            return "main";
                        }
                    }));

                    if (filename != null && !filename.equals("")) {
                        File file=new File(filename);
                        if(file.exists()) {
                            DataSource source = new FileDataSource(filename);
                            htmlPage.setDataHandler(new DataHandler(source));
                            htmlPage.setFileName(file.getName());
                            mp.addBodyPart(textPart);
                            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                            messageHelper.addAttachment(file.getName(),file);
                        }
                    }
                    htmlContent.addBodyPart(htmlPage);
                    BodyPart htmlPart = new MimeBodyPart();
                    htmlPart.setContent(htmlContent);
                    mp.addBodyPart(htmlPart);
                    mimeMessage.setContent(mp);

                    // if(attachment!=null) {
                    /*MimeMessageHelper messageHelper = new  MimeMessageHelper(mimeMessage, true);
                    messageHelper.addAttachment(attachmentFileName,attachment);*/
                    // }
                }
            };
            mailSender.send(preparator);
            emailLog.setSent(true);
            emailLogRepository.save(emailLog);
        }
    }

    public void sendSimpleEmail(SimpleMailMessage simpleMailMessage){
        mailSender.send(simpleMailMessage);
    }

    @Override
    @Async
    public void prepareAndSend(Email email, EmailLog emailLog) throws MessagingException, IOException {

        final String eml = email.getFrom();
        final String from = email.getFromEmail();
        final String to = email.getTo();
        final String subject = email.getSubject();
        final Map<String, String> templateTokens = email.getTemplateTokens();
        JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;

        if (emailConfig != null) {
            impl.setProtocol(emailConfig.getProtocol());
            impl.setHost(emailConfig.getHost());
            impl.setPort(Integer.parseInt(emailConfig.getPort()));
            impl.setUsername(emailConfig.getUsername());
            impl.setPassword(emailConfig.getPassword());

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", emailConfig.isSmtpAuth());
            prop.put("mail.smtp.starttls.enable", emailConfig.isStarttls());
            impl.setJavaMailProperties(prop);
        }

        Multipart htmlContent = new MimeMultipart("alternative");
        BodyPart htmlPage = new MimeBodyPart();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setTo(new InternetAddress(to));

        InternetAddress inetAddress = new InternetAddress();

        inetAddress.setPersonal(eml);
        inetAddress.setAddress(from);
        String filename = email.getFileName();
        messageHelper.setFrom(inetAddress);
        messageHelper.setSubject(subject);
   //     messageHelper.setText(messageContent);

        freemarkerMailConfiguration.setClassForTemplateLoading(HtmlEmailSenderImpl.class, "/");
        freemarkerMailConfiguration.setLogTemplateExceptions(true);
        Template htmlTemplate = freemarkerMailConfiguration.getTemplate(
                new StringBuilder(TEMPLATE_PATH).append("").append("/").append(email.getTemplateName()).toString());
        final StringWriter htmlWriter = new StringWriter();
        try {
            htmlTemplate.process(templateTokens, htmlWriter);
        } catch (TemplateException e) {
            throw new MailPreparationException("Can't generate HTML mail", e);
        }


        htmlPage.setDataHandler(new DataHandler(new DataSource() {
            public InputStream getInputStream() throws IOException {
                // return new StringBufferInputStream(htmlWriter
                // .toString());
                return new ByteArrayInputStream(htmlWriter.toString().getBytes(CHARSET));
            }

            public OutputStream getOutputStream() throws IOException {
                throw new IOException("Read-only data");
            }

            public String getContentType() {
                return "text/html";
            }

            public String getName() {
                return "main";
            }
        }));

        Multipart multipart = new MimeMultipart();
        
        if (StringUtils.isNotBlank(filename)) {
        	File file = new File(filename);
            DataSource source = new FileDataSource(file.getAbsoluteFile());
        	
            MimeBodyPart messageBodyPart = new MimeBodyPart();
        	messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(file.getName());
            
            multipart.addBodyPart(messageBodyPart);
        }

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent( htmlWriter.toString(), "text/html; charset=utf-8" );
        multipart.addBodyPart(htmlPart);

        mimeMessage.setContent(multipart);
        mailSender.send(mimeMessage);
    }

    public Configuration getFreemarkerMailConfiguration() {
        return freemarkerMailConfiguration;
    }

    public void setFreemarkerMailConfiguration(Configuration freemarkerMailConfiguration) {
        this.freemarkerMailConfiguration = freemarkerMailConfiguration;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}