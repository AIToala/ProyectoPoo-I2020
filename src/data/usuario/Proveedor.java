/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import Interfaz.Sistema;
import data.pedido.ESTADO;
import data.producto.Producto;
import data.pedido.Pedido;
import data.producto.CATEGORIA;
import java.util.ArrayList;
import java.util.Scanner;
import data.mail.Email;
import java.time.LocalDateTime;
/**
 * Clase que representa a uno de los actores del Sistema de AgroStoreNU
 * Proveedor extiende de Usuario.
 * Proveedor registra productos nuevos para el sistema y gestiona los pedidos que se realizan.
 * @author Grupo1ProyectoPOO
 */
public class Proveedor extends Usuario{
    //Variables de instancia.
    private String contacto;
    private ArrayList<Producto> oferta;
    private ArrayList<Pedido> pedidos;
    /**
     * Constructor que instancia a un Proveedor.
     * @param user      Usuario unico del Cliente. (Heredado del Padre)
     * @param password  Contrasena del Cliente. (Heredado del Padre)
     * @param nombre    Nombre del Cliente. (Heredado del Padre)
     * @param id        ID Unica del Cliente. (Heredado del Padre)
     * @param direccion Direccion del Cliente. (Heredado del Padre)
     * @param ubicacion Ubicacion del Cliente. (Heredado del Padre)
     * @param correo    Correo del Cliente. (Heredado del Padre)
     * @param contacto  Numero de contacto del Proveedor.
     */
    public Proveedor(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo, String contacto) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.contacto = contacto;
        this.oferta = new ArrayList<>();
        this.pedidos = new ArrayList<>();

    }
    //Usado unicamente para propositos de inicializacion de sistema.

    public Proveedor(String user, String password, String nombre, String id, String direccion, Coordenada ubicacion, String correo,
                     String contacto, ArrayList<Producto> oferta, ArrayList<Pedido> pedidos) {
        super(user, password, nombre, id, direccion, ubicacion, correo);
        this.contacto = contacto;
        this.oferta = oferta;
        this.pedidos = pedidos;
    }
    /**
     * Metodo que retorna la variable de instancia contacto de Proveedor.
     * @return contacto del proveedor.
     */
    public String getContacto() {
        return contacto;
    }
    /**
     * Metodo que modifica la variable de instancia contacto de Proveedor.
     * @param contacto contacto del proveedor.
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    /**
     * Metodo que retorna la variable de instancia oferta de Proveedor.
     * oferta es una lista de Producto.
     * @return Lista de Producto ofertados.
     */
    public ArrayList<Producto> getOferta() {
        return oferta;
    }
    /**
     * Metodo que retorna la variable de instancia pedidos de Proveedor.
     * pedidos es una lista de Pedidos.
     * @return Lista de Pedidos que gestiona.
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    /**
     * Metodo que agrega un pedido a la variable de instancia pedidos de Proveedor.
     * @param p Pedido que se desea agregar.
     */
    public void addPedido(Pedido p){
        this.pedidos.add(p);
    }
    /**
     * Metodo que agrega un producto a la variable de instancia oferta de Proveedor.
     * @param p Producto que se desea agregar.
     */
    public void addProducto(Producto p){
        this.oferta.add(p);
    }
    /**
     * Metodo que agrega todos los elementos de la lista de Producto dada a la variable de instancia oferta de Proveedor.
     * oferta es una Lista de Producto.
     * @param p Lista de productos que se desean agregar sus elementos.
     */
    public void addProductos(ArrayList<Producto> p){
        if(p!=null){
            if(!p.isEmpty()){
                for(Producto pr : p){
                    addProducto(pr);
                }
            }
        }
    }
    /**
     * Metodo que muestra los productos dados en una Lista de Producto.
     * @param productos lista de Productos
     * @return          verdadero si la lista no es null ni vacia, falso caso Contrario.
     */
    @Override
    public boolean consultarProducto(ArrayList<Producto> productos){
        if(productos == null){return false;}
        if(productos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PRODUCTOS DEL PROVEEDOR " + this.getUser());
        for(Producto prod : productos){
            System.out.print("\nCODIGO     : " + prod.getCodigo() + "|");
            System.out.print("\nNOMBRE     : " + prod.getNombre() + "|");
            System.out.print("\nCATEGORIA  : " + prod.getCategoria() + "|");
            System.out.println("\n---------------------------------");
        }
        return true;
    }
    /**
     * Metodo que filtra los productos de la variable de instancia oferta de un Proveedor por la lista de filtros dada.
     * oferta es una lista de Productos.
     * @param dataFiltro    Lista de filtros (categoria, nombre, rango Inicial del Precio, rango Final del precio)
     * @return              Lista de Producto filtrado.
     */
    @Override
    public ArrayList<Producto> filtrarProducto(ArrayList<String> dataFiltro){
        if(dataFiltro == null){
            System.out.println("NO FILTROS PASADOS.");
            return null;
        }
        if(dataFiltro.size()>2){
            System.out.println("FILTROS NO VALIDOS");
            return null;
        }
        String categoria = dataFiltro.get(0).toUpperCase();
        String nombre = dataFiltro.get(1).toLowerCase();
        if(oferta == null){
            return null;
        }
        if(oferta.isEmpty()){
            return null;
        }
        ArrayList<Producto> filtrado = new ArrayList<>();
        for(Producto p : oferta){
            //System.out.println(p.getCategoria().get(0));
            if(p.getCategoria().contains(categoria) && p.getNombre().toLowerCase().contains(nombre) && !nombre.isEmpty()){
                filtrado.add(p);
            }else if(p.getCategoria().contains(categoria) && nombre.equals("")){
                filtrado.add(p);
            }else if(p.getNombre().toLowerCase().contains(nombre) && categoria.equals("")){
                filtrado.add(p);
            }else if(nombre.equals("") && categoria.equals("")){
                filtrado.add(p);
            }
        }
        if(filtrado.isEmpty()){
            return null;
        }
        return filtrado;
    
    }
    /**
     * Metodo que consulta los pedidos que posee el Proveedor en su variable de instancia pedidos
     * pedidos es una lista de Pedidos.
     * @return  verdadero si la lista de Pedido no es nula ni vacia, falso caso contrario.
     */
    @Override
    public boolean consultarPedidos(){
        if(pedidos == null){return false;}
        if(pedidos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PEDIDOS REALIZADO AL PROVEEDOR " + this.getUser());
        for(Pedido pedido : pedidos){
            System.out.println("CODIGO     : " + pedido.getCodigo()+ "|");
            System.out.println("FECHA INICIO   : " + pedido.getFechas().get(0));
            System.out.println("PRODUCTOS:");
            if(pedido.getProductosPedidos() != null || !pedido.getProductosPedidos().isEmpty()){
                ArrayList<Producto> productosU = Producto.getProductosUnicos(pedido.getProductosPedidos());
                ArrayList<Integer> cantidad = Producto.getCantidadProducto(pedido.getProductosPedidos());
                System.out.println("CODIGO | NOMBRE | CANTIDAD");
                for(Producto p : productosU){
                    int index = productosU.indexOf(p);
                    System.out.println(p.getCodigo()+"|"+p.getNombre()+"|"+cantidad.get(index));                    
                }
            }else{
                System.out.println("No hay productos...");
            }
            System.out.println("\nDATOS DEL CLIENTE  /n" + 
                             "Nombre: " + pedido.getCliente().getNombre() + "\n" + 
                             "Direccion: " + pedido.getCliente().getDireccion() + "\n" +
                             "Numero Telefonico: " + pedido.getCliente().correo);
            System.out.println("DATOS DEL METODO DE PAGO  : " + pedido.getMetodoPago().toString());
            System.out.println("\nTOTAL DEL PEDIDO  : " + pedido.getTotalPagar());

            System.out.println("---------------------------------");
        }
        return true;
    }
    /**
     * Metodo que registra nuevos productos dentro de la variable de instancia oferta de un Proveedor.
     * Al registrar el producto valida que sus datos sean correctos y si no lo son los retorna al menu anterior.
     * Registra tambien una cantidad dada de elementos.
     * @return  Verdadero si se agrega un Producto, falso caso contrario.
     */
    public boolean registrarProducto(){
        if(oferta == null){this.oferta = new ArrayList<>();}
        Scanner sc = new Scanner(System.in);
        System.out.println("REGISTRANDO NUEVO PRODUCTO.");
        System.out.println("Ingrese codigo unico de su producto a registrar: ");
        String codigo = sc.nextLine();
        if(!Producto.esProductoUnico(Sistema.productos, codigo)){
            System.out.println("El codigo ingresado ya existe dentro del sistema. Ingrese nuevamente.");
            return false;
        }
        boolean valida = false;
        while(!valida){
            System.out.println("Ingrese nombre del producto:");
            nombre = sc.nextLine();
            if(nombre.equals("")){System.out.println("Por favor ingrese un nombre valido.");}
            else{valida=true;}
        }
        System.out.println("Ingrese breve descripcion del producto:");
        String descr = sc.nextLine();
        System.out.println("ESCOJA LAS CATEGORIAS A LAS QUE PERTENECE EL PRODUCTO.");
        boolean salir = false;
        CATEGORIA elegida = null;
        ArrayList<CATEGORIA> categorias = new ArrayList<>();
        while(!salir){
            System.out.println("Ingrese el numero de la categoria a añadir al producto "
                    + "\n(Categorias disponibles\n1. CARNICO.\n2. VEGETAL\n3. FRUTA\n4. LACTEO\n5. CONSERVA");
            String cat = sc.nextLine();
            switch(cat){
                case "1":
                    elegida = CATEGORIA.CARNICO;
                    break;
                case "2":
                    elegida = CATEGORIA.VEGETAL;
                    break;
                case "3":
                    elegida = CATEGORIA.FRUTA;
                    break;
                case "4":
                    elegida = CATEGORIA.LACTEO;
                    break;
                case "5":
                    elegida = CATEGORIA.CONSERVA;
                    break;
                default:
                    System.out.println("Elija correctamente...");
            }
            if(elegida != null){categorias.add(elegida);}
            System.out.println("Desea añadir otra categoria mas?: (Si/No)");
            String resp = sc.nextLine().toLowerCase();
            if(resp.equals("si")){
                salir = false;
            }else if(resp.equals("no") && elegida==null ){
                if(elegida==null){System.out.println("Ingrese por lo menos una categoria.");}
            }else if(resp.equals("no")){
                salir = true;
            }
        }
        System.out.println("Ingrese costo Unitario del producto:");
        String precio = sc.nextLine();
        double costoU;
        try{
            costoU = Double.parseDouble(precio);
        }catch(NumberFormatException e){
            System.out.println("Valor no numerico.");
            return false;
        }
        System.out.println("INGRESE CANTIDAD DEL PRODUCTO DESCRITO A REGISTRAR: ");
        String cant = sc.nextLine();
        int cantidad;
        try{
            cantidad = Integer.parseInt(cant);
        }catch(NumberFormatException e){
            System.out.println("Valor no numerico");
            return false;
        }
        int i=0;
        while(i<cantidad){
            Producto newProd = new Producto(codigo, this, nombre, descr, categorias, costoU); 
            oferta.add(newProd);
            i++;
        }
        return true;
    }
    /**
     * Metodo que gestiona los pedidos cambia su estado ya sea a Procesando o Despachando segun corresponda.
     * Si el estado actual del pedido es Inicializado lo cambia a procesado
     * Si el estado actual del pedido es Procesado lo cambia a despachado.
     * @return  verdadero si existen los pedidos y se cambia su estado, falso caso contrario
     */
    public boolean gestionarPedidos(){
        if(!consultarPedidos()){
            return false;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Que pedido desea cambiar su estado?.");
        System.out.println("Ingrese el codigo del pedido:");
        String cod = sc.nextLine();
        for(Pedido p : pedidos){
            if(p.getCodigo().equals(cod) && p.getEstado().equals(ESTADO.SOLICITADO)){
                System.out.println("EL ESTADO ACTUAL DEL PEDIDO ES SOLICITADO.");
                System.out.println("DESEA CAMBIAR SU ESTADO A PROCESANDO? (si/no):");
                if(sc.nextLine().toLowerCase().equals("si")){
                    p.setEstado(ESTADO.PROCESANDO);
                    p.setFechas(LocalDateTime.now());
                    return true;
                }else {
                    return false;
                }
            }else if(p.getCodigo().equals(cod) && p.getEstado().equals(ESTADO.PROCESANDO)){
                System.out.println("EL ESTADO ACTUAL DEL PEDIDO ES PROCESANDO.");
                System.out.println("DESEA CAMBIAR SU ESTADO A DESPACHADO? (si/no):");
                if(sc.nextLine().toLowerCase().equals("si")){
                    p.setEstado(ESTADO.PROCESANDO);
                    p.setFechas(LocalDateTime.now());
                    return true;
                }else {
                    return false;
                }
            }else if(p.getCodigo().equals(cod) && p.getEstado().equals(ESTADO.DESPACHADO)){
                System.out.println("EL PEDIDO HA SIDO DESPACHADO.");
                System.out.println("ENVIANDO CORREO DE ENVIO EXITOSO A CLIENTE.");
                String msg = "SU PEDIDO HA SIDO ENVIADO A LA DIRECCION PROPORCIONADA. CHEQUEE EL ESTADO DE SUS PEDIDOS EN AGROSTORENU.";
                Email e = new Email(p.getCliente().getCorreo(), msg);
                if(!e.enviarEmail()){
                    System.out.println("ERROR AL ENVIAR EL CORREO.");
                }else{
                    System.out.println("CORREO ENVIADO.");
                }
            }
        }
        sc.close();
        return true;
    }
    /**
     * Metodo que elimina el producto seleccionado por su codigo una cantidad de veces.
     * Si la cantidad de veces es mayor a la cantidad actual borra todas las que pueda.
     * @param cod       codigo unico del producto a eliminar.
     * @param cantidad  cantidad del producto a eliminar
     * @return          verdadero si borra algun producto, falso caso contrario.
     */
    public boolean eliminarProducto(String cod, String cantidad){
        if(cod.isEmpty()){return false;}
        int cant = 0;
        try{
            cant = Integer.parseInt(cantidad);
        }catch(NumberFormatException e){
            return false;
        }
        if(cant == 0){return false;}
        if(oferta == null){return false;}
        if(oferta.isEmpty()){return false;}
        int i = 0;
        boolean borro = false;
        int cantidadEnOferta = Producto.getCantidadProductoUnico(oferta, cod);
        if(cant <= cantidadEnOferta){
            while(i<cant){
                oferta.remove(Producto.getProducto(oferta, cod));
                i++;
            }
        }else if(cant>cantidadEnOferta){
            while(i<cantidadEnOferta){
                oferta.remove(Producto.getProducto(oferta, cod));
                i++;
            }
        }
        return i>0;
    }
    /**
     * Metodo para editar un producto dado un Producto seleccionado.
     * Se procede a pedir toda la informacion del producto a editar si uno de los datos no es valido,
     * se retorna al menu anterior.
     * @param prod  Producto a editar de la lista de Producto oferta
     * @return      verdadero si el producto fue editado con exito, falso caso contrario.
     */
    public boolean editarProducto(Producto prod){
        if(prod == null){return false;}
        if(oferta == null){return false;}
        if(oferta.isEmpty()){return false;}
        for(Producto prodOferta: oferta){
            if(prod.getCodigo().equals(prodOferta.getCodigo())){
                prodOferta.setNombre(prod.getNombre());
                prodOferta.setDescripcion(prod.getDescripcion());
                for (CATEGORIA cat : prod.getCATEGORIAS()){
                    prodOferta.setCategoria(cat);
                }        
                prodOferta.setCostoUnitario(prod.getCostoUnitario());
                
            }
        }
        return true;
    }
}