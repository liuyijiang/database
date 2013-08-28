package org.hb.com.select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * ≤‚ ‘∑µªÿmap
 * @author jim.liu
 *
 */
public class HBSelectWithMap {

	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public HBSelectWithMap(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	public void findStudent(){
//		Map loadMap = (Map) session.load(Student.class, 1);222222
//		System.out.println("ok");
		session.createQuery("select t.name, t.keynumber from Student t ");
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HBSelectWithMap h = new HBSelectWithMap();
		h.findStudent();

	}

}
