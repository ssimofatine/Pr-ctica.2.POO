package modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import Enums.EstadoReserva;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    //id , que será incremental.
    public static Long contador = 1L;


    /**
     * Atributos
     *
     *
     * long id
     * Cliente cliente
     * LocalDate fecha
     * LocalTime hora
     * int numPersonas
     * double importePrevisto
     * EstadoReserva estado
     * String zona
     */

    private long id;
    private Cliente cliente;
    private LocalDate fecha;
    private LocalTime hora;
    private int numPersonas;
    private double importePrevisto;
    private EstadoReserva estado;
    private String zona;


    /**
     * Métodos
     *
     * Constructor con todos los atributos excepto id , que será incremental.
     */

    public Reserva(Cliente cliente, LocalDate fecha, LocalTime hora, int numPersonas, double importePrevisto, EstadoReserva estado, String zona) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.numPersonas = numPersonas;
        this.importePrevisto = importePrevisto;
        this.estado = estado;
        this.zona = zona;
        this.id = contador++;
    }



     /**
     * toString() que muestre, por ejemplo:
     * ID: 1 | 12345678A | 2026-04-10 21:30 | 4 pers. | 80.0€ | CONFIRMADA | terraza
     */

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Reserva{");
        sb.append("ID: ").append(id);
        sb.append(" | ").append(cliente);
        sb.append(" | ").append(fecha).append(" ").append(hora);
        sb.append(" | ").append(numPersonas).append(" pers.");
        sb.append(" | ").append(importePrevisto).append("€");
        sb.append(" | ").append(estado);
        sb.append(" | ").append(zona);
        sb.append('}');
        return sb.toString();
    }
}
