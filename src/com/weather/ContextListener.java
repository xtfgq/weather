package com.weather;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class ContextListener extends HttpServlet implements ServletContextListener{
	private static final long serialVersionUID = 1L;
	private java.util.Timer timer = null;
	private WeatherTask myjob;
	private JSONObject js;

	public ContextListener() {
    }

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		  timer.cancel();
	      System.out.println("定时器销毁");
	      event.getServletContext().log("定时器销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer = new java.util.Timer(true);
	      event.getServletContext().log("定时器已启动");
	      myjob= new WeatherTask(event.getServletContext());
	      timer.schedule(myjob,0,3000000); 
	      event.getServletContext().log("已经添加任务调度表");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	String city = req.getParameter("city");
//
	city=new String(city.getBytes("iso-8859-1"), "utf-8");
		String outStr=myjob.hm.get(city);
	
		System.out.println("city------"+outStr);
		JSONObject js = new JSONObject();
		js.put("outstr", outStr);
		PrintWriter out = resp.getWriter();
		out.print(outStr);
	}

}
