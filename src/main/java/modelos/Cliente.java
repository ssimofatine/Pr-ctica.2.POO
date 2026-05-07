package modelos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    /**
     * Clase Cliente
     * Atributos
     *
     * String dni
     * String nombre
     * String telefono
     * String email
     * String ciudad
     *
     * Métodos
     * Constructor con todos los atributos.
     * Getters y setters.
     * toString() que muestre, por ejemplo:
     * DNI: 12345678A | Nombre: Ana Pérez | ana@email.com | 600123123 | Sevilla
     */

    //Atributos
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private String ciudad;


    // ToString Con StringBuffer


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cliente{");
        sb.append("DNI: ").append(dni);
        sb.append(" | Nombre: ").append(nombre);
        sb.append(" | ").append(telefono);
        sb.append(" | ").append(email);
        sb.append(" | ").append(ciudad);
        sb.append('}');
        return sb.toString();
    }
}
