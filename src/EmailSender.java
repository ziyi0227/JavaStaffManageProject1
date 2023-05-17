/*
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailSender {
    private final String host;
    private final String username;
    private final String password;
    private final String fromAddress;

    public EmailSender(String host, String username, String password, String fromAddress) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.fromAddress = fromAddress;
    }

    public void sendVerificationCode(String code, String toAddress) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");

        // 添加SSL配置
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("验证码");
            message.setText("您的验证码是：" + code);

            Transport.send(message);

            System.out.println("验证码已发送到邮箱：" + toAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomCode() {
        // 生成随机四位验证码
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }

    public static void main(String[] args) {
        String host = "183.47.101.192";
        String username = "510478741";
        String password = "sxhrpofogfrjbhgc";
        String fromAddress = "51047841@qq.com";
        String toAddress = "51047841@qq.com";

        EmailSender emailSender = new EmailSender(host, username, password, fromAddress);

        String verificationCode = generateRandomCode();
        emailSender.sendVerificationCode(verificationCode, toAddress);
    }
}
*/
