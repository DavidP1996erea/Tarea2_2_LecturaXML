import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main_DOM {
    public static void main(String[] args) {


        try {
            File comprasXml = new File("archivoCompras.xml");

            //Objetos para leer el fichero

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            //Se pasa el xml
            Document doc = db.parse(comprasXml);
            doc.getDocumentElement().normalize();

            NodeList listaCompra = doc.getElementsByTagName("compra");

            double compraDia=0;
            double totalProductoDia=0;
            double descuentoProducto=0;
            double resumenTotalCompra=0;


            for(int i=0; i< listaCompra.getLength();i++){

                Node nodo = listaCompra.item(i);

                if(nodo.getNodeType()==Node.ELEMENT_NODE) {

                    Element e = (Element) nodo;
                    NodeList hijos = e.getChildNodes();


                    System.out.println(e.getElementsByTagName("fecha").item(0).getTextContent());
                    System.out.println(e.getElementsByTagName("ticket").item(0).getTextContent());
                    for (int j = 0; j < hijos.getLength(); j++) {

                        Node hijo = hijos.item(j);
                        if(hijo.getNodeType()==Node.ELEMENT_NODE) {

                            Element eHijo = (Element) hijo;
                            NodeList hijoDelHijo = eHijo.getChildNodes();




                            for(int m =0; m<hijoDelHijo.getLength();m++){

                                Node hijito = hijoDelHijo.item(m);

                                if(hijito.getNodeType()==Node.ELEMENT_NODE) {

                                    Element hijoDelHijito = (Element) hijito;

                                    compraDia+=Double.parseDouble(hijoDelHijito.getElementsByTagName("precio_unidad").item(0).getTextContent());

                                    if(hijoDelHijito.getElementsByTagName("unidades").item(0)==null){
                                        totalProductoDia++;
                                    }else {
                                        totalProductoDia +=Double.parseDouble(hijoDelHijito.getElementsByTagName("unidades").item(0).getTextContent());
                                    }

                                    if(hijoDelHijito.getElementsByTagName("descuento").item(0)!=null) {
                                        descuentoProducto += Double.parseDouble(hijoDelHijito.getElementsByTagName("descuento").item(0).getTextContent());
                                    }

                                }


                            }



                        }

                    }
                }


                System.out.println("Suma total del producto: " + compraDia);
                System.out.println("Total productos: " + totalProductoDia);
                System.out.println("Total descuento " + descuentoProducto);
                resumenTotalCompra = compraDia-descuentoProducto;
                System.out.println("Resumen total compra " + resumenTotalCompra);
                compraDia=0;
                totalProductoDia=0;
                descuentoProducto=0;
            }


    }catch(
    Exception p)

    {
        p.printStackTrace();
    }


}
}

/*

 */