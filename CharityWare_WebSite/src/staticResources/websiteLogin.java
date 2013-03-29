package staticResources;

import java.io.File;

import shared.model.util.PasswordEncryption;
import system.model.User;
import system.clients.UserClient;


public class websiteLogin {
	private static final String siteUrl = "http://localhost:8080/CharityWare_Lite/";
	public static String getSiteUrl(){
		return siteUrl;
	}
	public static User login(String userName, String password)
	{
		System.out.println("Start login");
		try{
			System.out.println("Contact service");
			UserClient userClient = new UserClient();
			User MyUser = userClient.get(userName);
			System.out.println("Check password");
			if(PasswordEncryption.encryptPassword(password, MyUser.getSalt()).equals(MyUser.getUserPassword()))
			{
				System.out.println("End MyUser");
				return MyUser;
			}else
			{
				System.out.println("End Null");
				return null;
			}
		}catch(Exception e)
		{
			System.out.println("Failed to login, user probably not found in database.");
			//e.printStackTrace();
		}
		System.out.println("End null");
		return null;
	}
	
	public static String getHomePage(String userID)
	{
		switch(userID)
		{
		case "1":
			return "uclAdmin.jsp";
		case "2":
			return "charityAdmin.jsp";
		default:
			return "default.jsp";
		}
	}
	public static boolean isAuthenticated(String userTypeId, String URL)
	{
		Boolean ret = false;
		System.out.println(new File(URL).getName());
		System.out.println(userTypeId);
		switch(new File(URL).getName().toLowerCase())
		{
		case "default.jsp":
				switch(userTypeId)
				{
				case "0":
					ret = true;
					break;
				case "1":
					ret = true;
					break;
				}
			break;
		case "ucladmin.jsp":
			switch(userTypeId)
			{
			case "1":
				ret = true;
				break;
			}
		break;
		case "charityadmin.jsp":
			switch(userTypeId)
			{
			case "2":
				ret = true;
				break;
			}
		break;

			
		
		}
		return ret;
	}
	

}
