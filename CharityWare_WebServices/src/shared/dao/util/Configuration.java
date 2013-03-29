package shared.dao.util;

import javax.xml.bind.JAXBContext;

public  class Configuration {

	private static String MySQLrootUser;
	private static String MySQLrootPassword;
	private static String MySQLdriver;
	private static String MySQLConUrl;
	
	private static Resources  resource;
	static {
		System.out.println("Configuration construct");
		try{
			System.out.println("New JAXB parser");
			JAXBContext context = JAXBContext.newInstance(Resources.class);
			resource = (Resources) context.createUnmarshaller().unmarshal(Configuration.class.getResourceAsStream("Resources.xml"));
			MySQLrootUser = resource.getMySQLRootUser();
			MySQLrootPassword = resource.getMySQLRootPassword();
			MySQLdriver = resource.getMySQLDriver();
			MySQLConUrl = resource.getMySQLConUrl();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Configuration end");
	}
	
	public static String getMySQLrootUser() {
		return MySQLrootUser;
	}
	public static String getMySQLrootPassword() {
		return MySQLrootPassword;
	}
	public static String getMySQLdriver() {
		return MySQLdriver;
	}
	public static String getMySQLConUrl() {
		return MySQLConUrl;
	}
}
