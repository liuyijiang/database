package org.hb.com.insert.thread;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
public class ThreadInsert implements Runnable{
   
	private Session session;
	private String name;
	private boolean stop;
	private  List<Student> list ;
	
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public ThreadInsert(Session session,String name,List<Student> list){
		this.session = session;
		this.name = name;
		this.stop = true;
		this.list = list;
	}
	
	@Override
	public void run() {
		while(stop){
			Student s = new Student();
			s.setAge(21);
			s.setKeyNumber(name);
			s.setName("jack");
			session.save(s);
			list.add(s);
		}
		
	}
    
	
	
}
