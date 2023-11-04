package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

public class DomParserTest3 {
    public static void main(String[] args) {
        //parser를 만들어주는 Factory객체를 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            //xml문서를 파싱 할 수 있는 dom parser를 생성
            DocumentBuilder builder = factory.newDocumentBuilder();
            //xml문서를 파싱해서 자바에서 쓸 수 있는 객체로 리턴해준다.
            Document document =builder.parse("src/xml/dept.xml");
            //1.root 엘리먼트 구하기
            Element root = document.getDocumentElement();
            System.out.println("root 엘리먼트 태그명"+root.getTagName());
            //2. root 엘리먼트 에서 dept 태그에 해당하는 엘리먼트만 추출하기
            NodeList deptlist = root.getElementsByTagName("dept");
            System.out.println("dept 노드의 개수:" + deptlist.getLength());
            for(int i=0; i<deptlist.getLength(); i++) {
                //NodeList안에서 item하나씩 추출
                Node deptnode= deptlist.item(i);
                System.out.println("노드명:" + deptnode.getNodeName());
                //3.dept노드의 하위 노드를 추출
                NodeList deptnodeChildlist = deptnode.getChildNodes();
                for(int j=0; j<deptnodeChildlist.getLength(); j++) {
                    Node deptnoChildNode = deptnodeChildlist.item(j);
                    if(!deptnoChildNode.getNodeName().equals("#text")) { //공백노드가 아니면 출력
                        System.out.println("dept 하위노드명:" + deptnoChildNode.getNodeName());
                        System.out.println("dept 하위텍스트노드명:" + deptnoChildNode.getTextContent());
                    }
                }//dept child 노드 검색 완료
                System.out.println("-----------------");
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