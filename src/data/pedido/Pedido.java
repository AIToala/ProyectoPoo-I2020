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


//- estado: ESTADO = SOLICITADO
//Poner enum

public class Pedido {
private String codigo;
private ArrayList<LocalDateTime> fechas;
private ArrayList<Producto> productosPedidos;
private Cliente cliente;
private Pago metodoPago;
private double totalPagar;
private ESTADO estado;

    public Pedido(String codigo, ArrayList<Producto> productosPedidos, Cliente cliente, Pago metodoPago, double totalPagar) {
        this.codigo = codigo;
        LocalDateTime date = LocalDateTime.now();
        fechas = new ArrayList<>();
        fechas.add(date);
        this.productosPedidos = productosPedidos;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.totalPagar = totalPagar;
        this.estado = ESTADO.SOLICITADO;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<LocalDateTime> getFechas() {
        return fechas;
    }

    public void setFechas(LocalDateTime fecha) {
        this.fechas.add(fecha);
    }

    public ArrayList<Producto> getProductosPedidos() {
        return productosPedidos;
    }

    public void setProductosPedidos(ArrayList<Producto> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Pago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    
    
    
    
    
}
