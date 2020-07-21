/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.producto.Producto;
import data.pedido.Pedido;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Proveedor extends Usuario{
    private String contacto;
    private ArrayList<Producto> oferta;
    private ArrayList<Pedido> pedidos;

    public Proveedor(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo, String contacto) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.contacto = contacto;
        this.oferta = new ArrayList<>();
        this.pedidos = new ArrayList<>();

    }
    //Usado unicamente para propositos de inicializacion de sistema.

    public Proveedor(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo,
                     String contacto, ArrayList<Producto> oferta, ArrayList<Pedido> pedidos) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.contacto = contacto;
        this.oferta = oferta;
        this.pedidos = pedidos;
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
    
    public boolean registrarProducto(Producto p){
        return true;
    }
    
    public boolean gestionarPedidos(){
        return true;
    }
    public boolean editarProducto(){
        return true;
    }
}
