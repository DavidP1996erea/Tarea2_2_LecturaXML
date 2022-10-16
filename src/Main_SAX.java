import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Main_SAX {
    public static void main(String[] args) {


        try {

            /**
             * Se prepara el archivo XML con SAX, una vez que ejecutemos el programa se lanzará la clase SaxHelper,
             * con con los métodos Override leerá el XML.
             */
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser saxParser = factory.newSAXParser();
            SaxHelper handler = new SaxHelper();
            saxParser.parse("archivoCompras.xml", handler);


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
