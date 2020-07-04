/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner;
/**
 *
 * @author AToala
 */
public class Interfaz {

    public Scanner sc;
    
    public Interfaz(){
        sc = new Scanner(System.in);
    }
    public void menu(){
        System.out.println("Bienvenido a AgroStoreNU");
        String op="";
        while(!op.equals("3")){
            System.out.println("------------------------------------------");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el usuario
            switch(op){
                case "1":
                    //Menu INICIAR SESION
                    
                    continue;
                case "2":
                    //Menu REGISTRAR USUARIO
                    
                    continue;
                case "3":
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        Interfaz ui = new Interfaz();
        ui.menu();
        
    }
    
}
