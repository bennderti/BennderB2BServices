/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

/**
 *
 * @author dyanez
 */
import cl.bennder.bennderservices.model.EmailTemplate;
import cl.bennder.bennderservices.model.SimpleMail;
import cl.bennder.entitybennderwebrest.model.Validacion;
import java.io.StringWriter;
import java.util.Properties;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
public class Mailer {
 //private MailSender mailSender;
 private JavaMailSender mailSender;
 private VelocityEngine velocityEngine;
 
 private static final Logger log = LoggerFactory.getLogger(Mailer.class);

// public void setMailSender(MailSender mailSender) {
//  this.mailSender = mailSender;
// }
 public void setMailSender(JavaMailSender mailSender) {
  this.mailSender = mailSender;
 }

 public void setVelocityEngine(VelocityEngine velocityEngine) {
  this.velocityEngine = velocityEngine;
 }

 public void sendMail(SimpleMail mail, String nombreTemplate) {
     try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());
        
       
        log.info("getTemplate...");
        Template template = velocityEngine.getTemplate("./templates/" + nombreTemplate,"UTF-8");
        //template.setResourceLoader(resourceLoader);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("user", "dyanez");
        velocityContext.put("password", "admin");
        //velocityContext.put("location", "Pune");

        StringWriter stringWriter = new StringWriter();
        log.info("merge...");
        template.merge(velocityContext, stringWriter);
        log.info("setText...");
        message.setText(stringWriter.toString());
        
        log.info("mailSender.send - inicio...");
        mailSender.send(message);
        log.info("mailSender.send - fin...");
         
     } catch (Exception e) {
         log.error("Error en excepcion:",e);
     }

 }
 public Validacion envioCorreoTemplate(EmailTemplate emailTemplate, String passMailFrom, String username){
     Validacion response = new Validacion("0", "1", "Problemas al enviar correo");
    log.info("inicio");
    try {
        log.info("completando correo salida->{}/destinatario->{}/asunto...",emailTemplate.getMailFrom(),emailTemplate.getMailTo()); 
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setFrom(emailTemplate.getMailFrom());
        message.setTo(emailTemplate.getMailTo());
        message.setSubject(emailTemplate.getMailSubject());
        log.info("completando credenciales correo salida...");
        ((JavaMailSenderImpl)mailSender).setPassword(passMailFrom);
        ((JavaMailSenderImpl)mailSender).setUsername(username);
       
        log.info("obteniendo template...");
        Template template = velocityEngine.getTemplate("./templates/"+emailTemplate.getNombreTemplate(),"UTF-8");
        StringWriter stringWriter = new StringWriter();
        log.info("merge varibles contexto y template...");
        template.merge(emailTemplate.getContext(), stringWriter);
        log.info("completando contenido de correo (html)...");
        message.setText(stringWriter.toString(),true);        
        log.info("enviando correo...");
        this.mailSender.send(mimeMessage); 
        response.setCodigo("0");
        response.setCodigoNegocio("0");
        response.setMensaje("Correo enviado a "+emailTemplate.getMailTo()+" correctamente.");
        log.info("fin de envio de correo ->{}",emailTemplate.getMailTo());
         
     } catch (Exception e) {
        log.error("Error en enviarCorreoPassword.",e);
        response.setCodigo("1");
        response.setCodigoNegocio("1");
        response.setMensaje("Problemas al generar y enviar correo");
     }
    log.info("fin");
    return response;
 }
 
 public void sendMailMimeMessageHelper(SimpleMail mail,String nombreTemplate) {
     try {
         
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());
        
        //Properties props = ((JavaMailSenderImpl)mailSender).getJavaMailProperties();
//        Properties properties = new Properties(); 
//        properties.put("mail.smtp.auth", auth); 
//        properties.put("mail.smtp.timeout", timeout); 
//        properties.put("mail.smtp.connectiontimeout", timeout); 
//        properties.put("mail.smtp.starttls.enable", starttls); 
//        
//        <property name="username" value="soporte@bennder.cl" />
//    <property name="password" value="soportebennder2017" />
// 
//        sender.setJavaMailProperties(properties); 
//        sender.setHost(host); 
//        sender.setPort(port); 
//        sender.setProtocol(protocol); 
//        sender.setUsername(username); 
// <!--<property name="username" value="soporte@bennder.cl" />
//    <property name="password" value="soportebennder2017" />-->
//        sender.setPassword(password); 
         log.info("seteando credenciales...");
        ((JavaMailSenderImpl)mailSender).setPassword("soportebennder2017");
        ((JavaMailSenderImpl)mailSender).setUsername("soporte@bennder.cl");
       
        log.info("getTemplate...");
        Template template = velocityEngine.getTemplate("./templates/"+nombreTemplate,"UTF-8");
        //template.setResourceLoader(resourceLoader);

        VelocityContext velocityContext = new VelocityContext();
        //$clase.atributo o ${clase.atributo}
        //import org.apache.velocity.context.Context;//context.put("customer", customer);
        velocityContext.put("user", "dyanez");
        velocityContext.put("password", "admin");
        //velocityContext.put("location", "Pune");

        StringWriter stringWriter = new StringWriter();
        log.info("merge...");
        template.merge(velocityContext, stringWriter);
        log.info("setText...");
        message.setText(stringWriter.toString(),true);        
        log.info("mailSender.send - inicio...");
        this.mailSender.send(mimeMessage); 
        message.setText("This is the message body");
        
        log.info("mailSender.send - fin...");
         
     } catch (Exception e) {
         log.error("Error en excepcion:",e);
     }

 }
 
 
}
