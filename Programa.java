package programa;
//Librerias de lectura de teclado
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


//librerias de BD
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Programa {
    public static void main(String[] args) {

        //iniciamos la conexion con la bd
        ODB odb = ODBFactory.open("neodatis.test");

        if (args.length > 1) { //si hay más de 1 parámetro
            System.out.println("Hay demasiados parámetros. Debe escribir un solo parámetro");
        }
        else if (args.length == 0)
        {
            //si no hay parámetros
            //iniciamos el bucle de usuario con el controlador de fin seleccionado por el usuario
            boolean fin = false;
            int eleccion; // variable para almacenar la selección del usuario;

            while (!fin) {
                eleccion = pintarMenu();
                switch (eleccion) {
                    case (1):
                        insertarDatosEjemplo(odb);
                        break;
                    case (2):
                        visualizardatos(odb);
                        break;
                    case (3):
                        exportarDatos(odb, 0);
                        break;
                    case (0):
                        fin = true;
                        System.out.println("Saliendo del programa");
                        System.out.println("Hasta otra!! Bey bey");
                        //cerramos la base de datos
                        odb.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no valida, inserte un nuevo valor");
                        break;
                }
            }
        } else {
            exportarDatos(odb, Integer.parseInt(args[0]));
        }

    }

    //Metodo que pinta el menu de interacción con el usuario;
    private static int pintarMenu() {
        //variables locales para uso del menu
        int elec;
        Scanner teclado = new Scanner(System.in);

        //Pintando el menu
        System.out.println("\n\n---------------------------------------------------------------");
        System.out.println("|------------------------------Menu---------------------------|");
        System.out.println("---------------------------------------------------------------");
        System.out.println("---- 1. Insertar Datos de ejemplo");
        System.out.println("---- 2. Mostrar listado de proveedores");
        System.out.println("---- 3. Exportar datos");
        System.out.println("---- 0. Salir");
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.print("Inserte la elección ->");

        //leemos la eleccion
        elec = teclado.nextInt();
        //limpiamos el buffer
        teclado.nextLine();
        //retorna la eleccion escogida
        return elec;
    }

    // metodo que pinta el menú secundario de interacción con el usuario
    // el metodo recibe 1 parametro para cambiar el texto a mostrar
    private static int pintarMenuSecundario( int mostrar) {
        //variables locales para uso del menu
        int elec;
        Scanner teclado = new Scanner(System.in);

        //Pintando el menu
        System.out.println("\n");
        if(mostrar == 1)
            System.out.println("----------------Mostrar listado de proveedores-----------------");
        else if (mostrar == 2)
            System.out.println("----------------Exportar listado de proveedores-----------------");
        else
            System.out.println("----------------Listado de proveedores-----------------");

        System.out.println("---------------------------------------------------------------");
        System.out.println("---- 0. Salir");
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        System.out.print("Inserte la el id de cliente para buscar sus proveedores ->");

        //leemos la eleccion
        elec = teclado.nextInt();
        //limpiamos el buffer
        teclado.nextLine();
        //retorna la eleccion escogida
        return elec;
    }

    // Metodo que añade datos de prueba a la base de datos para poder hacer las comprobaciones
    private static void insertarDatosEjemplo(ODB odb)
    {
        //Instancias de clientes para almacenar en la DB:
        Clientes c1 = new Clientes(1, "Juan");
        Clientes c2 = new Clientes(2, "Paco");
        Clientes c3 = new Clientes(3, "Pepe");
        Clientes c4 = new Clientes(4, "Miguel");

        //instancias de proveedores para almacenar en BD
        Proveedores p1 = new Proveedores(1, "Coca-cola","14/11/1998", 1);
        Proveedores p2 = new Proveedores(2, "Pepsi", "14/11/1998");
        Proveedores p3 = new Proveedores(3, "Redbul", "14/11/1998");
        Proveedores p4 = new Proveedores (4, "Monster","14/11/1998", 4);
        Proveedores p5 = new Proveedores (5, "Nestea","14/11/1998", 4);

        //Almaceno los objetos en la BD
        odb.store (c1);
        odb.store (c2);
        odb.store (c3);
        odb.store (c4);

        //Almaceno los objetos en la BD
        odb.store (p1);
        odb.store (p2);
        odb.store (p3);
        odb.store (p4);
        odb.store (p5);

        //recupero todos los objetos
        Objects objectsprov = odb.getObjects(Proveedores.class);
        System.out.println("Hay "+ objectsprov.size()+" Proveedores en la BD:");
        //visualizar los objetos

        while(objectsprov.hasNext()) {
            // Creo un objeto proveedores y almaceno ahí el objeto que recupero de la BD
            Proveedores prov = (Proveedores) objectsprov.next();
            // Imprimo las propiedades que me interesan de ese objeto
            System.out.println(prov.getId_proveedor()+", "+prov.getNombre()+","+ prov.getFecha_de_alta()+", "+ prov.getId_cliente());
        }

        odb.close(); //Cerrar BD
    }

    //metodo que recorre la base de datos del id de proveedor seleccionado para mostrar sus datos
    private static void visualizardatos(ODB odb)
    {
        boolean fin = false;
        int eleccion; // variable para almacenar la selección del usuario;

        while (!fin)
        {
            //recoger la elección del usuario
            eleccion = pintarMenuSecundario( 1 );
            if(eleccion != 0) {
                //cargamos la query para consultar en la base de datos
                IQuery query = new CriteriaQuery(Proveedores.class, Where.equal("id_cliente", eleccion));
                query.orderByAsc("id_proveedor");
                Objects objects = odb.getObjects(query);

                if (objects.size() > 0)
                {
                    //recorremos y pintamos los resultados en pantalla
                    while(objects.hasNext()) {
                        Proveedores prov = (Proveedores) objects.next();
                        System.out.println(prov.getId_proveedor()+", "+prov.getNombre()+","+ prov.getFecha_de_alta()+", "+ prov.getId_cliente());
                    }
                }
                else {
                    System.out.println("El cliente no tiene proveedores asignados.");
                }
            }
            else{
                fin = true;
            }
        }
    }

    //metodo que exporta en la raiz del programa el fichero datos.txt con la información del cliente seleccionado
    private static void exportarDatos(ODB odb, int parametro)
    {
        boolean fin = false;
        int eleccion; // variable para almacenar la selección del usuario;

        while (!fin)
        {
            //compruebo si traemos un parametro en la llamada al programa
            if(parametro > 0) {
                eleccion = parametro;
                fin = true;
            }
            else {
                eleccion = pintarMenuSecundario(2);
            }

            //Buscamos el cliente en la bas ede datos
            if(eleccion != 0) {
                //cargamos la query
                IQuery query = new CriteriaQuery(Proveedores.class, Where.equal("id_cliente", eleccion));
                query.orderByAsc("id_proveedor");
                Objects objects = odb.getObjects(query);

                //inicialización de variables
                FileWriter fichero = null;
                PrintWriter pw;
                if (objects.size() > 0)
                {
                    //abrimos el fichero en modo lectura
                    try {
                        fichero = new FileWriter("datos.txt");
                        pw = new PrintWriter(fichero);
                        //escribimos el fichero
                        while(objects.hasNext()) {
                            Proveedores prov = (Proveedores) objects.next();
                            pw.println(prov.getId_proveedor() + ", " + prov.getNombre() + "," + prov.getFecha_de_alta() + ", " + prov.getId_cliente());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // Aprovechamos el finally para asegurarnos que se cierra el fichero.
                            if (null != fichero) {
                                fichero.close();
                                System.out.println("Fichero creado correctamente.");
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                else {
                    System.out.println("El cliente no tiene proveedores asignados.");
                }
            }
            else{
                fin = true;
            }
        }
    }
}

