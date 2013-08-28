package org.hb.com.select;

import java.util.ArrayList;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * ���Է���vo����
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
	 * ����1��������ݿ��У�û��userId�Ķ������ͨ��get�������أ��򷵻ص���һ��null�����ͨ��load���أ��򷵻�һ��������������������������user�����ĳ�����ԣ�����user.getPassword()�����׳��쳣��org.hibernate .ObjectNotFoundException��
	 * ����2��load֧���ӳټ��أ�get��֧���ӳټ��ء� 
	 */
	public void findStudent(){
		 Student s  = null;
		//get ��
		//Student s = (Student) session.get(Student.class, 0);
		
		//load f���ص��Ǵ���
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
