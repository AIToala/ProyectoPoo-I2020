/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class PagoPayPal extends Pago {
    private String username;
    private String password;

    public PagoPayPal(String username, String password) {
        this.username = username;
        this.password = password;
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
        int numero = (int) ((Math.random() * 1000 ) + 100);
        if(numero>=totalAPagar){
            return true;
        }else{
            return false;
        }
    }
}
