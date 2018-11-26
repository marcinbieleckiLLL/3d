package com.greencrane.utils.mail;

import com.greencrane.entity.Customer;
import com.greencrane.entity.File;
import com.greencrane.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by Marcin on 25.09.2018.
 */
public class MailUtilsImpl implements MailUtils {

    @Value("${mail.utils.from.email}")
    String fromEmail;
    @Value("${mail.utils.from.password}")
    String fromPassword;
    @Value("${mail.utils.to.email}")
    String toEmail;

    @Autowired
    FileUtils fileUtils;

    MailUtilsFormater mailUtilsFormater;
    MailHelper mailHelper;
    MailHelper.MimeMessageCreator mimeMessageCreator;

    public MailUtilsImpl(MailUtilsFormater mailUtilsFormater) {
        this.mailUtilsFormater = mailUtilsFormater;
    }

    @Override
    public void sendCustomerData(Customer customer, File file) {
        sendEmail(customer, file, false);
    }

    @Override
    public void sendEmailToCustomer(Customer customer) {
        sendEmail(customer, null, true);
    }

    private void sendEmail(Customer customer, File file, boolean getEmailFromCustomer) {
        initHelpers(customer, file, getEmailFromCustomer);
        try {
            sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void initHelpers(Customer customer, File file, boolean getEmailFromCustomer) {
        this.mailHelper = new MailHelper()
                .builder()
                .messageSubject(mailUtilsFormater.formatTitle(customer))
                .messageText(mailUtilsFormater.formatContent(customer))
                .messageAttachmentFileName(fileUtils.createCompletedPath(file))
                .fromEmail(fromEmail)
                .fromPassword(fromPassword)
                .toEmail(getEmailFromCustomer ? customer.getEmail() : toEmail)
                .build();
        this.mimeMessageCreator = mailHelper.new MimeMessageCreator();
    }

    private void sendEmail() throws MessagingException {
        Properties prop = mailHelper.createProperties();
        Session session = mailHelper.createSession(prop);
        Message message = mimeMessageCreator.createMimeMessage(session);
        mailHelper.sendMessage(message);
    }
}

@Builder
@NoArgsConstructor
@AllArgsConstructor
class MailHelper {
    private String fromEmail;
    private String fromPassword;
    private String toEmail;
    private String messageText;
    private String messageSubject;
    private String messageAttachmentFileName;

    public Properties createProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "false");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return prop;
    }

    public Session createSession(Properties prop) {
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });
    }

    public void sendMessage(Message message) throws MessagingException {
        Transport.send(message);
    }

    @NoArgsConstructor
    class MimeMessageCreator {
        private Message message;
        private MimeBodyPart mimeBodyPart;
        private Multipart multipart;

        public Message createMimeMessage(Session session) throws MessagingException {
            this.message = createMessage(session);
            this.mimeBodyPart = createBodyPart();
            this.multipart = createMultipart();
            this.message.setContent(this.multipart);
            return message;
        }

        private Message createMessage(Session session) throws MessagingException {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(messageSubject);
            return message;
        }

        private MimeBodyPart createBodyPart() throws MessagingException {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageText, "text/html");
            if (messageAttachmentFileName != null) {
                mimeBodyPart = createAttachment(mimeBodyPart);
            }
            return mimeBodyPart;
        }

        private MimeBodyPart createAttachment(MimeBodyPart mimeBodyPart) throws MessagingException {
            DataSource source = new FileDataSource(messageAttachmentFileName);
            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName(messageAttachmentFileName);
            return mimeBodyPart;
        }

        private Multipart createMultipart() throws MessagingException {
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            return multipart;
        }
    }
}

