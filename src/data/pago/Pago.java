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
public interface Pago {
    public boolean procesarPago(String s, ArrayList<String> d);
}
