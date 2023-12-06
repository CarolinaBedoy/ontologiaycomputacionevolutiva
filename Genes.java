package genes;

import java.util.Random;

public class Genes {

    static String randoms = "";
    static String[] poblacion = new String[8];
    static String[] poblacionNueva = new String[8];
    static String[] poblacionDec = new String[8];

    public static double eval(double num) {
        double resultado = -((Math.pow(num, 2)) - 1) * (num - 35) * (num - 4);
        return resultado;
    }

    public static String toDecimal(String cadena, int decimal) {
        String positivo = "";
        String negativo = "";
        String r;
        for (int i = 0; i < cadena.length(); i++) {
            if (i < decimal) {
                positivo = positivo.concat(String.valueOf(cadena.charAt(i)));
            } else if (i > decimal) {
                negativo = negativo.concat(String.valueOf(cadena.charAt(i)));
            }
        }
        r = String.valueOf(Integer.parseInt(positivo, 2)) + '.' + String.valueOf(Integer.parseInt(negativo, 2));
        return r;
    }

    public static void emparejamiento() {
        Random random = new Random();

        int a = (1 + random.nextInt((8 + 1) - 1));
        int b = (1 + random.nextInt((8 + 1) - 1));
        if (a == b || randoms.contains(String.valueOf(a)) || randoms.contains(String.valueOf(b))) {
            emparejamiento();
        } else {
            System.out.println(a + "-" + b);
            randoms = randoms.concat(String.valueOf(a));
            randoms = randoms.concat(String.valueOf(b));
        }

    }

    public static String[] mutacion(String[] pob, String randoms) {
        Random random = new Random();
        int particion = (2 + random.nextInt((7 + 1) - 2));

        String[] nuevaPoblacion = new String[8];
        for (int q = 0; q < 8; q += 2) {

            char a = randoms.charAt(q);
            char b = randoms.charAt(q + 1);
            int c = Integer.parseInt(String.valueOf(a));
            int d = Integer.parseInt(String.valueOf(b));

            String c1 = pob[c - 1];
            String c2 = pob[d - 1];

            String d1 = "";
            String d2 = "";

            for (int i = 0; i < 10; i++) {
                if (i <= particion) {
                    d1 = d1.concat(String.valueOf(c1.charAt(i)));
                    d2 = d2.concat(String.valueOf(c2.charAt(i)));
                } else {
                    d1 = d1.concat(String.valueOf(c2.charAt(i)));
                    d2 = d2.concat(String.valueOf(c1.charAt(i)));
                }
            }
            nuevaPoblacion[q] = d1;
            nuevaPoblacion[q + 1] = d2;
            poblacionNueva[q] = nuevaPoblacion[q];
            poblacionNueva[q + 1] = nuevaPoblacion[q + 1];

            if (d1.charAt(0) == '1') {
                d1 = d1.substring(0, 0) + '-' + d1.substring(0 + 1);
            } else if (d1.charAt(0) == '0') {
                d1 = d1.substring(0, 0) + '+' + d1.substring(0 + 1);
            } else if (d2.charAt(0) == '1') {
                d2 = d2.substring(0, 0) + '-' + d2.substring(0 + 1);
            } else if (d2.charAt(0) == '0') {
                d2 = d2.substring(0, 0) + '+' + d2.substring(0 + 1);
            }
            poblacionDec[q] = d1;
            poblacionDec[q + 1] = d2;

        }
        System.out.println("Posición en que parte: " + particion);
        poblacion = poblacionNueva;
        return nuevaPoblacion;
    }

    public static void print(String poblacion, String cadena, Integer decimal, Integer num) {
        System.out.println((num + ")" + poblacion + "            " + toDecimal(cadena, decimal) + "                    " + eval(Double.valueOf(toDecimal(cadena, decimal)))));

    }

    public static void main(String[] args) {

        Random random = new Random();
        String cadena = "";

        int decimal = (2 + random.nextInt((7 + 1) - 2)); //posición de punto decimal

        System.out.println("ECUACIÓN: -(X²-1)(X-35)(X-4)\n");
        System.out.println("POBLACIÓN INICIAL      BINARIO A DECIMAL       EVALUACIÓN EN ECUACIÓN");

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                if (i == decimal) {
                    cadena = cadena.concat((String.valueOf('.')));
                } else if (i != decimal) {
                    int d = (0 + random.nextInt((1 + 1) - 0));
                    cadena = cadena.concat(String.valueOf(d));
                }

            }
            poblacion[j] = cadena;
            if (cadena.charAt(0) == '1') {
                cadena = cadena.substring(0, 0) + '-' + cadena.substring(0 + 1);
            } else if (cadena.charAt(0) == '0') {
                cadena = cadena.substring(0, 0) + '+' + cadena.substring(0 + 1);
            }

            print(poblacion[j], cadena, decimal, j + 1);
            cadena = "";

        }

        for (int t = 0; t < 3; t++) { //número de iteraciones
            randoms = "";
            System.out.println("\nEMPAREJAMIENTO");

            for (int i = 0; i < 4; i++) {
                emparejamiento();
            }

            System.out.println("\nPOBLACIÓN              BINARIO A DECIMAL       EVALUACIÓN EN ECUACIÓN");
            mutacion(poblacion, randoms);
            for (int i = 0; i < 8; i++) {
                print(poblacion[i], poblacionDec[i], decimal, i + 1);
            }

        }

    }

}
