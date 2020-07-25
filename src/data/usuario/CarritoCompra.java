/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.producto.Producto;
import java.util.ArrayList;
/**
 * Clase que permite a una instancia de Cliente almacenar los productos que desea solicitar.
 * @author Grupo1ProyectoPOO
 */
public class CarritoCompra {
    //Variables de instancia
    private ArrayList<Producto> productos;
    /**
     * Constructor que instancia a un CarritoCompra.
     */
    public CarritoCompra(){
        this.productos = new ArrayList<>();
    }
    
    //Metodos Carrito de Compra Consulta, Eliminar y Compra
    /**
     * Metodo que permite eliminar productos no deseados de la instancia CarritoCompra 
     * haciendo uso de un codigo.
     * @param codigo    codigo unico del producto.
     * @return          retorna verdadero si elimina el producto, caso contrario falso.
     */
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
    /**
     * Metodo que retorna la variable de instancia productos de una instancia CarritoCompra.
     * productos es una lista de Producto.
     * @return  lista de Producto en la instancia de un CarritoCompra.
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    /**
     * Metodo que agrega Producto a la variable de instancia productos de una instancia CarritoCompra.
     * @param productos Producto que se desea agregar al carrito.
     */
    public void setProductos(Producto productos) {
        this.productos.add(productos);
    }
}
