package vet.spal_can.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data //Crea todos los metodos Get y Set
@NoArgsConstructor //Crea el constructor vacio
@AllArgsConstructor //Crea el constructor con todos los paremetros
@ToString //Metodo ToString
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para datos incrementales como el ID
    private Integer id;
    private String nombre;
    private String apellido;
    private String nombreAnimal;
    private String especie;
    private String raza;
    private String genero;
    


    //Comstructor de 6 parametros sin del ID
    public Cliente(String nombre, String apellido, String nombreAnimal, String especie, String raza, String genero){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreAnimal = nombreAnimal;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        
    }
}
