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
public class PagoTarjeta extends Pago{
    private String tipoTarjeta;
    private String numTarjeta;
    private String nombreTitular;

    public PagoTarjeta(String tipoTarjeta, String numTarjeta, String nombreTitular) {
        this.tipoTarjeta = tipoTarjeta;
        this.numTarjeta = numTarjeta;
        this.nombreTitular = nombreTitular;
    }
    
    //METODOS?
}
