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

		
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";	//���׿�����ȸ
		
		// Ȩ���������� ���� Ű
		String serviceKey = "MCR6WADbpGTRVXeVD%2BJFDYbAgi2GMbjTnCl7qvjaAI7Z%2BwPo7mqBc%2FEIcK4rZtzoCp6hQMzX0AsNueDYFCoZzA%3D%3D";
		String nx = x;	//����
		String ny = y;	//�浵
		String baseDate = sdf.format(date);	//��ȸ�ϰ���� ��¥
		String baseTime = "0200";	//API ���� �ð�
		String dataType = "json";	//Ÿ�� xml, json
		String numOfRows = "100";	//�� ������ ��� �� 

		
		
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //�浵
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //����
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* ��ȸ�ϰ���� ��¥*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* ��ȸ�ϰ���� �ð� AM 02�ú��� 3�ð� ���� */
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));	/* Ÿ�� */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));	/* �� ������ ��� �� */
		
		// GET������� �����ؼ� �Ķ���� �޾ƿ���
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
		
		// Jsonparser�� �Ľ� ����
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
				System.out.println(day+"  "+time+" ��󿹺�");
			}
			 if(category.equals("POP")) {
	                
	                category = "����Ȯ��";
	                fcstValue  = fcstValue+" %";
	            }
			 if(category.equals("PTY")){
				 category = "��������";
	                if(fcstValue.equals("0")) {
	                	fcstValue = "����";
	                }else if(fcstValue.equals("1")) {
	                	fcstValue = "��";
	                }else if(fcstValue.equals("2")) {
	                	fcstValue = "��/��";
	                }else if(fcstValue.equals("3")) {
	                	fcstValue = "��";
	                }
	            }
			 if(category.equals("R06")) {
	                category = "6�ð�������";
	                fcstValue = fcstValue + " mm";		   
	            }
			 if(category.equals("T1H")) {
				 category = "���";
				 fcstValue = fcstValue+"��";
	            }
			 if(category.equals("TMX")) {
				 category = "�� �ְ���";
				 fcstValue = fcstValue+"��";
	            }
			 if(category.equals("TMN")) {
				 category = "��ħ ���� ���";
	                fcstValue = fcstValue+"��";
	            }
			 if(category.equals("SKY")) {
				 category = "�ϴû���";
	                if(fcstValue.equals("1")) {
	                	fcstValue = "����";
	                }else if(fcstValue.equals("2")) {
	                	fcstValue = "��";
	                }else if(fcstValue.equals("3")) {
	                	fcstValue = "��������";
	                }else if(fcstValue.equals("4")) {
	                	fcstValue = "�帲";
	                }
	            }
			 if(category.equals("REH")) {
	                
	                category = "����";
	               fcstValue = fcstValue+" %";
	            }
			 if(category.equals("WSD")) {
	                
	                category = "ǳ��";
	               fcstValue = fcstValue+"m/s";
	            }
			 if(category.equals("S06")) {
				 category = "6�ð�������";
				 fcstValue = fcstValue + " mm";
	            }
			 if(category.equals("T3H")) {
				 category = "3�ð����";
				 fcstValue = fcstValue + " ��";
	            }
			 if(category.equals("UUU")) {
				 category = "��������ǳ��";
				 fcstValue = fcstValue+" m/s";
	            }
			 if(category.equals("VEC")) {
				 category = "ǳ��";
				 fcstValue = fcstValue + " m/s";
	            }
			 if(category.equals("VVV")) {
				 category = "���ϼ���ǳ��";
				 fcstValue = fcstValue+" m/s";
	            }
			 
			System.out.print(category);
			System.out.println(": "+ fcstValue);
			
			
		}
		       
	}
}
