package org.hb.com.memcash;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestFindWithMemcach {

	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
    private MemcachedClient mc = null;
	
	public void createClient(){
		try{
		mc = new MemcachedClient(
			    new InetSocketAddress("192.168.1.142", 11211));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public TestFindWithMemcach(){
		createClient();
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		//SessionImpl
		session = sf.openSession();
	}
	
	public void findStudent(int id){
		Student s = null;
		try{
			s = (Student) mc.get(String.valueOf(id));
			if(s == null){
				s = (Student) session.get(Student.class, 2);
				mc.set(String.valueOf(s.getId()), 3600, s);
			}
		}catch(Exception e){
		    s = (Student) session.get(Student.class, 2);
		    mc.set(String.valueOf(s.getId()), 3600, s);
		    e.printStackTrace();
		}
		System.out.println(s.getName());
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestFindWithMemcach t = new TestFindWithMemcach();
		t.findStudent(2);

	}

}
