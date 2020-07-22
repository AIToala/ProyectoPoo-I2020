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
public class PagoPayPal implements Pago {
    private String username;
    private String password;

    public PagoPayPal(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    public boolean procesarPago(String nombreCliente, ArrayList<String> pedidos){
        int numero = (int) (Math.random() * 1000) + 100;     
        
        return false;
    }
    
}
