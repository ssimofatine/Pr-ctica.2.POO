package servicios;
import modelos.*;
import Enums.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class test {

    // المتغير الأساسي: الخدمة تحتاج إلى مطعم لكي تعمل عليه
    private Restaurante restaurante;

    // Constructor
    public test(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    // ==========================================
    // 1. Reservas confirmadas de una fecha concreta
    // ==========================================
    public List<Reserva> getReservasConfirmadas(LocalDate fecha) {
        return restaurante.getReservas().stream()
                .filter(r -> r.getFecha().equals(fecha))
                .filter(r -> r.getEstado() == EstadoReserva.CONFIRMADA)
                .sorted(Comparator.comparing(Reserva::getHora))
                .collect(Collectors.toList());
    }

    // ==========================================
    // 2. Reservas de más de X personas
    // ==========================================
    public List<Reserva> getReservasGrandes(int numPersonas) {
        return restaurante.getReservas().stream()
                .filter(r -> r.getNumPersonas() > numPersonas)
                .collect(Collectors.toList());
    }

    // ==========================================
    // 3. Primera reserva cancelada
    // ==========================================
    public Optional<Reserva> getPrimeraCancelada() {
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.CANCELADA)
                .findFirst();
    }

    // ==========================================
    // 4. Reservas ordenadas por número de personas
    // ==========================================
    public List<Reserva> getReservasOrdenadas(LocalDate fecha) {
        return restaurante.getReservas().stream()
                .filter(r -> r.getFecha().equals(fecha))
                .filter(r -> r.getEstado() != EstadoReserva.CANCELADA)
                .sorted(Comparator.comparing(Reserva::getNumPersonas).reversed())
                .collect(Collectors.toList());
    }

    // ==========================================
    // 5. Clientes con reservas grandes
    // ==========================================
    public List<String> getClientesReservasGrandes() {
        return restaurante.getReservas().stream()
                .filter(r -> r.getNumPersonas() > 6)
                .map(r -> r.getCliente().getNombre())
                .distinct()
                .collect(Collectors.toList());
    }

    // ==========================================
    // 6. Total previsto de reservas atendidas
    // ==========================================
    public double getTotalPrevistoAtendidas() {
        return restaurante.getReservas().stream()
                .filter(r -> r.getEstado() == EstadoReserva.ATENDIDA)
                .mapToDouble(Reserva::getImportePrevisto)
                .sum();
    }

    // ==========================================
    // 7. Número de reservas por estado
    // ==========================================
    public Map<EstadoReserva, Long> getReservasPorEstado() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getEstado, Collectors.counting()));
    }

    // ==========================================
    // 8. Número de reservas por zona
    // ==========================================
    public Map<String, Long> getReservasPorZona() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getZona, Collectors.counting()));
    }

    // ==========================================
    // 9. Reservas agrupadas por fecha
    // ==========================================
    public Map<LocalDate, List<Reserva>> getReservasAgrupadasPorFecha() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getFecha));
    }

    // ==========================================
    // 10. Cliente con más reservas
    // ==========================================
    public Cliente getClienteTop() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(Reserva::getCliente, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // ==========================================
    // 11. Recaudación prevista por fecha
    // ==========================================
    public Map<LocalDate, Double> getTotalPrevistoAgrupadoPorFecha() {
        return restaurante.getReservas().stream()
                .collect(Collectors.groupingBy(
                        Reserva::getFecha,
                        Collectors.summingDouble(Reserva::getImportePrevisto)
                ));
    }

    // ==========================================
    // 12. Estadísticas de comensales
    // ==========================================
    public IntSummaryStatistics getEstadisticasNumPersonas() {
        return restaurante.getReservas().stream()
                .mapToInt(Reserva::getNumPersonas)
                //no se puedo uso (sum) solo summaryStatistics
                .summaryStatistics();
    }

    // ==========================================
    // 13. Clientes ordenados alfabéticamente
    // ==========================================
    public List<Cliente> getClientes() {
        return restaurante.getClientes().stream()
                .sorted(Comparator.comparing(Cliente::getNombre))
                .collect(Collectors.toList());
    }

    // ==========================================
    // 14. Reservas futuras agrupadas por fecha
    // ==========================================
    public Map<LocalDate, List<Reserva>> getReservasFuturasAgrupadasPorFecha() {
        LocalDate hoy = LocalDate.now();
        return restaurante.getReservas().stream()
                .filter(r -> !r.getFecha().isBefore(hoy))
                .sorted(Comparator.comparing(Reserva::getFecha)) // الترتيب المسبق كما طلب المعلم
                .collect(Collectors.groupingBy(
                        Reserva::getFecha,
                        LinkedHashMap::new, // الحفاظ على الترتيب داخل الـ Map
                        Collectors.toList()
                ));
    }

    // ==========================================
    // 15. Porcentaje de reservas canceladas
    // ==========================================
    public double getPorcentajeCanceladas() {
        List<Reserva> todas = restaurante.getReservas();
        if (todas.isEmpty()) return 0.0;

        long canceladas = todas.stream()
                .filter(r -> r.getEstado() == EstadoReserva.CANCELADA)
                .count();

        return (double) canceladas / todas.size() * 100;
    }
}
