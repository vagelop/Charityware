package shared.dao.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import system.dao.managers.CharityManager;
import charity.model.User;

public class GenerateSchemaManager {
	private ConnectionManager conn;
	
	public GenerateSchemaManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	public Boolean generateSchema(int CharityId) throws Exception{
		String CharityName = "charity" + CharityId;
		String Username = Configuration.getMySQLrootUser();
		String Password = Configuration.getMySQLrootPassword();	   		   		
		Class.forName(Configuration.getMySQLdriver()).newInstance();
		try {
			Map<String,String> parameters_map = new HashMap<String,String>();
			parameters_map.put("DB_Name", CharityName);
			conn.runProcedure("spSchemaGeneration", parameters_map);
			System.out.println("Charity Schema Generated");
			execute(CharityName,Username,Password);
			System.out.println("Charity Config Generated");
			CharityManager chMng = new CharityManager();
			chMng.approveCharity(CharityId);
			System.out.println("Charity Table Updated");

			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	private void execute(String DB_Name,String Username,String Password) throws Exception {
        
    	String FilePath = User.class.getResource("../dao/hbm/").getFile()+DB_Name +".cfg.xml";
    	System.out.println("The file path is:"+FilePath);
    	File file = new File(FilePath);
       
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            writer.write("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">\n");
            writer.write("<hibernate-configuration>\n");
            writer.write("<session-factory>\n");
            
            /** Set Properties **/
            writer.write("<property name=\"hibernate.hbm2ddl.auto\">update</property>\n");
            writer.write("<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLDialect</property>\n");
            writer.write("<property name=\"log4j.logger.org.hibernate.type\">TRACE</property>\n");
            writer.write("<property name=\"hibernate.max_fetch_depth\">3</property>\n");
            writer.write("<property name=\"hibernate.connection.driver_class\">com.mysql.jdbc.Driver</property>\n");
            writer.write("<property name=\"hibernate.ejb.event.post-insert\">org.hibernate.ejb.event.EJB3PostInsertEventListener,org.hibernate.envers.event.AuditEventListener</property>\n");
            writer.write("<property name=\"hibernate.show_sql\" >true</property>\n");
            writer.write("<property name=\"hibernate.ejb.event.post-update\">org.hibernate.ejb.event.EJB3PostUpdateEventListener,org.hibernate.envers.event.AuditEventListener</property>\n");
            writer.write("<property name=\"hibernate.ejb.event.post-delete\">org.hibernate.ejb.event.EJB3PostDeleteEventListener,org.hibernate.envers.event.AuditEventListener</property>\n");   
            writer.write("<property name=\"hibernate.ejb.event.pre-collection-update\">org.hibernate.envers.event.AuditEventListener</property>\n");
            writer.write("<property name=\"hibernate.ejb.event.pre-collection-remove\">org.hibernate.envers.event.AuditEventListener</property>\n");
            writer.write("<property name=\"hibernate.ejb.event.post-collection-recreate\">org.hibernate.envers.event.AuditEventListener</property>\n");
            writer.write("<property name=\"org.hibernate.envers.audit_table_suffix\" >_VER</property>\n");
            writer.write("<property name=\"org.hibernate.envers.revision_field_name\">REV</property>\n");
            writer.write("<property name=\"org.hibernate.envers.revision_type_field_name\">REVTYPE</property>\n");  
            writer.write("<property name=\"org.hibernate.envers.default_schema\">"+DB_Name+"</property>\n");   
            writer.write("<property name=\"org.hibernate.envers.revision_on_collection_change\">false</property>\n");              
            
            /** Set DB Connection Properties **/
            writer.write("<property name=\"hibernate.connection.url\">jdbc:mysql://localhost/"+DB_Name+"</property>\n");
            writer.write("<property name=\"hibernate.connection.username\">"+ Username +"</property>\n");
            writer.write("<property name=\"hibernate.connection.password\">"+ Password +"</property>\n");
            
            /** Bean problem fix **/
            writer.write("<property name=\"javax.persistence.validation.mode\">none</property>");
            
            /** Set Mapping Resources **/
            writer.write("<mapping resource=\"charity/dao/hbm/User.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/AccessLog.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/FormFields.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/MailingGroup.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/MailingList.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/Event.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/FilledForm.hbm.xml\"/>\n");	
            writer.write("<mapping resource=\"charity/dao/hbm/FormType.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/FieldType.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/FieldSelection.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/Form.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/FormPermissions.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"charity/dao/hbm/UserType.hbm.xml\"/>\n"); 
            writer.write("</session-factory>\n");
            writer.write("</hibernate-configuration>\n");
            writer.flush();
            writer.close();
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
