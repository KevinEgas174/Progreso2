package arreglos.tienda.utilitario;

import java.util.Scanner;

public class Valida {
    private static Scanner sc = new Scanner(System.in);

    public static String leerString(String mensaje){
        String valor;
        do{
            System.out.print(mensaje + ": ");
            valor= sc.nextLine();
            if(valor == null  ||  valor.trim().isEmpty()){
                System.out.println("El dato no puede ser nullo ni estar vacio");
            }
        }while(valor == null  ||  valor.trim().isEmpty());
        return valor;
    }

    public static int leerIntPositivo(String mensaje) {
        int numero = 0;
        boolean datoValido = false;
        do {
            System.out.print(mensaje + ": ");
            try {
                numero = Integer.parseInt(sc.nextLine());

                if (numero > 0) {
                    datoValido = true;
                } else {
                    System.out.println("El número debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresaste texto inválido. Usa solo números enteros.");
            }
        } while (!datoValido);

        return numero;
    }

    public static double leerDoublePositivo(String mensaje) {
        double numero = 0;
        boolean datoValido = false;

        do {
            System.out.print(mensaje + ": ");
            try {
                numero = Double.parseDouble(sc.nextLine());

                if (numero > 0) {
                    datoValido = true;
                } else {
                    System.out.println("El valor debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresaste texto inválido. Usa solo números.");
            }
        } while (!datoValido);

        return numero;
    }
}
