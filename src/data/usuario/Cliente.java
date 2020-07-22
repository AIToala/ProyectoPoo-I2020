/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.pago.*;
import data.producto.Producto;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Cliente extends Usuario {
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
    public boolean consultarProducto(ArrayList<Producto> productos){
        if(productos == null){return false;}
        if(productos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PRODUCTOS DEL PROVEEDOR " + this.getUser());
        for(Producto prod : carrito.productos){
            System.out.println("CODIGO     : " + prod.getCodigo());
            System.out.println("NOMBRE     : " + prod.getNombre());
            System.out.println("CATEGORIA  : " + prod.getCategoria());
            System.out.println("PRECIO     : " + prod.getCategoria());
            System.out.println("---------------------------------");
        }
        return true;
    }

    @Override
    public ArrayList<Producto> filtrarProducto(ArrayList<String> dataFiltro){
        if(dataFiltro == null){
            System.out.println("NO FILTROS PASADOS.");
            return null;
        }
        if(dataFiltro.size()>3){
            System.out.println("FILTROS NO VALIDOS");
            return null;
        }
        String categoria = dataFiltro.get(0).toLowerCase();
        String nombre = dataFiltro.get(1).toLowerCase();
        String rangoPrecio = dataFiltro.get(2);
        double rangoInicial = 0;
        double rangoFinal = 0;
        String[] rangos = null;
        if(rangoPrecio.contains("-")){
           rangos = rangoPrecio.split("-");
        }
        try{
            rangoInicial = Double.parseDouble(rangos[0]);
            rangoFinal = Double.parseDouble(rangos[1]);
        } catch (NumberFormatException | NullPointerException err){
            rangoFinal = Double.MAX_VALUE;
        }
        if(carrito == null){ return null;}
        if(carrito.productos == null){ return null;}
        if(carrito.productos.isEmpty()){ return null;}
        ArrayList<Producto> filtrado = new ArrayList<>();
        for(Producto p : carrito.productos){
            if(p.getCategoria().equals(categoria) && p.getNombre().contains(nombre) &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(p.getCategoria().equals(categoria) && p.getNombre().equals("") &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(p.getCategoria().equals("") && p.getNombre().contains(nombre) &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(nombre.equals("") && categoria.equals("") && p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }
        }
        if(filtrado.isEmpty()){
            return null;
        }
        return filtrado;
    
    }
    @Override
    public boolean consultarPedidos(){
        return true;
    }
    
    public void verCarrito(){
        if(carrito == null || carrito.productos.isEmpty()){ System.out.println("NO HAY PRODUCTOS EN CARRITO");}
        else{
            ArrayList<Producto> productosU = Producto.getProductosUnicos(carrito.productos);
            ArrayList<Integer> cantidad = Producto.getCantidadProducto(carrito.productos);
            double total = 0;
            for(Producto p : productosU){
                System.out.println("---------------------------------");  
                System.out.println("CODIGO     : " + p.getCodigo());
                System.out.println("NOMBRE     : " + p.getNombre());
                System.out.println("CANTIDAD   : " + p.getCategoria());
                System.out.println("PRECIO     : " + p.getCostoUnitario());
                System.out.println("SUBTOTAL   : " + p.getCostoUnitario() * cantidad.get(productosU.indexOf(p)));
                System.out.println("---------------------------------");  
                total += p.getCostoUnitario() * cantidad.get(productosU.indexOf(p));
            }
            System.out.println("TOTAL A PAGAR: " + total);
        }
        
    }
    public double calcularTotal(){
        if(carrito.productos == null){return 0;}
        if(carrito.productos.isEmpty()){return 0;}
        double total = 0;
        ArrayList<Producto> productosU = Producto.getProductosUnicos(carrito.productos);
        ArrayList<Integer> cantidad = Producto.getCantidadProducto(carrito.productos);
        for(Producto p : productosU){
            total += p.getCostoUnitario() * cantidad.get(productosU.indexOf(p));
        }
        return total;
    }
}
