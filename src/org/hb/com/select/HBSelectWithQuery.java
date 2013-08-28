package org.hb.com.select;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HBSelectWithQuery {

	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public HBSelectWithQuery(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	
	public void select(){
		Query q = session.createQuery("from Student s where s.name =:name");
		q.setParameter("name", "jim");
       List<Student> list = q.list();
		
		for(Student s : list){
			System.out.println(s.getName() + " - " + s.getAge());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HBSelectWithQuery h = new HBSelectWithQuery();
		h.select();
	}

}
