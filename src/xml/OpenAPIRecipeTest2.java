package xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OpenAPIRecipeTest2 {
    public static void main(String[] args) throws IOException {
        String recipe_url = String.format("https://openapi.foodsafetykorea.go.kr/f1ee0f6ca3374f7183611/COOKRCP01/xml/1/5");

        URL url = new URL(recipe_url);
        InputStream xmldata = url.openStream();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xmldata);
            Element root = document.getDocumentElement();
            System.out.println(root.getTagName());
            NodeList list = root.getElementsByTagName("row");
            System.out.println(list.getLength());
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                NodeList item_childlist = node.getChildNodes();
                for (int j = 0; j < item_childlist.getLength(); j++) {
                    Node item_child = item_childlist.item(j);
                    System.out.println(item_child.getNodeName() + ":" + item_child.getTextContent());

                }
                System.out.println();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}