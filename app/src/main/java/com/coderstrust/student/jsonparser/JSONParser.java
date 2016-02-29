package com.coderstrust.student.jsonparser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	public String getJSONFromUrl(String url, List<NameValuePair> nameValuePair) {

		// Making HTTP request
		try {
			// defaultHttpClient
			HttpClient httpClient = new DefaultHttpClient();
			// Creating HTTP Post
			HttpPost httpPost = new HttpPost(url);
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
			} catch (UnsupportedEncodingException e) {
				// writing error to Log
				e.printStackTrace();
			}

			// Making HTTP Request
			try {
				HttpResponse response = httpClient.execute(httpPost);

				// writing response to log
				Log.d("Http Response:", response.toString());
			} catch (ClientProtocolException e) {
				// writing exception to log
				e.printStackTrace();
			} catch (IOException e) {
				// writing exception to log
				e.printStackTrace();

			}

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return json;

	}
	
	//public JSONObject getJSONFromUrl1(String url) 

	public JSONObject getJSONFromUrl1(String url) {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);



			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();	
			
			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

//		// try parse the string to a JSON object
//		try {
//			jObj = new JSONObject(json);
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//		}

		// return JSON String
		return jObj;

	}
	
	
	
	public String requestGET(String url,String getData){
		//url = URLEncoder.encode(url);
		String myString = null;
		InputStream myInputStream =null;
		StringBuilder sb = new StringBuilder();
		sb.append(getData);
		URL url2;
		System.out.println("WWWWWWWWW   "+url +"  "+getData);
		try {
			url2 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url2.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(20000);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			// this is were we're adding post data to the request
			wr.write(sb.toString());
			wr.flush();
			myInputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(myInputStream);
			ByteArrayBuffer baf = new ByteArrayBuffer(100);
			int current = 0;
			while((current = bis.read()) != -1){
				baf.append((byte)current);
			}
			myString = new String(baf.toByteArray());
			wr.close();
			conn.disconnect();
		} catch (Exception e) {
			//handle the exception !
			System.out.println("tap  "+e.toString());
			myString = "Error: "+e.getMessage();

		}
		return myString;
	}

	public String requestGETHEAD(String url,String getData){
		//url = URLEncoder.encode(url);
		String myString = null;
		InputStream myInputStream =null;
		StringBuilder sb = new StringBuilder();
		sb.append(getData);
		URL url2;
		System.out.println("WWWWWWWWW   "+url +"  "+getData);
		try {
			url2 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url2.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			//conn.setRequestProperty ("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50X2lkIjo5MDY0LCJleHAiOjE0NTQ4OTI1MzJ9.eIlIm7__h_MqU6QVk3_JIA2SgtyIwz_QxvuSOm62RXc");
			conn.setConnectTimeout(20000);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			// this is were we're adding post data to the request
			wr.write(sb.toString());
			wr.flush();
			myInputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(myInputStream);
			ByteArrayBuffer baf = new ByteArrayBuffer(100);
			int current = 0;
			while((current = bis.read()) != -1){
				baf.append((byte)current);
			}
			myString = new String(baf.toByteArray());
			wr.close();
			conn.disconnect();
		} catch (Exception e) {
			//handle the exception !
			System.out.println("FUCK  "+e.toString());
			myString = "Error: "+e.getMessage();

		}
		return myString;
	}


	public String requestPOST(String url,String postData){
		//url = URLEncoder.encode(url);
		String myString = null;
		InputStream myInputStream =null;
		StringBuilder sb = new StringBuilder();
		sb.append(postData);
		URL url2;
		System.out.println("WWWWWWWWW   "+url +"  "+postData);
		try {
			url2 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url2.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(20000);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			// this is were we're adding post data to the request
			wr.write(sb.toString());
			wr.flush();
			myInputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(myInputStream);
			ByteArrayBuffer baf = new ByteArrayBuffer(100);
			int current = 0;
			while((current = bis.read()) != -1){
				baf.append((byte)current);
			}
			myString = new String(baf.toByteArray());
			wr.close();
			conn.disconnect();
		} catch (Exception e) {
			//handle the exception !
			System.out.println("tap  "+e.toString());
			myString = "Error: "+e.getMessage();

		}
		return myString;
	}


	
	public String getContents(String url , String token) {
        String contents ="";

		try {
			HttpClient client = new DefaultHttpClient();
			//String getURL = "http://helloworld.com/getmethod.aspx?id=1&method=getData";
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Authorization", token);
			HttpResponse response = client.execute(httpGet);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				//parse response.
				//Log.e("Response ee", EntityUtils.toString(resEntity));
				contents=EntityUtils.toString(resEntity);
				System.out.println("FUCK  "+contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
			contents = "Internet "+ e.toString();
			System.out.println("FUCK EE  " + e.toString());
		}
		 
		  return contents;
	}
 
	private String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
	 
	      BufferedReader reader = new BufferedReader(new    
	                              InputStreamReader(is, "UTF-8"));
	        StringBuilder sb = new StringBuilder();
	         String line = null;
	         try {
	                while ((line = reader.readLine()) != null) {
	                        sb.append(line + "\n");
	                }
	           } catch (IOException e) {
	                e.printStackTrace();
	           } finally {
	                try {
	                        is.close();
	                } catch (IOException e) {
	                        e.printStackTrace();
	                }
	            }
	        return sb.toString();
	 }

	
	public Document getDomElement(String xml){
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        doc = db.parse(is); 

			} catch (ParserConfigurationException e) {
				Log.e("Error: ", e.getMessage());
				e.printStackTrace() ;
				return null;
			} catch (SAXException e) {
				Log.e("Error: ", e.getMessage());
				e.printStackTrace() ;
	            return null;
			} catch (IOException e) {
				Log.e("Error: ", e.getMessage());
				e.printStackTrace() ;
				return null;
			}

	        return doc;
	}
	
	 public final String getElementValue( Node elem ) {
	     Node child;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                     return child.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
	 
	 /**
	  * Getting node value
	  * @param Element node
	  * @param key string
	  * */
	 public String getValue(Element item, String str) {		
			NodeList n = item.getElementsByTagName(str);		
			return this.getElementValue(n.item(0));
		}



	public JSONObject getJSONFromUrl11(String url) {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}

	
}
