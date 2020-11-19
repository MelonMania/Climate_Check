package ClimateCheck;

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

public class Climate {

	public JSONArray ClimateFetch(String x, String y) throws IOException, ParseException {
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";	//동네예보조회
		
		// 홈페이지에서 받은 키
		String serviceKey = "MCR6WADbpGTRVXeVD%2BJFDYbAgi2GMbjTnCl7qvjaAI7Z%2BwPo7mqBc%2FEIcK4rZtzoCp6hQMzX0AsNueDYFCoZzA%3D%3D";
		String nx = x;	//위도
		String ny = y;	//경도
		String baseDate = sdf.format(date);	//조회하고싶은 날짜
		String baseTime = "0200";	//API 제공 시간
		String dataType = "json";	//타입 xml, json
		String numOfRows = "100";	//한 페이지 결과 수 

		
		
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));	/* 타입 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));	/* 한 페이지 결과 수 */
		
		// GET방식으로 전송해서 파라미터 받아오기
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String data= sb.toString();
		
		// Jsonparser로 파싱 시작
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(data); 
		
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		 
		JSONObject parse_items = (JSONObject) parse_body.get("items");
		JSONArray parse_item = (JSONArray) parse_items.get("item");
		;
		
		return parse_item;
	}

	public void ClimatePrint(JSONArray array) {
		String category;
		JSONObject weather;
		String day="";
		String time="";
		JSONArray parse_item = array;
		
		for(int i = 0 ; i < parse_item.size(); i++) {
			weather = (JSONObject) parse_item.get(i);
			Object fcstValue = weather.get("fcstValue");
			Object fcstDate = weather.get("fcstDate");
			Object fcstTime = weather.get("fcstTime");
		
			category = (String)weather.get("category"); 
		
			
			if(!day.equals(fcstDate.toString())) {
				day=fcstDate.toString();
			}
			if(!time.equals(fcstTime.toString())) {
				time=fcstTime.toString();
				System.out.println("=============================");
				System.out.println(day+"  "+time+" 기상예보");
			}
			 if(category.equals("POP")) {
	                
	                category = "강수확률";
	                fcstValue  = fcstValue+" %";
	            }
			 if(category.equals("PTY")){
				 category = "강수형태";
	                if(fcstValue.equals("0")) {
	                	fcstValue = "없음";
	                }else if(fcstValue.equals("1")) {
	                	fcstValue = "비";
	                }else if(fcstValue.equals("2")) {
	                	fcstValue = "눈/비";
	                }else if(fcstValue.equals("3")) {
	                	fcstValue = "눈";
	                }
	            }
			 if(category.equals("R06")) {
	                category = "6시간강수량";
	                fcstValue = fcstValue + " mm";		   
	            }
			 if(category.equals("T1H")) {
				 category = "기온";
				 fcstValue = fcstValue+"℃";
	            }
			 if(category.equals("TMX")) {
				 category = "낮 최고기온";
				 fcstValue = fcstValue+"℃";
	            }
			 if(category.equals("TMN")) {
				 category = "아침 최저 기온";
	                fcstValue = fcstValue+"℃";
	            }
			 if(category.equals("SKY")) {
				 category = "하늘상태";
	                if(fcstValue.equals("1")) {
	                	fcstValue = "맑음";
	                }else if(fcstValue.equals("2")) {
	                	fcstValue = "비";
	                }else if(fcstValue.equals("3")) {
	                	fcstValue = "구름많음";
	                }else if(fcstValue.equals("4")) {
	                	fcstValue = "흐림";
	                }
	            }
			 if(category.equals("REH")) {
	                
	                category = "습도";
	               fcstValue = fcstValue+" %";
	            }
			 if(category.equals("WSD")) {
	                
	                category = "풍속";
	               fcstValue = fcstValue+"m/s";
	            }
			 if(category.equals("S06")) {
				 category = "6시간적설량";
				 fcstValue = fcstValue + " mm";
	            }
			 if(category.equals("T3H")) {
				 category = "3시간기온";
				 fcstValue = fcstValue + " ℃";
	            }
			 if(category.equals("UUU")) {
				 category = "동서성분풍속";
				 fcstValue = fcstValue+" m/s";
	            }
			 if(category.equals("VEC")) {
				 category = "풍향";
				 fcstValue = fcstValue + " m/s";
	            }
			 if(category.equals("VVV")) {
				 category = "남북성분풍속";
				 fcstValue = fcstValue+" m/s";
	            }
			 
			System.out.print(category);
			System.out.println(": "+ fcstValue);
			
			
		}
		       
	}
}
