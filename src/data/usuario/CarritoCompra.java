/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.producto.Producto;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class CarritoCompra {
    private ArrayList<Producto> productos;
    
    public CarritoCompra(){
        this.productos = new ArrayList<>();
    }
    
    //Metodos Carrito de Compra Consulta, Eliminar y Compra
    
    public boolean eliminarProdCarrito(String codigo){
        if(codigo==""){return false;}
        if(productos==null){return false;}
        if(productos.isEmpty()){return false;}
        boolean bandera = false;
        for(int i = 0; i<productos.size(); i++){
            Producto p = productos.get(i);
            String cod = p.getCodigo();
            if(codigo.equals(cod)){
                productos.remove(i);
                bandera = true;
            }
        }
        return bandera;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos.add(productos);
    }
    
    
 
}
