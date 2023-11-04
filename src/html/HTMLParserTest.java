package html;
//jsoup 라이브러리를 이용하여 HTML문서를 크롤링하는 작업
//https://jsoup.org/ 다운로드
//jsoup-1.16.1.jar core library 다운로드
//file - projectstructure - modules - dependency export (라이브러리 추가)


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParserTest {
    public static void main(String[] args) throws IOException {
        //1.원하는 정보가 있는 HTML페이지에 접속하기
        //=>Connection은 java.sql의 Connection이 아니라 크롤링하고 싶은 페이지에 연결한 정보를 담고 있는
        //Jsoup의 객체

        //=>접속하여 해당 페이지를 모두 response해서 Document객체로 받아오기
        //get은 Document를 리턴
        //Document document=Jsoup.connect("https://finance.naver.com/sise/sise_rise.naver").get();

        Connection con = Jsoup.connect("https://finance.naver.com/sise/sise_rise.naver");
        //2.접속한 페이지의 모든 response하는 데이터를 Document객체로 변환
        //=> Document객체는 돔 api의 Document가 아니라 Jsoup에서 제공하는 Document
        Document document=con.get();
        if(document != null){
            //3.원하는 엘리먼트(html태그 노드)를 받아오기
            //Elements는 여러개의 엘리먼트를 받아오기 - Nodelist와 동일
            //Element는 한개의 엘리먼트 -node
            //table태그 중에서 class속성이 type_2인 엘리먼트의 tbody 태그 하위의 모든 tr을 가져오기
            Elements elements= document.select("table[class='type_2']>tbody>tr");
            System.out.println(elements.size());

            //변수로 저장하면 메소드가 불러지는 작업이 줄어들기 때문에 메모리를 적게 사용한다.
            int size = elements.size();
            for(int i=0; i<size; i++){
                Element tag = elements.get(i);
                System.out.println(tag.text());
            }
        }
    }

}
