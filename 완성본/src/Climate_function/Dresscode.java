package Climate_function;
import ClimateCheck.Climate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dresscode extends Climate{

	Climate use = new Climate();
	String category;
	JSONObject weather;
	
	

	public void CheckDress(JSONArray array) {
		JSONArray result = array;
		int i = 0;
		
		while(i<result.size()) {
			weather = (JSONObject) result.get(i);
			Object fcstValue = weather.get("fcstValue");

			
			category = (String)weather.get("category"); 
			
			
			if(category.equals("T3H")) {
				
				System.out.println("앞으로 3시간동안의 평균 기온 : " + fcstValue+"℃");
				int tmp = Integer.valueOf((String)fcstValue);
				if(tmp<0) {
					System.out.println("따뜻하게 입으세요");
					break;
				}
				else if(tmp<10) {
					System.out.println("가벼운 외출이면 적당한 겉옷을 챙기시고 늦은 귀가가 예상되면 좀 더 두꺼운 겉옷을 챙기세요");
					break;
				}
				else if(tmp<20) {
					System.out.println("기모가 살짝 들어있는 맨투맨이나 후드티를 추천합니다.");
					break;
				}
				else if(tmp<30) {
					System.out.println("반팔에 얇은 겉옷이나 얇은 슬리브를 추천합니다.");
					break;
				}
				else if(tmp<40) {
					System.out.println("무조건 시원하게 입고 나가세요");
					break;
				}
                
            }		
			i++;
		}
	}
}
