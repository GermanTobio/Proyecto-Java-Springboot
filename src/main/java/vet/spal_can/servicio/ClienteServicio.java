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
    public List<Cliente> listar() {
        
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepositorio.findById(id).orElse(null); 
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteRepositorio.save(cliente); 
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
    
    
}
