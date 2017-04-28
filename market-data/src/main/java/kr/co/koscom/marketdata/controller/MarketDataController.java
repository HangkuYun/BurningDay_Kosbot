package kr.co.koscom.marketdata.controller;

 

import java.io.IOException;

import java.text.ParseException;

 

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

 

import kr.co.koscom.marketdata.master.MarketDataMaster;

import kr.co.koscom.marketdata.kospi.MarketDataKospi;

import kr.co.koscom.marketdata.bookkeeper.MarketDataBookkeeper;

import kr.co.koscom.marketdata.history.MarketDataHistory;

 

@RestController

public class MarketDataController {

 

	String tempStr = new String();

	String tempStr_kospi = new String();

	String tempStr_history = new String();

	String tempStr_bookkeeper=new String();

 

	@RequestMapping("/MarketData")

	public @ResponseBody String MarketData() throws IOException, ParseException, JSONException {

	

		

//삼성전자 마스터	

		MarketDataMaster testData = new MarketDataMaster();

		tempStr = testData.MarketDataMaster_Data();

		JSONObject obj = new JSONObject(tempStr);

		obj = obj.getJSONObject("result");

		String result="<pre><h1>Samsung Electronics</h1><h1>Market Data</h1>"+"\n";

		result+= "●종목코드: "+obj.getString("isuSrtCd")+"\n";

		result+="●전일가: "+obj.getInt("prevddClsprc")+"\n";

		result+="●전일거래량: "+obj.getInt("prevddAccTrdvol")+"\n";

		result+="●전일거래대금: "+obj.getLong("prevddAccTrdval")+"\n";

		result+="●거래일정: "+obj.getInt("trdDd")+"\n";

		result+="●eps: "+obj.getInt("eps")+"\n";

		result+="●bps: "+obj.getInt("bps")+"\n";

		result+="●per: "+obj.getDouble("per")+"\n";

		result+="●pbr: "+obj.getInt("pbr")+"\n";

		result+="●기준연도: "+obj.getInt("divYd")+"\n";

		result+="●시가: "+obj.getInt("basPrc")+"\n";

		result+="●종목명: "+obj.getString("isuKorAbbrv")+"\n";

		result+="●상장연월일: "+obj.getInt("listDd")+"\n";

		result+="●액면가: "+obj.getInt("parval")+"\n";

		result+="●dps: "+obj.getInt("dps")+"\n";

		result+="●개인미수가능여부: "+obj.getString("admisuYn")+"\n";

		result+="●거래정지여부: "+obj.getString("haltYn")+"\n";		

		result+="●상한가: "+obj.getInt("uplmtprc")+"\n";

		result+="●하한가: "+obj.getInt("lwlmtprc")+"\n\n";

//업종지수_코스피 인덱스

		MarketDataKospi testData_kospi = new MarketDataKospi();

		tempStr_kospi = testData_kospi.MarketDataKospi_Data();

		JSONObject obj_kospi = new JSONObject(tempStr_kospi);

		obj_kospi = obj_kospi.getJSONObject("result");

		String result_kospi="<h1>Kospi Index</h1>"+"\n";

		result_kospi+= "●업종코드: "+obj_kospi.getString("isuSrtCd")+"\n";

		result_kospi+="●지수: "+obj_kospi.getInt("trdPrc")+"\n";

		//result_kospi+="●: "+obj_kospi.getString("cmpprevddTpCd")+"\n";

		result_kospi+="●누적거래량: "+obj_kospi.getInt("accTrdvol")+"\n";

		//result_kospi+="●누적거래액: "+obj_kospi.getInt("trdTm")+"\n";

		result_kospi+="●누적거래액: "+obj_kospi.getInt("accTrdval")+"\n";

		//result_kospi+="●cmpprevddPrc: "+obj_kospi.getInt("cmpprevddPrc")+"\n";

//히스토리

		MarketDataHistory testData_history = new MarketDataHistory();

		tempStr_history = testData_history.MarketDataHistory_Data();

		JSONObject obj_history = new JSONObject(tempStr_history);

		obj_history = obj_history.getJSONObject("result");		

		JSONArray obj_history1 = obj_history.getJSONArray("hisLists");

		String result_history="\n<h1>History</h1>";

	

		for (int i = 0; i < 3; i++) {	

	

			

		obj_history=obj_history1.getJSONObject(i);

		result_history+= "●거래날짜: "+obj_history.getInt("trdDd")+" ";

		result_history+="거래가격: "+obj_history.getDouble("trdPrc")+", ";

		//result_history+="cmpprevddTpC: "+obj_history.getString("cmpprevddTpCd")+", ";

		result_history+="시가: "+obj_history.getDouble("opnprc")+", ";

		result_history+="상한가: "+obj_history.getDouble("hgprc")+", ";

		result_history+="하한가: "+obj_history.getDouble("lwprc")+", ";

		result_history+="거래량: "+obj_history.getDouble("accTrdvol")+", ";

		result_history+="거래대금: "+obj_history.getDouble("accTrdval")+", ";		

		result_history+="변동시가: "+obj_history.getDouble("cmpprevddPrc")+", \n";

		}

//bookkeeper

		String result_bookkeeper="\n<h1>Bookkeeper</h1>";

		MarketDataBookkeeper testData_bookkeeper = new MarketDataBookkeeper();

		

		tempStr_bookkeeper = testData_bookkeeper.MarketDataBookkeeper_Data();

		JSONObject obj_bookkeeper = new JSONObject(tempStr_bookkeeper);

		JSONArray obj_bookkeeper1 = new JSONArray();

		obj_bookkeeper1 = obj_bookkeeper.getJSONArray("data");

		obj_bookkeeper=obj_bookkeeper1.getJSONObject(0);

		result_bookkeeper+= "●Exchange: "+obj_bookkeeper.getString("exchange")+"\n";

		result_bookkeeper+= "●Market: "+obj_bookkeeper.getString("market")+"\n";

		obj_bookkeeper1=obj_bookkeeper.getJSONArray("income");

		obj_bookkeeper=obj_bookkeeper1.getJSONObject(0);

		result_bookkeeper+= "\n\t◎Name: "+obj_bookkeeper.getString("name")+"\n";

		result_bookkeeper+= "\t-Depth: "+obj_bookkeeper.getInt("depth")+"\n";

		result_bookkeeper+= "\t-Code: "+obj_bookkeeper.getString("code")+"\n";

		result_bookkeeper+= "\t-value: "+obj_bookkeeper.getInt("value")+"\n\n";

 

		obj_bookkeeper=obj_bookkeeper1.getJSONObject(17);

		result_bookkeeper+= "\n\t◎Name: "+obj_bookkeeper.getString("name")+"\n";

		result_bookkeeper+= "\t-Depth: "+obj_bookkeeper.getInt("depth")+"\n";

		result_bookkeeper+= "\t-Code: "+obj_bookkeeper.getString("code")+"\n";

		result_bookkeeper+= "\t-value: "+obj_bookkeeper.getInt("value")+"\n";

		

		

		

		//for (int i = 0; i < obj_bookkeeper1.size(); i++) {

			

			

		

		

		result_bookkeeper += "</pre>";

		

		

		

 

		

		

		

		return result+result_kospi+result_history+result_bookkeeper;

		

		

 

 

 

	}

}
