package modelos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    //Clase Cliente
    //Atributos
    //String dni
    //String nombre
    //String telefono
    //String email
    //String ciudad

    //Atributos

    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private String cuidad;




    //Métodos
    //Constructor con todos los atributos.
    //Getters y setters.



    //toString() que muestre, por ejemplo:


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cliente{");
        sb.append("dni='").append(dni).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", cuidad='").append(cuidad).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
