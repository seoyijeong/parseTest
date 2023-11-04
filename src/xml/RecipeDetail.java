package xml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RecipeDetail {
        public static void main(String[] args) {
            String key = "f1ee0f6ca3374f718361";
            String result = "";

            try{
//                String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
//                        + key + "&movieCd=20124039";

                String apiURL = "http://openapi.foodsafetykorea.go.kr/api/keyId/serviceId/dataType/startIdx/endIdx.json?key="
                        + key + "&movieCd=20124039";
                URL url = new URL(apiURL);

                BufferedReader bf;

                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = bf.readLine();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
