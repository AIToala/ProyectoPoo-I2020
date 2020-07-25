/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pedido;
import data.pago.Pago;
import data.producto.Producto;
import data.usuario.Cliente;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que almacena los productos que un Cliente solicite e informacion de su pago.
 * Aquellos que pueden gestionarlos son los Proveedor.
 * @author Grupo1ProyectoPOO
 */
public class Pedido {
    //Variables de instancia de Pedido
    private String codigo;
    private ArrayList<LocalDateTime> fechas;
    private ArrayList<Producto> productosPedidos;
    private Cliente cliente;
    private Pago metodoPago;
    private double totalPagar;
    private ESTADO estado;
    /**
     * Constructor que instancia a Pedido.
     * @param codigo            Codigo unico del pedido.
     * @param productosPedidos  Lista de Producto solicitados en el pedido.
     * @param cliente           Cliente que realiza la solicitud.
     * @param metodoPago        Metodo de pago del Cliente.
     * @param totalPagar        Total a pagar por el pedido solicitado.
     */
    public Pedido(String codigo, ArrayList<Producto> productosPedidos, Cliente cliente, Pago metodoPago, double totalPagar) {
        this.codigo = codigo;
        this.fechas = new ArrayList<>();
        fechas.add(LocalDateTime.now());
        this.productosPedidos = productosPedidos;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.totalPagar = totalPagar;
        this.estado = ESTADO.SOLICITADO;
    }
    /**
     * Metodo que retorna la variable de instancia codigo de una instancia de Pedido.
     * @return codigo unico del Pedido.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Metodo que modifica la variable de instancia codigo de una instancia de Pedido.
     * @param codigo codigo unico del Pedido.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Metodo que retorna la variable de instancia fechas de una instancia de Pedido.
     * @return Lista de fechas con este formato YY||MM||DDT-HH:MM:SS
     */
    public ArrayList<LocalDateTime> getFechas() {
        return fechas;
    }
    /**
     * Metodo que modifica la variable de instancia fechas de una instancia de Pedido.
     * Agregandole una fecha a la lista de fechas.
     * @param fecha 
     */
    public void setFechas(LocalDateTime fecha) {
        this.fechas.add(fecha);
    }
    /**
     * Metodo que retorna la variable de instancia productosPedidos de una instancia de Pedido.
     * @return Lista de Productos solicitados por el cliente.
     */
    public ArrayList<Producto> getProductosPedidos() {
        return productosPedidos;
    }
    /**
     * Metodo que retorna un String con el nombre de cada producto en la variable de instancia
     * productosPedidos de una instancia de Pedido.
     * @return String con nombre de cada producto en productosPedidos.
     */
    public String getProductos(){
        String s = "";
        for(Producto p : this.productosPedidos){
            s+= p.getNombre()+",";
        }
        return s.substring(0, s.lastIndexOf(","));
    }
    /**
     * Metodo que modifica la variable de instancia productosPedidos de una instancia de Pedido.
     * @param productosPedidos lista de Producto solicitado por el cliente.
     */
    public void setProductosPedidos(ArrayList<Producto> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }
    /**
     * Metodo que retorna la variable de instancia cliente de una instancia de Pedido.
     * @return Cliente que esta realizando el pedido. 
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Metodo que modifica la variable de instancia cliente de una instancia de Pedido.
     * @param cliente Cliente que esta realizando el pedido.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Metodo que retorna la variable de instancia metodoPago de una instancia de Pedido.
     * @return Pago con el cual se compro el pedido.  
     */
    public Pago getMetodoPago() {
        return metodoPago;
    }
    /**
     * Metodo que modifica la variable de instancia metodoPago de una instancia de Pedido.
     * @param metodoPago Pago con el cual se compro el pedido.
     */
    public void setMetodoPago(Pago metodoPago) {
        this.metodoPago = metodoPago;
    }
    /**
     * Metodo que retorna la variable de instancia totalPagar de una instancia de Pedido.
     * @return Total a pagar del pedido.
     */
    public double getTotalPagar() {
        return totalPagar;
    }
    /**
     * Metodo que modifica la variable de instancia totalPagar de una instancia de Pedido.
     * @param totalPagar total a pagar del pedido.
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    /**
     * Metodo que retorna la variable de instancia estado de una instancia de Pedido. 
     * @return Estado de un pedido (SOLICITADO, PROCESADO, DESPACHADO).
     */
    public ESTADO getEstado() {
        return estado;
    }
    /**
     * Metodo que modifica la variable de instancia estado de una instancia de Pedido.
     * @param estado Estado actual de un pedido.
     */
    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }
    /**
     * Metodo sobreescrito de Object, convierte a la instancia de Pedido en un string con toda su informacion importante.
     * @return informacion importante de un Pedido.
     */
    @Override
    public String toString() {
        String dataProd = "";
        for(Producto pr : productosPedidos){
            dataProd += pr.getNombre()+",";
        }
        dataProd = dataProd.substring(0, dataProd.lastIndexOf(",")).strip();
        return codigo + "-" + fechas.get(0) + "-" + dataProd + "-" + cliente + "-" + totalPagar + "-" + estado.name();
    }
    /**
     * Metodo sobreescrito de Object, compara a la instancia de Pedido con una instancia de Objeto 
     * el cual para que retorne true debe ser tambien una instancia de Pedido y poseer la misma variable de instancia de codigo.
     * @param obj   Objeto a comparar
     * @return      true si cumple la condicion, false caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
}
