package vet.spal_can.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vet.spal_can.modelo.Cliente;
import vet.spal_can.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio implements IClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarCliente() {
        //Gracias a la interface "clienteRepositorio" podemos crear un objeto de ella y llamar al metodo "findall" de la interface JpaRepository
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElse(null); //buscará en la BD a los clientes por el ID
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente); //ese metodo se fijará si el ID es null o no, si lo es, hara un insert, de lo contrarios un update
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
    
    
}
