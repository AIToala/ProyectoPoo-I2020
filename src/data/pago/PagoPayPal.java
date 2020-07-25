/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;

import data.pedido.Pedido;
import java.util.ArrayList;

/**
 * Clase que define a la forma de pago mediante PayPal de un Cliente.
 * PagoPayPal hereda de Pago 
 * @author Grupo1ProyectoPOO
 * @see Pago
 */
public class PagoPayPal extends Pago {
    //Variables de instancia de PagoPayPal
    private String username;
    private String password;
    
    /**
    * Constructor que instancia a PagoPayPal.
    * @param    username   nombre de usuario de la cuenta PayPal.
    * @param    password   contrasena de la cuenta PayPal.
    */
    public PagoPayPal(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
    * Metodo que es heredado de Pago. Procesa el pago por medio de una cuenta PayPal, 
    * Si el numero generado que representa el fondo que permite usar PayPal es menor o igual
    * al total a pagar de todos los pedidos se procesa el pago.
    * @param    emailTo   correo del Cliente que compra.
    * @param    pedidos   lista de pedidos.
    * @return             true si el pago se proceso exitosamente, caso contrario false.
    * @see Pago
    */
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
            totalAPagar += texto.getTotalPagar();
        }
        int numero = (int) ((Math.random() * 1000 ) + 100);
        if(numero>=totalAPagar){
            return true;
        }else{
            return false;
        }
    }
}
