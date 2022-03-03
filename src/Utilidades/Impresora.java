package Utilidades;

import Entidades.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Impresora {
    public static final String SEPARATOR = "-----------------------------------------------------";

    public static void imprimirTablaProductos(ArrayList<Producto> listadoProductos) {
        System.out.println(SEPARATOR);
        System.out.printf("%7s %20s %10s %10s", "ID", "DESCRIPCIÓN", "PRECIO", "STOCK");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Producto p : listadoProductos) {
            imprimirProducto(p);
        }
        System.out.println(SEPARATOR);
    }

    private static void imprimirProducto(Producto p) {
        System.out.format("%7s %20s %10s %10s",
                p.getId(), p.getDescripcion(), imprimirDouble(p.getPrecio()), p.getStock());
        System.out.println();
    }

    private static String imprimirDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(d);
        return string;
    }
}
