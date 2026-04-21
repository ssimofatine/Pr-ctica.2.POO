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
                .sorted()
                .collect(Collectors.toList());
    }
}
