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

public class Umbrella extends Climate{

	Climate use = new Climate();
	String category;
	JSONObject weather;
	
	

	public void CheckUmbrella(JSONArray array) {
		JSONArray result = array;
		
		for(int i = 0 ; i < result.size(); i++) {
			weather = (JSONObject) result.get(i);
			Object fcstValue = weather.get("fcstValue");
			
			category = (String)weather.get("category"); 
			
			
			if(category.equals("POP")) {
                
                category = "우산없이 가벼운 발걸음";
                if(Integer.valueOf((String)fcstValue) > 50) {
                	category = "우산을 챙기세요";
                }
                fcstValue  = fcstValue+" %";
            }
			
			System.out.println(category);
			i=result.size();
			
		}
	}
}
