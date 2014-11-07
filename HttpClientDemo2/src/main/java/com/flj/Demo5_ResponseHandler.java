package com.flj;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class Demo5_ResponseHandler {
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpClientContext context = HttpClientContext.create();
	    HttpGet httpget = new HttpGet("http://www.google.com.hk/#newwindow=1&q=maven+test&safe=strict");
	    CloseableHttpResponse response = httpclient.execute(httpget, context);
	    try {
	        HttpHost target = context.getTargetHost();
	        List<URI> redirectLocations = context.getRedirectLocations();
	        URI location = URIUtils.resolve(httpget.getURI(), target, redirectLocations);
	        System.out.println("Final HTTP location: " + location.toASCIIString());
	        // 一般会取得一个绝对路径的uri
	    } finally {
	        response.close();
	    }
	}

}
