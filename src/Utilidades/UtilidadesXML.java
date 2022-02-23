package Utilidades;

import Entidades.Producto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesXML {
public static int tamanoTabulaciones = 3;
public static String tipoDocumento = "xml";
public static String charset = "UTF-8";
    public static Document generarDOMXML(List<Producto> listaProductos) throws ParserConfigurationException {
        Document doc = getNewDocumentInstance(true,false);
        Element rootElement = doc.createElement("Productos");
        doc.appendChild(rootElement);
        for (Producto p : listaProductos) {
            Element producto = doc.createElement("Producto");
                Element id = doc.createElement(ProductoElement.ID_PRODUCTO);
                    id.setTextContent(String.valueOf(p.getId()));
                    producto.appendChild(id);
                Element descripcion = doc.createElement(ProductoElement.DESCRIPCION_PRODUCTO);
                    descripcion.setTextContent(p.getDescripcion());
                    producto.appendChild(descripcion);
                Element precio = doc.createElement(ProductoElement.PRECIO_PRODUCTO);
                    precio.setTextContent(String.valueOf(p.getPrecio()));
                    producto.appendChild(precio);
                Element stock = doc.createElement(ProductoElement.STOCK_PRODUCTO);
                    stock.setTextContent(String.valueOf(p.getStock()));
                    producto.appendChild(stock);
            rootElement.appendChild(producto);
        }
        return doc;
    }

    public static void serializaDOMXML(Document doc, String rutaFicheroSalida, boolean tabular) {
        try {
            Transformer transformer = getNewTransformerInstance(tabular);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaFicheroSalida));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void imprimirXMLSAX(String nombreFichero) {
//TODO Implementar
    }

    private static Transformer getNewTransformerInstance(boolean tabular) throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, tipoDocumento);
        transformer.setOutputProperty(OutputKeys.ENCODING, charset);
        if(tabular){
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(tamanoTabulaciones));
        }
        return transformer;
    }

    private static Document getNewDocumentInstance(boolean ignorarComentarios, boolean ignorarEspaciosEnBlanco) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setIgnoringComments(ignorarComentarios);
        docFactory.setIgnoringElementContentWhitespace(ignorarEspaciosEnBlanco);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }
}
