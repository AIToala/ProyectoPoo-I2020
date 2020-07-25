/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.pago;
import data.mail.Email;
import data.pedido.Pedido;
import java.util.ArrayList;

/**
 * Clase que define a la forma de pago mediante Tarjeta de un Cliente.
 * PagoTarjeta hereda de Pago 
 * @author Grupo1ProyectoPOO
 * @see Pago
 */
public class PagoTarjeta extends Pago{
    //variables de instancia
    private String tipoTarjeta;
    private String numTarjeta;
    private String nombreTitular;
    /**
     * Constructor que instancia a PagoTarjeta
     * @param   tipoTarjeta     Representa el tipo de tarjeta.
     * @param   numTarjeta      Numero de la tarjeta.
     * @param   nombreTitular   Propietario/Titular de la tarjeta
     * @see Pago
     */
    public PagoTarjeta(String tipoTarjeta, String numTarjeta, String nombreTitular) {
        this.tipoTarjeta = tipoTarjeta;
        this.numTarjeta = numTarjeta;
        this.nombreTitular = nombreTitular;
    }
    /**
     * Metodo sobreescrito de Objeto. Permite convertir a PagoTarjeta a un String.
     * @return  valores de las variables de instancia de PagoTarjeta
     */
    @Override
    public String toString() {
        return "PagoTarjeta{" + "tipoTarjeta=" + tipoTarjeta + ", numTarjeta=" + numTarjeta + ", nombreTitular=" + nombreTitular + '}';
    }
    /**
     * Metodo sobreescrito que permite procesar el pago de un Cliente mediante una tarjeta.
     * @param   emailTo     correo del Cliente.
     * @param   pedidos     lista de Pedidos realizados por el Cliente.
     * @return              true si el pago se genero con exito, false caso contrario.
     * @see Email
     */
    @Override
    public boolean procesarPago(String emailTo, ArrayList<Pedido> pedidos){
        if(emailTo.isEmpty()){return false;}
        if(pedidos == null) {return false;}
        if(pedidos.isEmpty()){return false;}
        double totalAPagar = 0;
        for(Pedido texto:pedidos){
            String cod = texto.getCodigo();
            String fecha = texto.getFechas().get(0).toString();
            String productosPedidos = texto.getProductos();
            String cliente = texto.getCliente().getUser();
            String totalPago = Double.toString(texto.getTotalPagar());
            String estado = texto.getEstado().name();
        }
        String msg = "Hola de parte de AGROSTORENU "+ "\n" +
                     "\nGracias por confiar en nosotros, AgroStoreNU.";
        Email e = new Email(emailTo, msg);
        return e.enviarEmail();
    }
    /**
     * Metodo que retorna la variable de instancia TipoTarjeta de la instancia de PagoTarjeta
     * @return  un String con el tipo de tarjeta.
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
    /**
     * Metodo que retorna la variable de instancia numTarjeta de la instancia de PagoTarjeta
     * @return  un String con el numero de tarjeta.
     */
    public String getNumTarjeta() {
        return numTarjeta;
    }
    /**
     * Metodo que retorna la variable de instancia nombreTitular de la instancia de PagoTarjeta
     * @return  un String con el nombre del titular o propietario de la tarjeta.
     */
    public String getNombreTitular() {
        return nombreTitular;
    }
}
