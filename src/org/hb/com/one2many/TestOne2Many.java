package org.hb.com.one2many;

import java.util.HashSet;
import java.util.Set;

import org.hb.com.entiy.ClassVO;
import org.hb.com.entiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 测试一对多 和 多对一
 * @author jim.liu
 *
 */
public class TestOne2Many {

	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	//private List<Student> list = new ArrayList<Student>();
	
	public TestOne2Many(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
		//SessionImpl
	}
	
	
	public void saveClassVO(){
		//Student s1 = new Student
		ClassVO c = new ClassVO();
		c.setName("class_test_05");
		Transaction t = session.beginTransaction();
		t.begin();
		Set<Student> set = new HashSet<Student>();
		Student s1 = new Student();
		s1.setAge(21);
		s1.setName("student_test_001");
		set.add(s1);
		Student s2 = new Student();
		s2.setAge(24);
		s2.setName("student_test_002");
		set.add(s2);
		c.setStudents(set);
		session.save(c);
		t.commit();
		success();
	}
	
	public void selectStudentMany2One(){
		Student s = (Student) session.get(Student.class, 1);
		ClassVO c = s.getCvo();
		System.out.println(c.getName());
	}
	
	public void selectClassOne2Many(){
		ClassVO c = (ClassVO) session.get(ClassVO.class, 1);
		System.out.println(c.getStudents().size());
	}
	
	
	private void success(){
		System.out.println("success");
	}
	
	public static void main(String[] args) {
		TestOne2Many t =new TestOne2Many();
		t.saveClassVO();

	}

}
