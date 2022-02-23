package Utilidades;

import Entidades.Producto;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class GestorEventosSAX extends DefaultHandler {
    private boolean id;
    private boolean descripcion;
    private boolean precio;
    private boolean stock;
    List<Producto> listaProductos;
    Producto currentProduct;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(ProductoElement.ID_PRODUCTO)) {
            id = true;
        }
        if (qName.equalsIgnoreCase(ProductoElement.DESCRIPCION_PRODUCTO)) {
            descripcion = true;
        }
        if (qName.equalsIgnoreCase(ProductoElement.PRECIO_PRODUCTO)) {
            precio = true;
        }
        if (qName.equalsIgnoreCase(ProductoElement.STOCK_PRODUCTO)) {
            stock = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(id){
            currentProduct.setId(Integer.valueOf(new String(ch, start, length)));
            id=false;
        }
        if(descripcion){
            currentProduct.setDescripcion(new String(ch, start, length));
            descripcion=false;
        }
        if(precio){
            currentProduct.setPrecio(Double.valueOf(new String(ch, start, length)));
            precio=false;
        }
        if(stock){
            currentProduct.setStock(Integer.valueOf(new String(ch, start, length)));
            stock=false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       if(qName.equalsIgnoreCase(ProductoElement.NAME_PRODUCTO)){
           listaProductos.add(currentProduct);
           currentProduct= new Producto();
       }
    }

    public List <Producto> getProductos(){
        return listaProductos;
    }


}
