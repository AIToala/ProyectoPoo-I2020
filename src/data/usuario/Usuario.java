/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;

/**
 *
 * @author Usuario
 */
public abstract class Usuario {
    protected String user;
    protected String password;
    protected String nombre;
    protected String id;
    protected String direccion;
    protected Coordenada ubicacion;
    protected String correo;
    
    public Usuario(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo) {
        this.user = user;
        this.password = password;
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.correo = correo;
    }
    
    public abstract void consultarProducto();
    public abstract void filtrarProducto();
    public abstract void consultarPedidos();
   
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Coordenada getUbicacion() {
        return ubicacion;
    }

    public String getCorreo() {
        return correo;
    }
    
     
    
    
    
    
    
    
    /* implementacion...
    public Coordenada (String direccion){
        this(generarLatLong(direccion)[0], generarLatLong(direccion)[1]);
    }
    public Double[] generarLatLong(String direccion){
        double a = 4;
        double b = 3;
        Double[] n = new Double[2];
        n[0] = a;
        n[1] = b;
        return n;
    }
    */

}
