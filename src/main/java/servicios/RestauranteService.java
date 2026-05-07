package servicios;
import modelos.*;
import Enums.EstadoReserva;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;




@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RestauranteService {
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    /**
     * Consultas con Streams
     * A continuación se deben implementar las siguientes consultas en la clase Restaurante o en una
     * clase de servicios auxiliar (RestauranteService)
     */

    /**
     * 1. Reservas confirmadas de una fecha concreta
     * getReservasConfirmadas(LocalDate fecha): mostrar todas las reservas con estado CONFIRMADA
     * de una fecha dada, ordenadas por hora de forma ascendente.
     *
     */

    public List<Reserva> getReservasConfirmadas(LocalDate fecha){
        return reservas.stream()
                .filter(r -> r.getEstado().equals(EstadoReserva.CONFIRMADA) && r.getFecha().equals(fecha))
                .sorted(Comparator.comparing(Reserva::getHora).reversed())
                .toList();
    }

    /**
     * 2. Reservas de más de X personas
     * getReservasGrandes(int numPersonas): mostrar las reservas que tengan más de un número
     * dado de personas, por ejemplo más de 4.
     */


    public List<Reserva> getReservasGrandes(int numPersonas){
        return reservas.stream()
                .filter(r -> r.getNumPersonas() > numPersonas)
                .sorted(Comparator.comparing(Reserva::getNumPersonas))
                .toList();
    }

    /**
     * 3. Primera reserva cancelada
     * getPrimeraCancelada(): buscar la primera reserva cancelada de la lista
     *
     *
     */

    public Optional<Reserva> getPrimeraCancelada(){
        return reservas.stream()
                .filter(r -> r.getEstado().equals(EstadoReserva.CANCELADA))
                .findFirst();
    }

    /**
     * 4. Reservas ordenadas por número de personas
     * getReservasOrdenadas(LocalDate fecha): mostrar todas las reservas no canceladas ordenadas
     * de mayor a menor número de personas en una fecha determinada.
     *
     */

    public List<Reserva> getReservasOrdenadas(LocalDate fecha){
        return reservas.stream()
                .filter(r -> r.getEstado() != EstadoReserva.CANCELADA && r.getFecha().equals(fecha))
                .sorted(Comparator.comparing(Reserva::getNumPersonas).reversed())
                .toList();
    }

    /**
     * 5. Clientes con reservas grandes
     * getClientesReservasGrandes(): obtener los nombres de los clientes que tengan alguna reserva
     * de más de 6 personas.
     *
     */

    public List<String> getClientesReservasGrandes(){
        return reservas.stream()
                .filter(r -> r.getNumPersonas() > 6)
                .map(r -> r.getCliente().getNombre())
                .collect(Collectors.toList());
    }

    /**
     * 6. Total previsto de reservas atendidas
     * getTotalPrevistoAtendidas(): calcular la suma total de importePrevisto de todas las reservas
     * con estado ATENDIDA .
     *
     */

    public double getTotalPrevistoAtendidas(){
        return reservas.stream()
                .filter(r -> r.getEstado().equals(EstadoReserva.ATENDIDA))
                .mapToDouble(Reserva::getImportePrevisto)
                .sum();
    }

    /**
     * 7. Número de reservas por estado
     * getReservasPorEstado(): crear un mapa donde la clave sea el estado de la reserva y el valor sea
     * el número total de reservas de ese estado.
     *
     *
     */

    public Map<EstadoReserva,Long> getReservasPorEstado(){
        return  reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getEstado,Collectors.counting()));
    }

    /**
     * 8. Número de reservas por zona
     * getReservasPorZona(): crear un mapa donde la clave sea la zona ( terraza , salon , barra , etc.)
     * y el valor sea el número de reservas de esa zona.
     *
     */
    public Map<String, Long> getReservasPorZona(){
        return reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getZona , Collectors.counting()));
    }

    /**
     * 9. Reservas agrupadas por fecha
     * getReservasAgrupadasPorFecha(): crear un mapa donde la clave sea la fecha y el valor sea la
     * lista de reservas de ese día.
     *
     *
     */

    public Map<LocalDate, List<Reserva>> getReservasAgrupadasPorFecha(){
        return reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getFecha, Collectors.toList()));
    }

    /**
     *10. Cliente con más reservas
     * getClienteTop(): obtener el cliente que más reservas tiene en el restaurante.
     *
     */

    public Cliente getClienteTop(){
        return reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getCliente , Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * 11. Recaudación prevista por fecha
     * getTotalPrevistoAgrupadoPorFecha(): mostrar la suma del importe previsto de las reservas
     * agrupada por fecha.
     *
     */

    public Map<LocalDate, Double> getTotalPrevistoAgrupadoPorFecha(){
        return reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getFecha, Collectors.summingDouble(Reserva::getImportePrevisto)));
    }

    /**
     * 12. Estadísticas de comensales
     * getEstadisticasNumPersonas(): obtener estadísticas sobre el número de personas por reserva:
     * media
     * máximo
     * mínimo
     * suma
     *
     */
    public void getEstadisticasNumPersonas(){
        IntSummaryStatistics estadisticas = reservas.stream()
                .mapToInt(Reserva::getNumPersonas)
                .summaryStatistics();
        IO.println("media: (" + estadisticas.getAverage() + ")");
        IO.println("máximo: (" + estadisticas.getMax() + ")");
        IO.println("mínimo: (" + estadisticas.getMin() + ")");
        IO.println("suma: (" + estadisticas.getSum() + ")");
    }

    /**
     * 13. Clientes ordenados alfabéticamente
     * getClientes(): mostrar la lista de clientes ordenada por nombre.
     *
     */
    public List<String> getClientes(){
        return clientes.stream()
                .map(Cliente::getNombre)
                .sorted()
                .toList();
    }

    /**
     * 14. Reservas futuras
     * getReservasFuturasAgrupadasPorFecha(): crear un mapa donde la clave sea la fecha y el valor
     * sea la lista de reservas de ese día, para las reservas a partir de hoy. Las reservas deben estar
     * previamente ordenadas por fecha.
     *
     *
     */

    public Map<LocalDate, List<Reserva>> getReservasFuturasAgrupadasPorFecha(){
        LocalDate hoy = LocalDate.now();
        return reservas.stream()
                .filter(r -> !r.getFecha().isBefore(hoy))
                .sorted(Comparator.comparing(Reserva::getFecha))
                .collect(Collectors.groupingBy(Reserva::getFecha, TreeMap::new, Collectors.toList()));
    }

    /**
     * 15. Porcentaje de reservas canceladas
     * getPorcentajeCanceladas(): calcular qué porcentaje del total de reservas están canceladas.
     *
     */

    public Long getPorcentajeCanceladas(){
        return reservas.stream()
                .filter(r -> r.getEstado().equals(EstadoReserva.CANCELADA))
                .count() / reservas.size();
    }


}
