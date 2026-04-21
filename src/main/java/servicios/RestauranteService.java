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
                //no canceladas
                .filter(r -> r.getEstado() != EstadoReserva.CANCELADA && r.getFecha() == fecha )
                //de mayor a menor número de personas (reversed)
                .sorted(Comparator.comparing(Reserva::getNumPersonas).reversed())
                .collect(Collectors.toList());
    }



    //5. Clientes con reservas grandes
    //getClientesReservasGrandes(): obtener los nombres de los clientes que tengan alguna reserva
    //de más de 6 personas

    public List<String> getClientesReservasGrandes() {
        return restaurante.getReservas().stream()
                .filter(r -> r.getNumPersonas() > 6)
                .map(r -> r.getCliente().getNombre())
                .distinct()
                .collect(Collectors.toList());
    }



    //6. Total previsto de reservas atendidas
    //getTotalPrevistoAtendidas(): calcular la suma total de importePrevisto de todas las reservas
    //con estado ATENDIDA .
    public double getTotalPrevistoAtendidas() {
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.ATENDIDA)
                .mapToDouble(Reserva::getImportePrevisto)
                .sum();
    }

    //7. Número de reservas por estado
    //getReservasPorEstado(): crear un mapa donde la clave sea el estado de la reserva y el valor sea
    //el número total de reservas de ese estado.
    public Map<EstadoReserva, Long> getReservasPorEstado() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getEstado, Collectors.counting()));
    }



    //8. Número de reservas por zona
    //getReservasPorZona(): crear un mapa donde la clave sea la zona ( terraza , salon , barra , etc.)
    //y el valor sea el número de reservas de esa zona.

    public Map<String, Long> getReservasPorZona() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getZona, Collectors.counting()));
    }



    //9. Reservas agrupadas por fecha
    //getReservasAgrupadasPorFecha(): crear un mapa donde la clave sea la fecha y el valor sea la
    //lista de reservas de ese día.
    public Map<LocalDate, List<Reserva>> getReservasAgrupadasPorFecha() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getFecha));
    }



    //10. Cliente con más reservas
    //getClienteTop(): obtener el cliente que más reservas tiene en el restaurante.




    //11. Recaudación prevista por fecha
    //getTotalPrevistoAgrupadoPorFecha(): mostrar la suma del importe previsto de las reservas
    //agrupada por fecha.
    public Map<LocalDate, Double> getTotalPrevistoAgrupadoPorFecha() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(
                        Reserva::getFecha,
                        Collectors.summingDouble(Reserva::getImportePrevisto)
                ));
    }




    //12. Estadísticas de comensales
    //getEstadisticasNumPersonas(): obtener estadísticas sobre el número de personas por reserva:
    //media
    //máximo
    //mínimo
    //suma




}
