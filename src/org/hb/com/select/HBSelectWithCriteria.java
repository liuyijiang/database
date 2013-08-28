package org.hb.com.select;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * test使用 criteria
 * @author jim.liu
 *
 */
public class HBSelectWithCriteria {

	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public HBSelectWithCriteria(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	
	//Criteria 传入的是类  Restrictions添加条件
	public void select(){
		//Criteria c = session.createCriteria(Student.class);
		//Criteria c = session.createCriteria("org.hb.com.entiy.Student");
		Criteria c = session.createCriteria(Student.class, "s");
//		c.add(Restrictions.like("name", "%k%")).add(Restrictions.eq("age", 45));
		c.addOrder(Order.asc("age"));//排序
		//Restrictions.
		List<Student> list = c.list();
		
		for(Student s : list){
			System.out.println(s.getName() + " - " + s.getAge());
		}
	}
	
	public void test(){
//		Student s1 = (Student) session.load(Student.class,1);
//		Student s2 = (Student) session.get(Student.class,1);
//		Student s3 = (Student) session.load(Student.class,1);
//		Student s4 = (Student) session.get(Student.class,1);
//		System.out.println(s1 == s3);
//		System.out.println(s2 == s4);
//		System.out.println(s1 == s4);
		session.beginTransaction().begin();
		Student s1 = (Student) session.load(Student.class,1);
		s1.setName("xxxx");
		s1.setName("yyyy");
		//session.update(s1);
		session.beginTransaction().commit();
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HBSelectWithCriteria h = new HBSelectWithCriteria();
		h.test();

	}

}
