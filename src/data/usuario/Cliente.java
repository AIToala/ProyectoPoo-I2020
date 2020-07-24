/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import Interfaz.Sistema;
import data.pago.*;
import data.pedido.Pedido;
import data.producto.Producto;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Cliente extends Usuario {
    private Pago formaPago;
    private CarritoCompra carrito;
    private ArrayList<Pedido> pedidos;

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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    
    
    // Consultar productos de proveedores a 50 km de distancia maxima.
    @Override
    public boolean consultarProducto(ArrayList<Producto> productos){
        if(productos == null){return false;}
        if(productos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PRODUCTOS DEL PROVEEDOR " + this.getUser());
        for(Producto prod : productos){
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
        String categoria = dataFiltro.get(0).toUpperCase();
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
        if(Sistema.getProductosCercanos(this) == null){ return null;}
        if(Sistema.getProductosCercanos(this) == null){ return null;}
        if(Sistema.getProductosCercanos(this).isEmpty()){ return null;}
        ArrayList<Producto> filtrado = new ArrayList<>();
        for(Producto p : carrito.getProductos()){
            if(p.getCategoria().contains(categoria) && p.getNombre().contains(nombre) &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(p.getCategoria().contains(categoria) && nombre.equals("") &&
               p.getCostoUnitario()>= rangoInicial && p.getCostoUnitario()<= rangoFinal){
                filtrado.add(p);
            }else if(categoria.equals("") && p.getNombre().contains(nombre) &&
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
        if(pedidos == null){return false;}
        if(pedidos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PEDIDOS REALIZADO AL PROVEEDOR " + this.getUser());
        for(Pedido pedido : pedidos){
            System.out.println("CODIGO     : " + pedido.getCodigo()+ "|");
            System.out.println("FECHA INICIO   : " + pedido.getFechas().get(0));
            System.out.println("\nTOTAL DEL PEDIDO  : " + pedido.getTotalPagar());
            String pago = "";
            if(pedido.getMetodoPago() instanceof PagoPayPal){
                pago = "PayPal";
            }
            else if(pedido.getMetodoPago() instanceof PagoTarjeta){
                PagoTarjeta pt = (PagoTarjeta) pedido.getMetodoPago();
                String numTarjeta = pt.getNumTarjeta();
                pago = "Tarjeta "+numTarjeta;
            }
            System.out.println("FORMA DE PAGO  : " + pago);
            System.out.println("PRODUCTOS:" + pedido.getProductosPedidos() + "|");
            if(pedido.getProductosPedidos() != null || !pedido.getProductosPedidos().isEmpty()){
                ArrayList<Producto> productosU = Producto.getProductosUnicos(pedido.getProductosPedidos());
                ArrayList<Integer> cantidad = Producto.getCantidadProducto(pedido.getProductosPedidos());
                System.out.println("CODIGO | NOMBRE | CANTIDAD");
                for(Producto p : productosU){
                    int index = productosU.indexOf(p);
                    System.out.println(p.getCodigo()+"|"+p.getNombre()+"|"+cantidad.get(index));                    
                }
            }else{
                System.out.println("No hay productos...");
            }
            System.out.println("ESTADO DEL PEDIDO: "+pedido.getEstado());
            System.out.println("---------------------------------");
        }
        return true;
    }
    public boolean removerPedidosGenerados(ArrayList<Pedido> pedidos){
        if(this.pedidos == null){return false;}
        if(this.pedidos.isEmpty()){return false;}
        if(pedidos == null){return false;}
        if(pedidos.isEmpty()){return false;}
        boolean bandera = false;
        for(Pedido p: this.pedidos){
            for(Pedido p2: pedidos){
                if(p.equals(p2)){
                    bandera = true;
                    this.pedidos.remove(p2);
                }
            }
        }
        return false;
    }

    public boolean agregarAlCarrito(String cod, String cantidad){
        if(carrito == null){ return false;}
        if(Sistema.getProductosCercanos(this) == null){return false;}
        if(Sistema.getProductosCercanos(this).isEmpty()){return false;}
        if(cod.isEmpty()){return false;}
        if(cantidad.isEmpty()){return false;}
        ArrayList<Integer> cantidadDisponible = Producto.getCantidadProducto(Sistema.getProductosCercanos(this));
        ArrayList<Producto> productosU = Producto.getProductosUnicos(Sistema.getProductosCercanos(this));
        int banderaCant = 0;
        int agregados = 0;
        
        try{
            banderaCant = Integer.parseInt(cantidad);
            agregados = Integer.parseInt(cantidad);
        }catch(NumberFormatException e){
            return false;
        }
        int index = productosU.indexOf(Producto.getProducto(productosU, cod));
        int cantDisponible = cantidadDisponible.get(index);
        Producto deseado = Producto.getProducto(productosU, cod);
        boolean suficiente = true;
        if(agregados>cantDisponible){
            agregados = cantDisponible;
            suficiente = false;
        }
        boolean bandera = false;
        for(Producto p: Sistema.getProductosCercanos(this)){
            String codigoP = p.getCodigo();
            if(cod.equals(codigoP)){
                bandera = true;
                for(int i=0; i<agregados; i++){
                    carrito.setProductos(p);
                }
                
            }else if(p.getNombre().equals(deseado.getNombre()) && suficiente == false){
                int indexOtro = productosU.indexOf(Producto.getProducto(productosU, p.getCodigo()));
                int cantOtro = cantidadDisponible.get(indexOtro);
                Producto requerido = Producto.getProducto(productosU, p.getCodigo());
                if(cantOtro>banderaCant-agregados){
                    cantOtro = banderaCant-agregados;
                    suficiente = false;
                }
                bandera = true;
                for(int i=0;i<cantOtro;i++){
                    carrito.setProductos(p);
                }
                agregados += cantOtro;
            }
        }
        return bandera;
    }
    public void verCarrito(){
        if(carrito == null || carrito.getProductos().isEmpty()){ System.out.println("NO HAY PRODUCTOS EN CARRITO");}
        else{
            ArrayList<Producto> productosU = Producto.getProductosUnicos(carrito.getProductos());
            ArrayList<Integer> cantidad = Producto.getCantidadProducto(carrito.getProductos());
            for(Producto p : productosU){
                System.out.println("---------------------------------");  
                System.out.println("CODIGO     : " + p.getCodigo());
                System.out.println("NOMBRE     : " + p.getNombre());
                System.out.println("CANTIDAD   : " + p.getCategoria());
                System.out.println("PRECIO     : " + p.getCostoUnitario());
                System.out.println("SUBTOTAL   : " + p.getCostoUnitario() * cantidad.get(productosU.indexOf(p)));
                System.out.println("---------------------------------");  
                
            }
            System.out.println("TOTAL A PAGAR: " + calcularTotalCarrito());
        }
       
    }
    public ArrayList<Pedido> generarPedidos(CarritoCompra carrito, Pago pago){
        if(carrito==null){return null;}
        if(carrito.getProductos().isEmpty()){return null;}
        ArrayList<Pedido> pedidosRealizados = new ArrayList<>();
        ArrayList<ArrayList<Producto>> listaProdXVendedor = new ArrayList<>();
        ArrayList<String> proveedores = new ArrayList<>();
        for(Producto prod : carrito.getProductos()){
            Proveedor pv = prod.getVendedor();
            String user = pv.getUser();
            if(!proveedores.contains(user)){
                proveedores.add(user);
            }
        }
        for(Producto prod:carrito.getProductos()){
            for(String name: proveedores){
                if(name.equals(prod.getVendedor().getUser())){
                    int index = proveedores.indexOf(name);
                    listaProdXVendedor.get(index).add(prod);
                }
            }
        }
        if(listaProdXVendedor.isEmpty()){return null;}
        int i=Sistema.cantidadPedidos() + 1;
        for(ArrayList<Producto> listaProd : listaProdXVendedor){
            String cod = Integer.toString(i);
            Pedido p = new Pedido(cod, listaProd, this, pago, Producto.getTotalAPagar(listaProd));
            pedidosRealizados.add(p);
            this.pedidos.add(p);
            i++;
        }
        return pedidosRealizados;
    }
    public double calcularTotalCarrito(){
        return Producto.getTotalAPagar(carrito.getProductos());
    }
}