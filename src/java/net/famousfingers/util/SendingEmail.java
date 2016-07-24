package net.famousfingers.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail
{
  public static Boolean sendMail(String email, String message, String subject)
  {
    String to = email;
    String from = "support@famousfingers.net";
    String pass = "support@famous";
    
    String host = "mail.famousfingers.net";
    
    Properties props = new Properties();
    
    props.put("mail.smtp.host", host);
    
    props.put("mail.debug", "true");
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "8025");
    props.put("mail.smtp.auth", "true");
    Session session = Session.getInstance(props, null);
    try
    {
      MimeMessage msg = new MimeMessage(session);
      
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address1 = { new InternetAddress(to) };
      msg.setRecipients(Message.RecipientType.TO, address1);
      msg.setSubject(subject);
      msg.setSentDate(new Date());
      msg.setText(message);
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(msg, msg.getAllRecipients());
      transport.close();
    }
    catch (MessagingException e)
    {
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }
}
