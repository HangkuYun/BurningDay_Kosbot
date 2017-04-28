package kr.co.koscom.marketdata;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.co.koscom.marketdata.history.MarketDataHistory;
import kr.co.koscom.marketdata.kospi.MarketDataKospi;
import kr.co.koscom.marketdata.master.MarketDataMaster;



@ComponentScan(basePackages={"kr.co.koscom.marketdata.controller"})
@SpringBootApplication
@EnableAutoConfiguration
public class MarketDataApplication {
   
       public static void main(String[] args) throws IOException {
           SpringApplication.run(MarketDataApplication.class, args);
           MarketDataMaster testData = new MarketDataMaster();
           System.out.println(testData.MarketDataMaster_Data());
          MarketDataKospi testData_kospi = new MarketDataKospi();
           System.out.println(testData_kospi.MarketDataKospi_Data());
           MarketDataHistory testData_history = new MarketDataHistory();
           System.out.println(testData_history.MarketDataHistory_Data());
       }
       
       @Bean
       public InternalResourceViewResolver setupViewResolver() {

           InternalResourceViewResolver resolver = new InternalResourceViewResolver();

           resolver.setPrefix("/WEB-INF/view/");

           resolver.setSuffix(".jsp");

           return resolver;

       }



}