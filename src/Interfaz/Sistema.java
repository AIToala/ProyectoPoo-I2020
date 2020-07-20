/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import data.usuario.*;
import data.producto.*;
import data.pago.*;
import data.pedido.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author AToala
 */
public class Sistema {

    public Scanner sc;
    public ArrayList<Usuario> usuarios;
    public ArrayList<Producto> productos;
    public ArrayList<Pedido> pedidos;
    
    
    public Sistema(){
        sc = new Scanner(System.in);
        usuarios = new ArrayList<>();
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }
    public void inicializarDatos(){
        Cliente c = new Cliente("andrest","030245","Andres","1","Samanes",new Coordenada(16,20), "andrestoala2013@gmail.com",
                                new PagoTarjeta("Visa", "0123456789123456", "Andres Toala"));
        Proveedor p = new Proveedor("finca_guayas","030245","Gabriela","2","Samanes",new Coordenada(16,20), "andrestoala2013@gmail.com",
                                    "0123456789");
        usuarios.add(p);
        usuarios.add(c);
    }
    //menu Principal
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
                    System.out.println("-------------------");
                    System.out.println("INICIO DE SESION.");
                    System.out.println("-------------------");
                    System.out.print("Usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String password = sc.nextLine();
                    if(!iniciarSesion(user, password)){
                        System.out.println("El usuario o la contraseña no son las correctas.");
                    }else{
                        Usuario u = conseguirUsuario(user, password);
                        System.out.println("Inicio de sesion satisfactorio.");
                        System.out.println("Bienvenido "+u.getUser().toUpperCase());
                        if(u instanceof Cliente){
                            menuCliente(u);
                        }else if(u instanceof Proveedor){
                            menuProveedor(u);
                        }
                    }
                    continue;
                case "2":
                    //Menu REGISTRAR USUARIO
                    System.out.println("-------------------");
                    System.out.println("REGISTRO DE USUARIO");
                    System.out.println("-------------------");
                    System.out.println("Ingrese usuario que desea crear: \n1. Cliente\n2. Proveedor");
                    System.out.print(":");
                    String sel = sc.nextLine();
                    if(sel.equals("1")){
                        System.out.println("Ingrese datos (Cliente)");
                        ArrayList<String> data = validaIngresoDatosUsuario();
                        if(data.isEmpty()){
                            System.out.println("Error, datos ingresados no guardados.");
                        }else{
                            System.out.println("Ingrese forma de pago preferida (tarjeta o paypal):");
                            String tipo = sc.nextLine().toLowerCase();
                            Pago pago = null;
                            if(tipo.equals("tarjeta")){
                                System.out.println("Ingrese tipo de tarjeta:");
                                String tipoTar = sc.nextLine();
                                System.out.println("Ingrese numero de tarjeta:");
                                String numTar = sc.nextLine();
                                System.out.println("Ingrese nombre titular:");
                                String nomTitular = sc.nextLine();
                                pago = new PagoTarjeta(tipoTar, numTar, nomTitular);
                            }else if(tipo.equals("paypal")){
                                //Validar que usuario y contraseña de paypal existan...
                                System.out.println("Ingrese usuario de paypal:");
                                String upaypal = sc.nextLine();
                                System.out.println("Ingrese contraseña de paypal:");
                                String ppaypal = sc.nextLine();
                                pago = new PagoPayPal(upaypal, ppaypal);
                            }else{
                                System.out.println("No forma de pago preferida...");
                                pago = null;
                            }
                            String d1 = data.get(0);String d2 = data.get(1);
                            String d3 = data.get(2);String d4 = data.get(3);
                            String d5 = data.get(4);String d6 = data.get(5);
                            double lat = Double.parseDouble(d6.split(",")[0]);
                            double longi = Double.parseDouble(d6.split(",")[1]);
                            
                            Coordenada cd = new Coordenada(lat, longi);
                            String d7 = data.get(6);
                            Cliente newUser = new Cliente(d1,d2,d3,d4,d5,cd,d7,pago); 
                            System.out.println("Cliente creado exitosamente.");
                            if(!registrarUsuario(newUser)){
                                System.out.println("Algo innesperado ocurrio al guardar el usuario.");
                            }else{
                                System.out.println("Usuario registrado exitosamente.");
                            }
                        }
                    }else if(sel.equals("2")){
                        System.out.println("Ingrese datos (Proveedor)");
                        ArrayList<String> data = validaIngresoDatosUsuario();
                        if(data.isEmpty()){
                            System.out.println("Error, datos ingresados no guardados.");
                        }else{
                            System.out.println("Ingrese contacto/numero telefonico:");
                            String contacto = sc.nextLine();
                            String d1 = data.get(0);String d2 = data.get(1);
                            String d3 = data.get(2);String d4 = data.get(3);
                            String d5 = data.get(4);String d6 = data.get(5);
                            double lat = Double.parseDouble(d6.split(",")[0]);
                            double longi = Double.parseDouble(d6.split(",")[1]);
                            
                            Coordenada cd = new Coordenada(lat, longi);
                            String d7 = data.get(6);
                            Proveedor newUser = new Proveedor(d1,d2,d3,d4,d5,cd,d7,contacto); 
                            System.out.println("Proveedor creado exitosamente.");
                            if(!registrarUsuario(newUser)){
                                System.out.println("Algo innesperado ocurrio al guardar el usuario.");
                            }else{
                                System.out.println("Usuario registrado exitosamente.");
                            }
                        }
                        

                    }else{
                        System.out.println("Ha escogido incorrectamente.");
                    }
                    System.out.println("Retornando al menú...");
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
    //menu Proveedor
    public void menuProveedor(Usuario u){
        System.out.println("Menu Proveedor");
        String op="";
        Proveedor currProv = (Proveedor) u;
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
    //menu Cliente
    public void menuCliente(Usuario u){
        System.out.println("Menu Cliente");
        Cliente currCl = (Cliente) u;
        String op = "";
        while(!op.equals("4")){
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar productos disponibles 50km a la redonda");
            System.out.println("2. Ver carrito de compras");
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
                    //VerCarritoCompra();
                    
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
    //Metodos del sistema...
    public ArrayList<String> validaIngresoDatosUsuario(){
        boolean valida = false;
        ArrayList<String> data = new ArrayList<>();
        do{
            System.out.println("Ingrese nombre de usuario: ");
            String user = sc.nextLine().toLowerCase();
            if(usuarios != null){
                if(!usuarios.isEmpty()){
                    for(Usuario u : usuarios){
                        if(u.getUser().equals(user)){
                            valida = false;
                            System.out.println("Usuario ya existe. Digite nuevamente.");
                            break;
                        }else{
                            valida = true;
                        }
                    }
                    if(valida == false){
                        System.out.println("----------------------------");
                        continue;
                    }
                }else{
                    valida = true;
                }
            }
            System.out.println("Ingrese contraseña de usuario: ");
            String password = sc.nextLine();
            System.out.println("Ingrese nombre del usuario: ");
            String nombre = sc.nextLine();
            System.out.println("Ingrese id de usuario: ");
            String id = sc.nextLine();
            if(usuarios != null){
                if(!usuarios.isEmpty()){
                    for(Usuario u : usuarios){
                        if(u.getId().equals(id)){
                            valida = false;
                            System.out.println("Id ya existe. Digite nuevamente.");
                            break;
                        }else{
                            valida = true;
                        }
                    }
                    if(valida == false){
                        System.out.println("----------------------------");
                        continue;
                    }
                }else{
                    valida = true;
                }
            }
            System.out.println("Ingrese direccion de usuario: ");
            String direccion = sc.nextLine();
            System.out.println("Ingrese coordenadas de usuario\nLatitud: ");
            String lat = sc.nextLine();
            System.out.println("Ingrese coordenadas de usuario\nLatitud: ");
            String longitud = sc.nextLine();
            try{
                Double.parseDouble(lat);
                Double.parseDouble(longitud);
                valida = true;
            } catch (NumberFormatException err){
                valida = false;
                System.out.println("----------------------------");
                continue;
            }
            System.out.println("Ingrese correo de usuario: ");
            String correo = sc.nextLine();
            System.out.println("Desea conservar estos datos y guardar (Si/No):");
            String resp = sc.nextLine().toLowerCase();
            if(resp.equals("si") && valida){
                data.add(user); 
                data.add(password);
                data.add(nombre);
                data.add(id);
                data.add(direccion);
                data.add(lat+","+longitud);
                data.add(correo);
                System.out.println("----------------------------");
                return data;
            }else{
                return null;
            }
        }while(!valida);
        return null;
    }
    public boolean iniciarSesion(String user, String password){
        if(usuarios == null){
            return false;
        }
        if(usuarios.isEmpty()){
            return false;
        }
        for(Usuario u : usuarios){
            if(u.getUser().equals(user) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean registrarUsuario(Cliente c){
        if(c == null){return false;}
        if(usuarios == null){return false;}
        usuarios.add(c);
        return true;
    }
    public boolean registrarUsuario(Proveedor p){
        if(p == null){return false;}
        if(usuarios == null){return false;}
        usuarios.add(p);
        return true;
    }
    public Usuario conseguirUsuario(String user, String password){
        if(usuarios == null){
            return null;
        }
        if(usuarios.isEmpty()){
            return null;
        }
        Usuario busc = null;
        for(Usuario u : usuarios){
            if(u.getUser().equals(user) && u.getPassword().equals(password)){
                busc = u;
            }
        }
        return busc;
    }
    
    public static void main(String[] args) {
        Sistema ui = new Sistema();
        ui.inicializarDatos();
        ui.menu();
        
    }
    
}
