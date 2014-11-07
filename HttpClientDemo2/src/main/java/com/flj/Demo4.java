package com.flj;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Demo4 {
	public static void main(String[] args) throws ClientProtocolException, IOException {
	 CloseableHttpClient httpclient = HttpClients.createDefault();
		   HttpGet httpget = new HttpGet("http://tool.oschina.net/commons?type=5");
		    System.out.println(httpget.getURI());
		   CloseableHttpResponse response = null;
		    try {
		    	response=httpclient.execute(httpget);
		    	 HttpEntity entity = response.getEntity();
		         if (entity != null) {
		             InputStream instream = entity.getContent();
		            
		             System.out.println(instream.read());
		             //System.out.println(byteTwo);
		         }
		    } finally {
		      //  response.close();
		    }
	}

}
