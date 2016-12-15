package com.weather;

import java.util.HashMap;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.github.kevinsawicki.http.HttpRequest;

public class WeatherTask extends TimerTask{
	private static boolean isRunning = false;
	 String baseUrl="http://api.map.baidu.com/telematics/v3/weather?";
	 //抓取的城市信息
	 String[] strArray={"郑州","北京","石家庄"};
	public static HashMap <String,String>hm=new HashMap<String,String>();
	public WeatherTask() {
	    super();
	 }
	private ServletContext context = null;
	public WeatherTask(ServletContext context) {
	    this.context = context;
	    
	 }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!isRunning) {
			 isRunning=true;
			for(int i=0;i<strArray.length;i++){
				String rs=result(strArray[i]);
				if(rs!=null){
					hm.put(strArray[i], rs);
				}
			}
		}else{
			isRunning=false;
		}
	}
	public String result(String city){
		 HashMap<String,String>hmCity=new HashMap<String,String>();
		 hmCity.put("location", city);
		 hmCity.put("output", "json");
		 hmCity.put("ak", "IuRqX0hMiTPnMwGKweGYBb3OnC20YICE");
		 HttpRequest req = HttpRequest.get(baseUrl, hmCity, true).readTimeout(10000).connectTimeout(1000000)
					.uncompress(true).trustAllCerts().trustAllHosts().ignoreCloseExceptions(true);
		 System.out.println(req); 
		 if (req.code() == 200) {
			 return req.body();
			} else{
				return null;
			}
		
	}

}
