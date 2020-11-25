package ClimateCheck;

import Climate_function.*;
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
	  Climate ck = new Climate();
      Umbrella um = new Umbrella();
      Dresscode ds = new Dresscode();
      LocationFetche lcf = new LocationFetche();
	
    public void PrintClimate(String fir, String sec, String th) throws IOException, ParseException {
    	
    	String first = fir;  	
    	String second = sec;   
    	String third = th;
    	
        String[] location = {first, second, third};
        
        JSONArray ClimateResult = RegionCheck(location);
        
        ck.ClimatePrint(ClimateResult);
    }
    
    public void PrintDressCode(String fir, String sec, String th) throws IOException, ParseException {
    	
    	String first = fir;  	
    	String second = sec;   
    	String third = th;
    	
        String[] location = {first, second, third};
        
        JSONArray ClimateResult = RegionCheck(location);
        
        ds.CheckDress(ClimateResult);
    }
    
    public void PrintUmbrella(String fir, String sec, String th) throws IOException, ParseException {
    	
    	String first = fir;  	
    	String second = sec;   
    	String third = th;
    	
        String[] location = {first, second, third};
        
        JSONArray ClimateResult = RegionCheck(location);
        
        um.CheckUmbrella(ClimateResult);
    }
    
    public JSONArray RegionCheck(String [] locate) throws IOException, ParseException{
        Region coLocationCode = null;
        String[] location = locate;
      
      
       
        coLocationCode = lcf.fetchLocationCode(location);
        System.out.println("================================================================================");
        System.out.println("location code : " + coLocationCode.getSx() + ", " + coLocationCode.getSy());
        JSONArray ClimateResult = ck.ClimateFetch(coLocationCode.getSx(), coLocationCode.getSy());
        
        return ClimateResult;
    }
  /*      
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
            		ds.CheckDress(ClimateResult);
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
        
    
    }*/
}