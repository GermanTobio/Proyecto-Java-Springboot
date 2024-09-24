package vet.spal_can.servicio;

import java.util.List;
import vet.spal_can.modelo.Cliente;

public interface IClienteServicio {
    
    List<Cliente> listar();
    
    Cliente buscarPorId(Integer id);
    
    void guardar(Cliente cliente);
    
    void eliminar(Cliente cliente);
}
