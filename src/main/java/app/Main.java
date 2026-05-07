package app;

import modelos.Cliente;
import Enums.EstadoReserva;
import modelos.Reserva;
import modelos.Restaurante;
import servicios.RestauranteService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void pintarMenu(){
        IO.println("---------------------- Menu ----------------------");
        IO.println("Select una opción: ");
        IO.println("1. Reservas confirmadas de una fecha concreta ");
        IO.println("2. Reservas de más de X personas");
        IO.println("3. Primera reserva cancelada");
        IO.println("4. Reservas ordenadas por número de personas");
        IO.println("5. Clientes con reservas grandes");
        IO.println("6. Total previsto de reservas atendidas");
        IO.println("7. Número de reservas por estado");
        IO.println("8. Número de reservas por zona");
        IO.println("9. Reservas agrupadas por fecha");
        IO.println("10. Cliente con más reservas");
        IO.println("11. Recaudación prevista por fecha");
        IO.println("12. Estadísticas de comensales");
        IO.println("13. Clientes ordenados alfabéticamente");
        IO.println("14. Reservas futuras");
        IO.println("15. Porcentaje de reservas canceladas");
        IO.println("16. Salir");
        IO.println("--------------------------------------------------");
    }

    public static void Menu(RestauranteService servcio){
        boolean salir = true;
        int opcion;

        try{
            do {
                pintarMenu();
                opcion = Integer.parseInt(IO.readln());
                switch (opcion){
                    case 1:
                        IO.println("Consultas -1- Reservas confirmadas de una fecha concreta ");
                        IO.println("Por Favor Dami tu fecha como Quiers");
                        IO.println("----------------------------------------------");
                        LocalDate fecha  = LocalDate.parse(IO.readln());
                        servcio.getReservasConfirmadas(fecha).forEach(IO::println);
                        break;
                    case 2:
                        IO.println("Consultas -2- Reservas de más de X personas");
                        IO.println("Pro Favor Dami tu total del personas como quiers: ");
                        IO.println("----------------------------------------------");
                        int numPersonas = Integer.parseInt(IO.readln());
                        servcio.getReservasGrandes(numPersonas).forEach(IO::println);
                        break;
                    case 3:
                        IO.println("Consultas -3- Primera reserva cancelada");
                        IO.println("----------------------------------------------");
                        servcio.getPrimeraCancelada().ifPresent(IO::println);
                        break;
                    case 4:
                        IO.println("Consultas -4- Reservas ordenadas por número de personas");
                        IO.println("Por Favor Dami tu fecha como Quiers");
                        IO.println("----------------------------------------------");
                        LocalDate fecha2 = LocalDate.parse(IO.readln());
                        servcio.getReservasOrdenadas(fecha2).forEach(IO::println);
                        break;
                    case 5:
                        IO.println("Consultas -5- Clientes con reservas grandes");
                        IO.println("----------------------------------------------");
                        servcio.getClientesReservasGrandes().forEach((Nombre) -> {
                            IO.println("Nombre: " + Nombre);
                        });
                        break;
                    case 6:
                        IO.println("Consultas -6- Total previsto de reservas atendidas");
                        IO.println("----------------------------------------------");
                        double sum = servcio.getTotalPrevistoAtendidas();
                        IO.println("la suma total de importePrevisto: " + sum);
                        break;
                    case 7:
                        IO.println("Consultas -7- Número de reservas por estado");
                        IO.println("----------------------------------------------");
                        servcio.getReservasPorEstado().forEach((Estado, totalReservas) -> {
                            IO.println("Estado: (" + Estado + ") " + " total de reservas (" + totalReservas + ")");
                        });

                        break;
                    case 8:
                        IO.println("Consultas -8- Número de reservas por zona");
                        IO.println("----------------------------------------------");
                        servcio.getReservasPorZona().forEach((zona, totalReservas) -> {
                            IO.println("la zona: (" + zona + ") " + " total de reservas (" + totalReservas + ")");
                        });

                        break;
                    case 9:
                        IO.println("Consultas -9- Reservas agrupadas por fecha");
                        IO.println("----------------------------------------------");
                        servcio.getReservasAgrupadasPorFecha().forEach((fechaLocalNow, res) -> {
                            IO.println("la fecha: (" + fechaLocalNow + ") " + " la lista de reservas: (" + res +")");
                        });

                        break;
                    case 10:
                        IO.println("Consultas -10- Cliente con más reservas");
                        IO.println("----------------------------------------------");
                        Cliente clienteTop = servcio.getClienteTop();

                        if(clienteTop != null){
                            IO.println("Este Cliente Tine Top Reservas");
                            IO.println(clienteTop);
                        }else {
                            IO.println("Este Cliente No Tine Top Reservas");
                        }

                        break;
                    case 11:
                        IO.println("Consultas -11- Recaudación prevista por fecha");
                        IO.println("----------------------------------------------");
                        servcio.getTotalPrevistoAgrupadoPorFecha().forEach((fechaSumaImporte, importe) -> {
                            IO.println("Fecha: (" + fechaSumaImporte + ") " + "la suma del importe previsto: (" + importe + ")");
                        });

                        break;
                    case 12:
                        IO.println("Consultas -12- Estadísticas de comensales");
                        IO.println("----------------------------------------------");
                        servcio.getEstadisticasNumPersonas();

                        break;
                    case 13:
                        IO.println("Consultas -13- Clientes ordenados alfabéticamente");
                        IO.println("----------------------------------------------");
                        servcio.getClientes().forEach((nombreCliente) -> {
                            IO.println("Nombre: (" + nombreCliente + ")");
                        });

                        break;
                    case 14:
                        IO.println("Consultas -14- Reservas futuras");
                        IO.println("----------------------------------------------");
                        servcio.getReservasFuturasAgrupadasPorFecha().forEach((a,v) -> {
                            IO.println("Fecha: (" + a + ") " + " Reservas: (" + v + ")");
                        });

                        break;
                    case 15:
                        IO.println("Consultas -15- Porcentaje de reservas canceladas");
                        IO.println("----------------------------------------------");
                        long Porcentaje = servcio.getPorcentajeCanceladas();
                        IO.println("El Porcentaje de reservas canceladas es: (" + Porcentaje + ")");
                        break;
                    case 16:
                        IO.println("----------------------------------------------");
                        IO.println("Adios, Gracias por usar este programacion ):");
                        IO.println("----------------------------------------------");
                        salir = false;
                        break;
                    default:
                        IO.println("------------------------");
                        IO.println("| Error Opción no valida |");
                        IO.println("------------------------");
                        break;
                }
            }while (salir);
        }catch (NumberFormatException e){
            IO.println("Error : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        //Clientes
        /**
         *     private String dni;
         *     private String nombre;
         *     private String telefono;
         *     private String email;
         *     private String ciudad; ana@email.com | 600123123 | Sevilla
         *
         */

        Cliente c1 = new Cliente("12345678A","Ana Pérez","600123123","anaperez@gmail.com","Almería");
        Cliente c2 = new Cliente("12345678B","Mohamed El Fatine","602415238","mohamedelfatine@gmail.com","Murcia");
        Cliente c3 = new Cliente("12345678C","Ana vecinte","610123123","ana@email.com","Madrid");
        Cliente c4 = new Cliente("12345678D","Juan JAroso","622415238","juan@gmail.com","Sevilla");
        Cliente c5 = new Cliente("12345678E","Robert JAroso","632415238","robert@gmail.com","Barcelona");
        Cliente c6 = new Cliente("12345678F","Adrian Jaroso","642415238","adrian@gmail.com","Valencia");
        Cliente c7 = new Cliente("12345678J","Jose Javi","652415238","josejavi@gmail.com","Granada");
        Cliente c8 = new Cliente("12345678H","caty BBDD","662415238","caty@gmail.com","Malaga");



        // 15 Reservas
        /**
         *     private Cliente cliente;
         *     private LocalDate fecha;
         *     private LocalTime hora;
         *     private int numPersonas;
         *     private double importePrevisto;
         *     private EstadoReserva estado;
         *     private String zona;
         */

        Reserva r1 = new Reserva(c1, LocalDate.of(2025,8,21), LocalTime.of(11,22,30),4,22.50, EstadoReserva.CONFIRMADA,"terraza");
        Reserva r2 = new Reserva(c2, LocalDate.of(2026,4,22), LocalTime.of(12,21,31),5,32.50, EstadoReserva.CONFIRMADA,"salon");
        Reserva r3 = new Reserva(c3, LocalDate.of(2024,3,23), LocalTime.of(13,23,32),6,42.50, EstadoReserva.PENDIENTE,"barra");
        Reserva r4 = new Reserva(c4, LocalDate.of(2023,2,24), LocalTime.of(14,24,33),7,52.50, EstadoReserva.CANCELADA,"terraza");
        Reserva r5 = new Reserva(c5, LocalDate.of(2022,1,25), LocalTime.of(15,25,34),8,62.50, EstadoReserva.ATENDIDA,"salon");
        Reserva r6 = new Reserva(c6, LocalDate.of(2021,2,26), LocalTime.of(16,26,35),2,72.50, EstadoReserva.CANCELADA,"barra");
        Reserva r7 = new Reserva(c7, LocalDate.of(2020,9,27), LocalTime.of(17,27,36),8,82.50, EstadoReserva.PENDIENTE,"terraza");
        Reserva r8 = new Reserva(c8, LocalDate.of(2025,10,28), LocalTime.of(18,28,37),9,92.50, EstadoReserva.CANCELADA,"salon");
        Reserva r9 = new Reserva(c7, LocalDate.of(2026,11,18), LocalTime.of(19,29,38),104,102.50, EstadoReserva.ATENDIDA,"barra");
        Reserva r10 = new Reserva(c8, LocalDate.of(2024,12,17), LocalTime.of(1,30,39),11,202.50, EstadoReserva.CONFIRMADA,"terraza");
        Reserva r11 = new Reserva(c6, LocalDate.of(2023,1,15), LocalTime.of(2,31,40),12,302.50, EstadoReserva.PENDIENTE,"salon");
        Reserva r12 = new Reserva(c5, LocalDate.of(2022,2,22), LocalTime.of(3,32,41),13,402.50, EstadoReserva.CANCELADA,"barra");
        Reserva r13 = new Reserva(c4, LocalDate.of(2021,3,11), LocalTime.of(4,33,42),14,502.50, EstadoReserva.ATENDIDA,"terraza");
        Reserva r14 = new Reserva(c3, LocalDate.of(2020,4,12), LocalTime.of(5,34,43),15,602.50, EstadoReserva.CONFIRMADA,"salon");
        Reserva r15 = new Reserva(c1, LocalDate.of(2025,8,19), LocalTime.of(6,35,44),16,702.50, EstadoReserva.PENDIENTE,"barra");


        // Restaurante

        Restaurante restaurante = new Restaurante("Restaurante de Examen");

        // add cliente in clase cliente
        restaurante.addCliente(c1);
        restaurante.addCliente(c2);
        restaurante.addCliente(c3);
        restaurante.addCliente(c4);
        restaurante.addCliente(c5);
        restaurante.addCliente(c6);
        restaurante.addCliente(c7);
        restaurante.addCliente(c8);

        // add reserva in clase reserva
        restaurante.addReserva(r1);
        restaurante.addReserva(r2);
        restaurante.addReserva(r3);
        restaurante.addReserva(r4);
        restaurante.addReserva(r5);
        restaurante.addReserva(r6);
        restaurante.addReserva(r7);
        restaurante.addReserva(r8);
        restaurante.addReserva(r9);
        restaurante.addReserva(r10);
        restaurante.addReserva(r11);
        restaurante.addReserva(r12);
        restaurante.addReserva(r13);
        restaurante.addReserva(r14);
        restaurante.addReserva(r15);

        //Llama clase RestauranteService
        RestauranteService restauranteService = new RestauranteService(restaurante.getClientes(),restaurante.getReservas());

        //Llama Método de Menu
        Menu(restauranteService);


    }
}