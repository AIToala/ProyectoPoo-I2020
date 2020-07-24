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
public abstract class Pago {
    public abstract boolean procesarPago(String s, ArrayList<Pedido> d);
}
