package com.flj;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Demo3 {
	static String loginUrl = "http://210.43.188.27/eol/homepage/common/login.jsp";
	static String testUrl = "http://210.43.188.27/eol/main.jsp";
	static CookieStore cookieStore = null;

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpPost httppost = new HttpPost(loginUrl);
		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("IPT_LOGINUSERNAME",
					"201158080122"));
			formparams.add(new BasicNameValuePair("IPT_LOGINPASSWORD",
					"19920314"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					Consts.UTF_8);
			
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			setCookieStore(response);
	        System.out.println("状态码->"+response.getStatusLine().getStatusCode());
	        
	        
			HttpGet httpGet2 = new HttpGet(testUrl);
			HttpResponse httpResponse1 = httpclient.execute(httpGet2);
		
			String respHtml = EntityUtils.toString(httpResponse1.getEntity());
			Document doc = Jsoup.parse(respHtml);// 解析HTML字符串返回一个Document实现
			Elements titles = doc.getElementsByClass("userinfobody");
			 
			 System.out.println(titles);
	        /*String result3 = new String(titles.text().toString()
					.getBytes("ISO_8859_1"), "GBK");
			System.out.println(result3);*/
	    //  }
			
			
			System.out.println("状态码->"+ response.getStatusLine().getStatusCode());

		} finally {
			 //response.close();
		}
	}

	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
				setCookie.indexOf(";"));
		
			
		System.out.println("JSESSIONID:" + JSESSIONID);
		
	}
}
