package com.flj;

import java.io.IOException;
import java.net.CookieStore;


import java.util.ArrayList;
import java.util.List;


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Demo3 {
	static String loginUrl = "http://210.43.188.27/eol/homepage/common/login.jsp";
	static String testUrl = "http://210.43.188.27/eol/homepage/common/index.jsp";
	static String Url3 = "http://210.43.188.27/eol/welcomepage/student/index.jsp";
	static String Url4 = "http://210.43.188.27/eol/popups/viewstudent_info.jsp?SID=55813&from=welcomepage";

	public static void main(String[] args) throws
			IOException {
		HttpClient httpclient = new HttpClient();
		 PostMethod httppost = new PostMethod(loginUrl);
		try {
			NameValuePair[] formparams = {
			new NameValuePair("IPT_LOGINUSERNAME",
					"201158080122"),
			new NameValuePair("IPT_LOGINPASSWORD",
					"19920314")};
			httpclient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			
			//httppost.setEntity();
			httppost.setRequestBody(formparams);
			
			int code = httpclient.executeMethod(httppost);
			//setCookieStore(code);
	        //System.out.println("状态码->"+code);
	        
	        String cookie_value = httpclient.getState().getCookies()[0].getValue();
	        //System.out.println(cookie_value);
	        HttpState state = new HttpState();
	        Cookie cookie = new Cookie();
	        cookie.setDomain(".43.188.27");
	        cookie.setPath("/");
	        cookie.setName("JSESSIONID");
	        cookie.setValue(cookie_value);
	        state.addCookie(cookie);
	        httpclient.setState(state);
	        
	     /*   
	      GetMethod get=new GetMethod(testUrl);
	        httpclient.executeMethod(get);
	        String str = get.getResponseBodyAsString();
	        Document doc = Jsoup.parse(str);// 解析HTML字符串返回一个Document实现
			Elements titles = doc.getElementsByClass("bodyer");
			 System.out.println(titles);*/
			 
			 GetMethod get2=new GetMethod(Url4);
		        httpclient.executeMethod(get2);
		        String str2 = get2.getResponseBodyAsString();
		        Document doc2 = Jsoup.parse(str2);// 解析HTML字符串返回一个Document实现
				//Elements titles = doc.getElementsByClass("bodyer");
				 System.out.println(doc2.text());
	       // System.out.println(str);
	        /*String result3 = new String(titles.text().toString()
					.getBytes("ISO_8859_1"), "GBK");
			System.out.println(result3);*/
	    //  }
			
			
			//System.out.println("状态码->"+ response.getStatusLine().getStatusCode());

		} finally {
			 //response.close();
		}
	}

}
