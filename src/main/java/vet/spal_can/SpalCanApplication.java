package vet.spal_can;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vet.spal_can.modelo.Cliente;
import vet.spal_can.servicio.IClienteServicio;

@SpringBootApplication
public class SpalCanApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    private static final Logger logger = LoggerFactory.getLogger(SpalCanApplication.class); //Crea un Log de la clase

    public static void main(String[] args) {

        logger.info("Iniciando la aplicación");

        //Spring llama al método run
        SpringApplication.run(SpalCanApplication.class, args);

        logger.info("Cerrando la aplicación");
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("\n*** SPAL-CAN APP ***");

        Scanner teclado = new Scanner(System.in);
        boolean salir = false;

        //Bucle "While" para salir de la app en cuanto la condición cambie
        while (!salir) {

            try {

                //Metodo que mostrará el menú de la App con sus opciones
                int opcion = mostrarMenu(teclado);

                //Metodo que ejecutará una opción desplegada en el menú de la app
                salir = ejecutarOpcion(opcion, teclado);

            } catch (Exception e) {
                logger.info("Error al elegir una opción: " + e.getMessage());
            }
        }
    }

    //Método para desplegar el menú de la app
    public int mostrarMenu(Scanner teclado) {

        //Creamos esta variable para depositar el la opcion que haya elegido el usuario
        int opcion = 0;

        logger.info("""
                    
                        1. Listar clientes
                        2. Buscar clientes por ID
                        3. Agregar un nuevo cliente
                        4. Modificar cliente
                        5. Eliminar cliente
                        6. Salir
                        
                        Elige una opción: """);

        opcion = Integer.parseInt(teclado.nextLine());

        return opcion;
    }

    public boolean ejecutarOpcion(int opcion, Scanner teclado) {

        boolean salir = false;

        //inicializamos las variables que utilizaremos.
        int id = 0;
        String nombre = "";
        String apellido = "";
        String especie = "";
        String raza = "";
        String genero = "";
        String nombreAnimal = "";

        switch (opcion) {
            case 1: //Crear lista de clientes
                logger.info("--- Listado de clientes ---");
                List<Cliente> clientes = clienteServicio.listarCliente();
                clientes.forEach(cliente -> logger.info(cliente.toString()));
                break;

            case 2: //Buscar un cliente por el ID
                logger.info("--- buscar cliente por ID ---");

                logger.info("Proporcione un ID: ");
                id = Integer.parseInt(teclado.nextLine());

                //Creamos un objeto de tipo Cliente e invocamos al método "buscarClientePorId"
                Cliente cliente = clienteServicio.buscarClientePorId(id);

                //Si es distinto de nulo, osea si encontró información del cliente con el ID pasado se le mostrará un mensaje
                //de encontrádo. Si la información encontrada es null se le devolverá el mensaje de no encontrado.
                if (cliente != null) {
                    logger.info("Cliente encontrado: " + cliente + "\n");
                } else {
                    logger.info("Cliente no encontrado: " + cliente + "\n");
                }
                break;

            case 3: //Agregar un cliente nuevo a la BD
                logger.info("--- Agregar un cliente nuevo ---");

                logger.info("Nombre:\s");
                nombre = teclado.nextLine();

                logger.info("Apellido:\s");
                apellido = teclado.nextLine();

                logger.info("Nombre del animal:\s");
                nombreAnimal = teclado.nextLine();

                logger.info("Especie:\s");
                especie = teclado.nextLine();

                logger.info("Raza:\s");
                raza = teclado.nextLine();

                logger.info("Genero:\s");
                genero = teclado.nextLine();

                //Creamos un objeto de tipo Cliente y llamo al constructor de 6 parámetros.
                Cliente nuevoCliente = new Cliente(nombre, apellido, nombreAnimal, especie, raza, genero);

                //Con el método guardarCliente guardamos la nueva información que le pasamos con el objeto "nuevoCliente" en la BD.
                clienteServicio.guardarCliente(nuevoCliente);

                logger.info("Cliente agregado: " + nuevoCliente);
                break;

            case 4: //Modificación de datos de clientes 
                logger.info("--- Modificar datos cliente ---");

                //le pedimos al usuario el ID del cliente que quiere modificar.
                logger.info("Proporcione un ID del cliente a modificar:\s");
                id = Integer.parseInt(teclado.nextLine());

                Cliente modificarCliente = new Cliente();

                //Con el método "bucarClientePorId" sabremos si ese cliente esta en la BD o no
                //si está, proseguiremos a editar sus datos
                modificarCliente = clienteServicio.buscarClientePorId(id);

                //Si el cliente es distinto de null, osea que con la busqueda por ID encontró información
                //modificaremos los datos del cliente encontrado.
                if (modificarCliente != null) {

                    //pedimos al usuario todos los datos a modificar del cliente.
                    logger.info("Nombre:\s");
                    nombre = teclado.nextLine();

                    logger.info("Apellido:\s");
                    apellido = teclado.nextLine();

                    logger.info("Nombre del animal:\s");
                    nombreAnimal = teclado.nextLine();

                    logger.info("Especie:\s");
                    especie = teclado.nextLine();

                    logger.info("Raza:\s");
                    raza = teclado.nextLine();

                    logger.info("Genero:\s");
                    genero = teclado.nextLine();

                    //Con el método Set vamos modificando todos los atributos del objeto
                    modificarCliente.setNombre(nombre);
                    modificarCliente.setApellido(apellido);
                    modificarCliente.setNombreAnimal(nombreAnimal);
                    modificarCliente.setEspecie(especie);
                    modificarCliente.setRaza(raza);
                    modificarCliente.setGenero(genero);

                    clienteServicio.guardarCliente(modificarCliente);

                    logger.info("Cliente modificado: " + modificarCliente);

                } else {
                    logger.info("El ID proporcionado no existe en la base de datos.");
                }
                break;

            case 5: //Eliminación de un cliente de la BD.
                logger.info("--- Eliminar cliente ---");

                //Pedimos al usuario el ID del cliente a eliminar.
                logger.info("Proporcione el ID del cliente a eliminar:");
                id = Integer.parseInt(teclado.nextLine());

                //El ID proporcionado se lo pasamos al método "buscarClientePorId".
                Cliente eliminarCliente = clienteServicio.buscarClientePorId(id);

                //Si el cliente que buscamos por ID no es null invocamos el metodo "eliminarCliente"
                //para borrarlo de la base de datos.
                if (eliminarCliente != null) {
                    clienteServicio.eliminarCliente(eliminarCliente);

                    logger.info("Cliente eliminado: " + eliminarCliente);
                } else {
                    logger.info("No se puede eliminar el cliente ya que el ID proporcionado no existe.");
                }
                break;

            case 6: //Opción para salir de la App.
                logger.info("¡Hasta pronto!");
                salir = true;
                break;

            default: //Opción por si el usuario proporciona una opción no válida.
                logger.info("Opción inválida: " + opcion);
        }
        
        //Al finalizar este método devuelve la variable booleana "salir", si devuelve "true" saldrá de la app
        //si devuelve "false" volverá a mostrar el menú de la app para seguir eligiendo opciones.
        return salir;
    }

}
