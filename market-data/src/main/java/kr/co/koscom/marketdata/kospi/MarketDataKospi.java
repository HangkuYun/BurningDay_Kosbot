package kr.co.koscom.marketdata.kospi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class MarketDataKospi {
	private StringBuilder sb = new StringBuilder();
	
    public MarketDataKospi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v2/market/index/{marketcode}/{issuecode}/index".replace("{marketcode}", URLEncoder.encode("kospi", "UTF-8")).replace("{issuecode}", URLEncoder.encode("K1", "UTF-8")));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apikey", "l7xx8724268b03814a949aed335282d4fb73");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
    }
    
    public String MarketDataKospi_Data(){
    	return sb.toString();
    }
}
