/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;
import data.pedido.ESTADO;
import data.producto.Producto;
import data.pedido.Pedido;
import data.producto.CATEGORIA;
import java.util.ArrayList;
import java.util.Scanner;
import data.mail.Email;
import java.time.LocalDateTime;
/**
 *
 * @author Usuario
 */
public class Proveedor extends Usuario{
    private String contacto;
    private ArrayList<Producto> oferta;
    private ArrayList<Pedido> pedidos;

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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public ArrayList<Producto> getOferta() {
        return oferta;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    public void addPedido(Pedido p){
        this.pedidos.add(p);
    }
    
    public void addProducto(Producto p){
        this.oferta.add(p);
    }

        
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
            System.out.println("---------------------------------");
        }
        return true;
    }
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
                System.out.println("if 1");
            }else if(p.getCategoria().contains(categoria) && nombre.equals("")){
                filtrado.add(p);
                System.out.println("if 2");
            }else if(p.getNombre().toLowerCase().contains(nombre) && categoria.equals("")){
                filtrado.add(p);
                System.out.println("if 3");
            }else if(nombre.equals("") && categoria.equals("")){
                filtrado.add(p);
                System.out.println("if 4");
            }
        }
        if(filtrado.isEmpty()){
            return null;
        }
        return filtrado;
    
    }
    
    @Override
    public boolean consultarPedidos(){
        if(pedidos == null){return false;}
        if(pedidos.isEmpty()){return false;}
        System.out.println("---------------------------------");
        System.out.println("PEDIDOS REALIZADO AL PROVEEDOR " + this.getUser());
        for(Pedido pedido : pedidos){
            System.out.println("CODIGO     : " + pedido.getCodigo()+ "|");
            System.out.println("FECHA INICIO   : " + pedido.getFechas().get(0));
            System.out.println("PRODUCTOS:" + pedido.getProductosPedidos() + "|");
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
            System.out.println("\nDATOS DEL CLIENTE  : " + 
                             "Nombre: " + pedido.getCliente().getNombre() + "\n" + 
                             "Direccion: " + pedido.getCliente().getDireccion() + "\n" +
                             "Numero Telefonico: " + pedido.getCliente().correo);
            System.out.println("DATOS DEL METODO DE PAGO  : " + pedido.getMetodoPago().toString());
            System.out.println("\nTOTAL DEL PEDIDO  : " + pedido.getTotalPagar());

            System.out.println("---------------------------------");
        }
        return true;
    }
    
    public boolean registrarProducto(){
        if(oferta == null){this.oferta = new ArrayList<>();}
        ArrayList<Producto> productosU = Producto.getProductosUnicos(oferta);
        Scanner sc = new Scanner(System.in);
        if(productosU == null){ productosU = new ArrayList<>();}
        System.out.println("REGISTRANDO NUEVO PRODUCTO.");
        System.out.println("Ingrese codigo unico de su producto a registrar: ");
        String codigo = sc.nextLine();
        if(!Producto.esProductoUnico(oferta, codigo)){
            System.out.println("El codigo ingresado ya existe. Ingrese nuevamente.");
            return false;
        }
        System.out.println("Ingrese nombre del producto:");
        String nombre = sc.nextLine();
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
                case "2":
                    elegida = CATEGORIA.VEGETAL;
                case "3":
                    elegida = CATEGORIA.FRUTA;
                case "4":
                    elegida = CATEGORIA.LACTEO;
                case "5":
                    elegida = CATEGORIA.CONSERVA;
                default:
                    System.out.println("Elija correctamente...");
                    salir = true;
            }
            if(elegida != null){categorias.add(elegida);}
            System.out.println("Desea añadir otra categoria mas?: (Si/No)");
            String resp = sc.nextLine().toLowerCase();
            if(resp.equals("si")){
                salir = false;
            }else{
                salir = true;
            }
        }
        if(elegida == null){
            elegida = CATEGORIA.CONSERVA;
        }
        System.out.println("Ingrese costo Unitario del producto:");
        String precio = sc.nextLine();
        double costoU;
        try{
            costoU = Double.parseDouble(precio);
        }catch(Exception e){
            System.out.println("Valor no numerico.");
            return false;
        }
        System.out.println("INGRESE CANTIDAD DEL PRODUCTO DESCRITO A REGISTRAR: ");
        String cant = sc.nextLine();
        int cantidad;
        try{
            cantidad = Integer.parseInt(cant);
        }catch(Exception e){
            System.out.println("Valor no numerico");
            return false;
        }
        
        for(int i=0; i<cantidad;i++){
            Producto newProd = new Producto(codigo, this, nombre, descr, categorias, costoU); 
            oferta.add(newProd);
            
        }
        sc.close();
        return true;
    }
    
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
        for(Producto prod : oferta){
            if(i<cant){
                if(prod.getCodigo().equals(cod)){
                    oferta.remove(prod);
                    borro = true;
                    i++;
                }
            }else{
                break;
            }
        }
        return borro;
    }
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
