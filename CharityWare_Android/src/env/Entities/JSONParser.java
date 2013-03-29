package env.Entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	   static InputStream is = null;
	    static JSONObject jObj = null;
	    static String json = "";
	 
	    // constructor
	    public JSONParser() {
	 
	    }
	 
	    public static String getJSONFromUrl(String url) {
	 
	        // Making HTTP request
	        try {
	            // defaultHttpClient
	        	HttpParams httpParameters = new BasicHttpParams();
	        	int timeoutConnection = 100000;
	        	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	        	int timeoutSocket = 90000;
	        	HttpConnectionParams.setSoTimeout(httpParameters, 0);
	            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
	            HttpGet httpGet = new HttpGet(url);
	 
	            HttpResponse httpResponse = httpClient.execute(httpGet);
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
	 
	        // try parse the string to a JSON object
	       /* try {
	            jObj = new JSONObject(json);
	        	
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }*/
	 
	        // return JSON String
	        return json;
	 
	    }
	    
	    
	    public static String makeRequest(String path, String json) throws Exception 
	    {
	        //instantiates httpclient to make request
	        DefaultHttpClient httpclient = new DefaultHttpClient();

	        //url with the post data
	        HttpPost httpost = new HttpPost(path);

	        //passes the results to a string builder/entity
	        StringEntity se = new StringEntity(json);
	        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	        pairs.add(new BasicNameValuePair("json",json));
	        
	        //sets the post request as the resulting string
	      //  httpost.setEntity(se);
	        httpost.setEntity(new UrlEncodedFormEntity(pairs));
	        //sets a request header so the page receving the request
	        //will know what to do with it
//	        httpost.setHeader("Accept", "application/json");
	        httpost.setHeader("Content-type", "application/json");
	        //Handles what is returned from the page 
	        ResponseHandler responseHandler = new BasicResponseHandler();
	         return httpclient.execute(httpost, responseHandler);
	    }
	    
	    
}
