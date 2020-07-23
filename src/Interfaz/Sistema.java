/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import data.mail.Email;
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
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Producto> productos;
    public static ArrayList<Pedido> pedidos;
    public static String codigo;
    
    public Sistema(){
        sc = new Scanner(System.in);
        usuarios = new ArrayList<>();
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        codigo = "BUYMEPLS";
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
        Sistema.actualizarData();
        Proveedor currProv = (Proveedor) u;
        System.out.println("Menu Proveedor");
        String op="";
        while(!op.equals("4")){
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar Información de los pedidos.");
            System.out.println("2. Gestionar estado de los pedidos.");
            System.out.println("3. Registrar producto.");
            System.out.println("4. Consultar Informacion de los productos registrados.");
            System.out.println("5. Editar Información de los productos registrados.");
            System.out.println("6. Eliminar producto de los productos registrados.");
            System.out.println("7. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");
            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el proveedor
            switch(op){
                case "1":
                    //Menu CONSULTAR INFORMACIÓN DE LOS PEDIDOS
                    if(!currProv.consultarPedidos()){
                        System.out.println("NO HAY PEDIDOS...");
                    }
                    continue;
                case "2":
                    if(!currProv.gestionarPedidos()){
                        System.out.println("NO HAY PEDIDOS...");
                    }
                    System.out.println("Retornando al menu...");
                    continue;
                case "3":
                    if(!currProv.registrarProducto()){
                        System.out.println("HUBO UN ERROR AL REGISTRAR EL PRODUCTO.");
                    }
                    continue;
                case "4":
                    //Menu CONSULTAR Y EDITAR INFORMACIÓN DE LOS PRODUCTOS REGISTRADOS
                    if(!currProv.consultarProducto(productos)){
                        System.out.println("NO HAY PRODUCTOS REGISTRADOS...");
                    }
                    System.out.println("Retornando al menu...");
                    continue;
                case "5":
                    System.out.println("PRODUCTOS REGISTRADOS DEL PROVEEDOR.");
                    if(!currProv.consultarProducto(productos)){
                        System.out.println("NO HAY PRODUCTOS REGISTRADOS...");
                    }else{
                        System.out.println("Desea editar informacion de algun producto? (Si/No)");
                        String pregunta = sc.nextLine().toLowerCase();
                        if(pregunta.equals("si")){
                            System.out.println("Ingreso de filtros (Categoria y Nombre)");
                            System.out.println("Filtro por Categorias de los productos"
                                             + " cárnicos, vegetales, frutas, lácteos y conservas.");
                            System.out.println("Escriba categoria del producto que desea editar: ");
                            String categoria = sc.nextLine();
                            System.out.println("Escriba nombre de producto que desea editar:");
                            String nombre = sc.nextLine();
                            ArrayList<String> dataFiltro = new ArrayList<>();
                            dataFiltro.add(categoria);dataFiltro.add(nombre);
                            ArrayList<Producto> filtro = currProv.filtrarProducto(dataFiltro);
                            //hacer clear en consola
                            if(currProv.consultarProducto(filtro)){
                                System.out.println("Ingrese codigo del producto a editar: ");
                                String cod = sc.nextLine();
                                if(!currProv.editarProducto(cod)){
                                    System.out.println("Producto no fue editado...");
                                }else{
                                    System.out.println("Producto editado con exito.");
                                }
                            }
                        }
                    }
                    System.out.println("Retornando al menu...");
                    continue;
                    
                case "6":
                    if(!currProv.consultarProducto(productos)){
                        System.out.println("NO HAY PRODUCTOS REGISTRADOS.");
                    }else{
                        System.out.println("ELIMINACION DE UN PRODUCTO.");
                        System.out.println("SI DESEA ELIMINAR, INGRESE EL CODIGO DEL PRODUCTO A ELIMINAR y LA CANTIDAD A ELIMINAR:");
                        System.out.print("\nCodigo: ");
                        String cod = sc.nextLine();
                        System.out.println("Cantidad:");
                        String cant = sc.nextLine();
                        if(!currProv.eliminarProducto(cod, cant)){
                            System.out.println("NO SE ELIMINO EL PRODUCTO.");
                        }else{
                            System.out.println("Producto ha sido eliminado.");
                        }
                    }
                    System.out.println("Retornando al menu...");
                    continue;
                case "7":
                    
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
            System.out.println("2. Agregar producto al carrito de compras.");
            System.out.println("3. Ver carrito de compras");
            System.out.println("4. Mostrar pedidos realizados");
            System.out.println("5. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el proveedor
            switch(op){
                case "1":
                    //consultarProductos();
                    if(!currCl.consultarProducto(getProductosCercanos(currCl))){
                        System.out.println("No hay productos.");
                    }   
                    continue;
                case "2":
                    //agregarAlCarrito();
                    if(!currCl.consultarProducto(getProductosCercanos(currCl))){
                        System.out.println("No hay productos.");
                    }
                    System.out.println("Desea filtrar los productos por categoria, nombre(Completo o Parcial) y rango del precio del producto.");
                    System.out.println("Si/No:");
                    if(sc.nextLine().toLowerCase().equals("si")){
                        System.out.println("Ingrese categoria deseada: "
                                + "1. CARNICO\n"
                                + "2. VEGETAL\n"
                                + "3. FRUTA\n" 
                                + "4. LACTEO\n" 
                                + "5. CONSERVA");
                        String sel = sc.nextLine();
                        switch (sel){
                                case "1":
                                    sel = CATEGORIA.CARNICO.name();
                                case "2":
                                    sel = CATEGORIA.VEGETAL.name();
                                case "3":
                                    sel = CATEGORIA.FRUTA.name();
                                case "4":
                                    sel = CATEGORIA.LACTEO.name();
                                case "5":
                                    sel = CATEGORIA.CONSERVA.name();
                                default:    
                                    sel = "";
                        }
                        System.out.println("Ingrese nombre (Completo o Parcial)");
                        String name = sc.nextLine();
                        System.out.println("Ingrese Rango Inicial del Precio deseado:");
                        String rangoI = sc.nextLine();
                        System.out.println("Ingrese Rango Final del Precio deseado:");
                        String rangoFinal = sc.nextLine();
                        ArrayList<String> dataFiltro = new ArrayList<>();
                        dataFiltro.add(sel);dataFiltro.add(name);dataFiltro.add(rangoI);dataFiltro.add(rangoFinal);
                        ArrayList<Producto> filtrado = currCl.filtrarProducto(dataFiltro);
                        // aqui se hara clear
                        if(!currCl.consultarProducto(filtrado)){
                            System.out.println("NO PRODUCTOS CON LOS FILTROS MOSTRADOS");
                            continue;
                        }else{
                            System.out.println("Ingrese codigo del producto a agregar:");
                            String cod = sc.nextLine();
                            System.out.println("Ingrese cantidad de productos a agregar:");
                            String cant = sc.nextLine();
                            if(!currCl.agregarAlCarrito(cod, cant)){
                                System.out.println("No se agrego el producto.");
                            }
                        }
                    }
                    System.out.println("Retornando al menu Cliente");
                    continue;
                case "3":
                    menuCarritoCompra(currCl);
                    continue;
                case "4":
                    if(!currCl.consultarPedidos()){
                        System.out.println("No hay pedidos.");
                    }
                    continue;
                case "5":
                    //Salir del Menu Proveedor
                    break;
                    
                default:
                    System.out.println("Entrada no válida, ingrese 1, 2, 3 o 4");
                    break;
            }
        }
    }
    public void menuCarritoCompra(Cliente currCl){
       System.out.println("Menu Carrito de compras");
        String op = "";
        while(!op.equals("4")){
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar carrito de compras");
            System.out.println("2. Eliminar producto de carrito de compras");
            System.out.println("3. Comprar"); 
            System.out.println("4. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese una opcion: ");

            op = sc.nextLine();
            
            //hace una opcion de acuerdo a lo ingresado por el proveedor
            switch(op){
                case "1":
                    if(!currCl.consultarProducto(currCl.getCarrito().getProductos())){
                        System.out.println("No hay productos");
                    }
                    System.out.println("Retornando al menú...");
                    continue;
                case "2":
                    if(!currCl.consultarProducto(currCl.getCarrito().getProductos())){
                        System.out.println("No hay productos");
                    }
                    else{
                        System.out.println("Ingrese el código del producto que desea eliminar");
                        String cod = sc.nextLine();
                        if(!currCl.getCarrito().eliminarProdCarrito(cod)){
                            System.out.println("No se eliminó el producto");
                        }
                    }
                    System.out.println("Retornando al menú...");
                    continue;
                case "3":
                    //se pregunta si se desea comprar, luego se debe ingresar el metodo de pago del cliente, se genera pedido, se confirma la compra, 
                    // pedido unico por cada proveedor usado, cambio de estado de pedido, envio de mail, si es tarjeta confirmar codigo, si es paypal depende de fondos
                    // finalizar compra...
                    if(!currCl.consultarProducto(currCl.getCarrito().getProductos())){
                        System.out.println("No hay productos");
                    }else{
                        System.out.println("Desea comprar los articulos mostrados? (si/no)");
                        if(sc.nextLine().toLowerCase().equals("si")){
                            Pago pago = null;
                            System.out.println("Ingrese que metodo pago a usar (digite el numero)? (1. Metodo Pago Registrado, 2. Otro, Cualquier otra tecla cancelara la compra.)");
                            String opcion = sc.nextLine();
                            if(opcion.equals("1")){
                                if(currCl.getFormaPago()==null){
                                    System.out.println("NO HAY METODO DE PAGO REGISTRADO. SE LE PEDIRA OTRO.");
                                }else{
                                    pago = currCl.getFormaPago();
                                }
                            }else if(opcion.equals("2")){
                                System.out.println("Digite metodo de pago que desee (1. Tarjeta, 2.PayPal, Otro numero para cancelar compra.)\n");
                                opcion = "";
                                opcion = sc.nextLine();
                                if(opcion.equals("1")){
                                    System.out.println("METODO PAGO TARJETA.");
                                    System.out.println("Ingrese tipo de tarjeta: ");
                                    String tipo = sc.nextLine();
                                    System.out.println("Ingrese numero de tarjeta: ");
                                    String numT = sc.nextLine();
                                    System.out.println("Ingrese nombre del titular: ");
                                    String nombreTitular = sc.nextLine();
                                    pago = new PagoTarjeta(tipo, numT, nombreTitular);
                                }else if(opcion.equals(2)){
                                    System.out.println("METODO PAGO PAYPAL");
                                    System.out.println("Ingrese usuario: ");
                                    String user = sc.nextLine();
                                    System.out.println("Ingrese contraseña: ");
                                    String pass = sc.nextLine();
                                    pago = new PagoPayPal(user, pass);
                                }else{
                                    continue;
                                }
                            }else{
                                continue;
                            }
                            ArrayList<Pedido> pedidos = currCl.generarPedidos(currCl.getCarrito(), pago);
                            if(pedidos==null){
                                System.out.println("No se pudo crear los pedidos.");
                            }else{
                                System.out.println("Se ingreso metodo de pago.");
                                System.out.println("Se procedera a enviar un correo electronico...");
                                if(pago instanceof PagoTarjeta){
                                    PagoTarjeta pagoT = (PagoTarjeta) pago;
                                    if(!Email.enviarEmailConfirmacion(currCl.getCorreo(), Sistema.PedidoEmail(pedidos))){
                                        System.out.println("CORREO DE CONFIRMACION NO ENVIADO...");
                                        currCl.removerPedidosGenerados(pedidos);
                                    }else{
                                        for(int i=0;i<3;i++){
                                            System.out.println("Ingrese codigo de confirmacion de compra...");
                                            if(sc.nextLine().equals(Sistema.codigo)){
                                                System.out.println("COMPRA HA SIDO FINALIZADA.");
                                                currCl.getCarrito().getProductos().clear();
                                                if(!pagoT.procesarPago(currCl.getCorreo(), Sistema.PedidoEmail(pedidos))){
                                                    System.out.println("No se envio el email de agradecimiento... ");
                                                }
                                                break;
                                            }else{
                                                System.out.println("CODIGO INCORRECTO");
                                                System.out.println("Tiene 3 intentos mas sino su compra sera cancelada.");
                                                if(i==2){
                                                    currCl.removerPedidosGenerados(pedidos);
                                                }
                                            }
                                        }
                                        
                                    }
                                }else if(pago instanceof PagoPayPal){
                                    PagoPayPal pagoP = (PagoPayPal) pago;
                                    if(!pagoP.procesarPago(opcion, Sistema.PedidoEmail(pedidos))){
                                        System.out.println("No tiene fondos suficientes para pagar.");
                                        currCl.removerPedidosGenerados(pedidos);
                                    }else{
                                        System.out.println("COMPRA HA SIDO FINALIZADA.");
                                        currCl.getCarrito().getProductos().clear();
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Retornando al menú...");
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
            if(user.isBlank() || user.isEmpty()){
                valida = false;
            }
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
    
    public static void actualizarData(){
        productos.clear();
        pedidos.clear();
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        if(usuarios!=null){
            for(Usuario u:usuarios){
                if(u instanceof Cliente){
                    Cliente c = (Cliente) u;
                    for(Pedido p:c.getPedidos()){
                        Sistema.getProveedor(p.getProductosPedidos().get(0).getVendedor().getUser()).addPedido(p);
                    }
                }
            }
            for(Usuario u : usuarios){
                if(u instanceof Proveedor){
                    Proveedor prov = (Proveedor) u;
                    ArrayList<Pedido> pedidoList = prov.getPedidos();
                    ArrayList<Producto> prodList = prov.getOferta();
                    if(pedidoList != null ){
                        if(!pedidoList.isEmpty()){
                            for(Pedido p : pedidoList){
                                pedidos.add(p);
                            }
                        }
                    }
                    if(prodList != null){
                        if(!prodList.isEmpty()){
                            for(Producto p : prodList){
                                productos.add(p);
                            }
                        }
                    }
                }
            }
        }
    }
    public static Proveedor getProveedor(String user){
        if(usuarios == null){return null;}
        if(usuarios.isEmpty()){return null;}
        if(user.isBlank()||user.isEmpty()){return null;}
        for(Usuario u:usuarios){
            if(u instanceof Proveedor){
                if(u.getUser().equals(user)){
                    Proveedor p = (Proveedor) u;
                    return p;
                }
            }
        }
        return null;
    }
    
    public static ArrayList<Producto> getProductosCercanos(Cliente c){
        if(productos == null){return null;}
        if(productos.isEmpty()){return null;}
        if(c == null){return null;}
        ArrayList<Producto> busq = new ArrayList<>();
        for(Producto prod : productos){
            Proveedor pv = prod.getVendedor();
            if(pv == null){return null;}
            double distancia = Coordenada.calcularDistancia(c.getUbicacion(), pv.getUbicacion());
            if(distancia<=50){
                busq.add(prod);
            }
        }
        return busq;
    }
    public static ArrayList<String> PedidoEmail(ArrayList<Pedido> pedidos){
        if(pedidos==null){return null;}
        if(pedidos.isEmpty()){return null;}
        ArrayList<String> pedidosFormat = new ArrayList<>();
        for(Pedido p: pedidos){
            String pedidoAct = p.toString();
            pedidosFormat.add(pedidoAct);
        }
        return pedidosFormat;
    }       
    public static void main(String[] args) {
        Sistema ui = new Sistema();
        ui.inicializarDatos();
        ui.menu();
        
    }
    
}
