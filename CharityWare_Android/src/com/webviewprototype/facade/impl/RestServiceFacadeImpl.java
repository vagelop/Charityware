package com.webviewprototype.facade.impl;

import java.io.BufferedReader; 
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;

import com.webviewprototype.facade.RestServiceFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import env.Entities.Charity;
import env.Entities.DataBean;
import env.Entities.FilledForm;
import env.Entities.Form;
import env.Entities.User;

public class RestServiceFacadeImpl implements RestServiceFacade,Runnable {
	
	JSONArray data;
	static String URLPathCore="";
	static String jsonResult="";
	DataBean bean;
	
	public RestServiceFacadeImpl() {
	}
	


	public  List<Form> getFormEntities(String username){
		bean= DataBean.getDataBean();
		List<Form> forms = new LinkedList<Form>();
//		FormURLOpenTask task = new FormURLOpenTask("http://130.43.173.7:8080/CharityWare_Lite/RESTCharity/userService/charityConfig/hibernate.cfg.xml/users/formEntities/"+username);
		FormURLOpenTask task = new FormURLOpenTask("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity().getConnection_string()+"/users/formEntities/"+username);
//		FormURLOpenTask task = new FormURLOpenTask("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/hibernate.cfg.xml/users/formEntities/"+username);

		//		FormURLOpenTask task = new FormURLOpenTask("http://128.16.80.47:8080/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity().getConnection_string()+"/users/formEntities/"+username);

		try {
			jsonResult=task.execute(new URL("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity()+"/users/formEntities/"+username)).get();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		String debug = jsonResult;
		JSONParser parser = new JSONParser();
		org.json.simple.JSONArray array = new org.json.simple.JSONArray();
		try {
			 array = (org.json.simple.JSONArray) parser.parse(jsonResult);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();		
		try{
			Type listType = new TypeToken<LinkedList<Form>>() {
			}.getType();
	        forms = gson.fromJson(jsonResult, listType);
		}catch(Exception e) {
			Form form = gson.fromJson(jsonResult, Form.class);
			forms.add(form);
		}
		
		return forms;
	}

	@Override
	public synchronized User validateUser(String username, String password) {
		bean= DataBean.getDataBean();
		try {
//				URLOpenTask task = new URLOpenTask("http://130.43.173.7:8080/CharityWare_Lite/RESTCharity/userService/charityConfig/hibernate.cfg.xml/userName/"+username);
			URLOpenTask task = new URLOpenTask("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity().getConnection_string()+"/userName/"+username);
//			URLOpenTask task = new URLOpenTask("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/hibernate.cfg.xml/userName/"+username);

			//			URLOpenTask task = new URLOpenTask("http://128.16.80.47:8080/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity().getConnection_string()+"/userName/"+username);
			jsonResult=task.execute(new URL("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/userService/charityConfig/"+bean.getSelectedCharity().getConnection_string()+"/userName/"+username)).get();
		} catch (Exception e1) {
			e1.printStackTrace();
			return new User();
		}
		System.out.println(jsonResult);
		Gson gson = new Gson();
		User user;
	    gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();
	    try{
			 user = gson.fromJson(jsonResult, User.class);
	    }catch(Exception e){
	    	return null;
	    }
		return user;

	}
	
	

	@Override
	public Boolean setFilledForm(List<FilledForm> forms) {
		bean= DataBean.getDataBean();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();
		String json = gson.toJson(forms);
		Boolean done=false;
		try {
			String[] debug = new String[2];
//			debug[0]="http://10.111.25.213:8080/CharityWare_Lite/RESTCharity/filledFormService/hibernate.cfg.xml/filledforms/insertFilledForms";
			debug[0]="http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/filledFormService/"+bean.getSelectedCharity().getConnection_string()+"/filledforms/insertFilledForms";
//			debug[0]="http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/filledFormService/hibernate.cfg.xml/filledforms/insertFilledForms";

			//			debug[0]="http://1285.16.80.47:8080/CharityWare_Lite/RESTCharity/filledFormService/"+bean.getSelectedCharity().getConnection_string()+"/filledforms/insertFilledForms";
			debug[1]=json;
			PostFormTask task = new PostFormTask(debug);
//			done=task.execute("http://10.111.25.213:8080/CharityWare_Lite/RESTCharity/filledFormService/hibernate.cfg.xml/filledforms/insertFilledForms",json).get();
//			done=task.execute("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/filledFormService/hibernate.cfg.xml/filledforms/insertFilledForms",json).get();

			done=task.execute("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTCharity/filledFormService/"+bean.getSelectedCharity().getConnection_string()+"/filledforms/insertFilledForms",json).get();
//			task.execute("http://128.16.80.47:8080/CharityWare_Lite/RESTCharity/filledFormService/"+bean.getSelectedCharity().getConnection_string()+"/filledforms/insertFilledForms",json).get();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return done;
	}
	
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        InputStream istream = url.openStream();
	        InputStreamReader is = new InputStreamReader(istream);
	        reader = new BufferedReader(is);
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[2048];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	
	
	private class FormURLOpenTask extends AsyncTask<URL, Void, String> {
		
		private String urlString;
		
		@Override
		protected String doInBackground(URL... params) {
			String result="";	
			try {
				System.out.println("Working");
					if (urlString.length()!=0) {
						result= env.Entities.JSONParser.getJSONFromUrl(urlString);
					
					}else{
						result= env.Entities.JSONParser.getJSONFromUrl(params[0].toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
		}
		
	
		public FormURLOpenTask(String url) {
			urlString=url;
		}
		
	}
	
	
	
	
	
	private class URLOpenTask extends AsyncTask<URL, Void, String> implements Runnable {

		private String urlString;
		
		@Override
		protected String doInBackground(URL... params) {
			String result="";	
			try {
				System.out.println("Working");
					if (urlString.length()!=0) {
						result= env.Entities.JSONParser.getJSONFromUrl(urlString);
					
					}else{
						result= env.Entities.JSONParser.getJSONFromUrl(params[0].toString());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
		}
		
		
	
		public URLOpenTask(String url) {
			urlString=url;
		}

		@Override
		public void run() {
			
		}
		
	}


	@Override
	public void run() {
		
	}
	
	
	
	private class PostFormTask extends AsyncTask<String, Boolean, Boolean>{
		
		private String[] data;
		@Override
		protected Boolean doInBackground(String... params) {
			String fin;
			try {
				if (data.length!=2) {
					 fin=env.Entities.JSONParser.makeRequest(params[0], params[1]);
				}else{
					  fin=env.Entities.JSONParser.makeRequest(data[0], data[1]);
				}
				System.out.println("Response :"+fin);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			return true;
		}
		
		public PostFormTask(String[] data) {
			this.data=data;
		}
		
	}



	@Override
	public List<Charity> getCharities() {
		List<Charity>  charities = new LinkedList<Charity>();
		URLOpenTask task = new URLOpenTask("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTSystem/charityService/charities");
		try {
			jsonResult = task.execute(new URL("http://charityware.cs.ucl.ac.uk/CharityWare_Lite/RESTSystem/charityService/charities")).get();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();		
		try{
			Type listType = new TypeToken<LinkedList<Charity>>() {
			}.getType();
	        charities = gson.fromJson(jsonResult, listType);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return charities;
		
	}

}
