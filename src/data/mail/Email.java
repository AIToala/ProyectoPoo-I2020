/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Usuario
 */
public class Email{
    private final static String emisor = "no_reply@agrostorenu.com";
    private String receptor;
    private String mensaje;
    
    
    public Email(String receptor, String mensaje){
        this.receptor = receptor;
        this.mensaje = mensaje;
    }
    
    public boolean enviarEmail(String mensaje, ArrayList<String> pedido){
        if(mensaje.isEmpty()){return false;}
        if(pedido == null) {return false;}
        if(pedido.isEmpty()){return false;}
        String enviar = "";
        int i = 1;
        for(String texto:pedido){
            String[] data = texto.split("-");
            String cod = data[0];String fecha = data[1];String productosPedidos = data[2];
            if(productosPedidos.contains(",")){
                productosPedidos = productosPedidos.replace(",","\n");
            }
            String cliente = data[3];String proveedor = data[4];String totalPago = data[5];
            enviar += "("+ i +")"+"Pedido a " + proveedor.toUpperCase() + " - Fecha: " + fecha + " - PAGO POR TARJETA\n" +
                      "Lista de productos solicitados: \n" + productosPedidos + "\n\nTotal a Pagar: " + totalPago + 
                      "--------------------------------------------------";
            i+=1;
        }
        // Set up the SMTP server.
        java.util.Properties props = new java.util.Properties();
        //smtp.myisp.com
        props.put("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = receptor;
        String subject = "Factura generada por compras de productos en app AGROSTORENU";
        Message msg = new MimeMessage(session);
        try {
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(emisor, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(emisor, false));
            msg.setSubject(subject);
            msg.setText("Hola "+ "\n" +
                        "¿Has comprado en AgroStoreNU?\n" +
                        "Verifica si has realizado las siguientes compras:\n" + enviar +
                        "\nSi has realizado estas compras, ingrese el codigo generado en AgroStoreNU y pronto tus productos estaran al pie de su hogar."+
                        "\t\t" + "\nGracias por confiar en nosotros, AgroStoreNU. ");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            System.out.println("Email listo para envio...");
            // Send the message.
            Transport.send(msg);
            System.out.println("Email enviado exitosamente");
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
    }
    
    
    public boolean enviarEmail(String mensaje){
        if(mensaje.isEmpty()){return false;}
        // Set up the SMTP server.
        java.util.Properties props = new java.util.Properties();
        //smtp.myisp.com
        props.put("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = receptor;
        String subject = "Factura generada por compras de productos en app AGROSTORENU";
        Message msg = new MimeMessage(session);
        try {
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(emisor, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(emisor, false));
            msg.setSubject(subject);
            msg.setText(mensaje);
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            System.out.println("Email listo para envio...");
            // Send the message.
            Transport.send(msg);
            System.out.println("Email enviado exitosamente");
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
    }
}
