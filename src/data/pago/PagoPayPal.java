/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;

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
    
    //Metodos?
}
