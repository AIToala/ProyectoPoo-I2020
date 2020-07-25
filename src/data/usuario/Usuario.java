/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import java.util.ArrayList;
import data.producto.Producto;
/**
 * Clase abstracta el cual es uno de los actores del Sistema.
 * @author Grupo1ProyectoPOO
 */
public abstract class Usuario {
    //Variables de instancia.
    protected String user;
    protected String password;
    protected String nombre;
    protected String id;
    protected String direccion;
    protected Coordenada ubicacion;
    protected String correo;
    /**
     * Constructor que instancia a un Usuario.
     * @param user      nombre de usuario unico del Usuario
     * @param password  contrasena del Usuario
     * @param nombre    nombre del usuario
     * @param id        id unico del usuario
     * @param direccion direccion del usuario
     * @param ubicacion ubicacion del usuario
     * @param correo    ubicacion del correo
     */
    public Usuario(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo) {
        this.user = user;
        this.password = password;
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.correo = correo;
    }
    //Metodos abstractos de Usuario
    /**
     * Metodo abstracto que consulta productos dado una lista de Producto.
     * @param prod  lista de Producto
     * @return      verdadero si cumple las condiciones necesarias, falso caso contrario.
     */
    public abstract boolean consultarProducto(ArrayList<Producto> prod);
    /**
     * Metodo abstracto que filtra productos dado una lista de filtros.
     * @param dataFiltro    Lista de filtros 
     * @return              Lista de Producto filtrados.
     */
    public abstract ArrayList<Producto> filtrarProducto(ArrayList<String> dataFiltro);
    /**
     * Metodo abstracto que muestra los pedidos.
     * @return  verdadero si cumple las condiciones necesarias, falso caso contrario.
     */
    public abstract boolean consultarPedidos();
    /**
     * Metodo que retorna la variable de instancia user de la instancia Usuario.
     * @return nombre de usuario del Usuario.
     */
    public String getUser() {
        return user;
    }
    /**
     * Metodo que retorna la variable de instancia password de la instancia Usuario.
     * @return contrasena del Usuario.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Metodo que retorna la variable de instancia nombre de la instancia Usuario.
     * @return nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo que retorna la variable de instancia id de la instancia Usuario.
     * @return id del usuario.
     */
    public String getId() {
        return id;
    }
    /**
     * Metodo que retorna la variable de instancia direccion de la instancia Usuario.
     * @return direccion del usuario.
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * Metodo que retorna la variable de instancia ubicacion de la instancia Usuario.
     * @return ubicacion del usuario.
     */
    public Coordenada getUbicacion() {
        return ubicacion;
    }
    /**
     * Metodo que retorna la variable de instancia correo de la instancia Usuario.
     * @return correo del usuario.
     */
    public String getCorreo() {
        return correo;
    }
}
