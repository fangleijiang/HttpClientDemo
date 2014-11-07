package com.flj;

import java.io.IOException;


import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.util.EntityUtils;


public class Demo1 {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		    HttpGet httpget = new HttpGet("http://www.google.com.hk/#newwindow=1&q=maven+test&safe=strict");
		    System.out.println(httpget.getURI());
		    CloseableHttpResponse response = null;
		    try {
		    	response = httpclient.execute(httpget);
		    	/*System.out.println(response.getProtocolVersion());
		        System.out.println(response.getStatusLine().getStatusCode());
		        System.out.println(response.getStatusLine().getReasonPhrase());
		        System.out.println(response.getStatusLine().toString());
		        HeaderIterator it = response.headerIterator("Set-Cookie");
		        while (it.hasNext()){ 
		        	System.out.println(it.next()); 
		        	} */
		    	
		    	/*HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));

		        while (it.hasNext()) {
		            HeaderElement elem = it.nextElement(); 
		            System.out.println(elem.getName() + " = " + elem.getValue());
		            NameValuePair[] params = elem.getParameters();
		            for (int i = 0; i < params.length; i++) {
		                System.out.println(" " + params[i]);
		            }
		        }*/
		    	
		    	
		    	  HttpEntity entity = response.getEntity();
		          if (entity != null) {
		              long len = entity.getContentLength();
		              if (len != -1 && len < 2048) {
		                  System.out.println(EntityUtils.toString(entity));
		              } else {
		                 System.out.println(len);
		              }
		          }
		     
		    } finally {
		        response.close();
		    }
	}

}
