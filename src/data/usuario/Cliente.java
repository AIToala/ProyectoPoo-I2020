/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.pago.*;
/**
 *
 * @author Usuario
 */
public class Cliente extends Usuario{
    private Pago formaPago;
    private CarritoCompra carrito;

    public Cliente(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo, Pago formaPago) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.formaPago = formaPago;
        this.carrito = new CarritoCompra();
    }
    //Usado unicamente para propositos de inicializacion de sistema.
    public Cliente(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo, Pago formaPago, CarritoCompra carrito) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.formaPago = formaPago;
        this.carrito = carrito;
    }

    public Pago getFormaPago() {
        return formaPago;
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }
    
    
    
    @Override
    public void consultarProducto(){
    }
    @Override
    public void filtrarProducto(){
    }
    @Override
    public void consultarPedidos(){
    }
    
}
