package com.webviewprototype;

import java.sql.Date; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.webviewprototype.facade.RestServiceFacade;
import com.webviewprototype.facade.impl.DBManager;
import com.webviewprototype.facade.impl.RestServiceFacadeImpl;
import com.webviewprototype.service.test.TestActivity;

import env.Entities.AndroidCalendarField;
import env.Entities.AndroidCheckBoxField;
import env.Entities.AndroidDropDownField;
import env.Entities.AndroidField;
import env.Entities.AndroidTextField;
import env.Entities.DataBean;
import env.Entities.FieldSelection;
import env.Entities.FilledForm;
import env.Entities.Form;
import env.Entities.FormFields;
import env.Entities.FormListAdapter;

import android.os.Bundle;
import android.os.DropBoxManager;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Layout;

@SuppressLint("NewApi")
public class FormActivity extends ListActivity {

	List<FilledForm> filledForm = new LinkedList<FilledForm>();
	List<FormFields> formFields;
	DBManager manager;
	Integer userid;
	String username;
	LinearLayout flayout;
	DataBean bean;
	RestServiceFacade restFacade = new RestServiceFacadeImpl();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		bean = DataBean.getDataBean();
		manager = bean.getManager();		
		Intent intent = getIntent();
		username = intent.getExtras().getString("Username");
		flayout = (LinearLayout) findViewById(R.layout.activity_form);
		ListView listView = (ListView) findViewById(android.R.id.list);
		formFields = bean.getFormFields();
		for (int i=0;i<formFields.size();i++) {
			FilledForm fform = new FilledForm();
			fform.setRecord_id(i);
			fform.setFormFields(formFields.get(i));
			fform.setValue("");
			fform.setUser(bean.getUser());
			fform.setIsActive(true);
			filledForm.add(fform);
		}
		bean.setFilledForm(filledForm);
		List<AndroidField> fields = new ArrayList<AndroidField>();
		fields = configAndroidFields(formFields);
		FormListAdapter formAdapter = new FormListAdapter(this, fields, R.layout.listview_layout_form);
		listView.setAdapter(formAdapter);
		TextView header = (TextView) findViewById(R.id.fname);
		header.setText(bean.getSelectedForm());
		
		
		//  creation of UI components here
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onStop() {
	    super.onStop(); 
	    ContentValues values = new ContentValues();
	    this.filledForm=null;
	}
	
	@Override 
	public void onRestart() {
		super.onRestart();
	}
	
	
	
	private void saveToSqlLite(View view) {
//			manager.createFilledForm(filledForm.getFilled_form_id(), form.getFormId(), Integer.parseInt(nrs.get(i).getText().toString()), editTexts.get(i).getText().toString(), userid, filledForm.getRecord_id(), true);
		
	
	}
	
	private void saveToSqlLite() {
		
	}
	
	
	
	private void check(View view) {
		CheckBox cbox = (CheckBox) view;
	}
	
	
	public void submitFilledForm(View view) {
		
		if (!restFacade.setFilledForm(bean.getFilledForm())) {
	
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.dialog_form_submission)
		       .setTitle(R.string.dialog_form_title);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	        	   dialog.dismiss();
	           }
	       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	        	   dialog.dismiss();
	           }
	       });
		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		dialog.show();
		}else{
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// 2. Chain together various setter methods to set the dialog characteristics
			builder.setMessage(R.string.succ)
			       .setTitle(R.string.dialog_form_title);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		        	   dialog.dismiss();
		           }
		       });
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		        	   dialog.dismiss();
		           }
		       });
			// 3. Get the AlertDialog from create()
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
	
	
	private List<AndroidField> configAndroidFields(List<FormFields> fields) {
		
		List<AndroidField> fin_fields = new LinkedList<AndroidField>();
		for ( int i=0;i<fields.size();i++) {
			FormFields field = fields.get(i);
			Integer type = field.getField_type().getField_type_id();
			switch(type) {
								
				case 3 :
								AndroidDropDownField afield = new AndroidDropDownField();
								afield.setLabel(field.getField_label());
								afield.setField_id(field.getF_id());
								afield.setField_Type_id(type);
								List<FieldSelection> dropvalues = new LinkedList<FieldSelection>();
								Set<FieldSelection> fs = field.getField_selections();
								Iterator<FieldSelection> it = fs.iterator();
								while (it.hasNext()) {
									dropvalues.add(it.next());
								}
								afield.setDropDownValues(dropvalues);
								fin_fields.add(afield);
								break;	
			
				case 5:
								AndroidCalendarField calfield = new AndroidCalendarField();
								calfield.setLabel(field.getField_label());
								calfield.setField_Type_id(type);
								calfield.setField_id(field.getF_id());
								calfield.setDate(new Date(Calendar.DATE));
								fin_fields.add(calfield);
								break;
				case 6:
								AndroidCheckBoxField cfield = new AndroidCheckBoxField();
								cfield.setLabel(field.getField_label());
								cfield.setField_Type_id(type);
								cfield.setField_id(field.getF_id());
								cfield.setChecked(false);
								fin_fields.add(cfield);
								break;
				default:
								AndroidTextField tfield = new AndroidTextField();
								tfield.setField_Type_id(type);
								tfield.setField_id(field.getF_id());
								tfield.setLabel(field.getField_label());
								tfield.setText("");
								tfield.setInputType(type==2 ? 0 : 1);
								fin_fields.add(tfield);
								break;
			}	
				
			
		}
		
		return fin_fields;
		
		
	}
	
	

	

	
	
	@Override
    public void finish()
    {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.warning)
	       .setTitle(R.string.confirmation);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   Intent intent = new Intent(FormActivity.this, CharityActivity.class);
	    			intent.putExtra(MainActivity.EXTRA_MESSAGE, bean.getUser().getUserName());
	    			bean.flush();
	       			FormActivity.this.startActivity(intent);
	           }
	       });
	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	           }
	       });
	AlertDialog dialog = builder.create();
	dialog.show();
    }
	
	
	

	@Override
    public void onBackPressed() {
//		super.onBackPressed();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.warning)
	       .setTitle(R.string.confirmation);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   Intent intent = new Intent(FormActivity.this, CharityActivity.class);
	    			intent.putExtra(MainActivity.EXTRA_MESSAGE, bean.getUser().getUserName());
	    			bean.flush();
	       			FormActivity.this.startActivity(intent);
	           }
	       });
	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	           }
	       });
	AlertDialog dialog = builder.create();
	dialog.show();
//	 super.onBackPressed();   

    }
}
