/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.producto;
import data.usuario.Proveedor;
import java.util.ArrayList;
/**
 * Clase Producto que maneja el Sistema mediante Proveedor.
 * @author Grupo1ProyectoPOO
 */
public class Producto {
    //Variables de instancia.
    private String codigo;
    private Proveedor vendedor;
    private String nombre;
    private String descripcion;
    private ArrayList<CATEGORIA> categorias;
    private double costoUnitario;
    /**
     * Constructor que instancia a Producto.
     * @param codigo        Codigo unico del producto.
     * @param vendedor      Proveedor que oferta el producto.
     * @param nombre        Nombre del producto.
     * @param descripcion   Breve descripcion del producto.
     * @param categoria     Lista de Categorias del Producto.
     * @param costo         Costo Unitario por cada Producto.
     */
    public Producto(String codigo, Proveedor vendedor, String nombre, String descripcion, ArrayList<CATEGORIA> categoria, double costo) {
        this.codigo = codigo;
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categorias = categoria;
        this.costoUnitario = costo;
    }
    /**
     * Metodo que retorna la variable de instancia codigo de una instancia Producto.
     * @return codigo unico del producto.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Metodo que modifica la variable de instancia codigo de una instancia Producto.
     * @param codigo codigo unico del producto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Metodo que retorna la variable de instancia vendedor de una instancia Producto.
     * vendedor es una instancia de Proveedor.
     * @return instancia de Proveedor.
     */
    public Proveedor getVendedor() {
        return vendedor;
    }
    /**
     * Metodo que modifica la variable de instancia vendedor de una instancia Producto.
     * vendedor es una instancia de Proveedor.
     * @param vendedor recibe una instancia de proveedor.
     */
    public void setVendedor(Proveedor vendedor) {
        this.vendedor = vendedor;
    }
    /**
     * Metodo que retorna la variable de instancia nombre de una instancia Producto.
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo que modifica la variable de instancia nombre de una instancia Producto.
     * @param nombre nombre del producto.
     */
    public void setNombre(String nombre) {
        if(!nombre.equals("")){
            this.nombre = nombre;
        }
    }
    /**
     * Metodo que retorna la variable de instancia descripcion de una instancia Producto.
     * @return breve descripcion del Producto.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Metodo que modifica la variable de instancia descripcion de una instancia Producto.
     * @param descripcion breve descripcion del Producto.
     */
    public void setDescripcion(String descripcion) {
        if(!descripcion.equals("")){
            this.descripcion = descripcion;
        }
    }
    /**
     * Metodo que retorna una lista de String con las caracteristicas de una instancia Producto.
     * categoria es una lista de CATEGORIAS
     * @return lista de String con las caracteristicas de una instancia Producto.
     */
    public ArrayList<String> getCategoria() {
        ArrayList<String> s = new ArrayList<>();
        for(CATEGORIA cat : categorias){
            s.add(cat.name());
        }
        return s;
    }
    /**
     * Metodo que retorna la variable de instancia categorias de una instancia Producto.
     * categoria es una Lista de CATEGORIA.
     * @return lista de CATEGORIAS.
     */
    public ArrayList<CATEGORIA> getCATEGORIAS() {
        return categorias;
    }
    /**
     * Metodo que agrega una nueva categoria a la variable de instancia categorias de una instancia Producto.
     * categoria es una lista de CATEGORIA.
     * @param categoria una CATEGORIA del Producto.
     */
    public void setCategoria(CATEGORIA categoria) {
        if(!categorias.contains(categoria)){
            this.categorias.add(categoria);
        }
    }
    /**
     * Metodo que borra toda la informacion dentro de la variable de instancia categoria de una instancia Producto.
     * categoria es una lista de CATEGORIA.
     */
    public void clearCategoria(){
        categorias = new ArrayList<>();
    }
    /**
     * Metodo que retorna la variable de instancia costoUnitario de una instancia Producto
     * @return costo unitario de un Producto.
     */
    public double getCostoUnitario() {
        return costoUnitario;
    }
    /**
     * Metodo que modifica la variable de instancia costoUnitario de una instancia Producto.
     * @param costoUnitario costo unitario de un Producto.
     */
    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    /**
     * Metodo estatico que me retorna una lista con la cantidad de productos de cada Producto.
     * Usado para obtener cantidad de productos que se desean vender.
     * @param prod  lista de Producto a tomar en cuenta su cantidad.
     * @return      lista con la cantidad de cada producto.
     */
    public static ArrayList<Integer> getCantidadProducto(ArrayList<Producto> prod){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        ArrayList<Integer> cantidad = new ArrayList<>();
        ArrayList<Producto> productoU = new ArrayList<>();
        for(Producto p : prod){
            if(!productoU.contains(p)){
                cantidad.add(1);
                productoU.add(p);
            }else{
                int index = prod.indexOf(p);
                cantidad.set(index, cantidad.get(index) + 1);
            }
        }
        return cantidad;
    }
    /**
     * Metodo estatico que me retorna una lista de Producto no repetidos dado una lista de Producto
     * Usado para obtener lista de productos unicos.
     * @param prod  lista de Producto a analizar.
     * @return      lista de Producto unicos y no repetidos.
     */
    public static ArrayList<Producto> getProductosUnicos(ArrayList<Producto> prod){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        ArrayList<Producto> productoUnico = new ArrayList<>();
        for(Producto p : prod){
            if(!productoUnico.contains(p)){
                productoUnico.add(p);
            }
        }
        return productoUnico;
    }
    /**
     * Metodo estatico que me retorna true si el codigo pasado como argumento coincide con alguno de la lista
     * de Producto no repetidos y unicos.
     * @param prod  lista de Producto unico y no repetidos.
     * @param cod   Codigo unico del producto.
     * @return      true si cumple con las condiciones, caso contrario false.
     */
    public static boolean esProductoUnico(ArrayList<Producto> prod, String cod){
        if(prod == null){return true;}
        if(prod.isEmpty()){return true;}
        ArrayList<Producto> productoU = Producto.getProductosUnicos(prod);
        for(Producto p : productoU){
            if(p.getCodigo().equals(cod)){
                return false;
            }
        }
        return true;
    }
    /**
     * Metodo estatico que me retorna un Producto al pasarle como argumento un codigo y una lista de Producto.
     * Usado para obtener el producto de una lista solo conociendo su codigo.
     * @param prod      Lista de Producto cualquiera.
     * @param codigo    codigo unico del Producto.
     * @return          una instancia de Producto con el codigo ingresado.
     */
    public static Producto getProducto(ArrayList<Producto> prod, String codigo){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        if(codigo.equals("")){return null;}
        ArrayList<Producto> productoU = Producto.getProductosUnicos(prod);
        for(Producto p : productoU){
            if(p.getCodigo().equals(codigo)){
                return p;
            }
        }
        return null;
    }
    /**
     * Metodo estatico que retorna el total a pagar dado una lista de Productos.
     * Usado para obtener total a pagar.
     * @param prod  Lista de Producto cualquiera.
     * @return      total a pagar.
     */
    public static double getTotalAPagar(ArrayList<Producto> prod){
        if(prod == null){return 0;}
        if(prod.isEmpty()){return 0;}
        double total = 0;
        for(Producto p : prod){
            total += p.getCostoUnitario();
        }
        return total;
    }
    /**
     * Metodo estatico que retorna la cantidad de un producto unico dado una lista de Producto cualquiera.
     * Usado para obtener cantidad simple de un solo producto unico.
     * @param prod  lista de Producto cualquiera.
     * @param cod   codigo unico del producto.
     * @return      cantidad de un producto unico.
     */
    public static int getCantidadProductoUnico(ArrayList<Producto> prod, String cod){
        if(prod == null){return 0;}
        if(prod.isEmpty()){return 0;}
        if(Producto.getProducto(prod, cod)==null){return 0;}
        ArrayList<Integer> cantidadU = Producto.getCantidadProducto(prod);
        ArrayList<Producto> productosU = Producto.getProductosUnicos(prod);
        int index = cantidadU.get(productosU.indexOf(Producto.getProducto(prod, cod)));
        return cantidadU.get(index);
    }
}
