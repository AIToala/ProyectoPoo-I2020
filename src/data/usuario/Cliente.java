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
    public void consultarProducto(ArrayList<Producto> productos){
        if(productos!=null){
            if(carrito != null){
                if(!carrito.productos.isEmpty()){
                    System.out.println("---------------------------------");
                    System.out.println("PRODUCTOS DEL PROVEEDOR " + this.getUser());
                    for(Producto prod : carrito.productos){
                        System.out.println("CODIGO     : " + prod.getCodigo());
                        System.out.println("NOMBRE     : " + prod.getNombre());
                        System.out.println("CATEGORIA  : " + prod.getCategoria());
                        System.out.println("PRECIO     : " + prod.getCategoria());
                        System.out.println("---------------------------------");
                    }
                }
            }
        }
        
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
        String categoria = dataFiltro.get(0);
        String nombre = dataFiltro.get(1);
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
            if(p.getCategoria().equals(categoria.toLowerCase()) && p.getNombre().equals(nombre) &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(p.getCategoria().equals(categoria.toLowerCase()) && p.getNombre().equals("") &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(p.getCategoria().equals("") && p.getNombre().equals(nombre) &&
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
    public void consultarPedidos(){
    }
    
}
