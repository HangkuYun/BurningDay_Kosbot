package kr.co.koscom.marketdata.bookkeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MarketDataBookkeeper  {
 private StringBuilder sb = new StringBuilder();
 
    public MarketDataBookkeeper() throws IOException {
      StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/bookkeeper/fs/ifrs/{issuecode}".replace("{issuecode}", URLEncoder.encode("005930", "UTF-8")));
         urlBuilder.append("?");
         urlBuilder.append(URLEncoder.encode("max_depth","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&");
         urlBuilder.append(URLEncoder.encode("from","UTF-8") + "=" + URLEncoder.encode("2016-01-01", "UTF-8"));
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("apikey", "l7xx47df10663b6b44f1b1c90e8394aee745");
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
    
    public String  MarketDataBookkeeper_Data(){
     return sb.toString();
    }
} 
