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
				
				System.out.println("������ 3�ð������� ��� ��� : " + fcstValue+"��");
				int tmp = Integer.valueOf((String)fcstValue);
				if(tmp<0) {
					System.out.println("�����ϰ� ��������");
					break;
				}
				else if(tmp<10) {
					System.out.println("������ �����̸� ������ �ѿ��� ì��ð� ���� �Ͱ��� ����Ǹ� �� �� �β��� �ѿ��� ì�⼼��");
					break;
				}
				else if(tmp<20) {
					System.out.println("��� ��¦ ����ִ� �������̳� �ĵ�Ƽ�� ��õ�մϴ�.");
					break;
				}
				else if(tmp<30) {
					System.out.println("���ȿ� ���� �ѿ��̳� ���� �����긦 ��õ�մϴ�.");
					break;
				}
				else if(tmp<40) {
					System.out.println("������ �ÿ��ϰ� �԰� ��������");
					break;
				}
                
            }		
			i++;
		}
	}
}
