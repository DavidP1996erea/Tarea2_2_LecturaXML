import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxHelper extends DefaultHandler {

    boolean fechaA = false;
    boolean descripcionA = false;
    boolean precio_unidadA = false;
    boolean descuentoA = false;
    boolean unidadesA = false;

    boolean ticketA = false;


    double precioTotal =0;
    int cantidadProductos;
    double descuentosDia=0;

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
