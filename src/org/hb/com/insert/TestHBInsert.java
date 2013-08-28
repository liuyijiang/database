package org.hb.com.insert;

import java.util.Date;

import org.hb.com.base.ModelType;
import org.hb.com.entiy.Model;
import org.hb.com.entiy.Student;
import org.hb.com.entiy.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHBInsert {

	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	
	
	public TestHBInsert(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	/**
	 * ö��insert
	 */
	public void insertEnum(){
		Model m = new Model();
		m.setDate(new Date());
		m.setName("yamato");
		m.setType(ModelType.SHIP);
		session.beginTransaction().begin();
		session.save(m);
		session.beginTransaction().commit();
	}
	
	
	public void insert(){
		Student s = new Student();
		s.setAge(1);
		s.setKeyNumber("jj88");
		s.setName("lucene");
//		session.beginTransaction().begin();
		session.save(s);
		//SaveOrUpdateEventListenerImpl
		//s.setName(ModelType.SHIP.toString());
//		session.update(s);
//		session.flush();
		System.out.println(s.getId());
		session.beginTransaction().commit();
		System.out.println("ok");
	}
	
	
	public void baseInsert(){
		User u = new User();
		u.setInfo("body");
		u.setName("jim");
		session.beginTransaction().begin();
		session.save(u);
		session.beginTransaction().commit();
		System.out.println("ok");
	}
	
	//插入12万条数据
	public void tetsql(){
		session.beginTransaction().begin();
		for (int i=0;i<124133;i++){
			session.createSQLQuery("insert into mytest.no_part_tab  values ("+ i +",'testing partitions',adddate('1995-01-01',(rand("+ i +")*36520) mod 3652));").executeUpdate();
		}
		System.out.println("ok");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestHBInsert t = new TestHBInsert();
		t.tetsql();

	}

}
