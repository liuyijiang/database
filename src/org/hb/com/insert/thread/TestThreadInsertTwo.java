package org.hb.com.insert.thread;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestThreadInsertTwo {
	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session1  = null;
	private  Session session2  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public TestThreadInsertTwo(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		//SessionImpl
		session1 = sf.openSession();
		session2 = sf.openSession();
	}
	
	public void test(){
		Thread t1 = new Thread(new ThreadInsertTwo(session1,"Thread_01"));
		Thread t2 = new Thread(new ThreadInsertTwo(session2,"Thread_02"));
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		TestThreadInsertTwo t = new TestThreadInsertTwo();
		t.test();
	}

}
