/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.producto;
import data.usuario.Proveedor;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Producto {
    private String codigo;
    private Proveedor vendedor;
    private String nombre;
    private String descripcion;
    private ArrayList<CATEGORIA> categorias;
    private double costoUnitario;

    public Producto(String codigo, Proveedor vendedor, String nombre, String descripcion, ArrayList<CATEGORIA> categoria, double costo) {
        this.codigo = codigo;
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categorias = categoria;
        this.costoUnitario = costo;
        //vendedor.addProducto(this);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Proveedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Proveedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.equals("")){
            this.nombre = nombre;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if(!descripcion.equals("")){
            this.descripcion = descripcion;
        }
    }

    public ArrayList<String> getCategoria() {
        ArrayList<String> s = new ArrayList<>();
        for(CATEGORIA cat : categorias){
            s.add(cat.name());
        }
        return s;
    }
    
    public ArrayList<CATEGORIA> getCATEGORIAS() {
        return categorias;
    }

    public void setCategoria(CATEGORIA categoria) {
        if(!categorias.contains(categoria)){
            this.categorias.add(categoria);
        }
    }
    public void clearCategoria(){
        categorias = new ArrayList<>();
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    public static ArrayList<Integer> getCantidadProducto(ArrayList<Producto> prod){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        ArrayList<Integer> cantidad = new ArrayList<>();
        for(Producto p : prod){
            if(!prod.contains(p)){
                cantidad.add(1);
            }else{
                int index = prod.indexOf(p);
                cantidad.set(index, cantidad.get(index) + 1);
            }
        }
        return cantidad;
    }
    public static ArrayList<Producto> getProductosUnicos(ArrayList<Producto> prod){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        ArrayList<Producto> productoUnico = new ArrayList<>();
        for(Producto p : prod){
            if(!prod.contains(p)){
                productoUnico.add(p);
            }
        }
        return productoUnico;
    }
    
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
    public static Producto getProducto(ArrayList<Producto> prod, String codigo){
        if(prod == null){return null;}
        if(prod.isEmpty()){return null;}
        if(codigo.isBlank() || codigo.equals("")){return null;}
        ArrayList<Producto> productoU = Producto.getProductosUnicos(prod);
        for(Producto p : productoU){
            if(p.getCodigo().equals(codigo)){
                return p;
            }
        }
        return null;
    }
    public static double getTotalAPagar(ArrayList<Producto> prod){
        if(prod == null){return 0;}
        if(prod.isEmpty()){return 0;}
        double total = 0;
        ArrayList<Producto> productosU = Producto.getProductosUnicos(prod);
        ArrayList<Integer> cantidad = Producto.getCantidadProducto(prod);
        for(Producto p : productosU){
            total += p.getCostoUnitario() * cantidad.get(productosU.indexOf(p));
        }
        return total;
    }
    
}
