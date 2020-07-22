/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.producto.Producto;
import java.util.ArrayList;
import Interfaz.Sistema;
/**
 *
 * @author Usuario
 */
public class CarritoCompra {
    ArrayList<Producto> productos;
    
    public CarritoCompra(){
        this.productos = new ArrayList<>();
    }
    
    //Metodos Carrito de Compra Consulta, Eliminar y Compra
    public boolean anadirProductos(ArrayList<Producto> pdts, String codigo, int cantidad){
        if(pdts == null){ return false;}
        if(pdts.isEmpty()){ return false;}
        for(Producto p: pdts){
            String cod = p.getCodigo();
            if(codigo.equals(cod)){
                for(int i=0; i<cantidad; i++){
                    productos.add(p);
                }
            }
        }
        return true;
    }
  
    public boolean eliminarProdCarrito(String codigo){
        for(int i = 0; i<productos.size(); i++){
            Producto p = productos.get(i);
            String cod = p.getCodigo();
            if(codigo.equals(cod)){
                productos.remove(i);
                return true;
            }
        }
        return false;
    }
    
 
    }
