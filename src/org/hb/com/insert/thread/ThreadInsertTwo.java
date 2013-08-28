package org.hb.com.insert.thread;

import org.hb.com.entiy.Student;
import org.hibernate.Session;

public class ThreadInsertTwo implements Runnable{
    
	private Session session = null;
	private String name;
	
	public ThreadInsertTwo(Session session,String name){
		this.session = session;
		this.name = name;
	}
	
	@Override
	public void run() {
		try{
			Student s = new Student();
			s.setAge(34);
			s.setName("ekin"+name);
			s.setKeyNumber("11112222");
			session.beginTransaction().begin();
			session.save(s);
			System.out.println(name +s.getId());
			Thread.sleep(10000);
			session.beginTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
