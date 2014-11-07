package com.flj;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class DemoForm {
	public static void main(String[] args) throws ClientProtocolException, IOException {
	 CloseableHttpClient httpclient = HttpClients.createDefault();
		   
		    try {
		    	//response = httpclient.execute(httpget);
		    	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		        formparams.add(new BasicNameValuePair("IPT_LOGINUSERNAME", "201158080122"));
		        formparams.add(new BasicNameValuePair("IPT_LOGINPASSWORD", "19920314"));
		        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		        HttpPost httppost = new HttpPost("http://210.43.188.27/eol/homepage/common/login.jsp");
		        httppost.setEntity(entity);
		        HttpResponse status=httpclient.execute(httppost);
		        String respHtml = EntityUtils.toString(status.getEntity());
		        System.out.println(respHtml);
		        System.out.println("状态码->"+status.getStatusLine().getStatusCode());
		      
		    } finally {
		      //  response.close();
		    }
	}

}
