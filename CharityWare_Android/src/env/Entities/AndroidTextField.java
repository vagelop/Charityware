package env.Entities;

public class AndroidTextField extends AndroidField {

	private String text;
	private Integer inputType; // 0 alphanumeric 1 numeric
	
	
	public Integer getInputType() {
		return inputType;
	}


	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public AndroidTextField() {
	}

}
