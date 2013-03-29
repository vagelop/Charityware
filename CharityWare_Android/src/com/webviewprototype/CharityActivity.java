package com.webviewprototype;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint; 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.webviewprototype.facade.NetworkServiceFacade;
import com.webviewprototype.facade.RestServiceFacade;
import com.webviewprototype.facade.impl.DBManager;
import com.webviewprototype.facade.impl.NetworkFacadeImpl;
import com.webviewprototype.facade.impl.RestServiceFacadeImpl;
import com.webviewprototype.service.test.TestActivity;

import env.Entities.Charity;
import env.Entities.DataBean;
import env.Entities.FieldType;
import env.Entities.FilledForm;
import env.Entities.Form;
import env.Entities.FormFields;
import env.Entities.FormType;

import android.net.NetworkInfo;

public class CharityActivity extends ListActivity {
	
	
	NetworkServiceFacade networkFacade = new NetworkFacadeImpl();
	static List<String> FORM_TITLES = new LinkedList<String>();
	LinearLayout layout ;
	NetworkPollTask task;
	static String name; 
	 DBManager manager;
	 Integer userid;
	 DataBean bean= DataBean.getDataBean();
	    LayoutParams params;
	    

	 
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FORM_TITLES = new LinkedList<String>();
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FORM_TITLES));
		setContentView(R.layout.activity_charity);
		manager = new DBManager(this);
		bean =DataBean.getDataBean();
		bean.setManager(manager);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		Intent intent = getIntent();
//		name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		name = bean.getUser().getUserName();
		TextView txt = (TextView) findViewById(R.id.textView1);
		txt.setText(bean.getSelectedCharity().getCharity_name());
		configureListTitles();
		}
	
	private void configureListTitles() {
		ListView listView = (ListView) findViewById(android.R.id.list);
		layout= (LinearLayout) findViewById(R.layout.activity_charity);
		listView.setTextFilterEnabled(true);
		ConnectivityManager connectivityManager 
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
			if (networkFacade.hasActiveInternetConnection(activeNetworkInfo,this)){
				RestServiceFacade RestServiceFacade = new RestServiceFacadeImpl();
				List<Form> form_list = RestServiceFacade.getFormEntities(name);
				bean.setAllForms(form_list);
				if (form_list!=null) {	
					for (int i=0;i<form_list.size();i++) {
						Set<FormFields> fs = form_list.get(i).getFields();
						Iterator<FormFields> it = fs.iterator();
						List<FormFields> list = new LinkedList<FormFields>();
						while (it.hasNext()) {
							list.add(it.next());
						}
						FORM_TITLES.add(form_list.get(i).getFormName());
					}
					bean.setFormNames(FORM_TITLES);
	
					this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FORM_TITLES));
	
					listView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int arg2,
								long arg3) {
							String title = ((TextView) view).getText().toString();
							bean.setSelectedForm(title);
							List<FormFields> flds = CharityActivity.this.findFormFieldsByFormName(title);
							if (flds!=null) {
								if (flds.size()>0){
								bean.setFormFields(flds);
								// Launching new Activity on selecting single List Item
					              Intent i = new Intent(getApplicationContext(), FormActivity.class);
					              // sending data to new activity
					              Bundle bundle = new Bundle();
					              bundle.putString("Username",bean.getUser().getUserName());
					             // bundle allocation
					              i.putExtras(bundle);
					              startActivity(i);
								}else{
									showNoFieldsPopup();
								}
							}else{
								showNoFieldsPopup();
							}
						}
					});
				}else{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);

					// 2. Chain together various setter methods to set the dialog characteristics
					builder.setMessage(R.string.no_forms_message)
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
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);

				// 2. Chain together various setter methods to set the dialog characteristics
				builder.setMessage(R.string.connection_message)
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
			
//			task = new NetworkPollTask(activeNetworkInfo,networkFacade);
//			task.execute(this);
			

	}

	private void showNoFieldsPopup() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.no_fields_message)
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
	
	private List<FormFields> findFormFieldsByFormName(String title) {
		List<FormFields> result = new LinkedList<FormFields>();
		List<Form> form_list = bean.getAllForms();
		for (int i=0;i<form_list.size();i++) {
			if (title.equals(form_list.get(i).getFormName())) {
				Set<FormFields> set = form_list.get(i).getFields();
				Iterator<FormFields> iterator = set.iterator();
				while (iterator.hasNext()) {
					result.add(iterator.next());
				}
				break;
			}
		}
		return result;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_charity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void synchronize(View view) {
		
	}
	
	public void synchronize() {
		
	}
	
	public void saveToSqlLite(View view) {

	}
	
	public void saveToSqLite() {
		
	}
	
	
	
	private class NetworkPollTask extends AsyncTask<Activity, Void, Void> {

		private NetworkServiceFacade nfacade;
		private NetworkInfo ninfo;
		
		@Override
		protected Void doInBackground(Activity... params) {
				nfacade.poll(params[0]);
				return null;
		}
		
		public NetworkPollTask(NetworkInfo info, NetworkServiceFacade facade) {
			this.ninfo = info;
			this.nfacade = facade;
		}
		
	}
	
	public void Popup(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.help_message)
		       .setTitle(R.string.help_popup);
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
		


	// The method that displays the popup.
	public void infoPopup(final Activity context, Point p) {
	   int popupWidth = 200;
	   int popupHeight = 150;

	   // Inflate the popup_layout.xml
	   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.pop);
	   LayoutInflater layoutInflater = (LayoutInflater) context
	     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View layout = layoutInflater.inflate(R.layout.popuplayout, viewGroup);

	   // Creating the PopupWindow
	   final PopupWindow popup = new PopupWindow(context);
	   popup.setContentView(layout);
	   popup.setWidth(popupWidth);
	   popup.setHeight(popupHeight);
	   popup.setFocusable(true);

	   // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	   int OFFSET_X = 30;
	   int OFFSET_Y = 30;

	   // Clear the default translucent background
	   popup.setBackgroundDrawable(new BitmapDrawable());

	   // Displaying the popup at the specified location, + offsets.
	   popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

	   // Getting a reference to Close button, and close the popup when clicked.
	   Button close = (Button) layout.findViewById(R.id.ok);
	   close.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		       popup.dismiss();
		}
	});
	}
	
	
	

	@Override
    public void onBackPressed() {
		super.onBackPressed();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.warning)
	       .setTitle(R.string.confirmation);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   	bean.flush();
	        	   Intent intent = new Intent(CharityActivity.this, MainActivity.class);
	       			CharityActivity.this.startActivity(intent);
	           }
	       });
	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   
	           }
	       });
	AlertDialog dialog = builder.create();
	dialog.show();


    }
	
	
	
	public void refresh(View view) {
		bean.setAllForms(new LinkedList<Form>());
		bean.setFormNames(new LinkedList<String>());
		bean.setSelectedForm("");
		bean.setFormFields(new LinkedList<FormFields>());
		Intent intent = new Intent(this,CharityActivity.class);
		startActivity(intent);
	}
	
	
	

}
