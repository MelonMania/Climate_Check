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
    	System.out.println("<���� ��ġ�� �Է����ּ���>\n ###���ǻ���### \n'��⵵ ������ ��â��' <= �̿� ���� ��, ��, ���� ���� �Է��ϼž� �մϴ�");
    	System.out.print("�� : ");
    	String first = sc.next();
    	System.out.print("�� : ");
    	String second = sc.next();
    	System.out.print("�� : ");
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
        	System.out.println("1 : �ð��뺰 ������� \n2 : ������ ������ �˾ƺ���  \n3 : ���� ��� �ʿ����� Ȯ���ϱ� \n4 : �����ϱ� ");
        	System.out.println("================================================================================");
        	System.out.println("input : ");
            int choice = sc.nextInt();
            switch(choice) {
            	case 1:
            		ck.ClimatePrint(ClimateResult);
            		break;
            	case 2:
            		System.out.println("��");
            		break;
            	case 3:
            		um.CheckUmbrella(ClimateResult);
            		break;
            	case 4:
            		System.out.println("�����մϴ�");
            		act = false;
            		break;
            }
        }
        
    
    }
}