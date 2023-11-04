package xml;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
//건강정보심사평가원_약국정보를 파싱하기
//xml 파일의 파싱이 아닌
//url을 통해서 접속하고 xml정보를 가져온 후 parser로 원하는 정보를 추출

// java-> data 접속 -> 응답데이터를 문서의 형태로 제공(읽어와야함) 이때 IO API를 사용
public class OpenAPIPharmacyTest2 {
    public static void main(String[] args)  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //파싱할 InputStream생성
            //1.웹 서버를 제동하는 url에 연결해서 데이터 가져오기
            //자바 주소 전체중 ?물음표 앞까지만 가져오기
            StringBuffer pharm_url = new StringBuffer();
            pharm_url.append("http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList");
            //물음표다음부터 서비스키 까지 가져오기
            //ServiceKey 잘못 표기 되어 있는 부분도 있음(소문자 서비스 키)
            //1) 접속할 url의 정보를 정의
            pharm_url.append("?ServiceKey=uc7c%2FuS1PxunV%2Ft0X8iNFWNrU9EKCnpQpjiSKk5jU%2BEUC4NfRPzSU%2F2%2Bv9t4YPzx1WIZXYZ%2BuLNgsgqvGRttoA%3D%3D");
            //2)웹서버에 접속하기
            URL url = new URL(pharm_url.toString());

            //3) 접속해서 response되는 데이터를 읽어오기
            //읽어온 데이터를 저장 -BufferedInputStream은 InputStream의 하위 로 받는다.
            BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
            System.out.println(url.openStream()); //객체만 리턴
            Document document= builder.parse(xmldata);   //InputStream객체의 형태로 파싱할 정보를 넘겨준다
            Element root = document.getDocumentElement();
            System.out.println(root.getTagName());
            NodeList list = root.getElementsByTagName("item");
            System.out.println(list.getLength());
            for(int i=0; i<list.getLength(); i++) {
                Node node = list.item(i);
                NodeList item_childlist=node.getChildNodes();
                for(int j=0; j<item_childlist.getLength(); j++) {
                    Node item_child = item_childlist.item(j);
                    System.out.println(item_child.getNodeName() +":" + item_child.getTextContent());

                }
                System.out.println();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SAXException e ) {
            e.printStackTrace();
        }
    }
}
