package ClimateCheck;

import java.io.IOException;
import  java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
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

public class ClimateMain {
    public static void main(String[] args) throws IOException, ParseException{
    	Scanner sc = new Scanner(System.in);
    	System.out.println("<현재 위치를 입력해주세요>\n ###주의사항### \n'경기도 구리시 인창동' <= 이와 같이 도, 시, 동을 따로 입력하셔야 합니다");
    	System.out.print("도 : ");
    	String first = sc.next();
    	System.out.print("시 : ");
    	String second = sc.next();
    	System.out.print("동 : ");
    	String third = sc.next();
    	
        String[] location = {first, second, third};
        Region coLocationCode = null;
        Climate ck = new Climate();
        Umbrella um = new Umbrella();
        LocationFetche lcf = new LocationFetche();
      
       
        coLocationCode = lcf.fetchLocationCode(location);
        System.out.println("location code : " + coLocationCode.getSx() + ", " + coLocationCode.getSy());
        JSONArray ClimateResult = ck.ClimateFetch(coLocationCode.getSx(), coLocationCode.getSy());
        
        boolean act = true;
        while(act) {
        	System.out.println("================================================================================");
        	System.out.println("1 : 시간대별 기상정보 \n2 : 오늘의 옷차림 알아보기  \n3 : 오늘 우산 필요한지 확인하기 \n4 : 종료하기 ");
        	System.out.println("================================================================================");
        	System.out.println("input : ");
            int choice = sc.nextInt();
            switch(choice) {
            	case 1:
            		ck.ClimatePrint(ClimateResult);
            		break;
            	case 2:
            		System.out.println("옷");
            		break;
            	case 3:
            		um.CheckUmbrella(ClimateResult);
            		break;
            	case 4:
            		System.out.println("종료합니다");
            		act = false;
            		break;
            }
        }
        
    
    }
}