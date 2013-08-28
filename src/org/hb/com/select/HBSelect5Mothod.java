package org.hb.com.select;

import java.util.HashMap;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hb.com.entiy.StudentSimple;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;

/**
 * ����hibernate��ѯ
 * @author jim.liu
 *
 */
public class HBSelect5Mothod {

	
	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	//private List<Student> list = new ArrayList<Student>();
	
	public HBSelect5Mothod(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
		//SessionImpl
	}
	
	
	/**
	 * ��������ͼ��ѯ  ����ͨ��stu.getTeam()�õ�team��ʵ��;
	 * ���ڹ���������
	 * 1'��������ͼ������ʽ��ͨ���Ѿ����صĶ��󣬵���.iterator()�������Եõ�order����
     * ������״�ִ�д˷�����Hib������ݿ���ع�����order���󣬷���ʹӻ����еõ���
	 */
	public void selectByNav(){
		
	}
	
	/**
	 * OID��ѯ
	 */
	public void selectOID(){
		session.beginTransaction().begin();
		Student s1 = (Student) session.get(Student.class, 1);
		// session.evict(s1);//�Ƴ�����
		Student s2 = (Student) session.get(Student.class, 1); //ֻ��һ��sql �ڶ��β黺��
		System.out.println(s1 == s2);
		session.close();
		session = sf.openSession();
		session.beginTransaction().begin();
		Student s3 = (Student) session.get(Student.class, 1); //ֻ��һ��sql �ڶ��β黺��
		System.out.println(s1 == s3);
		session.beginTransaction().commit();
	}
	
	
	
	/**
	 * HQL��ѯ
	 */
	public void selectByHql(){
		Query q = session.createQuery("from Student t ");
		List<Student> list = q.list();
		for(Student s : list){
			System.out.print(s.getName() + " - ");
		}
	}
	
	
	
	/**
	 * QBC��ѯ
	 */
	public void selectByQbc(){
		//Criteria c = session.createCriteria(Student.class);
      //Criteria c = session.createCriteria("org.hb.com.entiy.Student");
				Criteria c = session.createCriteria(Student.class, "s");
//				c.add(Restrictions.like("name", "%k%")).add(Restrictions.eq("age", 45));
				c.addOrder(Order.asc("age"));//����
				//Restrictions.
				List<Student> list = c.list();
				
				for(Student s : list){
					System.out.println(s.getName() + " - " + s.getAge());
				}
	}
	
	
	/**
	 * ԭʼsql ����object����
	 */
	public void selectBySql(){
		Query q = session.createSQLQuery("Select * from t_student t ");
		List<Object[]> list = q.list();
		System.out.println(list.size());
//		for(Student s : list){
//			System.out.print(s.getName() + " - ");
//		}
	}
	
	/**
	 * ����map 
	 */
	public void selectResultMap(){
		//��������س�map
		//Query q = session.createSQLQuery("Select * from t_student t").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		//��������س�list
		Query q = session.createSQLQuery("Select * from t_student t").setResultTransformer(Transformers.TO_LIST);
		 List<HashMap> list = q.list();
		 //List list = session.find("select new Map(sysRoles.roleId as roleId, sysRoles.roleDesc as roleDesc) from SysRoles sysRoles");
		 
		 System.out.println(list.size());
		//q.setR
	}
	
	/**
	 * ����ѯ�����������������µ�ʵ����
	 */
	public void selectNewObject(){
		String hql = "select new org.hb.com.entiy.StudentSimple(t.id,t.name) from Student t";
		Query q = session.createQuery(hql);
		List<StudentSimple> list = q.list();
		for(StudentSimple s : list){
			System.out.println(s.getName() + " - " +s.getId());
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HBSelect5Mothod h = new HBSelect5Mothod();
		h.selectOID();

	}

}
