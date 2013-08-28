package org.hb.com.insert.thread;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionImpl;
/**
 * 测试多线程对一个session操作 是否有异常
 * @author jim.liu
 *
 */
public class TestThreadInsert {
   
	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public TestThreadInsert(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		//SessionImpl
		session = sf.openSession();
	}
	
	public void save(){
		session.beginTransaction().begin();
		
		ThreadInsert ti1 = new ThreadInsert(session,"thread_insert_1",list);
		Thread t1 = new Thread(ti1);
		
		ThreadInsert ti2 = new ThreadInsert(session,"thread_insert_2",list);
		Thread t2 = new Thread(ti2);
		
		ThreadInsert ti3 = new ThreadInsert(session,"thread_insert_3",list);
		Thread t3 = new Thread(ti3);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(1);
//			ti1.setStop(false);
//			ti2.setStop(false);
//			ti3.setStop(false);
			System.out.println("comit");
			session.beginTransaction().commit();
			System.out.println("ok");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestThreadInsert t = new TestThreadInsert();
		t.save();

	}

}
