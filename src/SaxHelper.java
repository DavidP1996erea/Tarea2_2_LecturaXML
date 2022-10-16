import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxHelper extends DefaultHandler {

    /**
     * Se crean las variables booleanas de las etiquetas que queremos leer
     */
    boolean fechaA = false;
    boolean descripcionA = false;
    boolean precio_unidadA = false;
    boolean descuentoA = false;
    boolean unidadesA = false;

    boolean ticketA = false;


    double precioTotal =0;
    int cantidadProductos;
    double descuentosDia=0;

    /**
     * Este método se activará cuando encuentre una etiqueta de apertura. Con el switch le indicamos que cuando lea
     * en la etiqueta cada uno de los casos propuestos, cambie el booleano a true, indicando que lo ha leído
     *
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {

            case "ticket":
                ticketA = true;
                break;

            case "fecha":
                fechaA = true;
                break;

            case "descripcion":
                descripcionA = true;
                break;

            case "precio_unidad":
                precio_unidadA = true;
                break;

            case "unidades":
                unidadesA = true;
                break;

            case "descuento":
                descuentoA= true;
                break;
            default:
                break;

        }



    }

    /**
     * Este método se activará cuando encuentre algo escrito dentro de la etiqueta de apertura visto anteriormente.
     * En caso de hacernos falta, se irá acumulando el precio o el descuento para luego mostrarlo por pantalla.
     * @param ch The characters.
     * @param inicio The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    public void characters(char ch[], int inicio, int length) {

        if (fechaA) {
            System.out.println("Fecha: " + new String(ch, inicio, length));

            return;
        }
        if (descripcionA) {
            System.out.println("Descripcion: " + new String(ch, inicio, length));
            cantidadProductos++;

            return;
        }

        if (precio_unidadA) {
            System.out.println("Precio unidad: " + new String(ch, inicio, length));
            precioTotal+= Double.parseDouble(new String(ch, inicio, length));
            return;
        }


        if (descuentoA) {
            System.out.println("Descuento: " + new String(ch, inicio, length));
            descuentosDia+= Double.parseDouble(new String(ch, inicio, length));
            return;
        }

        if (unidadesA) {
            System.out.println("unidades: " + new String(ch, inicio, length));
            return;
        }

    }

    /**
     * Este método se activará cuando encuentre una etiqueta de cierre. La etiqueta ticket está puesta para que funcione
     * como diferenciador de compras. Cada vez que la etiqueta ticket se cierre significa que ha terminado la compra
     * de un día, por lo que todas las operaciones necesarias deben mostrarse ahí. También ahí es donde se resetea todas
     * las variables que necesitamos acumular por días, ya que como he dicho antes, cada vez que se cierra la etiqueta,
     * termina un día de compra, por lo que se resetea todas las variables.
     *
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param elementos The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @throws SAXException
     */
    public void endElement(String uri, String localName, String elementos) throws SAXException {
        switch (elementos) {

            case "ticket":
                System.out.println("El precio total del día es: " + precioTotal);
                System.out.println("la cantidad de productos total del día es: " + cantidadProductos);
                System.out.println("Descuento total del día es: " + descuentosDia);
                System.out.println("Total del día: " + (precioTotal-descuentosDia));
                ticketA = false;
                precioTotal=0;
                cantidadProductos=0;
                descuentosDia=0;

                System.out.println("Se termino el ticket \n");
                break;

            case "fecha":
                fechaA = false;
                break;

            case "descripcion":
                descripcionA = false;
                break;

            case "precio_unidad":
                precio_unidadA = false;
                break;

            case "unidades":
                unidadesA = false;
                break;

            case "descuento":
                descuentoA  = false;
                break;
            default:

                break;

        }
    }

}
