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
    /*
     this.codigo = codigo;
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
    
    */

        
    @Override
    public void consultarProducto(ArrayList<Producto> productos){
        if(productos!=null){
            if(!oferta.isEmpty()){
                System.out.println("---------------------------------");
                System.out.println("PRODUCTOS DEL PROVEEDOR " + this.getUser());
                for(Producto prod : oferta){
                    System.out.println("CODIGO     : " + prod.getCodigo());
                    System.out.println("NOMBRE     : " + prod.getNombre());
                    System.out.println("CATEGORIA  : " + prod.getCategoria());
                    System.out.println("---------------------------------");
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
        if(dataFiltro.size()>2){
            System.out.println("FILTROS NO VALIDOS");
            return null;
        }
        String categoria = dataFiltro.get(0);
        String nombre = dataFiltro.get(1);
        if(oferta == null){
            return null;
        }
        if(oferta.isEmpty()){
            return null;
        }
        ArrayList<Producto> filtrado = new ArrayList<>();
        for(Producto p : oferta){
            if(p.getCategoria().equals(categoria.toLowerCase()) && p.getNombre().equals(nombre)){
                filtrado.add(p);
            }else if(p.getCategoria().equals(categoria.toLowerCase()) && nombre.equals("")){
                filtrado.add(p);
            }else if(p.getNombre().equals(nombre) && p.getCategoria().equals("")){
                filtrado.add(p);
            }else if(nombre.equals("") && categoria.equals("")){
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
