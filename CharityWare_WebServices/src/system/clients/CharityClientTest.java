package system.clients;

import java.util.ArrayList;
import java.util.List;
import system.model.Charity;
public class CharityClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String Charity_Name = "Fantastic Charity";
		String Charity_Description = "Charity for nothing";
		String Address_Line1 = "Nowhere 1";
		String Address_Line2 = "Nowhere 2";
		String Location = "London";
		String Postcode ="WC1 BS2";
		String Email ="charityfornothing.com";
		String Phone = "43124141241";
		String Registration_No ="3251324";
		Charity charity1 = new Charity(Charity_Name,Charity_Description,Address_Line1,Address_Line2,Location,Postcode,Email,Phone,Registration_No);
		Charity charity2 = new Charity(Charity_Name,Charity_Description,Address_Line1,Address_Line2,Location,Postcode,Email,Phone,Registration_No);
		charity1.setConnection_string("mpla_mpla1");
		charity1.setIsActive(true);
		charity1.setIsVerified(true);
		charity2.setConnection_string("mpla_mpla2");
		charity2.setIsActive(true);
		charity2.setIsVerified(true);
		List<Charity> charities = new ArrayList<Charity>();
		charities.add(charity1);
		charities.add(charity2);
		CharityClient charityClient = new CharityClient();
		charityClient.addCharities(charities);
	}

}
