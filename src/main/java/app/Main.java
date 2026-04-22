package app;

import modelos.*;
import servicios.*;
import servicios.RestauranteService;
import Enums.EstadoReserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //mi profesor Javi este clase Restaurante tiene (Nombre) entonases añadir String nombre; dentro objeto Restaurante
        Restaurante miRestaurante = new Restaurante("Restaurante Jaroso");

        Cliente c1 = new Cliente("2026", "Mohamed El Fatine", "602415238", "simofatine3@email.com", "Almería");
        Cliente c2 = new Cliente("2026", "Mohamed El Fatine", "602415238", "simofatine3@email.com", "Almería");
        Cliente c3 = new Cliente("2026", "Mohamed El Fatine", "602415238", "simofatine3@email.com", "Almería");
        Cliente c4 = new Cliente("2026", "Mohamed El Fatine", "602415238", "simofatine3@email.com", "Almería");


        miRestaurante.addCliente(c1);
        miRestaurante.addCliente(c2);
        miRestaurante.addCliente(c3);
        miRestaurante.addCliente(c4);

        LocalDate hoy = LocalDate.now();
        LocalDate manana = hoy.plusDays(1);
        LocalDate proximaSemana = hoy.plusDays(7);

        miRestaurante.addReserva(new Reserva(c1, manana, LocalTime.of(14, 0), 4, 80.0, EstadoReserva.CONFIRMADA, "Zona bien"));
        miRestaurante.addReserva(new Reserva(c2, manana, LocalTime.of(14, 30), 2, 45.0, EstadoReserva.CONFIRMADA, "Zona muy bien"));
        miRestaurante.addReserva(new Reserva(c3, manana, LocalTime.of(21, 0), 8, 160.0, EstadoReserva.PENDIENTE, "Zona grande"));
        miRestaurante.addReserva(new Reserva(c4, proximaSemana, LocalTime.of(21, 30), 3, 60.0, EstadoReserva.CANCELADA, "Zona pequeña"));


        RestauranteService servicio = new RestauranteService(miRestaurante);

        System.out.println("---- Restaurante ----");

        System.out.println("1:");
        servicio.getReservasConfirmadas(manana).forEach(System.out::println);

        System.out.println("2:");
        servicio.getReservasGrandes(4).forEach(System.out::println);

        System.out.println("3:");
        servicio.getPrimeraCancelada().ifPresent(System.out::println);

        System.out.println("4:");
        servicio.getReservasOrdenadas(manana).forEach(System.out::println);

        System.out.println("5:");
        servicio.getClientesReservasGrandes().forEach(System.out::println);

        System.out.println("6:");
        System.out.println(servicio.getTotalPrevistoAtendidas() + "€");

        System.out.println("7:");
        servicio.getReservasPorEstado().forEach((estado, cantidad) -> System.out.println(estado + ": " + cantidad));

        System.out.println("8:");
        servicio.getReservasPorZona().forEach((zona, cantidad) -> System.out.println(zona + ": " + cantidad));

        System.out.println("9:");
        servicio.getReservasAgrupadasPorFecha().forEach((fecha, lista) -> {
            System.out.println("Fecha: " + fecha);
            lista.forEach(r -> System.out.println("  " + r));
        });


        System.out.println("11:");
        servicio.getTotalPrevistoAgrupadoPorFecha().forEach((fecha, total) -> System.out.println(fecha + ": " + total + "€"));

        System.out.println("12:");
        System.out.println(servicio.getEstadisticasNumPersonas());


    }
}