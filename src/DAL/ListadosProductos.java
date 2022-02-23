package DAL;

import Entidades.Producto;

import java.util.ArrayList;

public class ListadosProductos {

    public static ArrayList<Producto> getListadoProductos(){
        ArrayList <Producto> listaProductos = new ArrayList<Producto>();
        listaProductos.add(new Producto(2344, "Pasta termica 4G", 6,36));
        listaProductos.add(new Producto(1098, "Placa base Asus", 150,14));
        listaProductos.add(new Producto(7643, "Amd Ryzen 9", 6,560));
        return listaProductos;
    }
}
