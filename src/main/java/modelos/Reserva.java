package modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import Enums.EstadoReserva;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Reserva {

    //Atributos
    //long id
    //Cliente cliente
    //LocalDate fecha
    //LocalTime hora
    //int numPersonas
    //double importePrevisto
    //EstadoReserva estado
    //String zona

    private long id;
    private Cliente cliente;
    private LocalDate fecha;
    private LocalTime hora;
    private int numPersonas;
    private double importePrevisto;
    private EstadoReserva estado;
    private String zona;


    //Constructor con todos los atributos excepto id , que será incremental.
    //Getters y setters.
    //toString() que muestre, por ejemplo:
    //ID: 1 | 12345678A | 2026-04-10 21:30 | 4 pers. | 80.0€ | CONFIRMADA | terraza


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Reserva{");
        sb.append("id=").append(id);
        sb.append(", cliente=").append(cliente);
        sb.append(", fecha=").append(fecha);
        sb.append(", hora=").append(hora);
        sb.append(", numPersonas=").append(numPersonas);
        sb.append(", importePrevisto=").append(importePrevisto);
        sb.append(", estado=").append(estado);
        sb.append(", zona='").append(zona).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
