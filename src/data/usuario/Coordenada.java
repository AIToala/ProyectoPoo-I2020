/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;

/**
 * Clase que permite ubicar geograficamente mediante latitud y longitud a los usuarios
 * del Sistema AgroStoreNU.
 * @author Grupo1ProyectoPOO
 */
public class Coordenada {
    //Variables de instancia.
    private double latitud;
    private double longitud;
    private final static double RADIOTIERRA = 6378;
    /**
     * Constructor vacio que instancia una Coordenada.
     */
    public Coordenada(){}
    /**
     * Constructor que instancia una coordenada dado una latitud y una longitud.
     * @param latitud   latidud en grados.
     * @param longitud  longitud en grados.
     */
    public Coordenada(double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }
    /**
     * Metodo que calcula la distancia de una Coordenada a otra Coordenada.
     * @param c1    Coordenada inicial
     * @param c2    Coordenada final
     * @return      Distancia entre estas coordenadas.
     */
    public static double calcularDistancia(Coordenada c1, Coordenada c2){
        double lat1 = c1.latitud * (Math.PI/180);
        double lat2 = c2.latitud * (Math.PI/180);
        double long1 = c1.longitud * (Math.PI/180);
        double long2 = c2.longitud * (Math.PI/180);
        
        double dlat = lat2 - lat1;
        double dlong = long2 - long1;
        
        double a = Math.pow(Math.sin(dlat/2) + Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(dlong/2), 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        
        return RADIOTIERRA * c;
    }
}