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
        this.fechas = new ArrayList<>();
        fechas.add(LocalDateTime.now());
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
    public String getProductos(){
        String s = "";
        for(Producto p : this.productosPedidos){
            s+= p.getNombre()+",";
        }
        return s.substring(0, s.lastIndexOf(","));
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

    @Override
    public String toString() {
        String dataProd = "";
        for(Producto pr : productosPedidos){
            dataProd += pr.getNombre()+",";
        }
        dataProd = dataProd.substring(0, dataProd.lastIndexOf(",")).strip();
        return codigo + "-" + fechas.get(0) + "-" + dataProd + "-" + cliente + "-" + totalPagar + "-" + estado.name();
    }

    
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
