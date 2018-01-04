package Test;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTool {

	private String ownEmailAccount = "root@liu.com";

	private String ownEmailPassword = "ditnov07";

	private String myEmailSMTPHost = "9.119.108.62";

	private String receiveMailAccount = "jliujidl@cn.ibm.com";

	private String protocol = "smtp";

	private Properties properties;

	public Message createMail(Session session) throws Exception, MessagingException {

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(ownEmailAccount, "admin", "utf-8"));

		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount, "liu", "utf-8"));

		message.setSubject("数据错误通知");

		message.setContent("数据错误！请立即处理！", "text/html;charset=utf-8");

		message.setSentDate(new Date());

		message.saveChanges();

		return message;

	}

	public void sendMail() {

		properties = new Properties();

		properties.setProperty("mail.transport.protocol", protocol);

		properties.setProperty("mail.smtp.host", myEmailSMTPHost);

		properties.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties);

		session.setDebug(true);

		Message message;
		try {
			message = createMail(session);

			Transport transport = session.getTransport();

			transport.connect(ownEmailAccount, ownEmailPassword);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
