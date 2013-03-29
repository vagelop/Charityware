package shared.dao.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

public class ConnectionManager {
	private  SessionFactory factory;

	public ConnectionManager(String DBConfname){
		Configuration conf = new Configuration();
		conf.configure(((DBConfname == null || DBConfname == "" ) ? "/system/dao/hbm/hibernate.cfg.xml" : "/charity/dao/hbm/"+DBConfname));
		factory = conf.buildSessionFactory();
	}

	//Stub to remove old function
	public  List<?> getTable(String table){
		return runSelectQuery("from "+ table);
	}

	public  List<?> runSelectQuery(String querystr){
		Session session = this.getSession();
		Query query  = session.createQuery(querystr);
		List<?> result = query.list();
		closeSession(session);
		return result;
	}
	
	public void runProcedure (String procedure,Map<String,String> parameters_map){
		Session session = this.getSession();
		Iterator<Entry<String,String>> parameters_map_iter1 = parameters_map.entrySet().iterator();
		String parameters = "";
		while(parameters_map_iter1.hasNext()){			
			String parameterName = parameters_map_iter1.next().getKey();
			parameters = parameters+":"+parameterName;
			if(parameters_map_iter1.hasNext())
				parameters = parameters+",";
		}
		String callprocedure = "CALL "+procedure+"("+parameters+")";

		Query query = session.createSQLQuery(callprocedure);
		Iterator<Entry<String,String>> parameters_map_iter2 = parameters_map.entrySet().iterator();
		while(parameters_map_iter2.hasNext()){
			Entry<String,String> parameters_entry = parameters_map_iter2.next();
			String parameterName = parameters_entry.getKey();
			String parameterValue = parameters_entry.getValue();
			query.setParameter(parameterName, parameterValue);
		}
		query.executeUpdate();	
		closeSession(session);
		return;
	}

	public List<?> searchCriteria (Class<?> arg0,String groupbycolumn,String criteriaColumn1,Object criteriaValue1,String joint_table,String joint_table_name, String criteriaColumn2,Object criteriaValue2/*,List<?> objects*/ ){
		Session session = getSession();
		List<?> results =  session.createCriteria(arg0)
				.createAlias(joint_table,joint_table_name,JoinType.INNER_JOIN)
				.add(Restrictions.eq(joint_table_name+"."+criteriaColumn2,criteriaValue2 ))
				.add(Restrictions.eq( criteriaColumn1,criteriaValue1))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty(groupbycolumn))
						)
						.list();
		closeSession(session);
		return results;
	}

	public  Object get(Class<?> arg0,Serializable serial){
		Session session = this.getSession();
		Object result = session.get(arg0, serial);
		this.closeSession(session);
		return result;
	}

	public  Serializable transaction(String method,Object obj){
		Session session = this.getSession();
		Transaction tx = null;
		Serializable serial = null;
		try{
			tx = session.beginTransaction();
			serial = (Serializable)Session.class.getMethod(method,Object.class).invoke(session,obj);
			tx.commit();
		}catch(HibernateException hx) {
			if (tx!=null) {
				tx.rollback();
			}
			hx.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}catch(SecurityException e){
			e.printStackTrace();
		}finally{
			this.closeSession(session);
		}
		return serial;
	}

	public Session getSession(){
		Session result;
		try{			
			if (factory ==null){
				result = factory.openSession();
				System.out.println("First openSession invoked");
			}else{
				result = factory.getCurrentSession();
				System.out.println("getCurrentSession invoked");
			}
		}catch(org.hibernate.HibernateException e){

			result = factory.openSession();
			System.out.println("Second openSession invoked");
		}
		return result;
	}	
	public List<?> searchCriteria(Class<?> arg0,String column,Object obj){
		Session session = getSession();
		List<?> results =  session.createCriteria(arg0)
				.add(Restrictions.eq(column, obj)).list();
		closeSession(session);
		return results;
	}

	public List<?> searchCriteria (Class<?> arg0,String column,List<?> objects ){
		Session session = getSession();
		List<?> results =  session.createCriteria(arg0)
				.add(Restrictions.in( column,objects)).list();
		closeSession(session);
		return results;
	}

	public Object searchCriteria (Class<?> arg0,Integer objectid ){
		Session session = getSession();
		Object result = session.createCriteria(arg0)
				.add(Restrictions.idEq( new Integer(objectid)))
				.uniqueResult();
		closeSession(session);
		return result;
	}

	public Object merge(Object obj){
		Session session = this.getSession();
		Object result = session.merge(obj);
		this.closeSession(session);
		return result;
	}

	public void closeSession(Session session){
		session.close();
	}
}
