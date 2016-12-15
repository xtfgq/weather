package com.weather;

import java.util.HashMap;

import com.github.kevinsawicki.http.HttpRequest;

public class Test {
	 public static void main(String[] args) {
		 String baseUrl="http://localhost:8080/Weather/weather";
		 HashMap<String,String>hm=new HashMap();
		 hm.put("city", "北京");
	
//		 location=北京&output=json&ak=yourkey
		 
		 HttpRequest req = HttpRequest.get(baseUrl, hm, true).readTimeout(1000000).connectTimeout(1000000)
					.uncompress(true).trustAllCerts().trustAllHosts().ignoreCloseExceptions(true);
		 System.out.println(req); 
		 if (req.code() == 200) {
				System.out.println("re:"+req.body()); 
			} else {
				System.out.println("ccc"); 
			}
		 
	}
}
