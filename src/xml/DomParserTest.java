package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
//xml문의 엘리먼트(노드,태그)의 속성을 추출

public class DomParserTest {
    public static void main(String[] args) {
        //parser를 만들어주는 Factory객체를 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            //xml문서를 파싱 할 수 있는 dom parser를 생성
            DocumentBuilder builder = factory.newDocumentBuilder();
            //xml문서를 파싱해서 자바에서 쓸 수 있는 객체로 리턴해준다.
            Document document =builder.parse("src/xml/dept.xml");
            Element root = document.getDocumentElement();
            NodeList deptlist= root.getElementsByTagName("dept");
            //각각의 detpt노드를 탐색하면서 속성과 속성값을 출력하기
            for(int i=0; i<deptlist.getLength(); i++) {
                Node deptnode = deptlist.item(i);
                System.out.print((i+1)+"번 노드,");
                System.out.print("노드명:" + deptnode.getNodeName());

                //dept엘리먼트의 속성을 추출 - 노드로 관리
                //속성은 속성명과 속성값을 같이 저장해야 하므로 MAP(table 형태의 자료구조 key와value)으로 관리
                //deptnode 의 모든 속성을 추출 - NameNodeMap관리
                NamedNodeMap  attr_list = deptnode.getAttributes();
                System.out.print(", dept노드의 속성갯수:" +attr_list.getLength() );
                for(int j=0; j<attr_list.getLength(); j++) {
                    Node attribute= attr_list.item(j);
                    System.out.println(",속성명:"+attribute.getNodeName()+ "("+attribute.getNodeValue()+")");
                } //속성에 대한 탐색 완료
                System.out.println();

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}