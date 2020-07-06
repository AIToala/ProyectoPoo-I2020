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
public class Sistema {

    public Scanner sc;
    
    public Sistema(){
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
    public void menuProveedor(){
        System.out.println("Bienvenido a menú Proveedor");
        String op="";
        while(!op.equals("4")){
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar información de los pedidos");
            System.out.println("2. Registrar producto");
            System.out.println("3. Consultar y editar información de los productos registrados");
            System.out.println("4. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el proveedor
            switch(op){
                case "1":
                    //Menu CONSULTAR INFORMACIÓN DE LOS PEDIDOS
                    
                    continue;
                case "2":
                    //Menu REGISTRAR PRODUCTO
                    
                    continue;
                case "3":
                    //Menu CONSULTAR Y EDITAR INFORMACIÓN DE LOS PRODUCTOS REGISTRADOS
                    continue;
                    
                case "4":
                    //Salir del Menu Proveedor
                    break;
                    
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
    
    public void menuCliente(){
        System.out.println("MENU CLIENTE");
        
        String op = "";
        while(!op.equals("4")){
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar productos disponibles 50km a la redonda");
            System.out.println("2. Agregar productos al carrito de compras");
            System.out.println("3. Mostrar pedidos realizados");
            System.out.println("4. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el proveedor
            switch(op){
                case "1":
                    //consultarProductos();
                    
                    continue;
                case "2":
                    //añadirProductos();
                    
                    continue;
                case "3":
                    //System.out.println(Arrays.toString(cliente.compra.getPedidos()));
                    continue;
                    
                case "4":
                    //Salir del Menu Proveedor
                    break;
                    
                default:
                    System.out.println("Entrada no válida, ingrese 1, 2, 3 o 4");
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        Sistema ui = new Sistema();
        ui.menu();
        
    }
    
}
