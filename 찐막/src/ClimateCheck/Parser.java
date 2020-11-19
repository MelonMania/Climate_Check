package ClimateCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Parser {
    private Parser() {}
    private static class IODHI {
        private static final Parser instance = new Parser();
    }
    public static Parser getInstance() {
        return IODHI.instance;
    }

    public JSONArray getRemoteJSONArray(String url) {
        StringBuffer jsonHtml = new StringBuffer();
        try {
            URL u = new URL(url);
            InputStream uis = u.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(uis, "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                jsonHtml.append(line + "\r\n");
            }
            br.close();
            uis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArr = (JSONArray) JSONValue.parse(jsonHtml.toString());
        return jsonArr;
    }


    public Map<String, String> getJsonSubMap(JSONArray jsonArrSource) {
        Map<String, String> jsonMap = new LinkedHashMap<String, String>();

        
        for (int i = 0; i < jsonArrSource.size(); i++) {
            JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i); 
            String code = (String) jsonObjItem.get("code"); 
            String value = (String) jsonObjItem.get("value");
            jsonMap.put(value, code); 
        }
        return jsonMap;
    }

    public Map<String, Region> getJsonLeafMap(JSONArray jsonArrSource) {
        Map<String, Region> jsonMap = new LinkedHashMap<String, Region>();
        for (int i = 0; i < jsonArrSource.size(); i++) {
            JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i);
            String value = (String) jsonObjItem.get("value");
            String x = (String) jsonObjItem.get("x");
            String y = (String) jsonObjItem.get("y");
            jsonMap.put(value, new Region(x, y));
        }
        return jsonMap;
    }
}