package com.webviewprototype;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.webviewprototype.facade.RestServiceFacade;
import com.webviewprototype.facade.impl.DBManager;
import com.webviewprototype.facade.impl.RestServiceFacadeImpl;
import com.webviewprototype.service.test.TestActivity;

import env.Entities.AndroidDropDownField;
import env.Entities.Charity;
import env.Entities.DataBean;
import env.Entities.PasswordEncryption;
import env.Entities.User;

public class MainActivity extends Activity {
	
	 String NAME_MAIN="";
	 public RestServiceFacade restFacade = new RestServiceFacadeImpl();
	 String PASSWORD_MAIN = "";
	 EditText edit;
	 EditText edit2;
	 	DataBean bean;
	int counter =0;
	List<Charity> charities;
	public static final String EXTRA_MESSAGE ="com.webviewprototype.MESSAGE";
	public static final String PASSWORD_MESSAGE = "com.webviewprototype.PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> spinnerValues = new ArrayList<String>();
        bean = DataBean.getDataBean();
        charities = restFacade.getCharities();
        Spinner spinner =(Spinner) findViewById(R.id.charitySpinner);
			for ( int i=0;i<charities.size();i++) {
				spinnerValues.add(charities.get(i).getCharity_name());
			}
			ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerValues);
//			spinner.setBackgroundColor(getResources().getColor(R.color.FloralWhite));
			spinner.setAdapter(spinnerAdapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					String value=(String) parent.getItemAtPosition(pos);
					for ( int i=0;i<MainActivity.this.charities.size();i++) {
						if (value.equals(MainActivity.this.charities.get(i).getCharity_name())){
							bean.setSelectedCharity(MainActivity.this.charities.get(i));
						}
					}
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
			
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
   	 edit = (EditText) findViewById(R.id.user_name);
   	 edit2 = (EditText) findViewById(R.id.password);
   	if (!bean.getCreated())	{
		bean =DataBean.getDataBean();
		bean.setCreated(true);
    }
   	return true;
    }
    
    public void submitMessage(View view) {
    	Intent intent = new Intent(this, CharityActivity.class);

    	TextView text = (TextView) findViewById(R.id.error);
     	 edit = (EditText) findViewById(R.id.user_name);
       	 edit2 = (EditText) findViewById(R.id.password);
    	String name = edit.getText().toString();
    	String pass = edit2.getText().toString();
    	User user = restFacade.validateUser(name, pass);
    	if (user!=null) {
	    	if (user.getUser_id()!=null) {
	    		String final_pass = PasswordEncryption.encryptPassword(pass, user.getSalt());
	    		if (user.getUserPassword().equals(final_pass)) {
//	    			intent.putExtra(EXTRA_MESSAGE, edit.getText().toString());
	    			startActivity(intent);
	    			bean = DataBean.getDataBean();
	    			bean.setUser(user);
	    		}else{
	    			text.setText("Wrong login details, please try again");
	    			text.setVisibility(View.VISIBLE);
	    		}
	    	}else{
	    		text.setText("Charity not approved. Select another charity.");
				text.setVisibility(View.VISIBLE);
	    	}
    	}else{
    		text.setText("User does not exist");
			text.setVisibility(View.VISIBLE);
    	}
    	
    
    }
    
   
    
	public void temp_redirect(View view) {
		Intent intent = new Intent(this, TestActivity.class);
		startActivity(intent);
	}
	
	
	
	public void deletePassword(View view) {
		this.PASSWORD_MAIN="";
	   	 edit2 = (EditText) findViewById(R.id.password);
		edit2.setText("");
	}
	
	
	public void deleteUsername(View view) {
		this.NAME_MAIN="";
	   	 edit = (EditText) findViewById(R.id.user_name);
		edit.setText("");

	}
	
	
}
