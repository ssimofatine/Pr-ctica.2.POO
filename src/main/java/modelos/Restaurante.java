package modelos;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {

    /**
     * Atributos
     * String nombre
     * List<Cliente> clientes
     * List<Reserva> reservas
     */


    private String nombre;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    //Constructor con el nombre del restaurante.

    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void addCliente(Cliente c){
        this.clientes.add(c);
    }

    public void addReserva(Reserva r){
        this.reservas.add(r);
    }


    public Cliente getClientePorDni(String dni){
        return clientes.stream()
                .filter(c -> c.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }
}
