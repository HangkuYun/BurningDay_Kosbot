package kr.co.koscom.marketdata.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.koscom.marketdata.history.MarketDataHistory;
import kr.co.koscom.marketdata.kospi.MarketDataKospi;
import kr.co.koscom.marketdata.master.MarketDataMaster;

@RestController
public class MarketDataController {

	String tempStr = new String();
	String tempStr_kospi = new String();
	String tempStr_history = new String();

	@RequestMapping("/MarketData")
//	public @ResponseBody String MarketData() throws IOException, ParseException, JSONException {
	public @ResponseBody ModelAndView MarketData() throws IOException, ParseException, JSONException {
		
//삼성전자 마스터	
		MarketDataMaster testData = new MarketDataMaster();
		tempStr = testData.MarketDataMaster_Data();
		JSONObject obj = new JSONObject(tempStr);
		obj = obj.getJSONObject("result");
		String result= "종목코드: "+obj.getString("isuSrtCd")+", ";
		result+="전일가: "+obj.getInt("prevddClsprc")+", ";
		result+="전일거래량: "+obj.getInt("prevddAccTrdvol")+", ";
		result+="전일거래대금: "+obj.getInt("prevddAccTrdval")+", ";
		result+="거래일정: "+obj.getInt("trdDd")+", ";
		result+="eps: "+obj.getInt("eps")+", ";
		result+="bps: "+obj.getInt("bps")+", ";
		result+="per: "+obj.getInt("per")+", ";
		result+="pbr: "+obj.getInt("pbr")+", ";
		result+="기준연도: "+obj.getInt("divYd")+", ";
		result+="시가: "+obj.getInt("basPrc")+", ";
		result+="종목명: "+obj.getString("isuKorAbbrv")+", ";
		result+="상장연월일: "+obj.getInt("listDd")+", ";
		result+="액면가: "+obj.getInt("parval")+", ";
		result+="dps: "+obj.getInt("dps")+", ";
		result+="개인미수가능여부: "+obj.getString("admisuYn")+", ";
		result+="거래정지여부: "+obj.getString("haltYn")+", ";		
		result+="상한가: "+obj.getInt("uplmtprc")+", ";
		result+="하한가: "+obj.getInt("lwlmtprc")+" ";
//업종지수_코스피 인덱스
		MarketDataKospi testData_kospi = new MarketDataKospi();
		tempStr_kospi = testData_kospi.MarketDataKospi_Data();
		JSONObject obj_kospi = new JSONObject(tempStr_kospi);
		obj_kospi = obj_kospi.getJSONObject("result");
		String result_kospi= "업종코드: "+obj_kospi.getString("isuSrtCd")+", ";
		result_kospi+="trdPrc: "+obj_kospi.getInt("trdPrc")+", ";
		result_kospi+="cmpprevddTpCd: "+obj_kospi.getString("cmpprevddTpCd")+", ";
		result_kospi+="accTrdvol: "+obj_kospi.getInt("accTrdvol")+", ";
		result_kospi+="trdTm: "+obj_kospi.getInt("trdTm")+", ";
		result_kospi+="accTrdval: "+obj_kospi.getInt("accTrdval")+", ";
		result_kospi+="cmpprevddPrc: "+obj_kospi.getInt("cmpprevddPrc")+", ";
//히스토리
		MarketDataHistory testData_history = new MarketDataHistory();
		tempStr_history = testData_history.MarketDataHistory_Data();
		JSONObject obj_history = new JSONObject(tempStr_history);
		obj_history = obj_history.getJSONObject("result");
		String result_history= "업종코드: "+obj_kospi.getString("isuSrtCd")+", ";
		
//		return result+result_kospi+result_history;

		/////***변경***//////
		result = result+result_kospi+result_history;
		List<String> listData = new ArrayList<String>();
		listData.add(result);
		ModelAndView model_View = new ModelAndView();
		model_View.addObject("listData", listData);
		model_View.setViewName("MarketData/MarketData_UI");
		
		return model_View;
	    /////***변경***//////

	}
}