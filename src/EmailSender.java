import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailSender {
    private static String title = "\u5458\u5DE5\u5DE5\u8D44\u7BA1\u7406\u7CFB\u7EDF\u6CE8\u518C";//员工工资管理系统注册
    private static final String USER = "hovchen@qq.com"; // 发件人
    private static final String PASSWORD = "sxhrpofogfrjbhgc"; // 授权码

    /**
     * @param to 收件人邮箱
     */
    /* 发送验证信息的邮件 */
    public static int sendMail(String to){
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            //发件邮箱host
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.port", "587");// 端口号
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.ssl.enable", "false");
            //发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            String text = "\u6B22\u8FCE\u6CE8\u518C\u5458\u5DE5\u5DE5\u8D44\u7BA1\u7406\u7CFB\u7EDF\uFF0C\u60A8\u7684\u9A8C\u8BC1\u7801\u4E3A\uFF1A" + code + "\uFF0C\u9A8C\u8BC1\u7801\u6709\u6548\u671F\u4E3A\u4E94\u5206\u949F\uFF01";
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }
    // 做测试用
    public static void main(String[] args) throws Exception {
        EmailSender.sendMail("510478741@qq.com");//填写接收邮箱
        System.out.println("\u53D1\u9001\u6210\u529F");//发送成功
    }
}
