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
 * Clase que contiene a uno de los actores del Sistema AgroStoreNU.
 * Cliente extiende de Usuario.
 * Cliente es aquel que compra en el sistema y genera pedidos al sistema para que el Proveedor asignado lo gestione.
 * @author Grupo1ProyectoPOO
 */
public class Cliente extends Usuario {
    //Variables de instancia.
    private Pago formaPago;
    private CarritoCompra carrito;
    private ArrayList<Pedido> pedidos;
    /**
     * Constructor que instancie a un Cliente.
     * @param user      Usuario unico del Cliente. (Heredado del Padre)
     * @param password  Contrasena del Cliente. (Heredado del Padre)
     * @param nombre    Nombre del Cliente. (Heredado del Padre)
     * @param id        ID Unica del Cliente. (Heredado del Padre)
     * @param direccion Direccion del Cliente. (Heredado del Padre)
     * @param ubicacion Ubicacion del Cliente. (Heredado del Padre)
     * @param correo    Correo del Cliente. (Heredado del Padre)
     * @param formaPago Forma de pago del Cliente. 
     */
    public Cliente(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo, Pago formaPago) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.formaPago = formaPago;
        this.carrito = new CarritoCompra();
        this.pedidos = new ArrayList<>();
    }
    /**
     * Metodo que retorna la variable de instancia formaPago de una instancia de Cliente.
     * @return Metodo de pago del Cliente.
     */
    public Pago getFormaPago() {
        return formaPago;
    }
    /**
     * Metodo que retorna la variable de instancia carrito de una instancia de Cliente.
     * carrito es una Lista de Producto.
     * @return lista de Producto de Cliente.
     */
    public CarritoCompra getCarrito() {
        return carrito;
    }
    /**
     * Metodo que retorna la variable de instancia pedidos de una instancia de Cliente.
     * pedidos es una lista de Pedido.
     * @return lista de Pedidos solicitados por el Cliente.
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    /**
     * Metodo sobreescrito que permite consultar los productos dado una Lista de Producto cualquiera.
     * @param productos lista de Producto cualquiera.
     * @return          Falso si la lista no es null ni esta vacia, verdadero caso contrario. 
     */
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
            System.out.println("PRECIO     : " + prod.getCostoUnitario());
            System.out.println("---------------------------------");
        }
        return true;
    }
    /**
     * Metodo sobreescrito que permite filtrar una lista de Producto dado una Lista de filtros
     * La lista de productos es filtrado dado una categoria y/o un nombre.
     * @param dataFiltro    lista de filtros dados por el Cliente.
     * @return              Lista de Producto filtrados.
     */
    @Override
    public ArrayList<Producto> filtrarProducto(ArrayList<String> dataFiltro){
        if(dataFiltro == null){
            System.out.println("NO FILTROS PASADOS.");
            return null;
        }
        if(dataFiltro.size()>4){
            System.out.println("FILTROS NO VALIDOS");
            return null;
        }
        String categoria = dataFiltro.get(0).toUpperCase();
        String nombre = dataFiltro.get(1).toLowerCase();
        String rangoInicial = dataFiltro.get(2);
        String rangoFinal = dataFiltro.get(3);
        double rangoI = 0;
        double rangoF = 0;
        try{
            rangoI = Double.parseDouble(rangoInicial);
            rangoF = Double.parseDouble(rangoFinal);
        } catch (NumberFormatException err){
            rangoF = Double.MAX_VALUE;
        }
        if(Sistema.getProductosCercanos(this) == null){ return null;}
        if(Sistema.getProductosCercanos(this).isEmpty()){ return null;}
        ArrayList<Producto> filtrado = new ArrayList<>();
        for(Producto p : Sistema.getProductosCercanos(this)){
            if(p.getCategoria().contains(categoria) && p.getNombre().toLowerCase().contains(nombre) && !nombre.equals("") &&
               p.getCostoUnitario()>= rangoI && p.getCostoUnitario()<= rangoF){
                filtrado.add(p);
            }else if(p.getCategoria().contains(categoria) && nombre.equals("") &&
               p.getCostoUnitario()>= rangoI && p.getCostoUnitario()<= rangoF){
                filtrado.add(p);
            }else if(categoria.equals("") && p.getNombre().toLowerCase().contains(nombre) &&
               p.getCostoUnitario()>= rangoI && p.getCostoUnitario()<= rangoF){
                filtrado.add(p);
            }else if(nombre.equals("") && categoria.equals("") && p.getCostoUnitario()>= rangoI && p.getCostoUnitario()<= rangoF){
                filtrado.add(p);
            }
        }
        if(filtrado.isEmpty()){
            return Sistema.getProductosCercanos(this);
        }
        return filtrado;
    
    }
    /**
     * Metodo sobreescrito que muestra los pedidos solicitados por el Cliente.
     * @return falso si la lista de Pedido de la instancia Cliente es nula o vacia, caso contrario vederadero.
     */
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
            System.out.println("PRODUCTOS: ");
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
    /**
     * Metodo que remueve los pedidos del Cliente si hubo errores en la generacion de pedidos o cancelacion de la compra.
     * @param pedidos   Lista de Pedido de la instancia de Cliente.
     * @return          Verdadero si remueve los pedidos generados con exito, caso contrario falso.
     */
    public boolean removerPedidosGenerados(ArrayList<Pedido> pedidos){
        if(this.pedidos == null){return false;}
        if(this.pedidos.isEmpty()){return false;}
        if(pedidos == null){return false;}
        if(pedidos.isEmpty()){return false;}
        boolean bandera = false;
        ArrayList<Pedido> borrar = new ArrayList<>();
        for(Pedido p: this.pedidos){ 
            for(Pedido p2: pedidos){
                if(p.equals(p2)){
                    Proveedor prov = p2.getProductosPedidos().get(0).getVendedor();
                    bandera = true;
                    borrar.add(p2);
                    prov.getPedidos().remove(p2);
                    Usuario u = prov;
                    Sistema.recuperaProdProv(u, p.getProductosPedidos());
                }
            }
        }
        for(Pedido b: borrar){
            if(this.pedidos.contains(b)){
                this.pedidos.remove(b);
            }
        }
        return false;
    }
    /**
     * Metodo que agrega Producto al carrito de compras de la instancia de Cliente dado un codigo.
     * Este metodo agrega tantos productos como haya disponible.
     * Si no existen mas productos en stock de un proveedor dado un codigo, busca productos de otro proveedor en el que
     * su nombre coincida con el producto deseado.
     * @param cod       codigo unico del producto.
     * @param cantidad  cantidad de productos que se desean agregar
     * @return          verdadero si agrega productos, caso contrario falso.
     */
    public boolean agregarAlCarrito(String cod, String cantidad){
        if(carrito == null){ return false;}
        if(Sistema.getProductosCercanos(this) == null){return false;}
        if(Sistema.getProductosCercanos(this).isEmpty()){return false;}
        if(cod.isEmpty()){return false;}
        if(cantidad.isEmpty()){return false;}
        if(Producto.getProducto(Sistema.getProductosCercanos(this), cod)==null){return false;}
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
                    Sistema.productos.remove(p);
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
                    Sistema.productos.remove(p);
                }
                agregados += cantOtro;
            }
        }
        return bandera;
    }
    /**
     * Metodo que muestra el carrito de compras del Cliente.
     */
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
    /**
     * Metodo que genera los pedidos de acuerdo a los productos que esten en el carrito de compras del cliente.
     * Si algun producto pertenece a otro proveedor, se genera un pedido distinto.
     * Por cada proveedor existente atribuido a cada producto se generara un pedido y sera agregado a la lista
     * de pedidos del cliente.
     * @param pago  Metodo de pago del Cliente
     * @return      Lista de Pedido generado.
     */
    public ArrayList<Pedido> generarPedidos(Pago pago){
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
                listaProdXVendedor.add(new ArrayList<>());
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
            Proveedor prov = listaProd.get(0).getVendedor();
            String cod = Integer.toString(i);
            Pedido p = new Pedido(cod, listaProd, this, pago, Producto.getTotalAPagar(listaProd));
            pedidosRealizados.add(p);
            this.pedidos.add(p);
            prov.addPedido(p);
            i++;
        }
        return pedidosRealizados;
    }
    /**
     * Metodo que calcula el total a pagar de la lista de productos ingresados en el carrito de compra del cliente.
     * @return valor del total a pagar.
     */
    public double calcularTotalCarrito(){
        return Producto.getTotalAPagar(carrito.getProductos());
    }
}