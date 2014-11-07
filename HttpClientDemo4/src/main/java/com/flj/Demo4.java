package com.flj;

import java.io.IOException;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Demo4 {
	static String loginUrl = "http://210.43.188.27/eol/homepage/common/login.jsp";
	static String testUrl = "http://210.43.188.27/eol/welcomepage/student/index.jsp";

	public static void main(String[] args) throws IOException {
		HttpClient httpclient = new HttpClient();
		PostMethod httppost = new PostMethod(loginUrl);
		try {
			NameValuePair[] formparams = {
					new NameValuePair("IPT_LOGINUSERNAME", "201258080119"),
					new NameValuePair("IPT_LOGINPASSWORD", "178415") };
			httpclient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			httppost.setRequestBody(formparams);

			int code = httpclient.executeMethod(httppost);

			String cookie_value = httpclient.getState().getCookies()[0]
					.getValue();
			HttpState state = new HttpState();
			Cookie cookie = new Cookie();
			cookie.setDomain(".43.188.27");
			cookie.setPath("/");
			cookie.setName("JSESSIONID");
			cookie.setValue(cookie_value);
			state.addCookie(cookie);
			httpclient.setState(state);

			GetMethod get2 = new GetMethod(testUrl);
			httpclient.executeMethod(get2);
			String str2 = get2.getResponseBodyAsString();
			Document doc2 = Jsoup.parse(str2);// 解析HTML字符串返回一个Document实现
			if(doc2!=null){
			Elements information = doc2.getElementsByClass("reminder");
			if(information!=null){
			Element reminderbody = information.get(0).getElementsByTag("div").first();
			if(reminderbody!=null){
				Elements title = reminderbody.getElementsByClass("title");
				/**标题：互动提醒**/
				System.out.println(title.text());
				
			}
			}
			
			Element reminder = doc2.getElementById("reminder");
			int index = reminder.childNodeSize();
			
			if(reminder!=null){
			Element li1_up = reminder.getElementsByTag("li").get(0);
			int infor_size = 0;
			if(li1_up.nextElementSibling()!=null){
			Element li1 = reminder.getElementsByTag("li").get(0);
			///判断通知
						if(li1!=null){
				///通知***********************************************
				Elements ul1 = li1.getElementsByTag("ul");
				Elements infors = ul1.get(0).getElementsByTag("li");
				Element infor_title = li1.getElementsByTag("a").get(0);
				infor_size = infors.size();
				System.out.println(infor_title.text());
				String[] values= infors.text().split(" ");
				for(int i=0;i<values.length;i++){
				System.out.println(values[i]);
				}
			}
						else{
							System.out.println("没有通知");
						}
			}
			
			///判断作业
			int tasks_size = 0;
			Element li2 = reminder.getElementsByTag("li").get(infor_size+1);
			
			if(li2!=null){
				//System.out.println(li2);
				Elements ul2 = li2.getElementsByTag("ul");
				//System.out.println(ul2.size());
				Elements tasks = ul2.get(0).getElementsByTag("li");
				Element task_title = li2.getElementsByTag("a").get(0);
				
				tasks_size = tasks.size();
				//作业标题：N门待交作业
				System.out.println(task_title.text());
					//待交作业详细
					String[] task = tasks.text().split(" ");
					for(int i=0;i<task.length;i++){
						System.out.println(task[i]);
						}
				
			}
			///判断邮件
			Element li3_up = reminder.getElementsByTag("li").get(infor_size+tasks_size+1);
			//System.out.println(li3_up.hasText());
			if(li3_up.nextElementSibling()!=null){
				Element li3 = reminder.getElementsByTag("li").get(infor_size+tasks_size+2);
				
				if(li3!=null){
					Element email_title = li3.getElementsByTag("a").get(0);
					System.out.println(email_title.text());
				}
				else{
					System.out.println("没有邮件");
				}
				}
			else{
				System.out.println("没有邮件");
			}
				}
				
			}
			
		
			

		} finally {
			
		}
	}

}
