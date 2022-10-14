import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHelper extends DefaultHandler {

    boolean descripcion = false;
    boolean precio_unidad = false;
    boolean descuento = false;
    boolean unidades = false;
    boolean fecha = false;


    public void starElement (String uri, String localName, String elementos, Attributes atributos){

        switch (elementos){

            case "descripcion":
                descripcion = true;
                break;

            case "precio_unidad":
                precio_unidad = true;
                break;

            case "descuento":
                descuento = true;
                break;

            case "unidades":
                unidades = true;
                break;

            case "fecha":
                fecha = true;
                break;

        }
    }


    public void characters(char ch[], int inicio, int length){

        if(descripcion){
            System.out.println("Descripcion: " + new String(ch, inicio, length));
            return;
        }

        if(precio_unidad){
            System.out.println("Precio unidad: " + new String(ch, inicio, length));
            return;
        }

        if(descuento){
            System.out.println("Descuento: " + new String(ch, inicio, length));
            return;
        }
    }

}
