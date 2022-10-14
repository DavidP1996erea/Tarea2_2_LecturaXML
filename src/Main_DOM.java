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

            double sd=0;


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

                                    sd += Double.parseDouble(hijoDelHijito.getElementsByTagName("precio_unidad").item(0).getTextContent());


                                }


                            }



                        }

                    }
                }


                System.out.println("Suma total del producto: " + sd);

                sd=0;
            }


          /* for (int i = 0; i < listaCompra.getLength(); i++) {


                Node node = listaCompra.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) node;

                        System.out.println("Marca: "+ eElement.getElementsByTagName("producto").item(i).getTextContent());

                    }





                System.out.println("Suma total del producto: " + sd);
                sd = 0;
            }
*/


    }catch(
    Exception p)

    {
        p.printStackTrace();
    }


}
}

/*

 */