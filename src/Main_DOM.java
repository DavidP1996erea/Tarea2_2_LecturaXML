import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main_DOM {
    public static void main(String[] args) {



        try{
            File comprasXml = new File("archivoCompras.xml");

            //Objetos para leer el fichero

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            //Se pasa el xml
            Document doc =  db.parse(comprasXml);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("compra");


            for(int i=0; i< nodeList.getLength();i++){


                Node node = nodeList.item(i);

                if(node.getNodeType()==Node.ELEMENT_NODE){

                    Element element = (Element) node;

                    System.out.println("Fecha " + element.getElementsByTagName("fecha").item(0).getTextContent());
                    System.out.println("Ticket " + element.getElementsByTagName("ticket").item(0).getTextContent());
                    System.out.println("Descripción " + element.getElementsByTagName("descripcion").item(0).getTextContent());
                    System.out.println("Precio " + element.getElementsByTagName("precio_unidad").item(0).getTextContent());
                   // System.out.println("Descuento " + element.getElementsByTagName("descuento").item(0).getTextContent());

                }



            }





        }catch (Exception e){
            e.printStackTrace();
        }



    }
}