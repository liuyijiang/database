package org.hb.com.select;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 测试返回vo对象
 * @author jim.liu
 *
 */
public class HBSelectWithVO {

	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	//private List<Student> list = new ArrayList<Student>();
	
	public HBSelectWithVO(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	/**
	 * 区别1：如果数据库中，没有userId的对象。如果通过get方法加载，则返回的是一个null；如果通过load加载，则返回一个代理对象，如果后面代码如果调用user对象的某个属性（比如user.getPassword()）会抛出异常：org.hibernate .ObjectNotFoundException；
	 * 区别2：load支持延迟加载，get不支持延迟加载。 
	 */
	public void findStudent(){
		 Student s  = null;
		//get 当
		//Student s = (Student) session.get(Student.class, 0);
		
		//load f返回的是代理
		try{
		   s = (Student) session.load(Student.class, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(s != null){
			System.out.println(s.getName());
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HBSelectWithVO h = new HBSelectWithVO();
		h.findStudent();

	}

}
