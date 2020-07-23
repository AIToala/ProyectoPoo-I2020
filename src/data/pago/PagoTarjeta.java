/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;
import data.mail.Email;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class PagoTarjeta extends Pago{
    private String tipoTarjeta;
    private String numTarjeta;
    private String nombreTitular;

    public PagoTarjeta(String tipoTarjeta, String numTarjeta, String nombreTitular) {
        this.tipoTarjeta = tipoTarjeta;
        this.numTarjeta = numTarjeta;
        this.nombreTitular = nombreTitular;
    }
    
    @Override
    public boolean procesarPago(String emailTo, ArrayList<String> pedidos){
        if(emailTo.isEmpty()){return false;}
        if(pedidos == null) {return false;}
        if(pedidos.isEmpty()){return false;}
        double totalAPagar = 0;
        for(String texto:pedidos){
            String[] data = texto.split("-");
            String totalPago = data[5];
            totalAPagar += Double.parseDouble(totalPago);
        }
        String msg = "Hola de parte de AGROSTORENU "+ "\n" +
                     "\nGracias por confiar en nosotros, AgroStoreNU.";
        Email e = new Email(emailTo, msg);
        if(e.enviarEmail()){
            return true;}
        return false;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }
    
    
    
    
    
    
    
    
    
    
}
