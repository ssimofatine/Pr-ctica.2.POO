package servicios;
// yo uso metodo como eso (*) por importe todo
import modelos.*;
import java.util.*;
import Enums.*;
import java.time.*;
import java.util.stream.Collectors;

public class RestauranteService {
    // llama calse restaurante privada
    private Restaurante restaurante;

    // constructor

    public RestauranteService(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


    public List<Reserva> getReservasConfirmadas(LocalDate fecha){
        //mostrar todas las reservas con estado CONFIRMADA
        //de una fecha dada, ordenadas por hora de forma ascendente.
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.CONFIRMADA && r.getFecha().equals(fecha))
                //ordenadas por hora de forma ascendente
                //.sorted(Comparator.comparing(r -> r.getHora()))
                .sorted(Comparator.comparing(Reserva::getHora))
                .collect(Collectors.toList());
    }

    //2. Reservas de más de X personas
    //getReservasGrandes(int numPersonas): mostrar las reservas que tengan más de un número
    //dado de personas, por ejemplo más de 4.

    public List<Reserva> getReservasGrandes(int numPersonas){
        return restaurante.getReservas().stream()
                .filter(r -> r.getNumPersonas() > numPersonas )
                .collect(Collectors.toList());


    }

    //3. Primera reserva cancelada
    //getPrimeraCancelada(): buscar la primera reserva cancelada de la lista.

    public Optional<Reserva> getPrimeraCancelada(){
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.CANCELADA )// Clase Enums
                .findFirst();// este buscar la primera reserva


    }

    //4. Reservas ordenadas por número de personas
    //getReservasOrdenadas(LocalDate fecha): mostrar todas las reservas no canceladas ordenadas
    //de mayor a menor número de personas en una fecha determinada.
    public List<Reserva> getReservasOrdenadas(LocalDate fecha){
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.CONFIRMADA && r.getFecha() == fecha )
                .sorted(Comparator.comparing(Reserva::getNumPersonas).reversed())
                .collect(Collectors.toList());
    }




}
