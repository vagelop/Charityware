package env.Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.webviewprototype.R;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FormListAdapter extends ArrayAdapter<AndroidField> {

	
	
	 private static final int TEXT_VIEW = 0;
	 private static final int CHECK_BOX = 1;
	 private static final int CALENDAR = 2;
	 private static final int DROP_DOWN =3;
	 private Context mContext;
	 private List<AndroidField> fields = new ArrayList<AndroidField>();
		DataBean bean = DataBean.getDataBean();



	private int id;

	public FormListAdapter(Context c, List<AndroidField> fs, int pos) {
		super(c, pos, fs);
			this.mContext =c;
			this.fields =fs;
			this.id = pos;
			notifyDataSetChanged();
	}
	
	
	public void addData (final AndroidField field) {
		fields.add(field);
		notifyDataSetChanged();
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public List<AndroidField> getFields() {
		return fields;
	}

	public void setFields(List<AndroidField> fields) {
		this.fields = fields;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public AndroidField getItem(int position) {
		return fields.get(position);
	}

	@Override
	public int getItemViewType (int position) {
		return (fields.get(position).getField_Type_id().intValue()==1) ? TEXT_VIEW : ((fields.get(position).getField_Type_id().intValue()==3) ? DROP_DOWN : ((fields.get(position).getField_Type_id().intValue()==5) ? CALENDAR : (fields.get(position).getField_Type_id().intValue()==6) ? CHECK_BOX : TEXT_VIEW)); 
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        int type = getItemViewType(position);
        AndroidField field = getItem(position);
        TextFieldHolder holder =null;
        DropDownFieldHolder dholder = null;
        CheckBoxFieldHolder cholder = null;
        CalendarFieldHolder calholder = null;
        // inflate new layout if null
                if(v == null) {
                	LayoutInflater inflater = LayoutInflater.from(mContext);
                switch (type) { 
                
                	case TEXT_VIEW : 		
                							v = inflater.inflate(R.layout.listview_layout_form,null);
                							holder = new TextFieldHolder();
                		                    holder.textView=(TextView) v.findViewById(R.id.label);
                		                    holder.edit=(EditText) v.findViewById(R.id.fname);
                		                    holder.edit.addTextChangedListener(new EditWatcher(field));
                		                    if (((AndroidTextField) field).getInputType()==1) {
                		                    	holder.edit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                		                    }else{
                		                    	holder.edit.setInputType( InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_VARIATION_NORMAL);
                		                    }
                		                    v.setTag(holder);
                		                    holder.textView.setText(field.getLabel());
                		                    holder.edit.setText( ((AndroidTextField) field).getText());
                		               
                		                    
                		                    break;
                	case DROP_DOWN :
                							v = inflater.inflate(R.layout.list_view_dropdown_layout, null);
                							dholder = new DropDownFieldHolder();
                							dholder.txtView=(TextView) v.findViewById(R.id.label3);
                							dholder.spinner= (Spinner) v.findViewById(R.id.spinner1);
                							ArrayList<String> values = new ArrayList<String>();
                							for ( int i=0;i<((AndroidDropDownField) field).getDropDownValues().size();i++) {
                								values.add(((AndroidDropDownField) field).getDropDownValues().get(i).getField_selection_value());
                							}
                							ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,values);
//                							spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                							dholder.spinner.setAdapter(spinnerAdapter);
                							dholder.spinner.setOnItemSelectedListener(new CustomSpinnerListener(field));
                						/*	for ( int i=0;i<((AndroidDropDownField) field).getDropDownValues().size();i++) {
                								spinnerAdapter.add(((AndroidDropDownField) field).getDropDownValues().get(i).getField_selection_value());
                								spinnerAdapter.notifyDataSetChanged();
                							}*/
                							dholder.txtView.setText(field.getLabel());
                							v.setTag(dholder);
                							break;
                	case CHECK_BOX :
                							v = inflater.inflate(R.layout.list_view_checkbox_layout,null);
                							cholder = new CheckBoxFieldHolder();
                							cholder.txView  = (TextView) v.findViewById(R.id.label2);
                							cholder.checkbox =(CheckBox) v.findViewById(R.id.checkbox);
                							cholder.checkbox.setOnCheckedChangeListener(new CustomCheckboxWatcher(field));
                							v.setTag(cholder);
                							cholder.txView.setText(field.getLabel());
                							cholder.checkbox.setChecked(((AndroidCheckBoxField)field).getChecked());
                							break;
                	case CALENDAR:			
                							v = inflater.inflate(R.layout.list_view_calendar, null);
                							calholder = new CalendarFieldHolder();
                							calholder.label = (TextView) v.findViewById(R.id.label4);
                							calholder.calendar = (CalendarView) v.findViewById(R.id.calendarView);
                							calholder.calendar.setOnDateChangeListener(new CalendarChangeWatcher(field));
                							v.setTag(calholder);
                							calholder.label.setText(field.getLabel());
                							calholder.calendar.setDate(((AndroidCalendarField) field).getDate().getTime());
                							break;
                	default:
                		
                							break;
                }
                	
                	
                	
                }else{
                	holder= (TextFieldHolder) v.getTag();
                }

		return v;
	}
	
	
	static class TextFieldHolder {
		
		TextView textView;
		EditText edit;
	}
	
	static class DropDownFieldHolder{
		TextView txtView;
		Spinner spinner;
	}
	
	static class CheckBoxFieldHolder{
		TextView txView;
		CheckBox checkbox;
	}
	
	static class CalendarFieldHolder {
		TextView label;
		CalendarView calendar;
	}
	
	
	
	
	
	
	
	private class CalendarChangeWatcher implements OnDateChangeListener{

		private AndroidField field;
		
		public CalendarChangeWatcher(AndroidField afield) {
			field=afield;
		}
		
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			
			Integer field_id = field.getField_id();
			List<FilledForm> fform = bean.getFilledForm();
			for ( int i=0;i<bean.getFilledForm().size();i++) {
				if (field_id.equals(bean.getFilledForm().get(i).getFormFields())){
					if (bean.getFilledForm().get(i).getValue()!=null) {
						bean.getFilledForm().get(i).setValue(new Date(year, month, dayOfMonth).toString());
						}
					}
			
			}
		
		}
	}
	
	
	private class CustomCheckboxWatcher implements OnCheckedChangeListener{

		AndroidField field;
		
		public CustomCheckboxWatcher(AndroidField afield) {
			field = afield;
		}
		
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Integer field_id = field.getField_id();
			List<FilledForm> fform = bean.getFilledForm();
			for ( int i=0;i<bean.getFilledForm().size();i++) {
				if (field_id.equals(bean.getFilledForm().get(i).getFormFields())){
					if (bean.getFilledForm().get(i).getValue()!=null) {
						if (bean.getFilledForm().get(i).getValue().length()!=0) {
							bean.getFilledForm().get(i).setValue(Boolean.toString(isChecked));
						}else{
							boolean stored = Boolean.parseBoolean(bean.getFilledForm().get(i).getValue());
							bean.getFilledForm().get(i).setValue(Boolean.toString(!stored));
						}
					}
				}
			}
				
		}
		
	}
	
	private class EditWatcher implements TextWatcher{
		
		AndroidField field;

		public EditWatcher(AndroidField afield) {
			field = afield;
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			Integer field_id = field.getField_id();
			List<FilledForm> fform = bean.getFilledForm();
			for ( int i=0;i<bean.getFilledForm().size();i++) {
				if (field_id.equals(bean.getFilledForm().get(i).getFormFields().getF_id())){
					bean.getFilledForm().get(i).setValue(s.toString());
				}
			}
				
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}
	}	
	
	
	
	private class CustomSpinnerListener implements OnItemSelectedListener {
		
		AndroidField field;
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			String value=(String) parent.getItemAtPosition(pos);
			Integer field_id = field.getField_id();
			for ( int i=0;i<bean.getFilledForm().size();i++) {
				if (field_id.equals(bean.getFilledForm().get(i).getFormFields().getF_id())){
					bean.getFilledForm().get(i).setValue(value);
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
		
		public CustomSpinnerListener(AndroidField afield) {
			field = afield;
		}
		
	}

}
