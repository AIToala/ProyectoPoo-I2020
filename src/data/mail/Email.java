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
 * @author Usuario
 */
public class Email{
    private final static String gmailAcc = "agrostoreNU@gmail.com";
    private String receptor;
    private String mensaje;
    
    
    public Email(String receptor, String mensaje){
        this.receptor = receptor;
        this.mensaje = mensaje;
    }
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
           // MimeMessage object. 
           Message message = new MimeMessage(session); 

           // Set From Field: adding senders email to from field. 
           message.setFrom(new InternetAddress(gmailAcc)); 

           // Set To Field: adding recipient's email to from field. 
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor)); 
           // Set Subject: subject of the email 
           message.setSubject(subject); 

           // set body of the email. 
           message.setText(mensaje); 

           // Send email. 
           Transport.send(message); 
           System.out.println("Email enviado exitosamente a " + receptor); 
        } 
        catch (MessagingException mex){
            mex.printStackTrace(); 
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
        return true;
        
    }
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
           // MimeMessage object. 
           Message message = new MimeMessage(session); 

           // Set From Field: adding senders email to from field. 
           message.setFrom(new InternetAddress(gmailAcc)); 

           // Set To Field: adding recipient's email to from field. 
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
           // Set Subject: subject of the email 
           message.setSubject(subject); 
           String htmlCode = "<div style=\"background: antiquewhite;\">"
                           + "<h1 style=\"text-align:center;color:#FF0000;\">Hola, has comprado en AgroStoreNU?</h1>"
                           + "<p>Verifica si has realizado las siguientes compras:</p></br>"
                           + "<p>" + enviar + "</p>"
                           + "<p> Si has realizado estas compras, ingrese el codigo generado en AgroStoreNU y prontos tus productos estaran al pie de su hogar. </p>"
                           + "<p><center><b>" + "Codigo:" + codigo + "</b></center></p>"
                           + "</div>";
           message.setContent(htmlCode, "text/html");
           /*
           message.setText("Hola "+ "\n" +
                        "¿Has comprado en AgroStoreNU?\n" +
                        "Verifica si has realizado las siguientes compras:\n" + enviar +
                        "\n\nSi has realizado estas compras, ingrese el codigo generado en AgroStoreNU y pronto tus productos estaran al pie de su hogar."+
                        "\t\t" + codigo + "\nGracias por confiar en nosotros, AgroStoreNU. "); 
           */
           Transport.send(message); 
           System.out.println("Email enviado exitosamente a " + recipient); 
        } 
        catch (Exception mex){
            mex.printStackTrace(); 
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
        return true;
    }
    /*
    public static boolean enviarEmailConfirmacion(String correo, ArrayList<String> pedido){
        if(correo.isEmpty()){return false;}
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
        String to = correo;
        String codigo = "BUYMEPLS";
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
                        "\t\t" + codigo + "\nGracias por confiar en nosotros, AgroStoreNU. ");
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
    /*
    
    /*
    public boolean enviarEmail(){
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
            e.printStackTrace();
            System.out.println("ERROR AL ENVIAR EMAIL. PRUEBE NUEVAMENTE, SU COMPRA HA SIDO CANCELADA.");
            return false;
        }
    }
    */
}
