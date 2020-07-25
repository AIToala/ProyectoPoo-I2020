/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;

import data.pedido.Pedido;
import java.util.ArrayList;

/**
 * Clase asbtracta Pago usada para admitir formas de pago de instancias de Cliente.
 * @author Grupo1ProyectoPOO
 */

public abstract class Pago {
    /**
    * Metodo asbtracto usado para procesar el pago como argumento posee un string s y una lista de pedidos d.
    * @param    s   String.
    * @param    d   lista de pedidos.
    * @see PagoPayPal
    * @see PagoTarjeta
    * @see Pedido
    */
    public abstract boolean procesarPago(String s, ArrayList<Pedido> d);
}
