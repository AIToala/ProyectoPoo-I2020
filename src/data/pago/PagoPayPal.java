/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;

import data.pedido.Pedido;
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
    public boolean procesarPago(String emailTo, ArrayList<Pedido> pedidos){
        if(emailTo.isEmpty()){return false;}
        if(pedidos == null) {return false;}
        if(pedidos.isEmpty()){return false;}
        double totalAPagar = 0;
        for(Pedido texto:pedidos){
            String cod = texto.getCodigo();
            String fecha = texto.getFechas().get(0).toString();
            String productosPedidos = texto.getProductos();
            String cliente = texto.getCliente().getUser();
            String totalPago = Double.toString(texto.getTotalPagar());
            String estado = texto.getEstado().name();
        }
        int numero = (int) ((Math.random() * 1000 ) + 100);
        if(numero>=totalAPagar){
            return true;
        }else{
            return false;
        }
    }
}
