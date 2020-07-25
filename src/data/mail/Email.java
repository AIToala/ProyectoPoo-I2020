/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mail;

import data.pedido.Pedido;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 

/**
 *
 * @author Grupo1ProyectoPOO
 */
public class Email{
    //Variables de instancia de Email
    private final static String gmailAcc = "agrostoreNU@gmail.com";
    private String receptor;
    private String mensaje;
    
    /**
     * Constructor que instancia a Email.
     * @param   receptor   Cliente al que se enviara correo.
     * @param   mensaje    Mensaje a enviar.
     */
    public Email(String receptor, String mensaje){
        this.receptor = receptor;
        this.mensaje = mensaje;
    }
    /**
     * Metodo que envia correos al cliente pasado por parametro de la instancia de email receptor.
     */
    public boolean enviarEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String password = "030245AT";
        
        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(gmailAcc, password);
            }
        });
        
        String subject = "Factura generada por compras de productos en app AGROSTORENU";
        try{ 
           Message message = new MimeMessage(session); 
           message.setFrom(new InternetAddress(gmailAcc)); 
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor)); 
           message.setSubject(subject); 
           message.setText(mensaje); 

           Transport.send(message); 
           System.out.println("Email enviado exitosamente a " + receptor); 
        } 
        catch (MessagingException mex){
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
        return true;
    }
    /**
     * Metodo estatico que nos permite enviar el email de confirmacion de compra del Cliente pasado como argumento correo.
     * Hace uso SMTP server de Gmail.
     * @param   correo        nombre de usuario.
     * @param   pedido    contrasena del usuario.
     * @return  true si envia el correo, caso contrario false.
     */
    public static boolean enviarEmailConfirmacion(String correo, ArrayList<Pedido> pedido){
        if(correo.isEmpty()){return false;}
        if(pedido == null) {return false;}
        if(pedido.isEmpty()){return false;}
        String enviar = "";
        int i = 1;
        for(Pedido texto:pedido){
            String cod = texto.getCodigo();
            String fecha = texto.getFechas().get(0).toString();
            String productosPedidos = texto.getProductos();
            String cliente = texto.getCliente().getUser();
            String totalPago = Double.toString(texto.getTotalPagar());
            String estado = texto.getEstado().name();
            enviar += "("+ i +") Codigo: "+ cod +"- Pedido" + " - Fecha: " + LocalDateTime.now() + " - PAGO POR TARJETA" +
                      "- Lista de productos solicitados: " + productosPedidos + " - Total a Pagar: " + totalPago;
            i+=1;
        }
        String recipient = correo;
        String codigo = "BUYMEPLS";
        String subject = "Factura generada por compras de productos en app AGROSTORENU";
        
        Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String password = "030245AT";
        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(gmailAcc, password);
            }
        });
        try{ 
           Message message = new MimeMessage(session); 
           message.setFrom(new InternetAddress(gmailAcc)); 
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
           message.setSubject(subject); 
           String htmlCode = "<div style=\"background: antiquewhite;\">"
                           + "<h1 style=\"text-align:center;color:#FF0000;\">Hola, has comprado en AgroStoreNU?</h1>"
                           + "<p>Verifica si has realizado las siguientes compras:</p></br>"
                           + "<p>" + enviar + "</p>"
                           + "<p> Si has realizado estas compras, ingrese el codigo generado en AgroStoreNU y prontos tus productos estaran al pie de su hogar. </p>"
                           + "<p><center><b>" + "Codigo:" + codigo + "</b></center></p>"
                           + "</div>";
           message.setContent(htmlCode, "text/html");
           Transport.send(message); 
           System.out.println("Email enviado exitosamente a " + recipient); 
        } 
        catch (MessagingException mex){
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
        return true;
    }
}
