/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.producto;
import data.usuario.Proveedor;
/**
 *
 * @author Usuario
 */
public class Producto {
    private String codigo;
    private Proveedor vendedor;
    private String nombre;
    private String descripcion;
    private CATEGORIA categoria;
    private double costoUnitario;

    public Producto(String codigo, Proveedor vendedor, String nombre, String descripcion, CATEGORIA categoria, double costo) {
        this.codigo = codigo;
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.costoUnitario = costo;
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
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria.toString().toLowerCase();
    }

    public void setCategoria(CATEGORIA categoria) {
        this.categoria = categoria;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    
    
}
