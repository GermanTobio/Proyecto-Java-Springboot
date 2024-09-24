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

    private static final Logger logger = LoggerFactory.getLogger(SpalCanApplication.class);

    public static void main(String[] args) {

        logger.info("Iniciando la aplicación");

        SpringApplication.run(SpalCanApplication.class, args);

        logger.info("Cerrando la aplicación");
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("\n*** SPAL-CAN APP ***");

        Scanner teclado = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {

            try {

                int opcion = mostrarMenu(teclado);

                salir = ejecutarOpcion(opcion, teclado);

            } catch (Exception e) {
                logger.info("Error al elegir una opción: " + e.getMessage());
            }
        }
    }

    public int mostrarMenu(Scanner teclado) {

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

        int id = 0;
        String nombre = "";
        String apellido = "";
        String especie = "";
        String raza = "";
        String genero = "";
        String nombreAnimal = "";

        switch (opcion) {
            case 1:
                logger.info("--- Listado de clientes ---");
                List<Cliente> clientes = clienteServicio.listar();
                clientes.forEach(cliente -> logger.info(cliente.toString()));
                break;

            case 2: 
                logger.info("--- buscar cliente por ID ---");

                logger.info("Proporcione un ID: ");
                id = Integer.parseInt(teclado.nextLine());

                Cliente cliente = clienteServicio.buscarPorId(id);

                if (cliente != null) {
                    logger.info("Cliente encontrado: " + cliente + "\n");
                } else {
                    logger.info("Cliente no encontrado: " + cliente + "\n");
                }
                break;

            case 3: 
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

                Cliente nuevoCliente = new Cliente(nombre, apellido, nombreAnimal, especie, raza, genero);

                clienteServicio.guardar(nuevoCliente);

                logger.info("Cliente agregado: " + nuevoCliente);
                break;

            case 4:  
                logger.info("--- Modificar datos cliente ---");

                logger.info("Proporcione un ID del cliente a modificar:\s");
                id = Integer.parseInt(teclado.nextLine());

                Cliente modificarCliente = new Cliente();

                modificarCliente = clienteServicio.buscarPorId(id);

                if (modificarCliente != null) {

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

                    modificarCliente.setNombre(nombre);
                    modificarCliente.setApellido(apellido);
                    modificarCliente.setNombreAnimal(nombreAnimal);
                    modificarCliente.setEspecie(especie);
                    modificarCliente.setRaza(raza);
                    modificarCliente.setGenero(genero);

                    clienteServicio.guardar(modificarCliente);

                    logger.info("Cliente modificado: " + modificarCliente);

                } else {
                    logger.info("El ID proporcionado no existe en la base de datos.");
                }
                break;

            case 5: 
                logger.info("--- Eliminar cliente ---");

                logger.info("Proporcione el ID del cliente a eliminar:");
                id = Integer.parseInt(teclado.nextLine());

                Cliente eliminarCliente = clienteServicio.buscarPorId(id);

                if (eliminarCliente != null) {
                    clienteServicio.eliminar(eliminarCliente);

                    logger.info("Cliente eliminado: " + eliminarCliente);
                } else {
                    logger.info("No se puede eliminar el cliente ya que el ID proporcionado no existe.");
                }
                break;

            case 6:
                logger.info("¡Hasta pronto!");
                salir = true;
                break;

            default:
                logger.info("Opción inválida: " + opcion);
        }
        
        return salir;
    }

}
