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
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString 
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    private String nombre;
    private String apellido;
    private String nombreAnimal;
    private String especie;
    private String raza;
    private String genero;
    


    public Cliente(String nombre, String apellido, String nombreAnimal, String especie, String raza, String genero){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreAnimal = nombreAnimal;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        
    }
}
