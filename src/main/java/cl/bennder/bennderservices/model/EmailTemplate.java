/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.model;

import org.apache.velocity.VelocityContext;

/**
 * Clase con datos necesarios para template
 * @author dyanez
 */
public class EmailTemplate extends SimpleMail{
    private String nombreTemplate;//nombre de template de velocity *.vm
    private VelocityContext context;//variables contexto para completar datos de template de velocity

    public EmailTemplate() {
    }

    public EmailTemplate(String nombreTemplate, VelocityContext context) {
        this.nombreTemplate = nombreTemplate;
        this.context = context;
    }

    public EmailTemplate(String nombreTemplate, VelocityContext context, String mailFrom, String mailTo, String mailCc, String mailBcc, String mailSubject, String mailContent) {
        super(mailFrom, mailTo, mailCc, mailBcc, mailSubject, mailContent);
        this.nombreTemplate = nombreTemplate;
        this.context = context;
    }

    public String getNombreTemplate() {
        return nombreTemplate;
    }

    public void setNombreTemplate(String nombreTemplate) {
        this.nombreTemplate = nombreTemplate;
    }

    public VelocityContext getContext() {
        return context;
    }

    public void setContext(VelocityContext context) {
        this.context = context;
    }
    
    
}
