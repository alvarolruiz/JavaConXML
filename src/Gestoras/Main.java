package Gestoras;
import DAL.ListadosProductos;
import Entidades.Producto;
import Utilidades.UtilidadesXML;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class Main {

    public static final String RUTA_FICHERO_SALIDA = ".\\listaProductosXML.xml";
    public static void main(String[] args) {
    ArrayList<Producto> listaProductos = ListadosProductos.getListadoProductos();
        try {
           Document docXml=  UtilidadesXML.generarDOMXML(listaProductos);
           UtilidadesXML.serializaDOMXML(docXml,RUTA_FICHERO_SALIDA, true);
           UtilidadesXML.imprimirXMLSAX(RUTA_FICHERO_SALIDA);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
