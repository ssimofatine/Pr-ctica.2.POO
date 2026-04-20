package modelos;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Restaurante {
    //Atributos
    //String nombre
    //List<Cliente> clientes
    //List<Reserva> reservas


    private String nombre;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }


    public List<Cliente> getClientes() {
        return clientes;
    }

    //getReservas()
    public List<Reserva> getReservas() {
        return reservas;
    }

    //addCliente(Cliente c)

    public void addCliente(Cliente c){

        this.clientes.add(c);


    }


    //addReserva(Reserva r)

    public void addReserva(Reserva r) {

        this.reservas.add(r);
    }



    //getClientePorDni(String dni)
    public void getClientePorDni(String dni){
        clientes.stream()
                .filter(cliente -> cliente.getDni().equals(dni))
                .findFirst()
                .ifPresent(System.out::println);
        //
    }


    //toString() que muestre los clientes y reservas almacenados.


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Restaurante{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", clientes=").append(clientes);
        sb.append(", reservas=").append(reservas);
        sb.append('}');
        return sb.toString();
    }
}
