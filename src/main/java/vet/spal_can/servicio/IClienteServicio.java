package vet.spal_can.servicio;

import java.util.List;
import vet.spal_can.modelo.Cliente;

public interface IClienteServicio {
    
    public List<Cliente> listarCliente();
    
    public Cliente buscarClientePorId(Integer id);
    
    public void guardarCliente(Cliente cliente);
    
    public void eliminarCliente(Cliente cliente);
}
