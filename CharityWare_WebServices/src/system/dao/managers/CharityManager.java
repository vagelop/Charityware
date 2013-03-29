package system.dao.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shared.dao.util.ConnectionManager;
import system.model.Charity;
import system.model.User;

public class CharityManager {

	private ConnectionManager conn;

	public CharityManager(){
		conn = new ConnectionManager("");
	} 

	public ArrayList<Charity> getCharitiesRequests(){
		ArrayList<Charity> charity = (ArrayList<Charity>)conn.getTable("Charity where isVerified = 0 and isActive = 0");
		return charity;
	}   

	public List<Charity> getCharities(){
		List<Charity> results = new  ArrayList<Charity>();
		List<Charity> charities = (List<Charity>)conn.getTable("Charity");
		Iterator<Charity> charities_iter = charities.iterator();
		while(charities_iter.hasNext()){
			Charity char_temp = charities_iter.next();
			if(char_temp.getIsActive()&&char_temp.getIsVerified()){
				results.add(char_temp);
			}
		}
		return results;
	}


	public Charity getCharity(int charityID){
		Charity charity = (Charity)conn.get(Charity.class, charityID);
		return charity;
	}

	public Charity getUserCharity(int userID){;
	System.out.println("Manager");
	ArrayList<Charity> charity = (ArrayList<Charity>)conn.getTable("Charity where User_Id="+userID);
	//Charity charity = (Charity)conn.get(Charity.class, userID);
	System.out.println("Manager return");
	return charity.get(0);
	}

	public Integer addCharity (String chName, String RegNo,String chEmail,String chDescription,String AdminUsername,String AdminPassword) { 

		//User user = (User) userManager.getUsers("rcadmin").get(0);
		User user = new User(AdminUsername,AdminPassword);
		Charity newCharity = new Charity();
		user.setUserName(AdminUsername);
		user.setIsActive(true);
		conn.transaction("save",user);

		//Add Charity
		UserManager userManager = new UserManager();
		User u = (User) userManager.getUser(user.getUser_id());
		newCharity.setCharity_name(chName);
		newCharity.setRegistration_no(RegNo);
		newCharity.setEmail(chEmail);

		newCharity.setCharity_description(chDescription);
		newCharity.setUser(u);
		newCharity.setConnection_string("NA");
		newCharity.setIsVerified(false);
		newCharity.setIsActive(false);
		return (Integer) conn.transaction("save",newCharity);
	}

	public Integer addCharities (List<Charity> newCharities) { 
		Iterator<Charity> char_iterator = newCharities.iterator();

		while(char_iterator.hasNext()){
			Charity charity = char_iterator.next();
			UserManager userManager = new UserManager();
			User user = (User) userManager.getUsers("rcadmin").get(0);
			charity.setUser(user);
			if(conn.transaction("save",charity)==null)
				return null;
		}

		return 1;
	}

	public Integer approveCharity(Integer CharityId){
		Charity charity = (Charity)conn.get(Charity.class, CharityId);
		charity.setIsActive(true);
		charity.setIsVerified(true);
		charity.setConnection_string("charity"+CharityId+".cfg.xml");
		return (Integer) conn.transaction("update",charity);
	}

	public Integer rejectCharity(Integer CharityId){
		Charity charity = (Charity)conn.get(Charity.class, CharityId);
		charity.setIsActive(true);
		charity.setIsVerified(false);
		charity.setConnection_string("NA");
		return (Integer) conn.transaction("update",charity);
	}

	public Integer deleteCharityAccount(Integer CharityId){
		Charity charity = (Charity)conn.get(Charity.class, CharityId);
		charity.setIsActive(false);
		return (Integer) conn.transaction("update",charity);
	}
	public String getSystemVerifiedCharities(){
		ArrayList<Charity> verifiedCharity = (ArrayList<Charity>)conn.getTable("Charity where isVerified=1");
		ArrayList<Charity> unverifiedCharity = (ArrayList<Charity>)conn.getTable("Charity where isVerified=0");

		StringBuilder finalresult = new StringBuilder();

		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],", "Verified Charity Accounts", verifiedCharity.size()));
		finalresult.append(String.format("[\"%s\",%d]", "Unverified Charity Accounts", unverifiedCharity.size()));
		finalresult.append(']');

		return finalresult.toString();
	}

	public String getCharityRegistrationActivity(){
		ArrayList<Charity> charities = (ArrayList<Charity>)conn.getTable("Charity");
		StringBuilder finalresult = new StringBuilder();
		Integer justCreated = 0, oneDay = 0, oneWeek = 0, oneMonth = 0, others = 0;

		for (Charity c : charities) {

			long lDuration = (System.currentTimeMillis() - c.getDateCreated().getTime()) / 100000;
			if (lDuration > 36 && lDuration <= 864) {
				oneDay++;
			} else if (lDuration <= 36) {
				justCreated++;
			} else if (lDuration > 864 && lDuration <= 6048) {
				oneWeek++;
			} else if (lDuration > 6048 && lDuration <= 25920) {
				oneMonth++;
			} else {
				others++;
			}

		}

		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],","Added an Hour ago", justCreated));
		finalresult.append(String.format("[\"%s\",%d],","Added one Day ago", oneDay));
		finalresult.append(String.format("[\"%s\",%d],","Added one Week ago", oneWeek));
		finalresult.append(String.format("[\"%s\",%d],","Added one Month ago", oneMonth));
		finalresult.append(String.format("[\"%s\",%d]", "Added Before", others));
		finalresult.append(']');

		return finalresult.toString();
	}
}
