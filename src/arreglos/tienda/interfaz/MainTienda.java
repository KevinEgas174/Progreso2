package arreglos.tienda.interfaz;

import arreglos.tienda.modelo.AppStore;
import arreglos.tienda.modelo.Juego;
import arreglos.tienda.utilitario.Valida;

import javax.swing.*;
import java.util.Scanner;

public class MainTienda {

    private static Scanner sc = new Scanner(System.in);
    private static AppStore tienda = new AppStore();

    public static void main(String[] args){ // Agregado String[] args
        int opc = 0;
        tienda.cargarDatos();
        do{
            menu();
            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número del menú.");
                continue; // Vuelve al inicio del do-while si escriben letras en el menú
            }

            switch (opc){
                case 1:{
                    ingresarJuegos();
                }break;
                case 2:{
                    mostrarJuegos();
                }break;
                case 3:{
                    System.out.println("*******Buscar Juego********");
                    String nombreBuscar = Valida.leerString("Ingrese el nombre del juego");
                    Juego encontrado = tienda.buscarJuego(nombreBuscar);

                    if(encontrado != null){
                        System.out.println("Juego encontrado: " + encontrado);
                    } else {
                        System.out.println("El juego no existe en la tienda.");
                    }
                }break;
                case 4:{
                    System.out.println("*******Comprar Licencias********");
                    String nombreCompra = Valida.leerString("Ingrese el nombre del juego");
                    int cantidadCompra = Valida.leerIntPositivo("Ingrese la cantidad a comprar");

                    if(tienda.comprar(nombreCompra, cantidadCompra)){
                        System.out.println("¡Compra exitosa! Stock actualizado.");
                    } else {
                        System.out.println("Error: No se pudo realizar la compra (el juego no existe).");
                    }
                }break;
                case 5:{
                    System.out.println("*******Vender Licencias********");
                    String nombreVenta = Valida.leerString("Ingrese el nombre del juego");
                    int cantidadVenta = Valida.leerIntPositivo("Ingrese la cantidad a vender");

                    double totalVenta = tienda.vender(nombreVenta, cantidadVenta);
                    if(totalVenta > 0){
                        System.out.println("¡Venta exitosa! El total a pagar es: $" + totalVenta);
                    } else {
                        System.out.println("Error: No se pudo vender (stock insuficiente o juego no existe).");
                    }
                }break;
                case 6:{
                    System.out.println("*******Juego más vendido********");
                    String masVendido = tienda.masVendido();
                    if(!masVendido.isEmpty()){
                        System.out.println("El juego más vendido es: " + masVendido);
                    } else {
                        System.out.println("Aún no hay ventas registradas.");
                    }
                }break;
                case 7:{
                    System.out.println("Gracias por usar la APP");
                }break;
                default:
                    System.out.println("Opción incorrecta, intente de nuevo.");
            }
        }while(opc != 7 );

    }

    public static void menu(){
        System.out.println("\n**********AppStore Udla**********");
        System.out.println("1. Ingresar Juego");
        System.out.println("2. Mostrar juegos");
        System.out.println("3. Buscar juego");
        System.out.println("4. Comprar");
        System.out.println("5. Vender");
        System.out.println("6. Juego más vendido");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    public static void ingresarJuegos(){
        System.out.println("*******Ingreso de datos********");
        String nombre = Valida.leerString("Ingrese el nombre");
        String categoria = Valida.leerString("Ingrese la categoria");
        int tamanio = Valida.leerIntPositivo("Ingrese el tamanio (MB/GB)");
        double precio = Valida.leerDoublePositivo("Ingrese el precio");
        int licenciasDisponibles = Valida.leerIntPositivo("Ingrese el stock inicial");

        if(tienda.agregarJuego(new Juego(nombre,categoria, tamanio, precio, licenciasDisponibles))){
            JOptionPane.showMessageDialog(null,"El juego se ha agregado con exito!!");
        }else{
            JOptionPane.showMessageDialog(null,"El juego no pudo ser agregado (memoria llena)");
        }
    }

    public static void mostrarJuegos() {
        Juego juegos[] = tienda.getJuegos();
        if (tienda.getIndice() >= 0) {
            for (int i = 0; i <= tienda.getIndice(); i++) {
                System.out.println(juegos[i]);
            }
        }else{
            System.out.println("No existen juegos a mostrar");
        }
    }
}