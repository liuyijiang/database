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
 * 五种hibernate查询
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
	 * 导航对象图查询  比如通过stu.getTeam()得到team的实例;
	 * 用在关联对象中
	 * 1'导航对象图检索方式。通过已经加载的对象，调用.iterator()方法可以得到order对象
     * 如果是首次执行此方法，Hib会从数据库加载关联的order对象，否则就从缓存中得到。
	 */
	public void selectByNav(){
		
	}
	
	/**
	 * OID查询
	 */
	public void selectOID(){
		session.beginTransaction().begin();
		Student s1 = (Student) session.get(Student.class, 1);
		// session.evict(s1);//移除缓存
		Student s2 = (Student) session.get(Student.class, 1); //只有一条sql 第二次查缓存
		System.out.println(s1 == s2);
		session.close();
		session = sf.openSession();
		session.beginTransaction().begin();
		Student s3 = (Student) session.get(Student.class, 1); //只有一条sql 第二次查缓存
		System.out.println(s1 == s3);
		session.beginTransaction().commit();
	}
	
	
	
	/**
	 * HQL查询
	 */
	public void selectByHql(){
		Query q = session.createQuery("from Student t ");
		List<Student> list = q.list();
		for(Student s : list){
			System.out.print(s.getName() + " - ");
		}
	}
	
	
	
	/**
	 * QBC查询
	 */
	public void selectByQbc(){
		//Criteria c = session.createCriteria(Student.class);
      //Criteria c = session.createCriteria("org.hb.com.entiy.Student");
				Criteria c = session.createCriteria(Student.class, "s");
//				c.add(Restrictions.like("name", "%k%")).add(Restrictions.eq("age", 45));
				c.addOrder(Order.asc("age"));//排序
				//Restrictions.
				List<Student> list = c.list();
				
				for(Student s : list){
					System.out.println(s.getName() + " - " + s.getAge());
				}
	}
	
	
	/**
	 * 原始sql 返回object数组
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
	 * 返回map 
	 */
	public void selectResultMap(){
		//将结果返回成map
		//Query q = session.createSQLQuery("Select * from t_student t").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		//将结果返回成list
		Query q = session.createSQLQuery("Select * from t_student t").setResultTransformer(Transformers.TO_LIST);
		 List<HashMap> list = q.list();
		 //List list = session.find("select new Map(sysRoles.roleId as roleId, sysRoles.roleDesc as roleDesc) from SysRoles sysRoles");
		 
		 System.out.println(list.size());
		//q.setR
	}
	
	/**
	 * 将查询出的数据重新生成新的实体类
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
